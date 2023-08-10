package algonquin.cst2335.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Score_RecyclerViewAdapter extends RecyclerView.Adapter<Score_RecyclerViewAdapter.MyViewHolder> {

    static Context context;
    ArrayList<TriviaQuestionModel> questionModels;
    static List<TriviaScores> ByScoreList;
    TriviaDatabase db;
    static TriviaDAO tDAO;


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

//        holder.answerView3.setText(questionModels.get(position).getAnswerList().get(3));
//        holder.answerView4.setText(questionModels.get(position).getAnswerList().get(2));
//        holder.tempAnswer.setText((questionModels.get(position).getCorrectAnswer()));
//        holder.answerView4.setText(questionModels.get(position).getCorrectAnswer());
//        holder.questionView.setText(questionModels.get(position).getQuestion());

    }

    @Override
    public int getItemCount() {
        return ByScoreList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userNameRecycler;
        TextView scoreRecycler;
        TextView scoreCategory;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameRecycler = itemView.findViewById(R.id.userNameRecyclerScore);
            scoreRecycler = itemView.findViewById(R.id.scoreRecyclerScore);
            scoreCategory = itemView.findViewById(R.id.scoreCategory);



        }
    }
}