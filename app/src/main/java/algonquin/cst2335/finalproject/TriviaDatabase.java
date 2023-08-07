package algonquin.cst2335.finalproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TriviaScores.class}, version = 1)
public abstract class TriviaDatabase extends RoomDatabase {

    public abstract TriviaDAO tDAO();
}
