package algonquin.cst2335.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import algonquin.cst2335.finalproject.databinding.ActivityMainBinding;
import algonquin.cst2335.finalproject.databinding.ActivityTriviaBinding;



public class TriviaActivity extends AppCompatActivity {

    protected RequestQueue queue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_trivia);
        ActivityTriviaBinding binding = ActivityTriviaBinding.inflate( getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submitButton.setOnClickListener(clk -> {
            int numberOfQuestions = binding.numberOfQuestions.getTextAlignment();
            String categoryOfQuestions = binding.categoryOfQuestions.toString();
            //todo logic of category (translating string to int)

            String URL = "https://opentdb.com/api.php?amount="
                    + numberOfQuestions
                    + "&category="
                    + categoryOfQuestions
                    + "&type=multiple";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                    (response) -> {


                    },
                    (error) -> {

            });

        });




    }
}