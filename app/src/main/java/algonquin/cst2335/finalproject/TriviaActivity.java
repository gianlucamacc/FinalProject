package algonquin.cst2335.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

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

    int correctAnswerCount;

    int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_trivia);
        ActivityTriviaBinding binding = ActivityTriviaBinding.inflate( getLayoutInflater());
        setContentView(binding.getRoot());

//        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);


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
            else if (categoryOfQuestions.equalsIgnoreCase("sports")){
                categoryNumber = "21";
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




                                questionModels.add(model);

                                binding.questionView.setText(model.getQuestion());
                                binding.answerView1.setText(model.getAnswerList().get(0));
                                binding.answerView2.setText(model.getAnswerList().get(1));
                                binding.answerView3.setText(model.getAnswerList().get(2));
                                binding.answerView4.setText(model.getAnswerList().get(3));




                                Trivia_RecyclerViewAdapter adapter = new Trivia_RecyclerViewAdapter(this, questionModels);
//                                recyclerView.setAdapter(adapter);
//                                recyclerView.setLayoutManager(new LinearLayoutManager(this));




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
        /**
         * SECOND CLICK LISTENER, FOR NEXT QUESTION
         */

        binding.nextQuestionSet.setOnClickListener(clickers -> {

            // Increment the count to move to the next position in the ArrayList
            count++;

            // Check if the count is within the valid range of the ArrayList
            if (count < questionModels.size()) {
                // Get the TriviaQuestionModel at the current count position
                TriviaQuestionModel model = questionModels.get(count);

                // Update the UI with the new question and answer options
                binding.questionView.setText(model.getQuestion());
                binding.answerView1.setText(model.getAnswerList().get(0));
                binding.answerView2.setText(model.getAnswerList().get(1));
                binding.answerView3.setText(model.getAnswerList().get(2));
                binding.answerView4.setText(model.getAnswerList().get(3));

                // Check which radio button is checked
                int selectedId = binding.RadioGroup.getCheckedRadioButtonId();

                // Compare the selectedId with each radio button's ID to determine the user's answer
                String userAnswer = null;
                if (selectedId == R.id.answerView1) {
                    userAnswer = model.getAnswerList().get(0);
                } else if (selectedId == R.id.answerView2) {
                    userAnswer = model.getAnswerList().get(1);
                } else if (selectedId == R.id.answerView3) {
                    userAnswer = model.getAnswerList().get(2);
                } else if (selectedId == R.id.answerView4) {
                    userAnswer = model.getAnswerList().get(3);
                }

                // Compare the user's answer with the correct answer to check if it's correct
                if (userAnswer != null && userAnswer.equals(model.getCorrectAnswer())) {
                    // Increment the correct answer count and update the UI with the count
                    // Assuming you have a TextView with the ID 'count' to display the count
                    binding.count.setText("Correct answer count: " + (++correctAnswerCount));
                } else {
                    // Display a message if the answer is incorrect
                    Toast.makeText(this, "Incorrect answer!", Toast.LENGTH_SHORT).show();
                }
            } else {
                // If count exceeds the size of the ArrayList, display a message indicating the end of questions
                Toast.makeText(this, "End of questions", Toast.LENGTH_SHORT).show();
                binding.userNamePrompt.setVisibility(View.VISIBLE);
                binding.userName.setVisibility(View.VISIBLE);
                binding.score.setVisibility(View.VISIBLE);

                binding.score.setText("Your Score is:" + correctAnswerCount + "/" + questionModels.size());

                String scoreString = "Your Score is:" + correctAnswerCount + "/" + questionModels.size();



            }
        });
        }







        


    }
