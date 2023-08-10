package algonquin.cst2335.finalproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * This interface defines the data access methods for interacting with the TriviaScores table in the database.
 */
@Dao
public interface TriviaDAO {

    /**
     * Inserts a new TriviaScore into the database.
     *
     * @param triviaScore The TriviaScore object to be inserted.
     */
    @Insert
    void insertScore(TriviaScores triviaScore);

    /**
     * Retrieves all the TriviaScores from the database.
     *
     * @return A list of all TriviaScores in the database.
     */
    @Query("SELECT * from TriviaScores")
    List<TriviaScores> getAllTriviaScores();

    /**
     * Retrieves all the TriviaScores from the database ordered by scoreCount in descending order.
     *
     * @return A list of TriviaScores ordered by scoreCount in descending order.
     */
    @Query("SELECT * FROM TriviaScores ORDER BY scoreCount DESC;")
    List<TriviaScores> getAllByScoreCounts();

    /**
     * Deletes a TriviaScore from the database.
     *
     * @param triviaScore The TriviaScore object to be deleted.
     */
    @Delete
    void deleteScore(TriviaScores triviaScore);
}
