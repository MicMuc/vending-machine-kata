
public enum Coin {
nickel("medium", "medium", .05), dime("small", "light", .10), quarter("big", "heavy", .25);

    private String size;
    private String weight;
    private double value;

    private Coin(String size, String weight, double value){
        this.size = size;
        this.weight = weight;
        this.value = value;
    }

    public static Coin getCoinByAttributes(String size, String weight){
        for(Coin coin: Coin.values()){
            if(coin.size == size && coin.weight == weight){
                return coin;
            }
        }
        return null;
    }

    public double getValue() {
        return value;
    }
}
