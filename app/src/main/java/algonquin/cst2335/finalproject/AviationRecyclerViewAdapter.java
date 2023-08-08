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

public class AviationRecyclerViewAdapter extends RecyclerView.Adapter<AviationRecyclerViewAdapter.MyViewHolder> {

    static Context context;
    static List<FlightModel> flightModelList;
    AviationDatabase db;
    static AviationDAO aDAO;
    ArrayList<AviationHolder> aviationHolder = new ArrayList<>();


    public AviationRecyclerViewAdapter(Context context, List<FlightModel> flightModelList, AviationDatabase db){
        this.context = context;
        this.flightModelList = flightModelList;
        this.db = db;
        this.aDAO = db.aDAO();

    }
    public AviationRecyclerViewAdapter(Context context, ArrayList<AviationHolder> aviationHolder){
        this.context = context;
        this.aviationHolder = aviationHolder;

    }


    @NonNull
    @Override
    public AviationRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_aviation_row, parent, false);
        return new AviationRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AviationRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.airportName.setText(aviationHolder.get(position).getAirportName());
        holder.terminal.setText(aviationHolder.get(position).getTerminal());
        holder.gate.setText(aviationHolder.get(position).getGate());
        holder.delay.setText(aviationHolder.get(position).getDelay());


    }

    @Override
    public int getItemCount() {
        return aviationHolder.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView airportName;
        TextView terminal;
        TextView gate;
        TextView delay;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            airportName = itemView.findViewById(R.id.airportName);
            terminal = itemView.findViewById(R.id.terminal);
            gate = itemView.findViewById(R.id.gate);
            delay = itemView.findViewById(R.id.delay);


        }
    }
}


