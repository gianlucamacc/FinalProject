package algonquin.cst2335.finalproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing currency conversion records in the database.
 */
@Dao
public interface CurrencyConverterDAO {
    /**
     * Inserts a new currency conversion record into the database.
     *
     * @param conversion The currency conversion record to be inserted.
     */
    @Insert
    void insertConversion(CurrencyConverter conversion);

    /**
     * Retrieves a list of all currency conversion records from the database.
     *
     * @return A list of all currency conversion records stored in the database.
     */
    @Query("Select * from CurrencyConverter")
    List<CurrencyConverter> getAllConversions();

    /**
     * Deletes a currency conversion record from the database.
     *
     * @param conversion The currency conversion record to be deleted.
     */
    @Delete
    void deleteConversion(CurrencyConverter conversion);

}
