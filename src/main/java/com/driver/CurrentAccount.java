
package com.driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name, balance, 5000);
        if(balance < 5000) {
            throw new Exception("Insufficient Balance");
        }
        this.tradeLicenseId = tradeLicenseId;
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        if(!tradeLicenseId.equals(tradeLicenseId.toUpperCase())) {
            throw new Exception("Valid License can not be generated");
        }

        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<tradeLicenseId.length();i++) {
            if(map.containsKey(tradeLicenseId.charAt(i))) {
                int value = map.get(tradeLicenseId.charAt(i));
                map.put(tradeLicenseId.charAt(i), value+1 );
            } else {
                map.put(tradeLicenseId.charAt(i), 1 );
            }
        }
        if(Collections.max(map.values()) > (tradeLicenseId.length() + 1)/2) {
            throw new Exception("Valid License can not be generated");
        }

        Collections.shuffle(Arrays.asList(tradeLicenseId.toCharArray()));
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
    }

}