
public enum Coin {
nickel("medium", "medium", 5), dime("small", "light", 10), quarter("big", "heavy", 25);

    private String size;
    private String weight;
    private int value;

    private Coin(String size, String weight, int value){
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

}
