package com.ticket.tiqeet.Utils;

import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by cted on 10/5/15.
 */
public class HelperMethods {

    public static boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
