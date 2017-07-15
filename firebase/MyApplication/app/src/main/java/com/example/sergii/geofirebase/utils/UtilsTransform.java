package com.example.sergii.geofirebase.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sergii on 15.07.17.
 */

public class UtilsTransform {

    private static final String PROHIBITED_STRING = "[.\\[\\]\\#\\$]";
    private static final String REPLACE_SYMBOL = "s";
    private static Pattern pattern = Pattern.compile(PROHIBITED_STRING);
    /** geofire doesn't alow to use '.', '#', '$', '[', or ']'
     * That's why we change this symbol to "s" and add "tr" at beginning.
     *
     * @param email
     * @return
     */
    public static String getTransformedEmail(String email) {
        // part 1

        System.out.println("UtilsTransform: getTransformedEmail: before: " + email);
        // the expression
        Matcher m = pattern.matcher(email);
        // the source
        if(m.find()){
            email = m.replaceAll("s");
        }
        System.out.println("UtilsTransform: getTransformedEmail: after: " + email);
        return email;
    }

    public static String getTransformedEmail2(String email) {
        System.out.println ("getTransformedEmail: BEFORE: email: " + email);
        email = email.replaceAll(PROHIBITED_STRING, REPLACE_SYMBOL);
        System.out.println ("getTransformedEmail: AFTER: email: " + email);
        return email;
    }
}
