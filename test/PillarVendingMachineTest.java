import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PillarVendingMachineTest {

    private PillarVendingMachine pillarVendingMachine;

    @Before
    public void setup() {
        pillarVendingMachine = new PillarVendingMachine();
    }

    @Test
    public void testVendingMachineCanAcceptDimes(){
        String currentValue = pillarVendingMachine.acceptCoin("small", "light");
        assertEquals("$0.10", currentValue);
    }
    @Test
    public void testVendingMachineCanAcceptNickels(){
        String currentValue = pillarVendingMachine.acceptCoin("medium", "medium");
        assertEquals("$0.05", currentValue);
    }
    @Test
    public void testVendingMachineCanAcceptQuarters(){
        String currentValue = pillarVendingMachine.acceptCoin("big", "heavy");
        assertEquals("$0.25", currentValue);
    }

    @Test
    public void testVendingMachineCanTakeMultipleCoins(){
        pillarVendingMachine.acceptCoin("small", "light");
        String currentValue = pillarVendingMachine.acceptCoin("small", "light");
        assertEquals("$0.20", currentValue);
    }

    @Test
    public void testVendingMachineDoesNotAddInvalidCoinsToTotal(){
        String currentValue;
        pillarVendingMachine.acceptCoin("small", "light");
        currentValue = pillarVendingMachine.acceptCoin("small", "light");
        assertEquals("$0.20", currentValue);
        currentValue = pillarVendingMachine.acceptCoin("medium", "light");
        assertEquals("$0.20", currentValue);
    }

    @Test
    public void testVendingMachineDisplaysInsertCoin(){
        String displayValue = pillarVendingMachine.display();
        assertEquals("INSERT COINS", displayValue);
    }

    @Test
    public void testVendingMachineDisplaysCurrentValue(){
        String displayValue = pillarVendingMachine.display();
        assertEquals("INSERT COINS", displayValue);
        pillarVendingMachine.acceptCoin("medium", "medium");
        displayValue = pillarVendingMachine.display();
        assertEquals("$0.05", displayValue);
    }

    @Test
    public void testVendingMachineReturnsRejectedCoin(){
        pillarVendingMachine.acceptCoin("medium", "light");
        List rejectedCoins = pillarVendingMachine.coinReturn();
        assertEquals(rejectedCoins.get(0), "unknown coin with size of medium and weight of light");

    }

    @Test
    public void testVendingMachineReturnsMultipleRejectedCoins(){
        pillarVendingMachine.acceptCoin("medium", "light");
        pillarVendingMachine.acceptCoin("medium", "light");
        pillarVendingMachine.acceptCoin("giant", "really heavy");

        List rejectedCoins = pillarVendingMachine.coinReturn();
        assertEquals(rejectedCoins.get(0), "unknown coin with size of medium and weight of light");
        assertEquals(rejectedCoins.get(1), "unknown coin with size of medium and weight of light");
        assertEquals(rejectedCoins.get(2), "unknown coin with size of giant and weight of really heavy");
    }

    @Test
    public void testVendingMachineHasProducts(){
        Set<String> availableProducts = pillarVendingMachine.getProducts();
        assertTrue(availableProducts.contains("cola"));
        assertTrue(availableProducts.contains("chips"));
        assertTrue(availableProducts.contains("candy"));
    }

    @Test
    public void testVendingMachineCanSelectProduct(){
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        String display = pillarVendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
    }

    @Test
    public void testVendingMachineWillNotDispenseProductIfThereIsNotEnoughMoney(){
        String display = pillarVendingMachine.selectProduct("cola");
        assertEquals("$1.00", display);
    }

    @Test
    public void testVendingMachineDisplaysInsertCoinsAfterPurchase(){
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        String display = pillarVendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
        display = pillarVendingMachine.display();
        assertEquals("INSERT COINS", display);
    }

    @Test
    public void testVendingMachineDisplaysInsertCoinsAfterPurchaseWhenThereWasExcessMoney(){
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        String display = pillarVendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
        display = pillarVendingMachine.display();
        assertEquals("INSERT COINS", display);
    }

    @Test
    public void testVendingMachineReturnsChange(){
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        pillarVendingMachine.acceptCoin("big", "heavy");
        String display = pillarVendingMachine.selectProduct("cola");
        assertEquals("THANK YOU", display);
        List<String> change = pillarVendingMachine.coinReturn;
        assertTrue(change.contains("quarter"));
    }
}
