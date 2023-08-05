package algonquin.cst2335.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import algonquin.cst2335.finalproject.databinding.ActivityMainBinding;
import algonquin.cst2335.finalproject.databinding.ActivityTriviaBinding;



public class TriviaActivity extends AppCompatActivity {

    protected RequestQueue queue = null;

    ArrayList<TriviaQuestionModel> questionModels = new ArrayList<>();




    String category;
    String question;
    String difficulty;
    String correctAnswer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_trivia);
        ActivityTriviaBinding binding = ActivityTriviaBinding.inflate( getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);


        queue = Volley.newRequestQueue(this);

        RadioGroup radioGroup;

        binding.submitButton.setOnClickListener(clk -> {
            String numberOfQuestions = binding.numberOfQuestions.getText().toString().trim();
            String categoryOfQuestions = binding.categoryOfQuestions.getText().toString().trim();
            numberOfQuestions = numberOfQuestions.replaceAll("\\s","");
            categoryOfQuestions = categoryOfQuestions.replaceAll("\\s","");
            String categoryNumber = null;

            if(categoryOfQuestions.equalsIgnoreCase("celebrities")) {
            categoryNumber = "26";
            }
            else if(categoryOfQuestions.equalsIgnoreCase("videogames")){
                categoryNumber = "15";
            }
            else if(categoryOfQuestions.equalsIgnoreCase("general")){
                categoryNumber = "9";
            }
            else{
                categoryNumber = "9";
            }

            String URL = "https://opentdb.com/api.php?amount="
                    + numberOfQuestions
                    + "&category="
                    + categoryNumber
                    + "&type=multiple";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                    (response) -> {
                        try {

                            JSONArray results = response.getJSONArray("results");


                            for (int i = 0; i < results.length(); i++){


                                JSONObject resultPosition = results.getJSONObject(i);

                                JSONArray incorrect_answers = resultPosition.getJSONArray("incorrect_answers");

                                category = resultPosition.getString("category");
                                question = resultPosition.getString("question");
                                difficulty = resultPosition.getString("difficulty");
                                correctAnswer = resultPosition.getString("correct_answer");


                                ArrayList<String> answerList = new ArrayList<>();
                                answerList.add(resultPosition.getString("correct_answer"));
                                for(int j = 0; j < incorrect_answers.length(); j++){

                                    answerList.add(incorrect_answers.getString(j));
                                }

                                Collections.shuffle(answerList);
                                TriviaQuestionModel model = new TriviaQuestionModel( category,  question,  difficulty,  correctAnswer, answerList);

                                /**
                                 * TODO radio group section
                                 */




                                questionModels.add(model);
                                Trivia_RecyclerViewAdapter adapter = new Trivia_RecyclerViewAdapter(this, questionModels);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(this));

//                               String category = question.getString("category");
//                               String questionText = question.getString("question");
////                                binding.testText.setText(category);
////                                binding.testText2.setText(questionText);

                            }




                        } catch (JSONException e) {
                            throw new RuntimeException(e);

                        }


                    },
                    (error) -> { int test = 0;

            });

            queue.add(request);


            //end of clk listener

        });
        radioGroup = findViewById(R.id.RadioGroup);
        


    }
}