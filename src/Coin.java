import java.util.Objects;

public enum Coin {
    quarter("big", "heavy", .25),  dime("small", "light", .10),nickel("medium", "medium", .05);

    private String size;
    private String weight;
    private double value;

    Coin(String size, String weight, double value){
        this.size = size;
        this.weight = weight;
        this.value = value;
    }

    public static Coin getCoinByAttributes(String size, String weight){
        for(Coin coin: Coin.values()){
            if(Objects.equals(coin.size, size) && Objects.equals(coin.weight, weight)){
                return coin;
            }
        }
        throw new IllegalArgumentException("unknown coin with size of "+size+" and weight of "+weight);
    }

    public double getValue() {
        return value;
    }
}
