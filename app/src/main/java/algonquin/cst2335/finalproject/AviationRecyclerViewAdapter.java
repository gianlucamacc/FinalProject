package algonquin.cst2335.finalproject;

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

import java.util.ArrayList;

public class AviationRecyclerViewAdapter extends RecyclerView.Adapter<AviationRecyclerViewAdapter.MyViewHolder> {

    static Context context;
    AviationDatabase db;
    static AviationDAO aDAO;
    ArrayList<FlightModel> flightModels = new ArrayList<>();


    public AviationRecyclerViewAdapter(Context context, ArrayList<FlightModel> flightModelList, AviationDatabase db){
        this.context = context;
        this.flightModels = flightModelList;
        this.db = db;
        this.aDAO = db.aDAO();

    }
    public AviationRecyclerViewAdapter(Context context, ArrayList<FlightModel> flightModelList){
        this.context = context;
        this.flightModels = flightModelList;

    }

    public AviationRecyclerViewAdapter(SavedFlights context, ArrayList<FlightModel> savedFlights, SavedFlights savedFlights1) {
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
        holder.airportName.setText(flightModels.get(position).getAirportName());
        holder.terminal.setText(flightModels.get(position).getTerminal());
        holder.gate.setText(flightModels.get(position).getGate());
        holder.delay.setText(flightModels.get(position).getDelay());


    }

    @Override
    public int getItemCount() {
        return flightModels.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView airportName;
        TextView terminal;
        TextView gate;
        TextView delay;
        Button saveDetailsButton = itemView.findViewById(R.id.saveDetailsButton);


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            airportName = itemView.findViewById(R.id.airportName);
            terminal = itemView.findViewById(R.id.terminalName);
            gate = itemView.findViewById(R.id.gate);
            delay = itemView.findViewById(R.id.delay);


            saveDetailsButton.setOnClickListener(p->{
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    FlightModel flightDetail = flightModels.get(position);

                    DetailsFragment frag = new DetailsFragment(flightDetail);
                    FragmentManager fMgr = ((AppCompatActivity) context).getSupportFragmentManager();

                    fMgr.beginTransaction().replace(R.id.fragmentLocation, frag).addToBackStack(null).commit();


                }
            });
        }
    }
}


