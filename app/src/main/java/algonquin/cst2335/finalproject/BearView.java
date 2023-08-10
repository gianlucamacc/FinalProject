package algonquin.cst2335.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * An adapter class for populating a RecyclerView with a list of image URLs using Volley's ImageLoader.
 * This adapter also provides methods for updating data and handling item clicks.
 *
 * @author Daniel Stewart
 * @version 1.0
 */
public class BearView extends RecyclerView.Adapter<BearView.ViewHolder> {

    private final Context context;
    private final ArrayList<String> urlList;
    private BearDatabase databaseHelper;

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    private ItemClickListener itemClickListener;

    /**
     * Constructor for the BearView class.
     *
     * @param context The application context.
     * @param urlList The list of image URLs to be displayed in the RecyclerView.
     */
    public BearView(Context context, ArrayList<String> urlList) {
        this.context = context;
        this.urlList = urlList;
        // databaseHelper = new BearImageDatabaseHelper(context);
    }

    @NonNull
    @Override
    public BearView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bearimages, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageUrl = urlList.get(position);
        holder.bearImage.setImageUrl(imageUrl, BearVolley.getInstance(context).getImageLoader());
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        NetworkImageView bearImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bearImage = itemView.findViewById(R.id.bearImage);
        }
    }

    /**
     * Update the data in the adapter with a new list of image URLs.
     *
     * @param urlList The new list of image URLs.
     */
    public void updateData(ArrayList<String> urlList) {
        this.urlList.clear();
        this.urlList.addAll(urlList);
        notifyDataSetChanged();
    }

    /**
     * Delete an item from the adapter at the given position.
     *
     * @param position The position of the item to be deleted.
     */
    public void deleteItem(int position) {
        String imageUrl = urlList.get(position);
        urlList.remove(position);
        notifyDataSetChanged();
        // databaseHelper.deleteImageUrl(imageUrl);
    }

    /**
     * Set the item click listener for the adapter.
     *
     * @param itemClickListener The item click listener.
     */
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
