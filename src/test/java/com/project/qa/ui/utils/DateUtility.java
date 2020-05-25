package com.project.qa.ui.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtility.class);

    /**
     * Method to get Today's date value
     * @param format
     * @return today's date
     */
    public static String getTodaysDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat();
        return dateFormat.format(new Date());
    }

    /**
     * Method to get relative date w.r.t given interval
     * @param format
     * @param dateValue
     * @param noOfDays
     * @return date after given time period
     */
    public static String getRelativeDate(String format, String dateValue, int noOfDays) {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            calendar.setTime(dateFormat.parse(dateValue));
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
        calendar.add(Calendar.DAY_OF_MONTH, noOfDays);
        return dateFormat.format(calendar.getTime());
    }
}
