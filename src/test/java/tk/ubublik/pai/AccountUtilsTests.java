package tk.ubublik.pai;

import org.junit.Test;
import org.junit.runners.JUnit4;
import tk.ubublik.pai.exception.AccountNumberChecksumException;
import tk.ubublik.pai.exception.AccountNumberFormatException;
import tk.ubublik.pai.utility.AccountUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class AccountUtilsTests {

    @Test
    public void accountNumberGenerationAndValidationTest() throws Exception{
        long[] testValues = new long[]{0, 1, 2, 5, 20, 100, 9999999, Long.MAX_VALUE-1};
        for (long v: testValues){
            assertEquals(v, AccountUtils.accountNumberToId(AccountUtils.idToAccountNumber(v)));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void accountNumberNegativeIdExceptionTest(){
        AccountUtils.idToAccountNumber(-1);
    }

    @Test(expected = AccountNumberChecksumException.class)
    public void accountNumberChecksumExceptionTest() throws AccountNumberChecksumException, AccountNumberFormatException {
        int id = 5;
        String accountNumber = AccountUtils.idToAccountNumber(id);
        int index = accountNumber.length()-2;
        StringBuilder stringBuilder = new StringBuilder(accountNumber);
        stringBuilder.setCharAt(index, (char)('0'+((accountNumber.charAt(index)-'0')+2)%10));
        assertNotEquals(id, AccountUtils.accountNumberToId(stringBuilder.toString()));
    }
}
