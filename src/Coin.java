
public enum Coin {
nickel("small", "medium"), dime("small", "light"), quarter("big", "heavy");

    private String size;
    private String weight;

    private Coin(String size, String weight){
        this.size = size;
        this.weight = weight;
    }

}
