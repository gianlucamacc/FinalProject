package algonquin.cst2335.finalproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity class representing TriviaScores to be stored in the database.
 */
@Entity
public class TriviaScores {

    /**
     * The user's name associated with the TriviaScore.
     */
    @ColumnInfo(name = "UserName")
    public String userName;

    /**
     * The score achieved for the trivia game, represented as a string.
     */
    @ColumnInfo(name = "Score")
    public String scoreString;

    /**
     * The time taken to complete the trivia game, represented as a string.
     */
    @ColumnInfo(name = "TimeTaken")
    public String timeTaken;

    /**
     * The numerical score count associated with the TriviaScore.
     */
    @ColumnInfo(name = "scoreCount")
    public int scoreCount;

    /**
     * The category of the trivia game associated with the TriviaScore.
     */
    @ColumnInfo(name = "category")
    public String category;

    /**
     * The auto-generated primary key identifier for the TriviaScore.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
}
