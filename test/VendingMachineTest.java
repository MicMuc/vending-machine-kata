import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @Before
    public void setup() {
        ArrayList<Product> products= new ArrayList<>();
        products.add(new Product("cola", 1.00, 5));
        products.add(new Product("chips", 0.50, 5));
        products.add(new Product("candy", 0.65, 5));

        ArrayList<Coin> coinSupply= new ArrayList<>();
        for(int i=0; i<15; i++){
            coinSupply.add(Coin.quarter);
            coinSupply.add(Coin.dime);
            coinSupply.add(Coin.nickel);
        }
        vendingMachine = new VendingMachine(coinSupply, products);
    }

    @Test
    public void testVendingMachineCanAcceptDimes(){
        String currentValue = vendingMachine.acceptCoin("small", "light");
        assertEquals("$0.10", currentValue);
    }
    @Test
    public void testVendingMachineCanAcceptNickels(){
        String currentValue = vendingMachine.acceptCoin("medium", "medium");
        assertEquals("$0.05", currentValue);
    }
    @Test
    public void testVendingMachineCanAcceptQuarters(){
        String currentValue = vendingMachine.acceptCoin("big", "heavy");
        assertEquals("$0.25", currentValue);
    }

    @Test
    public void testVendingMachineCanTakeMultipleCoins(){
        vendingMachine.acceptCoin("small", "light");
        String currentValue = vendingMachine.acceptCoin("small", "light");
        assertEquals("$0.20", currentValue);
    }

    @Test
    public void testVendingMachineDoesNotAddInvalidCoinsToTotal(){
        String currentValue;
        vendingMachine.acceptCoin("small", "light");
        currentValue = vendingMachine.acceptCoin("small", "light");
        assertEquals("$0.20", currentValue);
        currentValue = vendingMachine.acceptCoin("medium", "light");
        assertEquals("$0.20", currentValue);
    }

    @Test
    public void testVendingMachineDisplaysInsertCoin(){
        String displayValue = vendingMachine.display();
        assertEquals("INSERT COINS", displayValue);
    }

    @Test
    public void testVendingMachineDisplaysCurrentValue(){
        String displayValue = vendingMachine.display();
        assertEquals("INSERT COINS", displayValue);
        vendingMachine.acceptCoin("medium", "medium");
        displayValue = vendingMachine.display();
        assertEquals("$0.05", displayValue);
    }

    @Test
    public void testVendingMachineReturnsRejectedCoin(){
        vendingMachine.acceptCoin("medium", "light");
        List rejectedCoins = vendingMachine.coinReturn();
        assertEquals(rejectedCoins.get(0), "unknown coin with size of medium and weight of light");

    }

    @Test
    public void testVendingMachineReturnsMultipleRejectedCoins(){
        vendingMachine.acceptCoin("medium", "light");
        vendingMachine.acceptCoin("medium", "light");
        vendingMachine.acceptCoin("giant", "really heavy");

        List rejectedCoins = vendingMachine.coinReturn();
        assertEquals(rejectedCoins.get(0), "unknown coin with size of medium and weight of light");
        assertEquals(rejectedCoins.get(1), "unknown coin with size of medium and weight of light");
        assertEquals(rejectedCoins.get(2), "unknown coin with size of giant and weight of really heavy");
    }

    @Test
    public void testVendingMachineHasProducts(){
        Set<String> availableProducts = vendingMachine.getProducts();
        assertTrue(availableProducts.contains("cola"));
        assertTrue(availableProducts.contains("chips"));
        assertTrue(availableProducts.contains("candy"));
    }

    @Test
    public void testVendingMachineCanSelectProduct(){
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        String display = vendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
    }

    @Test
    public void testVendingMachineWillNotDispenseProductIfThereIsNotEnoughMoney(){
        String display = vendingMachine.selectProduct("cola");
        assertEquals("$1.00", display);
    }

    @Test
    public void testVendingMachineDisplaysInsertCoinsAfterPurchase(){
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        String display = vendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
        display = vendingMachine.display();
        assertEquals("INSERT COINS", display);
    }

    @Test
    public void testVendingMachineDisplaysInsertCoinsAfterPurchaseWhenThereWasExcessMoney(){
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        String display = vendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
        display = vendingMachine.display();
        assertEquals("INSERT COINS", display);
    }

    @Test
    public void testVendingMachineReturnsChange(){
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        String display = vendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
        List change = vendingMachine.coinReturn();
        assertTrue(change.contains("quarter"));
    }


    @Test
    public void testVendingMachineReturnsChangeThatCanContainMultiplesOfTheSameCoin(){
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        String display = vendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
        List<String> change = vendingMachine.coinReturn();
        int count = 0;
        for(String coin: change){
            if(coin.equals("quarter")){
                count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void testVendingMachineReturnsChangeThatIsNotTheSameCoinAsPutIn(){
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        String display = vendingMachine.selectProduct("candy");
        assertEquals("THANK YOU", display);
        List<String> change = vendingMachine.coinReturn();
        assertTrue(change.contains("dime"));
    }

    @Test
    public void testVendingMachineReturnsCorrectChange(){
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("small", "light");
        String display = vendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
        List<String> change = vendingMachine.coinReturn();
        assertTrue(change.contains("dime"));
    }

    @Test
    public void testVendingMachineReturnsCorrectChangeWithMultipleCoins(){
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("small", "light");
        String display = vendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
        List<String> change = vendingMachine.coinReturn();
        assertTrue(change.contains("dime"));
        assertTrue(change.contains("quarter"));
    }


    @Test
    public void testVendingMachineReturnsCorrectChangeWithMultipleCoinsIncludingUnknownCoins(){
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("small", "light");
        vendingMachine.acceptCoin("giant", "really heavy");
        String display = vendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
        List<String> change = vendingMachine.coinReturn();
        assertTrue(change.contains("dime"));
        assertTrue(change.contains("quarter"));
        assertTrue(change.contains("unknown coin with size of giant and weight of really heavy"));

    }

    @Test
    public void testVendingMachineCanReturnCoinsWhenCustomerWants(){
        vendingMachine.acceptCoin("small", "light");
        vendingMachine.acceptCoin("medium", "medium");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.returnCoins();
        ArrayList change = vendingMachine.coinReturn();
        assertTrue(change.contains("nickel"));
        assertTrue(change.contains("dime"));
        assertTrue(change.contains("quarter"));
        vendingMachine.coinReturn();
        assertEquals("INSERT COINS", vendingMachine.display());
    }

    @Test
    public void testVendingMachineReturnsMultipleCoinsOfTheSameType(){
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.returnCoins();
        assertEquals("INSERT COINS", vendingMachine.display());
        List<String> change = vendingMachine.coinReturn();
        int count = 0;
        for(String coin: change){
            if(coin.equals("quarter")){
                count++;
            }
        }
        assertEquals(3, count);
    }

    @Test
    public void testVendingMachineCanRunOutOfAProduct(){
        String display;
        ArrayList<Product> products= new ArrayList<>();
        products.add(new Product("chips", 0.50, 1));
        ArrayList<Coin> coinSupply= new ArrayList<>();
        for(int i=0; i<3; i++){
            coinSupply.add(Coin.quarter);
            coinSupply.add(Coin.dime);
            coinSupply.add(Coin.nickel);
        }
        vendingMachine = new VendingMachine(coinSupply, products);
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
       display = vendingMachine.selectProduct("chips");
        assertEquals("THANK YOU", display);
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        display = vendingMachine.selectProduct("chips");
        assertEquals("SOLD OUT", display);
    }

    @Test
    public void testVendingMachineCanRunOutOfAProductAndDisplayAmountInTransaction(){
        String display;
        ArrayList<Product> products= new ArrayList<>();
        products.add(new Product("chips", 0.50, 1));
        ArrayList<Coin> coinSupply= new ArrayList<>();
        for(int i=0; i<3; i++){
            coinSupply.add(Coin.quarter);
            coinSupply.add(Coin.dime);
            coinSupply.add(Coin.nickel);
        }
        vendingMachine = new VendingMachine(coinSupply, products);
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        display = vendingMachine.selectProduct("chips");
        assertEquals("THANK YOU", display);
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        display = vendingMachine.selectProduct("chips");
        assertEquals("SOLD OUT", display);
        display = vendingMachine.display();
        assertEquals("$0.50", display);
    }

    @Test
    public void testVendingMachineCanRunOutOfAProductAndDisplayInsertCoinsIfThereIsNoMoneyInTheTransaction(){
        String display;
        ArrayList<Product> products= new ArrayList<>();
        products.add(new Product("chips", 0.50, 1));
        ArrayList<Coin> coinSupply= new ArrayList<>();
        for(int i=0; i<15; i++){
            coinSupply.add(Coin.quarter);
            coinSupply.add(Coin.dime);
            coinSupply.add(Coin.nickel);
        }
        vendingMachine = new VendingMachine(coinSupply, products);
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        display = vendingMachine.selectProduct("chips");
        assertEquals("THANK YOU", display);
        display = vendingMachine.selectProduct("chips");
        assertEquals("SOLD OUT", display);
        display = vendingMachine.display();
        assertEquals("INSERT COINS", display);
    }

    @Test
    public void testVendingMachineCanNeedExactChange(){
        ArrayList<Product> products= new ArrayList<>();
        products.add(new Product("chips", 0.65, 1));
        ArrayList<Coin> coinSupply= new ArrayList<>();
        vendingMachine = new VendingMachine(coinSupply, products);
        String display = vendingMachine.display();
        assertEquals("EXACT CHANGE ONLY", display);


    }

    @Test
    public void testVendingMachineCanRunOutOfQuartersAndStillReturnFullChange(){
        String display;
        ArrayList<Product> products= new ArrayList<>();
        products.add(new Product("cola", 1.00, 5));
        ArrayList<Coin> coinSupply= new ArrayList<>();
        for(int i=0; i<15; i++){
            coinSupply.add(Coin.dime);
            coinSupply.add(Coin.nickel);
        }
        coinSupply.add(Coin.quarter);
        vendingMachine = new VendingMachine(coinSupply, products);
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        display = vendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
        List<String> returnedCoins = vendingMachine.coinReturn();
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;
        for(String coin: returnedCoins) {
            if (coin.equals(Coin.quarter.name())) quarterCount++;
            if (coin.equals(Coin.dime.name())) dimeCount++;
            if (coin.equals(Coin.nickel.name())) nickelCount++;
        }
        assertEquals(1, quarterCount);
        assertEquals(2, dimeCount);
        assertEquals(1, nickelCount);
    }

    @Test
    public void testVendingMachineCanPutCoinsIntoSupplyAfterPurchase(){
        String display;
        ArrayList<Product> products= new ArrayList<>();
        products.add(new Product("cola", 1.00, 5));
        ArrayList<Coin> coinSupply= new ArrayList<>();
        for(int i=0; i<15; i++){
            coinSupply.add(Coin.dime);
            coinSupply.add(Coin.nickel);
        }
        coinSupply.add(Coin.quarter);
        vendingMachine = new VendingMachine(coinSupply, products);
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");
        vendingMachine.acceptCoin("big", "heavy");

        display = vendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
        List<String> returnedCoins = vendingMachine.checkSupply();
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;
        for(String coin: returnedCoins) {
            if (coin.equals(Coin.quarter.name())) quarterCount++;
            if (coin.equals(Coin.dime.name())) dimeCount++;
            if (coin.equals(Coin.nickel.name())) nickelCount++;
        }
        assertEquals(5, quarterCount);
        assertEquals(15, dimeCount);
        assertEquals(15, nickelCount);
    }


}

