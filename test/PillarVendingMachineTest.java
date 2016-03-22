import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PillarVendingMachineTest {

    @Test
    public void testVendingMachineCanAcceptDimes(){
        PillarVendingMachine pillarVendingMachine = new PillarVendingMachine();
        int currentValue = pillarVendingMachine.acceptCoin("small", "light");
        assertEquals(10, currentValue);
    }
    @Test
    public void testVendingMachineCanAcceptNickels(){
        PillarVendingMachine pillarVendingMachine = new PillarVendingMachine();
        int currentValue = pillarVendingMachine.acceptCoin("medium", "medium");
        assertEquals(5, currentValue);
    }
    @Test
    public void testVendingMachineCanAcceptQuarters(){
        PillarVendingMachine pillarVendingMachine = new PillarVendingMachine();
        int currentValue = pillarVendingMachine.acceptCoin("big", "heavy");
        assertEquals(25, currentValue);
    }
}
