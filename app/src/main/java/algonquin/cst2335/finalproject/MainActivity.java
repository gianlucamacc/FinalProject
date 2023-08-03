package algonquin.cst2335.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import algonquin.cst2335.finalproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate( getLayoutInflater());
        setContentView(binding.getRoot());

        //This is Bear Image Generator onClickListener
        binding.bearGenerator.setOnClickListener(click ->{
            Intent toBear = new Intent(this, BearActivity.class);
            startActivity(toBear);
        });

        //This is the Trvia Question onClickListener
        binding.trivia.setOnClickListener(click->{
            Intent toTrivia = new Intent(this, TriviaActivity.class);
            startActivity(toTrivia);
        });

        //This is the Currency Converter onClickListener
        binding.currencyConverter.setOnClickListener(click->{
            Intent toCurrency = new Intent(this, ConverterActivity.class);
            startActivity(toCurrency);
        });

        //This is the Aviation Tracker onClickListener
        binding.aviationTracker.setOnClickListener(click->{
            Intent toAviation = new Intent(this, AviationActivity.class);
            startActivity(toAviation);
        });

    }
}