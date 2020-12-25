package com.example.quruqmeva;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Kerakli {
    public String summa(String son){
        String originalString = String.valueOf(son).toString();
        Long longval;
        if (originalString.contains(",")) {
            originalString = originalString.replaceAll(",", "");
        }
        longval = Long.parseLong(originalString);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###,###,###");
        String formattedString = formatter.format(longval);
        return formattedString;
    }
}
