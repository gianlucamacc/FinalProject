package algonquin.cst2335.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

import algonquin.cst2335.finalproject.databinding.ActivityConverterBinding;


public class ConverterActivity extends AppCompatActivity {


    protected RequestQueue queue = null;
    protected String inputCurrency;
    protected String outputCurrency;
    protected String intputAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityConverterBinding binding = ActivityConverterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();

        queue = Volley.newRequestQueue(this);

        binding.convertButton.setOnClickListener(clk -> {

            Toast.makeText(this, "this shit is working", Toast.LENGTH_LONG).show();
//          String URL = "https://api.getgeoapi.com/v2/currency/convert?format=json&from=CAD&to=USD&amount=10&api_key=c1f37b28035f89328e61c24caefd20d99f97cdf0&format=json";
            inputCurrency = binding.inputCurrency.getText().toString();
            outputCurrency = binding.outputCurrency.getText().toString();
            intputAmount = binding.inputAmount.getText().toString();


            String URL = "https://api.getgeoapi.com/v2/currency/convert?format=json&from="
                    + URLEncoder.encode(inputCurrency)
                    + "&to="
                    + URLEncoder.encode(outputCurrency)
                    + "&amount="
                    + URLEncoder.encode(intputAmount)
                    + "&api_key=c1f37b28035f89328e61c24caefd20d99f97cdf0&format=json";


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                    (response) -> {
                        try {
                            JSONObject rates = response.getJSONObject("rates");
                            JSONObject cad = rates.getJSONObject(outputCurrency);
                            String ratesAmount = cad.getString("rate_for_amount");
                            binding.outputAmount.setText(ratesAmount);

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
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            Toast.makeText(this, "You clicked on Item 1", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.item2) {
            Toast.makeText(this, "You clicked on Item 2", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}