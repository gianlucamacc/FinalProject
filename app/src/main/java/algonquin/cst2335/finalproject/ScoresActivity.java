package algonquin.cst2335.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * An activity that displays trivia scores using two RecyclerViews for different sorting options.
 */
public class ScoresActivity extends AppCompatActivity {

    private TriviaDAO tDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        toolbar2.showOverflowMenu();

        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);
        RecyclerView ScoreRecyclerView = findViewById(R.id.scoreRecyclerView);

        TriviaDatabase db = Room.databaseBuilder(getApplicationContext(), TriviaDatabase.class, "TriviaScores-name").build();
        tDAO = db.tDAO();

        // Load trivia scores and populate the first RecyclerView
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<TriviaScores> scoreList = tDAO.getAllTriviaScores();
            runOnUiThread(() -> {
                Trivia_RecyclerViewAdapter adapter = new Trivia_RecyclerViewAdapter(this, scoreList, db);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            });
        });

        // Load trivia scores sorted by score and populate the second RecyclerView
        Executor thread = Executors.newSingleThreadExecutor();
        thread.execute(() -> {
            List<TriviaScores> ByScoreList = tDAO.getAllByScoreCounts();
            runOnUiThread(() -> {
                Score_RecyclerViewAdapter scoreAdapter = new Score_RecyclerViewAdapter(this, ByScoreList, db);
                ScoreRecyclerView.setAdapter(scoreAdapter);
                ScoreRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            Intent scoresActivityVariable = new Intent(this, TriviaActivity.class);
            startActivity(scoresActivityVariable);
        } else if (item.getItemId() == R.id.item2) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Quiz Options: ");
            alertDialogBuilder.setMessage("General, Video Games, Celebrities, Sports");
            alertDialogBuilder.setPositiveButton("Got it!", (dialog, which) -> {});
            alertDialogBuilder.show();
        }
        return true;
    }
}
