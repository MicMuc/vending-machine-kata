public class PillarVendingMachine {

    int currentAmount = 0;


    public int acceptCoin(String size, String weight) {
        Coin coin = Coin.getCoinByAttributes(size, weight);
        if(coin == null){
            return currentAmount;
        }
        return currentAmount += coin.getValue();
    }
}
