package algonquin.cst2335.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ScoresActivity extends AppCompatActivity {
TriviaDAO tDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);

        TriviaDatabase db = Room.databaseBuilder(getApplicationContext(), TriviaDatabase.class, "TriviaScores-name").build();
        tDAO = db.tDAO();

        // Perform database operations in a background thread
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<TriviaScores> scoreList = tDAO.getAllTriviaScores();

            // Update the UI on the main thread after database operation is complete
            runOnUiThread(() -> {
                Trivia_RecyclerViewAdapter adapter = new Trivia_RecyclerViewAdapter(this, scoreList, db);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            });
        });

        Button returnButton = findViewById(R.id.returnFromScoreButton);

        returnButton.setOnClickListener(k->{
            Intent vari = new Intent(this,TriviaActivity.class);
            startActivity(vari);
        });

    }
}