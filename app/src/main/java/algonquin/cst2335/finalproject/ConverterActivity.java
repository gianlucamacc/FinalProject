package algonquin.cst2335.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.finalproject.databinding.ActivityConverterBinding;


public class ConverterActivity extends AppCompatActivity {


    protected RequestQueue queue = null;
    CurrencyConverterDAO cDAO;
    ActivityConverterBinding binding;
    SharedPreferences prefs;
    EditText inputAmount;
    EditText inputCurrency;
    EditText outputCurrency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConverterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();

        prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        inputAmount = findViewById(R.id.inputAmount);
        inputCurrency = findViewById(R.id.inputCurrency);
        outputCurrency = findViewById(R.id.outputCurrency);
        String savedInputCurrency = prefs.getString("inputCurrency", "");
        String savedOutputCurrency = prefs.getString("outputCurrency", "");
        String savedInputAmount = prefs.getString("inputAmount", "");


        inputAmount.setText(savedInputAmount);
        inputCurrency.setText(savedInputCurrency);
        outputCurrency.setText(savedOutputCurrency);


        queue = Volley.newRequestQueue(this);
        /**This is the Currency Conversion database*/
        ConversionsDatabase db = Room.databaseBuilder(getApplicationContext(), ConversionsDatabase.class, "database-name").build();
        cDAO = db.ccDAO();


        /**Inside this onClickListener is all for the API url and the convert button functions*/
        binding.convertButton.setOnClickListener(clk -> {
            String newInputCurrency = inputCurrency.getText().toString();
            String newInputAmount = inputAmount.getText().toString();
            String newOutputCurrency = outputCurrency.getText().toString();
            saveConversionDataToSharedPreferences(newInputCurrency, newOutputCurrency, newInputAmount);


            Toast.makeText(this, "Conversion saved to database", Toast.LENGTH_LONG).show();
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

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy \nhh:mm:ss a");
        return sdf.format(new Date());
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
            Intent conversionRecycler = new Intent(this, ConversionRecycler.class);
            startActivity(conversionRecycler);
        } else if (item.getItemId() == R.id.item3) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(getString(R.string.instructions));
            alertDialogBuilder.setMessage(getString(R.string.instruction_text));

            alertDialogBuilder.setPositiveButton("Got It", (dialog, which) -> dialog.dismiss());

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
        return true;
    }

    private void saveCurrencyConversionToDatabase() {

        String inputAmount = binding.inputAmount.getText().toString();
        String outputAmount = binding.outputAmount.getText().toString();
        String inputCurrency = binding.inputCurrency.getText().toString().toUpperCase();
        String outputCurrency = binding.outputCurrency.getText().toString().toUpperCase();

        CurrencyConverter conversion = new CurrencyConverter();
        conversion.inputAmount = inputAmount;
        conversion.outputAmount = outputAmount;
        conversion.inputCurrency = inputCurrency;
        conversion.outputCurrency = outputCurrency;
        conversion.timeExecuted = getCurrentTime();

        Executor thread = Executors.newSingleThreadExecutor();
        thread.execute(() -> {
            cDAO.insertConversion(conversion);
        });
    }

    private void saveConversionDataToSharedPreferences(String inputCurrency, String outputCurrency, String inputAmount) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("inputCurrency", inputCurrency);
        editor.putString("outputCurrency", outputCurrency);
        editor.putString("inputAmount", inputAmount);
        editor.apply();
    }

}