package algonquin.cst2335.finalproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * An activity that displays a list of saved bear image URLs using a RecyclerView.
 * Allows the user to delete or display the selected image.
 * Implements the ItemClickListener interface from BearView for handling item clicks.
 *
 * @author [Your Name]
 * @version 1.0
 */
public class SavedImages extends AppCompatActivity implements BearView.ItemClickListener {

    private RecyclerView recyclerView;
    private ArrayList<String> savedImageUrls;
    private BearView recyclerViewAdapter;
    private BearDatabase databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bearimages);

        recyclerView = findViewById(R.id.savedImagesRecyclerView);
        savedImageUrls = new ArrayList<>();
        recyclerViewAdapter = new BearView(this, savedImageUrls);

        // Use a vertical LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setItemClickListener(this);

        databaseHelper = new BearDatabase(this);

        loadSavedImagesFromDatabase();
    }

    /**
     * Load saved bear image URLs from the database and update the RecyclerView adapter.
     */
    private void loadSavedImagesFromDatabase() {
        ArrayList<String> savedImageUrls = databaseHelper.getAllImageUrls();
        recyclerViewAdapter.updateData(savedImageUrls);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database when the activity is destroyed
        databaseHelper.close();
    }

    @Override
    public void onItemClick(int position) {
        Log.d("SavedImagesActivity", "Item clicked at position: " + position);
        String imageUrl = savedImageUrls.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options");
        builder.setItems(new CharSequence[]{"Delete", "Display Image"}, (dialog, which) -> {
            if (which == 0) {
                // Delete option selected
                deleteImage(position);
            } else if (which == 1) {
                // Display Image option selected
                displayImage(imageUrl);
            }
        });
        builder.show();
    }

    /**
     * Delete the selected image URL from the database and update the RecyclerView.
     *
     * @param position The position of the selected image URL.
     */
    private void deleteImage(int position) {
        String imageUrl = savedImageUrls.get(position);
        databaseHelper.deleteImageUrl(imageUrl);
        savedImageUrls.remove(position);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    /**
     * Display the selected image by sending its URL back to the MainActivity.
     *
     * @param imageUrl The URL of the selected image.
     */
    private void displayImage(String imageUrl) {
        // Navigate back to MainActivity and display the selected image
        Intent intent = new Intent();
        intent.putExtra("selectedImageUrl", imageUrl);
        setResult(Activity.RESULT_OK, intent);
        finish();
        Log.d("SavedImagesActivity", "Selected image URL: " + imageUrl);
    }
}
