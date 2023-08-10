package algonquin.cst2335.finalproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * This abstract class defines the Room database for storing TriviaScores.
 */
@Database(entities = {TriviaScores.class}, version = 1)
public abstract class TriviaDatabase extends RoomDatabase {

    /**
     * Retrieves the Data Access Object (DAO) for interacting with the TriviaScores table in the database.
     *
     * @return The TriviaDAO instance for database operations.
     */
    public abstract TriviaDAO tDAO();
}
