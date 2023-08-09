package algonquin.cst2335.finalproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TriviaScores {

    @ColumnInfo(name = "UserName")
    public String userName;

    @ColumnInfo(name = "Score")
    public String scoreString;

    @ColumnInfo(name = "TimeTaken")
    public String timeTaken;
    @ColumnInfo(name = "scoreCount")
    public int scoreCount;

    @ColumnInfo(name = "category")
    public String category;


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
}
