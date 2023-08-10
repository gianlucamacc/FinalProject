package algonquin.cst2335.finalproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FlightModel.class},version = 1)
public abstract class AviationDatabase extends RoomDatabase {
    public abstract AviationDAO aDAO();
}
