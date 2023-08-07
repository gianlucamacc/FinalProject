package algonquin.cst2335.finalproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CurrencyConverter {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "Input Currency")
    public String inputCurrency;
    @ColumnInfo(name = "Input Amount")
    public String inputAmount;
    @ColumnInfo(name = "Output Currency")
    public String outputCurrency;
    @ColumnInfo(name = "Output Amount")
    public String outputAmount;


}
