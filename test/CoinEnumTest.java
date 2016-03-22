import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoinEnumTest {

    @Test
    public void testCoinGetAttributesReturnsNickelCorrectly(){
        Coin nickel = Coin.nickel;
        Coin returnedCoin = Coin.getCoinByAttributes("medium", "medium");
        assertEquals(nickel, returnedCoin);
    }
    @Test
    public void testCoinGetAttributesReturnsDimeCorrectly(){
        Coin dime = Coin.dime;
        Coin returnedCoin = Coin.getCoinByAttributes("small", "light");
        assertEquals(dime, returnedCoin);
    }
    @Test
    public void testCoinGetAttributesReturnsQuarterCorrectly(){
        Coin quarter = Coin.quarter;
        Coin returnedCoin = Coin.getCoinByAttributes("big", "heavy");
        assertEquals(quarter, returnedCoin);
    }

    @Test
    public void testNickelReturnsCorrectValue(){
        assertEquals(5, Coin.nickel.getValue());
    }

    @Test
    public void testDimeReturnsCorrectValue(){
        assertEquals(10, Coin.dime.getValue());
    }

    @Test
    public void testQuarterReturnsCorrectValue(){
        assertEquals(25, Coin.quarter.getValue());
    }
}
