import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PillarVendingMachineTest {

    @Test
    public void testVendingMachineCanAcceptDimes(){
        PillarVendingMachine pillarVendingMachine = new PillarVendingMachine();
        int currentValue = pillarVendingMachine.acceptCoin("dime");
        assertEquals(10, currentValue);
    }
    @Test
    public void testVendingMachineCanAcceptNickels(){
        PillarVendingMachine pillarVendingMachine = new PillarVendingMachine();
        int currentValue = pillarVendingMachine.acceptCoin("nickel");
        assertEquals(5, currentValue);
    }
    @Test
    public void testVendingMachineCanAcceptQuarters(){
        PillarVendingMachine pillarVendingMachine = new PillarVendingMachine();
        int currentValue = pillarVendingMachine.acceptCoin("quarter");
        assertEquals(25, currentValue);
    }
}
