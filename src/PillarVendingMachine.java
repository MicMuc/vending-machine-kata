public class PillarVendingMachine {


    public int acceptCoin(String size, String weight) {
        Coin coin = Coin.getCoinByAttributes(size, weight);

        return coin.getValue();
    }
}
