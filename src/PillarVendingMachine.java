import java.text.NumberFormat;
import java.util.*;

public class PillarVendingMachine {

    private double currentAmount = 0;
    private List<Coin> coinSupply = new ArrayList();
    private double valueOfCoinSupply = 0;
    private List<String> coinReturn = new ArrayList();
    private List<Coin> coinsInTransaction = new ArrayList();
    private Map<String, Product> products = new HashMap();


    public PillarVendingMachine(ArrayList<Coin> changeSupply, List<Product> products){
        this.coinSupply = changeSupply;
        for(Coin coin: coinSupply){
            valueOfCoinSupply += coin.getValue();
        }
        for(Product product: products){
            this.products.put(product.getName(), product);
        }
    }

    public String acceptCoin(String size, String weight) {
        Coin coin;
        try {
            coin = Coin.getCoinByAttributes(size, weight);
        } catch (IllegalArgumentException iae){
            coinReturn.add(iae.getMessage());
            return formatOutput(currentAmount);
        }
        coinsInTransaction.add(coin);
        return formatOutput(currentAmount += coin.getValue());
    }
    private String formatOutput(double value){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        return formatter.format(value);
    }

    public String display() {
        if(currentAmount != 0){
            return formatOutput(currentAmount);
        } else if (needExactChange()){
            return "EXACT CHANGE ONLY";
        }
        return "INSERT COINS";
    }

    private boolean needExactChange() {
        double highestCost = -1;
        for(Product product: products.values()){
            if(highestCost == -1 || (product.getCost() > highestCost && product.getAmount() > 0)){
                highestCost = product.getCost();
            }
        }
        if(valueOfCoinSupply < highestCost*3){
            return true;
        }
        return false;
    }

    public List coinReturn() {
        List<String> coinReturnCopy = coinReturn;
        coinReturn = new ArrayList<>();
        return coinReturnCopy;
    }

    public Set<String> getProducts(){
        return products.keySet();
    }

    public String selectProduct(String productName) {
        Product product = products.get(productName);
        double cost = product.getCost();
        if(product.getAmount()<1){
            return "SOLD OUT";
        }
        else if(cost > currentAmount){
            return formatOutput(cost);
        } else {
            determineChange(cost);
            product.subtractProduct();
            currentAmount = 0;
            return "THANK YOU";
        }
    }

    private void determineChange(double cost){
        double currentAmountInCentst =  currentAmount*100;
        double costInCents= cost*100;
        double change = currentAmountInCentst - costInCents;
        for(Coin coin: Coin.values()) {
            double currentCoinInCents = coin.getValue()*100;
            int coins = (int) (change / currentCoinInCents);
            change = change - (coins * currentCoinInCents);
            for (int i = 0; i < coins; i++) {
                coinReturn.add(coin.name());
            }
        }
    }

    public void returnCoins() {
        for(Coin coin: coinsInTransaction){
            coinReturn.add(coin.name());
        }
        currentAmount = 0;
    }
}
