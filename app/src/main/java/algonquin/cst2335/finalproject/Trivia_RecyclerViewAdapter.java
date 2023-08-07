package algonquin.cst2335.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class Trivia_RecyclerViewAdapter extends RecyclerView.Adapter<Trivia_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<TriviaQuestionModel> questionModels;
    List<TriviaScores> scoreList;





    public Trivia_RecyclerViewAdapter(Context context, List<TriviaScores> scoreList){
        this.context = context;
        this.scoreList = scoreList;

    }


    @NonNull
    @Override
    public Trivia_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_trivia_row, parent, false);
        return new Trivia_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Trivia_RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.userNameRecycler.setText(scoreList.get(position).userName);
        holder.scoreRecycler.setText(scoreList.get(position).scoreString);
//        holder.answerView3.setText(questionModels.get(position).getAnswerList().get(3));
//        holder.answerView4.setText(questionModels.get(position).getAnswerList().get(2));
//        holder.tempAnswer.setText((questionModels.get(position).getCorrectAnswer()));
//        holder.answerView4.setText(questionModels.get(position).getCorrectAnswer());
//        holder.questionView.setText(questionModels.get(position).getQuestion());

    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
     TextView userNameRecycler;
     TextView scoreRecycler;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
             userNameRecycler = itemView.findViewById(R.id.userNameRecycler);
             scoreRecycler = itemView.findViewById(R.id.scoreRecycler);



        }
    }
}
