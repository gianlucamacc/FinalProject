package algonquin.cst2335.finalproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TriviaScores {

    @ColumnInfo(name = "User Name")
    public String userName;

    @ColumnInfo(name = "Score")
    public String scoreString;

//    @ColumnInfo(name = "Time Taken")
//    public String timeTaken;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
}
