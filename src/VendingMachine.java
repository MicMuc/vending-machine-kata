import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class VendingMachine {

    private double currentAmount = 0;
    private ArrayList<Coin> coinSupply = new ArrayList<>();
    private double valueOfCoinSupply = 0;
    private ArrayList<String> coinReturn = new ArrayList<>();
    private ArrayList<Coin> coinsInTransaction = new ArrayList<>();
    private HashMap<String, Product> products = new HashMap<>();


    public VendingMachine(ArrayList<Coin> changeSupply, List<Product> products){
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
        return valueOfCoinSupply < highestCost * 3;
    }

    public ArrayList<String> coinReturn() {
        ArrayList<String> coinReturnCopy = coinReturn;
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
            addCoinsInTransactionToSupply();
            return "THANK YOU";
        }
    }

    private void addCoinsInTransactionToSupply() {
        for(Coin coin: coinsInTransaction){
            coinSupply.add(coin);
            valueOfCoinSupply += coin.getValue();
        }
    }

    private void determineChange(double cost){
        double currentAmountInCents =  currentAmount*100;
        double costInCents= cost*100;
        double change = currentAmountInCents - costInCents;
        for(Coin coin: Coin.values()) {
            double currentCoinInCents = coin.getValue()*100;
            int coins = (int) (change / currentCoinInCents);
            coins = removeCoinsFromSupply(coin, coins);
            change = change - (coins * currentCoinInCents);
            for (int i = 0; i < coins; i++) {
                coinReturn.add(coin.name());
            }
        }
    }

    private int removeCoinsFromSupply(Coin coinTypeToReturn, int coins) {
        int j = 0;
        for(int i=coins; i>0; i--){
            if(!coinSupply.remove(coinTypeToReturn)){
                return j;
            }
            j++;
        }
        return j;
    }

    public void returnCoins() {
        coinReturn.addAll(coinsInTransaction.stream().map(Coin::name).collect(Collectors.toList()));
        currentAmount = 0;
    }

    public List<String> checkSupply() {
        return coinSupply.stream().map(Coin::name).collect(Collectors.toList());
    }
}
