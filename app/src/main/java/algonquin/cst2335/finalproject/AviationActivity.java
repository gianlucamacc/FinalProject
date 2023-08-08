package algonquin.cst2335.finalproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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
    ArrayList<AviationHolder> aviationHolder = new ArrayList<>();




    @Override
        protected void onCreate(Bundle savedInstanceState) {
        queue = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        ActivityAviationBinding binding = ActivityAviationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Update the TextView with the airport name
        RecyclerView recyclerView = findViewById(R.id.recyclerView);


//        AviationDatabase db = Room.databaseBuilder(getApplicationContext(), AviationDatabase.class, "FlightModel-name").build();
//        aDAO = db.aDAO();
//
//        // Perform database operations in a background thread
//        Executor executor = Executors.newSingleThreadExecutor();
//        executor.execute(() -> {
//            List<FlightModel> flightModelList = aDAO.getAllFlights();
//
//            // Update the UI on the main thread after database operation is complete
//            runOnUiThread(() -> {
//                AviationRecyclerViewAdapter adapter = new AviationRecyclerViewAdapter(this, aviationHolder);
//                recyclerView.setAdapter(adapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            });
//        });




        binding.searchButton.setOnClickListener(clk -> {
            String URL = "http://api.aviationstack.com/v1/flights?access_key=a4ab4837ecdcd2022d3fc174fae4ee37&dep_iata=YYZ";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                    (response) -> {
                        try {
                            // Get the "data" array from the JSON response
                            JSONArray data = response.getJSONArray("data");


                            // Iterate through the array
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject dataPosition = data.getJSONObject(i);

                                // Get the departure airport flight details
                                String airportName = dataPosition.getJSONObject("departure").getString("airport");
                                String terminal = dataPosition.getJSONObject("departure").getString("terminal");
                                String gate = dataPosition.getJSONObject("departure").getString("gate");
                                String delay = dataPosition.getJSONObject("departure").getString("delay");

                                if (airportName != null && terminal != null && gate != null && delay != null) {
                                    AviationHolder holder = new AviationHolder(airportName, terminal, gate, delay);
                                    aviationHolder.add(holder);
                                }
                            }
                            AviationRecyclerViewAdapter adapter = new AviationRecyclerViewAdapter(this, aviationHolder);
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


}