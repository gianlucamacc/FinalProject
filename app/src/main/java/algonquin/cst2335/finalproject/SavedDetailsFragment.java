package algonquin.cst2335.finalproject;

import static algonquin.cst2335.finalproject.AviationRecyclerViewAdapter2.aDAO;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.finalproject.databinding.SavedDetailsBinding;

public class SavedDetailsFragment extends Fragment {

    FlightModel selected;

    public SavedDetailsFragment(FlightModel t) {
        selected = t;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SavedDetailsBinding binding = SavedDetailsBinding.inflate(inflater);
        AviationDatabase db = Room.databaseBuilder(getActivity(), AviationDatabase.class, "SavedFlights").build();
        aDAO = db.aDAO();

        binding.detailsFragment1.setText(selected.airportName);
        binding.detailsFragment2.setText(selected.terminal);
        binding.detailsFragment3.setText(selected.gate);
        binding.detailsFragment4.setText(selected.delay);

        binding.deleteButton.setOnClickListener(click -> {
            Executor thread = Executors.newSingleThreadExecutor();


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Are you sure you want to delete this flight?")
                    .setTitle("Question:")
                    .setNegativeButton("No", (dialog, cl) -> {})
                    .setPositiveButton("Yes", (dialog, cl) -> {
                        thread.execute(() -> {
                            aDAO.deleteFlight(selected);//add to database;
                            /*this runs in another thread*/
                        });
                        Snackbar.make(container, "Flight deleted...", Snackbar.LENGTH_LONG).setAction("Undo", clk -> {
                            thread.execute(() -> {
                                aDAO.insertFlight(selected);//add to database;
                                /*this runs in another thread*/
                            });

                        }).show();
                    }).create().show();

        });

        return binding.getRoot();
    }
}