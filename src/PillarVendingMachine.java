import java.text.NumberFormat;
import java.util.*;

public class PillarVendingMachine {

    double currentAmount = 0;
    List<String> coinReturn = new ArrayList();
    Map<String, Double> products = new HashMap();

    public PillarVendingMachine(){
        products.put("cola", 1.00);
        products.put("chips", 0.50);
        products.put("candy", 0.65);
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
        return "INSERT COINS";
    }

    public List coinReturn() {
        List<String> coinReturnCopy = coinReturn;
        coinReturn = new ArrayList<>();
        return coinReturnCopy;
    }

    public Set<String> getProducts(){
        return products.keySet();
    }

    public String selectProduct(String product) {
        double cost = products.get(product);
        if(cost > currentAmount){
            return formatOutput(cost);
        } else {
            determineChange(currentAmount - cost);
            currentAmount = 0;
            return "THANK YOU";
        }
    }

    private void determineChange(double change) {
        for(Coin coin: Coin.values()) {
            int coins = (int) (change / coin.getValue());
            change = change - coins * coin.getValue();
            for (int i = 0; i < coins; i++) {
                coinReturn.add(coin.name());
            }
        }
    }
}
