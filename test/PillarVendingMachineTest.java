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
        int currentValue = pillarVendingMachine.acceptCoin("small", "light");
        assertEquals(10, currentValue);
    }
    @Test
    public void testVendingMachineCanAcceptNickels(){
        int currentValue = pillarVendingMachine.acceptCoin("medium", "medium");
        assertEquals(5, currentValue);
    }
    @Test
    public void testVendingMachineCanAcceptQuarters(){
        int currentValue = pillarVendingMachine.acceptCoin("big", "heavy");
        assertEquals(25, currentValue);
    }

    @Test
    public void testVendingMachineCanTakeMultipleCoins(){
        pillarVendingMachine.acceptCoin("small", "light");
        int currentValue = pillarVendingMachine.acceptCoin("small", "light");
        assertEquals(20, currentValue);
    }
}
