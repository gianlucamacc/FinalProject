package algonquin.cst2335.finalproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Represents a currency conversion record stored in a database.
 */
@Entity
public class CurrencyConverter {
    /**
     * The auto-generated primary key for the currency conversion record.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    /**
     * The input currency used for the conversion.
     */
    @ColumnInfo(name = "Input Currency")
    public String inputCurrency;
    /**
     * The input amount used for the conversion.
     */
    @ColumnInfo(name = "Input Amount")
    public String inputAmount;
    /**
     * The output currency resulting from the conversion.
     */
    @ColumnInfo(name = "Output Currency")
    public String outputCurrency;
    /**
     * The output amount resulting from the conversion.
     */
    @ColumnInfo(name = "Output Amount")
    public String outputAmount;
    /**
     * The time when the conversion was executed.
     */
    @ColumnInfo(name = "Time Conversion Executed")
    public String timeExecuted;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInputCurrency() {
        return inputCurrency;
    }

    public void setInputCurrency(String inputCurrency) {
        this.inputCurrency = inputCurrency;
    }

    public String getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(String inputAmount) {
        this.inputAmount = inputAmount;
    }

    public String getOutputCurrency() {
        return outputCurrency;
    }

    public void setOutputCurrency(String outputCurrency) {
        this.outputCurrency = outputCurrency;
    }

    public String getOutputAmount() {
        return outputAmount;
    }

    public void setOutputAmount(String outputAmount) {
        this.outputAmount = outputAmount;
    }

    public String getTimeExecuted() {
        return timeExecuted;
    }

    public void setTimeExecuted(String timeExecuted) {
        this.timeExecuted = timeExecuted;
    }
}
