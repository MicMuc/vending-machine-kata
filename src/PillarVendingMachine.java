public class PillarVendingMachine {


    public int acceptCoin(String coin) {
        if(coin.equals("nickel")){
            return 5;
        }
        if(coin.equals("dime")){
            return 10;
        }
        if(coin.equals("quarter")){
            return 25;
        }
        return 0;
    }
}
