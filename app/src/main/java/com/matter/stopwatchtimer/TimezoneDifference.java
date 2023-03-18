package com.matter.stopwatchtimer;

import java.util.Calendar;
import java.util.TimeZone;

public class TimezoneDifference {
    public static String getDifference(String targetTimezoneID) {
        TimeZone targetTimezone = TimeZone.getTimeZone(targetTimezoneID);

        Calendar calendar = Calendar.getInstance();
        TimeZone currentTImezone = calendar.getTimeZone();

        int offsetInMillis = targetTimezone.getOffset(calendar.getTimeInMillis()) - currentTImezone.getOffset(calendar.getTimeInMillis());
        int offsetInHours = offsetInMillis / (60 * 60 * 1000);

        String identifier = "";
        if (offsetInHours < 0) identifier = Math.abs(offsetInHours) + " hours behind";
        else if (offsetInHours > 0) identifier = Math.abs(offsetInHours) + " hours ahead";
        else identifier = "same time zone";

        return identifier;
    }
}
