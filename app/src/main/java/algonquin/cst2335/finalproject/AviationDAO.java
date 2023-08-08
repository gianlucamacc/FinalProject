package algonquin.cst2335.finalproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface AviationDAO {
    @Insert
    void insertFlight(FlightModel flight);
    @Query("SELECT * FROM FlightModel")
    List<FlightModel> getAllFlights();
    @Delete
    void deleteFlight(FlightModel flight);
}
