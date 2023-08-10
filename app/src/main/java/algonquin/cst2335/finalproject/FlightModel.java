package algonquin.cst2335.finalproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FlightModel {
    @ColumnInfo(name="airportName")
    String airportName;

    @ColumnInfo(name="terminal")
    String terminal;

    @ColumnInfo(name="gate")
    String gate;

    @ColumnInfo(name="delay")
    String delay;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    public int id;

    public FlightModel(String airportName, String terminal, String gate, String delay) {
        this.airportName = airportName;
        this.terminal = terminal;
        this.gate = gate;
        this.delay = delay;
    }


    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}