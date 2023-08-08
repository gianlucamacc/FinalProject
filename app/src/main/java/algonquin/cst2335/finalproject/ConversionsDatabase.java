package algonquin.cst2335.finalproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;
/**
 * A Room database class that defines the database structure and provides access to the data access object (DAO).
 */
@Database(entities = {CurrencyConverter.class}, version = 1)
public abstract class ConversionsDatabase extends RoomDatabase {
    /**
     * Returns an instance of the CurrencyConverterDAO to interact with the database.
     *
     * @return The CurrencyConverterDAO instance.
     */
    public abstract CurrencyConverterDAO ccDAO();
}
