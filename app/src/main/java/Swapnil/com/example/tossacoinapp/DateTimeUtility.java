package Swapnil.com.example.tossacoinapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtility {

    // Display current time in 12 hour format with AM/PM. Like this 02:32:45 PM.
    protected static String getTime(){
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
        return sdf.format(cal.getTime());
    }

    // Get Date Day/Mounth/Year. Like this 4/2/2018.
    protected static String getDate(){
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(cal.getTime());

    }



}
