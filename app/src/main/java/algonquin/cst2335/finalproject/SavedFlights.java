package algonquin.cst2335.finalproject;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.finalproject.databinding.SavedFlightsBinding;

public class SavedFlights extends AppCompatActivity implements FlightRecyclerViewInterface {

    ArrayList<FlightModel> savedFlights = new ArrayList<>();
    AviationDAO aDAO;
    @NonNull SavedFlightsBinding binding;

    AviationRecyclerViewAdapter2 adapter;

    @Override
    public void onItemClick(int position) {

        SavedDetailsFragment frag = new SavedDetailsFragment(savedFlights.get(position));
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentLocation2, frag)
                .addToBackStack("")
                .commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_menu_aviation, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.help){
            AlertDialog.Builder builder = new AlertDialog.Builder(SavedFlights.this);
            builder.setMessage("This is the help page!! I gotta edit this soon!")
                    .setTitle("Flight Tracker Help")
                    .setNeutralButton("Ok", (dialog, cl)->{})
                    .create().show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle SavedInstance) {
        super.onCreate(SavedInstance);

        AviationDatabase db = Room.databaseBuilder(getApplicationContext(), AviationDatabase.class, "SavedFlights").build();
        aDAO = db.aDAO();
        binding = SavedFlightsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RecyclerView recyclerView = binding.recyclerView2;


        Executor thread = Executors.newSingleThreadExecutor();
        thread.execute(() ->{
            savedFlights.addAll(aDAO.getAllFlights());

        });

        adapter = new AviationRecyclerViewAdapter2(this, savedFlights, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}