package algonquin.cst2335.finalproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface TriviaDAO {

    @Insert
    void insertScore(TriviaScores triviaScore);


    @Query("SELECT * from TriviaScores")
    List<TriviaScores> getAllTriviaScores();

    @Delete
    void deleteScore(TriviaScores triviaScore);
}