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
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(findViewById(R.id.toolbar2));
        toolbar2.showOverflowMenu();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_menu2, menu);



        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if( item.getItemId() == R.id.item1 )
        {
            Intent scoresActivityVariable = new Intent(this,TriviaActivity.class);
            startActivity(scoresActivityVariable);


        }
        else if (item.getItemId() == R.id.item2)
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Quiz Options: ");
            alertDialogBuilder.setMessage("General, Video Games, Celebrities, Sports");
            alertDialogBuilder.setPositiveButton("Got it!", (dialog, which) -> {});
            alertDialogBuilder.show();


        }

        return true;
    }
}