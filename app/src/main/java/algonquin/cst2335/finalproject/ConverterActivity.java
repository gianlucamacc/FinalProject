package algonquin.cst2335.finalproject;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.finalproject.databinding.ActivityConverterBinding;


public class ConverterActivity extends AppCompatActivity {


    protected RequestQueue queue = null;
    CurrencyConverterDAO cDAO;
    ActivityConverterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConverterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();

        queue = Volley.newRequestQueue(this);
        /**This is the Currency Conversion database*/
        ConversionsDatabase db = Room.databaseBuilder(getApplicationContext(), ConversionsDatabase.class, "database-name").build();
        cDAO = db.ccDAO();
        Executor thread = Executors.newSingleThreadExecutor();
        thread.execute(()->{

        });


        /**Inside this onClickListener is all for the API url and the convert button functions*/
        binding.convertButton.setOnClickListener(clk -> {

            Toast.makeText(this, "THIS SHIT IS WORKING, KEEP GOING!!!", Toast.LENGTH_LONG).show();
//          String URL = "https://api.getgeoapi.com/v2/currency/convert?format=json&from=CAD&to=USD&amount=10&api_key=c1f37b28035f89328e61c24caefd20d99f97cdf0&format=json";
            String inputCurrency = binding.inputCurrency.getText().toString().toUpperCase();
            String outputCurrency = binding.outputCurrency.getText().toString().toUpperCase();
            String inputAmount = binding.inputAmount.getText().toString();


            String URL = "https://api.getgeoapi.com/v2/currency/convert?format=json&from="
                    + URLEncoder.encode(inputCurrency)
                    + "&to="
                    + URLEncoder.encode(outputCurrency)
                    + "&amount="
                    + URLEncoder.encode(inputAmount)
                    + "&api_key=c1f37b28035f89328e61c24caefd20d99f97cdf0&format=json";


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                    (response) -> {
                        try {
                            JSONObject rates = response.getJSONObject("rates");
                            JSONObject currency = rates.getJSONObject(outputCurrency);
                            String ratesAmount = currency.getString("rate_for_amount");
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
            saveCurrencyConversionToDatabase();
            Toast.makeText(this, "Currency conversion is saved to the database", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.item2) {
            Toast.makeText(this, "You clicked on Item 2", Toast.LENGTH_LONG).show();
        }
        return true;
    }
    private void saveCurrencyConversionToDatabase(){

        String inputAmount = binding.inputAmount.getText().toString();
        String outputAmount = binding.outputAmount.getText().toString();
        String inputCurrency = binding.inputCurrency.getText().toString().toUpperCase();
        String outputCurrency = binding.outputCurrency.getText().toString().toUpperCase();

        CurrencyConverter conversion = new CurrencyConverter();
        conversion.inputAmount = inputAmount;
        conversion.outputAmount = outputAmount;
        conversion.inputCurrency = inputCurrency;
        conversion.outputCurrency = outputCurrency;

        Executor thread = Executors.newSingleThreadExecutor();
        thread.execute(()->{
            cDAO.insertConversion(conversion);
        });
    }
}