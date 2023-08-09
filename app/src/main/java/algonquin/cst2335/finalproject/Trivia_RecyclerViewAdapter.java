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
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Trivia_RecyclerViewAdapter extends RecyclerView.Adapter<Trivia_RecyclerViewAdapter.MyViewHolder> {
    static Context context;
    ArrayList<TriviaQuestionModel> questionModels;
    static List<TriviaScores> scoreList;

    TriviaDatabase db;
    static TriviaDAO tDAO;


    public Trivia_RecyclerViewAdapter(Context context, List<TriviaScores> scoreList, TriviaDatabase db){
        this.context = context;
        this.scoreList = scoreList;
        this.db = db;
        this.tDAO = db.tDAO();

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
        holder.category.setText(scoreList.get(position).category);

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

    public class MyViewHolder extends RecyclerView.ViewHolder{
     TextView userNameRecycler;
     TextView scoreRecycler;

     TextView timeRecycler;
     TextView category;
        Button getTimeButton = itemView.findViewById(R.id.getTimeButton);
        public MyViewHolder(@NonNull View itemView){
                    super(itemView);
             userNameRecycler = itemView.findViewById(R.id.userNameRecycler);
             scoreRecycler = itemView.findViewById(R.id.scoreRecycler);
             category = itemView.findViewById(R.id.categoryView);


//             itemView.setOnClickListener(l->{
//                 deleteConversion();
//             });
                itemView.setOnClickListener(d -> {
                    deleteConversion();
            });

                getTimeButton.setOnClickListener(p->{
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        TriviaScores observeScore = scoreList.get(position);

                        DetailsFragment frag = new DetailsFragment(observeScore);
                        FragmentManager fMgr = ((AppCompatActivity) context).getSupportFragmentManager();

                        fMgr.beginTransaction().replace(R.id.fragmentLocation, frag).addToBackStack(null).commit();


                    }
                });





        }
        public void deleteConversion() {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                TriviaScores removedScore = scoreList.get(position);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Delete Entry");
                alertDialogBuilder.setMessage("Are you sure you want to delete this score entry?");
                alertDialogBuilder.setPositiveButton("Delete", (dialog, which) -> {
                    Executor thread = Executors.newSingleThreadExecutor();
                    thread.execute(() -> {
                        tDAO.deleteScore(removedScore);

                        ((Activity) context).runOnUiThread(() -> {
                            scoreList.remove(position);
                            notifyItemRemoved(position);

                            Snackbar.make(itemView, "Entry deleted", Snackbar.LENGTH_LONG)
                                    .setAction("Undo", v -> {
                                        scoreList.add(position, removedScore);
                                        notifyItemInserted(position);
                                        // If you want to insert the item back to the database as well, you can do it here
                                        Executor threadUndo = Executors.newSingleThreadExecutor();
                                        threadUndo.execute(() -> {
                                            tDAO.insertScore(removedScore);
                                        });
                                    }).show();
                        });
                    });
                });
                alertDialogBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }




    }
    }
