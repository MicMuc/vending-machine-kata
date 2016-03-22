import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PillarVendingMachine {

    double currentAmount = 0;


    public String acceptCoin(String size, String weight) {
        Coin coin = Coin.getCoinByAttributes(size, weight);
        if (coin == null) {
            return formatOutput(currentAmount);
        }
        return formatOutput(currentAmount += coin.getValue());
    }
    private String formatOutput(double value){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        return formatter.format(value);
    }
}
