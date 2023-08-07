package algonquin.cst2335.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ConversionsRecyclerViewAdapter extends RecyclerView.Adapter<ConversionsRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<CurrencyConverter> converterList;
    ConversionsDatabase db;
    CurrencyConverterDAO cDAO;

    public ConversionsRecyclerViewAdapter(Context context, List<CurrencyConverter> converterList, ConversionsDatabase db) {
        this.context = context;
        this.converterList = converterList;
        this.db = db;
        this.cDAO = db.ccDAO();
    }

    @NonNull
    @Override
    public ConversionsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.conversion_rows, parent, false);
        return new ConversionsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionsRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.outputAmountDatabase.setText(converterList.get(position).outputAmount);
        holder.inputAmountDatabase.setText(converterList.get(position).inputAmount);
        holder.outputCurrencyDatabase.setText(converterList.get(position).outputCurrency);
        holder.inputCurrencyDatabase.setText(converterList.get(position).inputCurrency);

    }

    @Override
    public int getItemCount() {
        return converterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView inputCurrencyDatabase;
        TextView outputCurrencyDatabase;
        TextView inputAmountDatabase;
        TextView outputAmountDatabase;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            inputCurrencyDatabase = itemView.findViewById(R.id.inputCurrencyDatabase);
            outputCurrencyDatabase = itemView.findViewById(R.id.outputCurrencyDatabase);
            inputAmountDatabase = itemView.findViewById(R.id.inputAmountDatabase);
            outputAmountDatabase = itemView.findViewById(R.id.outputAmountDatabase);

//            itemView.setOnClickListener(clk -> {
//
////            });
            itemView.setOnClickListener(d -> {
                int position = getAbsoluteAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    CurrencyConverter cConverter = converterList.get(position);

                    ConversionTimeFragment frag = new ConversionTimeFragment(cConverter);
                    FragmentManager fMgr = ((AppCompatActivity) context).getSupportFragmentManager();

                    fMgr.beginTransaction().replace(R.id.conversionTimeLayout, frag).addToBackStack(null).commit();
                    deleteConversion();
                }
            });



        }

        public void deleteConversion() {
            int position = getAbsoluteAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                CurrencyConverter removedConversion = converterList.get(position);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Delete Entry");
                alertDialogBuilder.setMessage("Are you sure you want to delete this entry?");
                alertDialogBuilder.setPositiveButton("Delete", (dialog, which) -> {

                    Executor thread = Executors.newSingleThreadExecutor();
                    thread.execute(() -> {
                        cDAO.deleteConversion(removedConversion);

                        ((Activity) context).runOnUiThread(() -> {
                            converterList.remove(position);
                            notifyItemRemoved(position);

                            Snackbar.make(itemView, "Conversion deleted", Snackbar.LENGTH_LONG)
                                    .setAction("Undo", v -> {
                                        converterList.add(position, removedConversion);
                                        notifyItemInserted(position);

                                        Executor threadUndo = Executors.newSingleThreadExecutor();
                                        threadUndo.execute(() -> {
                                            cDAO.insertConversion(removedConversion);
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
