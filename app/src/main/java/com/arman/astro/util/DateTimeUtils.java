package com.arman.astro.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Arman on 5/18/2017.
 */

public class DateTimeUtils {

    /**
     * Format date to from existingFormat to newFormat
     */
    public static String formatDate(String existingFormat, String newFormat, String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(existingFormat, Locale.getDefault());
            return android.text.format.DateFormat.format(newFormat, sdf.parse(date)).toString();
        } catch (ParseException e) {
            Log.e("getRenewalDates", e.getMessage());
            return date;
        } catch (IllegalArgumentException e) {
            Log.e("getRenewalDates", e.getMessage());
            return date;
        }
    }

    /**
     * Format date to from dd/MM/yyyy to dd-MMM-yyyy
     */
    public static String formatDate(Date date, String format) {
        return android.text.format.DateFormat.format(format, date).toString();
    }

}
