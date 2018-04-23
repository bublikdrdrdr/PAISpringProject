package tk.ubublik.pai.utility;

import tk.ubublik.pai.exception.AccountNumberChecksumException;
import tk.ubublik.pai.exception.AccountNumberFormatException;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class AccountUtils {

    public static int longHashTo4Digits(long value){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
            buffer.putLong(value);
            byte[] array = md.digest(buffer.array());
            long hash = 0;
            for (int i = 0; i < array.length; i++) {
                hash ^= array[i]<<((i%2)*8);
            }
            return (int)Math.abs(hash % 10000);
        } catch (Exception e){
            throw new RuntimeException("Can't generate hash", e);
        }
    }

    public static int long2DigitChecksum(long value){
        int checksum = 0;
        while (value>0){
            checksum += value%10;
            value /= 10;
        }
        return checksum%100;
    }

    public static String idToAccountNumber(long id){
        if (id<0) throw new IllegalArgumentException("Value can't be negative");
        return String.format("%02d%020d%04d", long2DigitChecksum(id), id, longHashTo4Digits(id));
    }

    public static long accountNumberToId(String accountNumber) throws AccountNumberFormatException, AccountNumberChecksumException {
        accountNumber = accountNumber.replaceAll("\\D", "");
        long checksum, hash, id;
        try{
            if (accountNumber.length()!=2+5*4+4) throw new IllegalArgumentException("Wrong account number length");
            checksum = Long.parseLong(accountNumber.substring(0,2));
            id = Long.parseLong(accountNumber.substring(2, 22));
            hash = Long.parseLong(accountNumber.substring(22, 26));
        } catch (Exception e){
            throw new AccountNumberFormatException("Can't parse account number", e);
        }
        if (long2DigitChecksum(id)!=checksum || longHashTo4Digits(id) != hash)
            throw new AccountNumberChecksumException("Account number checksum doesn't match");
        return id;
    }
}
