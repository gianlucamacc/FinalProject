/**
 * A RecyclerView adapter for displaying trivia scores in a list format.
 * This adapter populates the RecyclerView with score data and provides a way to customize the view.
 */
package algonquin.cst2335.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Score_RecyclerViewAdapter extends RecyclerView.Adapter<Score_RecyclerViewAdapter.MyViewHolder> {

    private static Context context;
    private ArrayList<TriviaQuestionModel> questionModels;
    private static List<TriviaScores> ByScoreList;
    private TriviaDatabase db;
    private static TriviaDAO tDAO;

    /**
     * Constructs a new Score_RecyclerViewAdapter.
     *
     * @param context       The context of the application.
     * @param ByScoreList   A list of TriviaScores objects containing score information.
     * @param db            The TriviaDatabase instance used for database operations.
     */
    public Score_RecyclerViewAdapter(Context context, List<TriviaScores> ByScoreList, TriviaDatabase db) {
        this.context = context;
        this.ByScoreList = ByScoreList;
        this.db = db;
        this.tDAO = db.tDAO();
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return         A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public Score_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.score_trivia_row, parent, false);
        return new Score_RecyclerViewAdapter.MyViewHolder(view);
    }

    /**
     * Called to display the data at the specified position.
     *
     * @param holder   The ViewHolder that holds the View.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull Score_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.userNameRecycler.setText(ByScoreList.get(position).userName);
        holder.scoreRecycler.setText(ByScoreList.get(position).scoreString);
        holder.scoreCategory.setText(ByScoreList.get(position).category);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items.
     */
    @Override
    public int getItemCount() {
        return ByScoreList.size();
    }

    /**
     * ViewHolder class that represents an item view in the RecyclerView.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userNameRecycler;
        TextView scoreRecycler;
        TextView scoreCategory;

        /**
         * Constructs a new MyViewHolder.
         *
         * @param itemView The View representing an item in the RecyclerView.
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameRecycler = itemView.findViewById(R.id.userNameRecyclerScore);
            scoreRecycler = itemView.findViewById(R.id.scoreRecyclerScore);
            scoreCategory = itemView.findViewById(R.id.scoreCategory);
        }
    }
}
