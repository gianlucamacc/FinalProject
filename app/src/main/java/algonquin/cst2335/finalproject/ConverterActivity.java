package algonquin.cst2335.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import algonquin.cst2335.finalproject.databinding.ActivityConverterBinding;


public class ConverterActivity extends AppCompatActivity {

    ActivityConverterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        binding = ActivityConverterBinding.inflate(getLayoutInflater());
        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item1){
            Toast.makeText(this, "You clicked on Item 1", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.item2){
            Toast.makeText(this, "You clicked on Item 2", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}