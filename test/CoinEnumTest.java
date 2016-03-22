import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoinEnumTest {

    @Test
    public void TestCoinGetAttributesReturnsNickelCorrectly(){
        Coin nickel = Coin.nickel;
        Coin returnedCoin = Coin.getCoinByAttributes("medium", "medium");
        assertEquals(nickel, returnedCoin);
    }
    @Test
    public void TestCoinGetAttributesReturnsDimeCorrectly(){
        Coin dime = Coin.dime;
        Coin returnedCoin = Coin.getCoinByAttributes("small", "light");
        assertEquals(dime, returnedCoin);
    }
    @Test
    public void TestCoinGetAttributesReturnsQuarterCorrectly(){
        Coin quarter = Coin.quarter;
        Coin returnedCoin = Coin.getCoinByAttributes("big", "heavy");
        assertEquals(quarter, returnedCoin);
    }
}
