import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PillarVendingMachineTest {

@Test
public void testVendingMachineCanAcceptCoins(){
    PillarVendingMachine pillarVendingMachine = new PillarVendingMachine();
    int currentValue = pillarVendingMachine.acceptCoin("dime");
    assertEquals(10, currentValue);
}
}
