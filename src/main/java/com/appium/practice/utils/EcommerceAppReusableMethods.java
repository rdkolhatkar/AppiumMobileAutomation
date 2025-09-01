package com.appium.practice.utils;

public class EcommerceAppReusableMethods {
    public Double getFormattedAmounts(String amount){
        Double price = Double.parseDouble(amount.substring(1));
        return price;

    }
}
