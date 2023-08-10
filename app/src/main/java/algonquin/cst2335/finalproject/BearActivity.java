package algonquin.cst2335.finalproject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import algonquin.cst2335.finalproject.databinding.ActivityBearBinding;

/**
 * This activity allows users to generate and display bear images with specified dimensions.
 */
public class BearActivity extends AppCompatActivity {

    // UI components
    private Toolbar toolbar;
    private EditText width;
    private EditText height;
    private Button generateButton;

    // RecyclerView components
    private RecyclerView recyclerView;
    private ArrayList<String> stringArrayList;
    private BearView recyclerViewAdapter;

    // Request code for image display
    private static final int REQUEST_CODE_DISPLAY_IMAGE = 101;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu
        getMenuInflater().inflate(R.menu.menu_bear, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_help) {
            // Show help dialog
            showHelpDialog();
            return true;
        } else if (id == R.id.menu_saved_images) {
            // Open saved images activity
            openSavedImagesActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Displays a help dialog with instructions.
     */
    private void showHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Instructions");
        builder.setMessage("Enter Width at the top, Height at the bottom, then press the generate button.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private BearDatabase databaseHelper;

    /**
     * Loads saved images from the database and updates the adapter.
     */
    private void loadSavedImagesFromDatabase() {
        ArrayList<String> savedImageUrls = databaseHelper.getAllImageUrls();
        stringArrayList.clear();
        if (!savedImageUrls.isEmpty()) {
            String lastSavedImageUrl = savedImageUrls.get(savedImageUrls.size() - 1);
            stringArrayList.add(lastSavedImageUrl);
        }
        recyclerViewAdapter.notifyDataSetChanged();
    }

    /**
     * Inserts an image URL into the database.
     */
    private void insertImageUrlToDatabase(String imageUrl) {
        databaseHelper.insertImageUrl(imageUrl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout using View Binding
        ActivityBearBinding binding = ActivityBearBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize UI components
        toolbar = findViewById(R.id.toolbar);
        width = findViewById(R.id.width);
        height = findViewById(R.id.height);
        generateButton = findViewById(R.id.generateButton);
        recyclerView = findViewById(R.id.recycleView);

        // Initialize ArrayList for RecyclerView
        stringArrayList = new ArrayList<>();

        // Set up RecyclerView and its adapter
        recyclerViewAdapter = new BearView(this, stringArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        // Initialize database helper and load saved images
        databaseHelper = new BearDatabase(this);
        databaseHelper.getWritableDatabase();
        loadSavedImagesFromDatabase();

        // Handle the generate button click
        generateButton.setOnClickListener(clk -> {
            String widthInput = width.getText().toString();
            String heightInput = height.getText().toString();

            if (widthInput.isEmpty() && heightInput.isEmpty()) {
                // Display a toast if both width and height are not provided
                Toast.makeText(BearActivity.this, "Both width and height cannot be null", Toast.LENGTH_SHORT).show();
            } else {
                stringArrayList.clear();
                int widthValue = Integer.parseInt(widthInput);
                int heightValue = Integer.parseInt(heightInput);
                String url = "https://placebear.com/" + widthValue + "/" + heightValue + ".jpg";
                stringArrayList.add(url);
                recyclerViewAdapter.notifyDataSetChanged();

                // Insert the image URL to the database
                insertImageUrlToDatabase(url);

                // Load the last saved image URL from the database after inserting
                loadSavedImagesFromDatabase();
            }
        });

        // Handle the "Next Page" button click
        Button nextPageButton = binding.nextPageButton;
        nextPageButton.setOnClickListener(c -> {
            Intent t = new Intent(this, SavedImages.class);
            startActivity(t);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("MainActivity", "onActivityResult - RequestCode: " + requestCode + ", ResultCode: " + resultCode);

        if (requestCode == REQUEST_CODE_DISPLAY_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            // Get the selected image URL from the SavedImagesActivity
            String selectedImageUrl = data.getStringExtra("selectedImageUrl");

            // Add the selected image URL to the RecyclerView
            stringArrayList.clear();
            stringArrayList.add(selectedImageUrl);
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database when the activity is destroyed
        databaseHelper.close();
    }

    private void openSavedImagesActivity() {
        // Open the SavedImages activity and handle its result
        Intent intent = new Intent(this, SavedImages.class);
        startActivityForResult(intent, REQUEST_CODE_DISPLAY_IMAGE);
    }
}
