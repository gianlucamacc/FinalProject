package algonquin.cst2335.finalproject;

import static algonquin.cst2335.finalproject.AviationRecyclerViewAdapter.aDAO;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.finalproject.databinding.DetailsLayoutBinding;

public class DetailsFragment extends Fragment {

    FlightModel selected;

    public DetailsFragment(FlightModel t) {
        selected = t;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater);
        AviationDatabase db = Room.databaseBuilder(getActivity(), AviationDatabase.class, "SavedFlights").build();
        aDAO = db.aDAO();

        binding.detailsFragment1.setText(selected.airportName);
        binding.detailsFragment2.setText(selected.terminal);
        binding.detailsFragment3.setText(selected.gate);
        binding.detailsFragment4.setText(selected.delay);

        binding.saveButton.setOnClickListener(click -> {
            Executor thread = Executors.newSingleThreadExecutor();

            thread.execute(() ->{
                aDAO.insertFlight(selected);//add to database;
                /*this runs in another thread*/
            });
            Toast toast = Toast.makeText(getActivity(), "Flight saved!", Toast.LENGTH_LONG);
            toast.show();

        });

        return binding.getRoot();
    }
}