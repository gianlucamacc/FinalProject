package algonquin.cst2335.finalproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CurrencyConverter {

    @ColumnInfo(name = "Input Amount")
    protected String inputAmount;

    @ColumnInfo(name = "Output Amount")
    protected String outputAmount;

    @ColumnInfo(name = "Input Currency")
    protected String inputCurrency;

    @ColumnInfo(name = "Output Currency")
    protected String outputCurrency;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
}
