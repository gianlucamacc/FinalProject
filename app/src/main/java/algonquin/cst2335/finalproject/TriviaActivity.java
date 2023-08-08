package algonquin.cst2335.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.finalproject.databinding.ActivityMainBinding;
import algonquin.cst2335.finalproject.databinding.ActivityTriviaBinding;



public class TriviaActivity extends AppCompatActivity {

    protected RequestQueue queue = null;

    ArrayList<TriviaQuestionModel> questionModels = new ArrayList<>();




    String category;
    String question;
    String difficulty;
    String correctAnswer;
    String scoreString;

    ArrayList<Integer> compareScoreList= new ArrayList<>();

    TriviaDAO tDAO;

    int correctAnswerCount;

    int count = 0;

    int scoreCount = 0;

    SharedPreferences prefs;

    EditText numberOfQuestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_trivia);
        ActivityTriviaBinding binding = ActivityTriviaBinding.inflate( getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(binding.toolbar);
        toolbar.showOverflowMenu();

        prefs = getSharedPreferences("myData", Context.MODE_PRIVATE);
        numberOfQuestions = findViewById(R.id.numberOfQuestions);
        String savedNumberOfQuestions = prefs.getString("numberOfQuestions", "");
        numberOfQuestions.setText(savedNumberOfQuestions);

        TriviaDatabase db = Room.databaseBuilder(getApplicationContext(), TriviaDatabase.class, "TriviaScores-name").fallbackToDestructiveMigration()
                .build();
        tDAO = db.tDAO();




        queue = Volley.newRequestQueue(this);



        binding.submitButton.setOnClickListener(clk -> {

            String newNumberOfQuestions = numberOfQuestions.getText().toString().trim();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("numberOfQuestions", newNumberOfQuestions);
            editor.apply();


            binding.questionView.setVisibility(View.VISIBLE);
            binding.answerView1.setVisibility(View.VISIBLE);
            binding.answerView2.setVisibility(View.VISIBLE);
            binding.answerView3.setVisibility(View.VISIBLE);
            binding.answerView4.setVisibility(View.VISIBLE);
            binding.nextQuestionSet.setVisibility(View.VISIBLE);


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
                    + "&difficulty=easy&type=multiple";

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

                                TriviaQuestionModel firstModel = questionModels.get(count);
                                binding.questionView.setText(model.getQuestion());
                                binding.answerView1.setText(model.getAnswerList().get(0));
                                binding.answerView2.setText(model.getAnswerList().get(1));
                                binding.answerView3.setText(model.getAnswerList().get(2));
                                binding.answerView4.setText(model.getAnswerList().get(3));








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



            // Check if the count is within the valid range of the ArrayList
            if (count < questionModels.size()) {
                // Get the TriviaQuestionModel at the current count position
                TriviaQuestionModel model = questionModels.get(count);



                // Check which radio button is checked
                int selectedId = binding.RadioGroup.getCheckedRadioButtonId();

                // Compare the selectedId with each radio button's ID to determine the user's answer
                String userAnswer = null;
                if (selectedId == R.id.answerView1) {
                    userAnswer = binding.answerView1.getText().toString();
                } else if (selectedId == R.id.answerView2) {
                    userAnswer =  binding.answerView2.getText().toString();
                } else if (selectedId == R.id.answerView3) {
                    userAnswer =  binding.answerView3.getText().toString();
                } else if (selectedId == R.id.answerView4) {
                    userAnswer =  binding.answerView4.getText().toString();
                }

                count++;
                // Compare the user's answer with the correct answer to check if it's correct
                if (userAnswer.equalsIgnoreCase( binding.ghost.getText().toString())) {
                    // Increment the correct answer count and update the UI with the count
                    // Assuming you have a TextView with the ID 'count' to display the count
                    scoreCount = scoreCount + 10;
//                    binding.count.setText("Correct answer count: " + (++correctAnswerCount) + "(" +(scoreCount) + ")");
                    binding.RadioGroup.clearCheck();
//


//
//                    Toast.makeText(this, "yes!", Toast.LENGTH_SHORT).show();
                } else {
                    binding.numberOfQuestionsText.setText(userAnswer);
                    // Display a message if the answer is incorrect
//                    Toast.makeText(this, "Incorrect answer!", Toast.LENGTH_SHORT).show();
//
                    binding.RadioGroup.clearCheck();

                }
                // Update the UI with the new question and answer options
                binding.questionView.setText(model.getQuestion());
                binding.answerView1.setText(model.getAnswerList().get(0));
                binding.answerView2.setText(model.getAnswerList().get(1));
                binding.answerView3.setText(model.getAnswerList().get(2));
                binding.answerView4.setText(model.getAnswerList().get(3));
                binding.ghost.setText(model.getCorrectAnswer());
            }
            else {
                // If count exceeds the size of the ArrayList, display a message indicating the end of questions
                Toast.makeText(this, "You Scored: " + correctAnswerCount + " / " + questionModels.size() + "  " + "(" + scoreCount + ")", Toast.LENGTH_LONG).show();
                binding.userNamePrompt.setVisibility(View.VISIBLE);
                binding.userName.setVisibility(View.VISIBLE);
                binding.score.setVisibility(View.VISIBLE);
                binding.saveScoreButton.setVisibility(View.VISIBLE);


                binding.score.setText("Your Score is:" + correctAnswerCount + "/" + questionModels.size() + "(" + scoreCount + ")");

                 scoreString = "Your Score is: " + correctAnswerCount + "/" + questionModels.size() + "(" + scoreCount + ")" ;





            }


            /**
             * end of second click listener
             */
        });

        binding.saveScoreButton.setOnClickListener(c->{
            String userName = binding.userName.getText().toString();

            TriviaScores scores = new TriviaScores();

            scores.userName = userName;
            scores.scoreString = scoreString;
            scores.timeTaken = getCurrentTime();

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(()->{
                tDAO.insertScore(scores);


            });
            Toast.makeText(this, "Saved to dataBase!!", Toast.LENGTH_SHORT).show();





        });
        binding.viewScoresButton.setOnClickListener(ck->{
            Intent scoresActivityVariable = new Intent(this,ScoresActivity.class);
            startActivity(scoresActivityVariable);

        });


        binding.helpButton.setOnClickListener(o ->{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Quiz Options: ");
            alertDialogBuilder.setMessage("General, Video Games, Celebrities, Sports");
            alertDialogBuilder.setPositiveButton("Delete", (dialog, which) -> {});

        });


        }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy \nhh:mm:ss a");
        return sdf.format(new Date());
    }





        


    }
