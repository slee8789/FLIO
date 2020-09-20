package com.fund.flio.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class CommonUtils {

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static String getChatTime(String dateString) {
        SimpleDateFormat rawFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA);
        Date tempDate = null;
        try {
            tempDate = rawFormat.parse(dateString);
            SimpleDateFormat timeFormat = new SimpleDateFormat("a h:mm", Locale.KOREA);
            return timeFormat.format(tempDate);
        } catch (ParseException e) {
            return dateString;
        }
    }

    public static String getChatDate(String dateString) {
        SimpleDateFormat rawFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA);
        Date tempDate = null;
        try {
            tempDate = rawFormat.parse(dateString);
            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy년 M월 dd일", Locale.KOREA);
            return timeFormat.format(tempDate);
        } catch (ParseException e) {
            return dateString;
        }
    }

    public static long diffOfDate(String begin, String end) {
        if (end == null) return -1;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate = formatter.parse(begin);
            Date endDate = formatter.parse(end);
            long diff = endDate.getTime() - beginDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            return diffDays;
        } catch (ParseException e) {
            return -1;
        }

    }


}