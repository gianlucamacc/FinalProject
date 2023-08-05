package algonquin.cst2335.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Trivia_RecyclerViewAdapter extends RecyclerView.Adapter<Trivia_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<TriviaQuestionModel> questionModels;


    public Trivia_RecyclerViewAdapter(Context context, ArrayList<TriviaQuestionModel> questionModels){
        this.context = context;
        this.questionModels = questionModels;

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
        holder.questionView.setText(questionModels.get(position).getQuestion());
        holder.answerView1.setText(questionModels.get(position).getAnswerList().get(0));
        holder.answerView2.setText(questionModels.get(position).getAnswerList().get(1));
        holder.answerView3.setText(questionModels.get(position).getAnswerList().get(3));
        holder.answerView4.setText(questionModels.get(position).getAnswerList().get(2));
        holder.tempAnswer.setText((questionModels.get(position).getCorrectAnswer()));
//        holder.answerView4.setText(questionModels.get(position).getCorrectAnswer());

    }

    @Override
    public int getItemCount() {
        return questionModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView questionView;
        TextView answerView1;
        TextView answerView2;
        TextView answerView3;
        TextView answerView4;
        TextView tempAnswer;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
             questionView = itemView.findViewById(R.id.question);
             answerView1 = itemView.findViewById(R.id.answer1);
             answerView2 = itemView.findViewById(R.id.answer2);
             answerView3 = itemView.findViewById(R.id.answer3);
             answerView4 = itemView.findViewById(R.id.answer4);
             tempAnswer = itemView.findViewById(R.id.tempAnswer);




        }
    }
}
