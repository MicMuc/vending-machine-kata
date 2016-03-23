public class Product {

    private String name;
    private double cost;
    private int amount;

    public Product(String name, double cost, int amount) {
        this.name = name;
        this.cost = cost;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getAmount() {
        return amount;
    }

    public void subtractProduct(){
        amount --;
    }
}
