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





}