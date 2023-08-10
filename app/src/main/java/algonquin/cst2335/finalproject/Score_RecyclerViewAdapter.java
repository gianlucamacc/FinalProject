package algonquin.cst2335.finalproject;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter class for populating a RecyclerView with trivia scores and related information.
 */
public class Score_RecyclerViewAdapter extends RecyclerView.Adapter<Score_RecyclerViewAdapter.MyViewHolder> {

    private static Context context;
    private List<TriviaScores> ByScoreList;
    private TriviaDatabase db;
    private static TriviaDAO tDAO;

    /**
     * Constructs a Score_RecyclerViewAdapter instance.
     *
     * @param context      The context in which the adapter is created.
     * @param ByScoreList  A list of TriviaScores objects representing the trivia scores.
     * @param db           The TriviaDatabase instance used for database operations.
     */
    public Score_RecyclerViewAdapter(Context context, List<TriviaScores> ByScoreList, TriviaDatabase db) {
        this.context = context;
        this.ByScoreList = ByScoreList;
        this.db = db;
        this.tDAO = db.tDAO();
    }

    @NonNull
    @Override
    public Score_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.score_trivia_row, parent, false);
        return new Score_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Score_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.userNameRecycler.setText(ByScoreList.get(position).userName);
        holder.scoreRecycler.setText(ByScoreList.get(position).scoreString);
        holder.scoreCategory.setText(ByScoreList.get(position).category);
    }

    @Override
    public int getItemCount() {
        return ByScoreList.size();
    }

    /**
     * ViewHolder class for holding views associated with each trivia score item in the RecyclerView.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userNameRecycler;
        TextView scoreRecycler;
        TextView scoreCategory;

        /**
         * Constructs a MyViewHolder instance.
         *
         * @param itemView The view representing a trivia score item.
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameRecycler = itemView.findViewById(R.id.userNameRecyclerScore);
            scoreRecycler = itemView.findViewById(R.id.scoreRecyclerScore);
            scoreCategory = itemView.findViewById(R.id.scoreCategory);
        }
    }
}
