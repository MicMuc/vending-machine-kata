import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PillarVendingMachineTest {

    PillarVendingMachine pillarVendingMachine;

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
        assertEquals("INSERT COIN", displayValue);
    }
}
