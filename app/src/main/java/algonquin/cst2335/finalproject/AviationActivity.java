package algonquin.cst2335.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.finalproject.databinding.ActivityAviationBinding;

public class AviationActivity extends AppCompatActivity {


    protected RequestQueue queue = null;
    AviationDAO aDAO;
    ArrayList<FlightModel> flightModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        queue = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        ActivityAviationBinding binding = ActivityAviationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = (Toolbar)findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();

        // Update the TextView with the airport name
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        binding.searchButton.setOnClickListener(clk -> {
            String AirportCode = binding.airportCode.getText().toString().trim();
            AirportCode = AirportCode.replaceAll("\\s", "");
            String URL = "http://api.aviationstack.com/v1/flights?access_key=f9e2094870bb1beecf19ced9e9d08d67&dep_iata=" + AirportCode;


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                    (response) -> {
                        try {
                            // Get the "data" array from the JSON response
                            JSONArray data = response.getJSONArray("data");


                            // Iterate through the array
                            for (int i = 0; i < 10; i++) {
                                JSONObject dataPosition = data.getJSONObject(i);

                                // Get the departure airport flight details
                                String airportName = dataPosition.getJSONObject("departure").getString("airport");
                                String terminal = dataPosition.getJSONObject("departure").getString("terminal");
                                String gate = dataPosition.getJSONObject("departure").getString("gate");
                                String delay = dataPosition.getJSONObject("departure").getString("delay");

                                FlightModel holder = new FlightModel(airportName, terminal, gate, delay);
                                flightModels.add(holder);
                            }
                            AviationRecyclerViewAdapter adapter = new AviationRecyclerViewAdapter(this, flightModels);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(this));


                        } catch (JSONException e) {
                            throw new RuntimeException(e);

                        }


                    },
                    (error) -> {
                        int test = 0;

                    });

            queue.add(request);

        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_menu_aviation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.savedFlights){
            Intent savedFlights = new Intent(this, SavedFlights.class);
            startActivity(savedFlights);
        }
        return true;
    }
}