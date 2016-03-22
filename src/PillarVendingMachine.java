import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PillarVendingMachine {

    double currentAmount = 0;
    List<String> coinReturn = new ArrayList();
    Map<String, String> products = new HashMap();

    public PillarVendingMachine(){
        products.put("cola", "$1.00");
        products.put("chips", "$0.50");
        products.put("candy", "$0.65");
    }



    public String acceptCoin(String size, String weight) {
        Coin coin;
        try {
            coin = Coin.getCoinByAttributes(size, weight);
        } catch (IllegalArgumentException iae){
            coinReturn.add(iae.getMessage());
            return formatOutput(currentAmount);
        }

        return formatOutput(currentAmount += coin.getValue());
    }
    private String formatOutput(double value){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        return formatter.format(value);
    }

    public String display() {
        if(currentAmount != 0){
            return formatOutput(currentAmount);
        }
        return "INSERT COIN";
    }

    public List coinReturn() {
        return coinReturn;
    }

    public Map<String, String> getProducts(){
        return products;
    }
}
