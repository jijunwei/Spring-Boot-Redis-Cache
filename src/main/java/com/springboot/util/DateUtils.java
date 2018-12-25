package com.springboot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author
 * @time 2017-09-23 10:19
 */
public class DateUtils {

    public static final String DATE_LONGTIME12_PATTERN = "yyyy-MM-dd hh:mm:ss";

    public static final String DATE_LONGTIME24_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_LONGTIME24_HOUR_PATTERN = "yyyy-MM-dd HH:00:00";

    public static final String DATE_LONGTIME24_START_PATTERN = "yyyy-MM-dd 00:00:00";

    public static final String DATE_LONGTIME24_END_PATTERN = "yyyy-MM-dd 23:59:59";

    public static final String DATE_SHORTDATE_PATTERN = "yyyy-MM-dd";

    public static final String INTERNAL_DATE_SECOND = "second";

    public static final String INTERNAL_DATE_MINUTE = "minute";

    public static final String INTERNAL_DATE_HOUR = "hour";

    public static final String INTERNAL_DATE_DAY = "day";

    public static final String INTERNAL_DATE_WEEK = "week";

    public static final String INTERNAL_DATE_MONTH = "month";

    public static final String INTERNAL_DATE_YEAR = "year";

    public static int currentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }


    public static String getDateString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date);
        return dateString;
    }


    public static String getCurrentDateString(String pattern) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static Date getCurrentDate(String pattern) {
        String dateString = getCurrentDateString(pattern);
        Date date = getDate(dateString, pattern);
        return date;
    }

    public static Date getDate(String dateString, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            Date date = formatter.parse(dateString);
            return date;
        } catch (ParseException e) {
        }
        return null;
    }


    public static Date getMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        calendar.clear();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date monthFirstDay = calendar.getTime();
        return monthFirstDay;
    }


    public static Date getMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        calendar.clear();
        calendar.set(Calendar.MONTH, month + 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date monthLastDay = calendar.getTime();
        return monthLastDay;
    }

    public static long getDaysBetween(Date day1, Date day2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(day2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time1 - time2) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }


    public static long getSecondsBetween(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endDate);
        long time2 = cal.getTimeInMillis();
        return (time2 - time1) / 1000;
    }


    public static Date calculateDateByDay(Date sDate, int days) {
        Calendar sCalendar = Calendar.getInstance();
        sCalendar.setTime(sDate);
        sCalendar.add(Calendar.DATE, days);
        return sCalendar.getTime();
    }

    public static Date calculateDate(Date sDate, int interval, int timeUnit) {
        Calendar sCalendar = Calendar.getInstance();
        sCalendar.setTime(sDate);
        sCalendar.add(timeUnit, interval);
        return sCalendar.getTime();
    }

    public static Date calculateDateByMinute(Date sDate, int minute) {
        Calendar sCalendar = Calendar.getInstance();
        sCalendar.setTime(sDate);
        sCalendar.add(Calendar.MINUTE, minute);
        return sCalendar.getTime();
    }

    public static Date calculateDateBySecond(Date sDate, int second) {
        Calendar sCalendar = Calendar.getInstance();
        sCalendar.setTime(sDate);
        sCalendar.add(Calendar.SECOND, second);
        return sCalendar.getTime();
    }

    public static String getDaysBetweenToString(Date begin, Date end) {

        long between = (end.getTime() - begin.getTime()) / 1000;
        if (between <= 0) {
            between = 0 - between;
        }
        long day1 = between / (24 * 3600);
        long hour1 = between % (24 * 3600) / 3600;
        long minute1 = between % 3600 / 60;
        long second1 = between % 60 / 60;
        StringBuffer result = new StringBuffer();
        if (day1 > 0) {
            result.append(day1).append("天");
        }
        if (hour1 > 0) {
            result.append(hour1).append("小时");
        }
        if (minute1 > 0) {
            result.append(minute1).append("分");
        }
        if (second1 > 0) {
            result.append(second1).append("秒");
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public static long dateDiff(Date beginTime, Date endTime, String interval) {
        Calendar beginTimeCalendar = Calendar.getInstance();
        beginTimeCalendar.setTime(beginTime);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endTime);
        switch (interval) {
            case INTERNAL_DATE_SECOND:
                return (endTime.getTime() - beginTime.getTime()) / 1000;
            case INTERNAL_DATE_MINUTE:
                return (endTime.getTime() - beginTime.getTime()) / 60000;
            case INTERNAL_DATE_HOUR:
                return (endTime.getTime() - beginTime.getTime()) / 3600000;
            case INTERNAL_DATE_DAY:
                return (endTime.getTime() - beginTime.getTime()) / 86400000;
            case INTERNAL_DATE_WEEK:
                return (endTime.getTime() - beginTime.getTime()) / (86400000 * 7);
            case INTERNAL_DATE_MONTH:
                return (endCalendar.get(Calendar.MONTH) + 1)
                        + ((endCalendar.get(Calendar.YEAR) - beginTimeCalendar.get(Calendar.YEAR)) * 12)
                        - (beginTimeCalendar.get(Calendar.MONTH) + 1);
            case INTERNAL_DATE_YEAR:
                return endCalendar.get(Calendar.YEAR) - beginTimeCalendar.get(Calendar.YEAR);
            default:
                return 0;
        }
    }


    public static long getYears(Date beginTime, Date endTime) {
        Calendar beginTimeCalendar = Calendar.getInstance();
        beginTimeCalendar.setTime(beginTime);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endTime);
        int years = endCalendar.get(Calendar.YEAR) - beginTimeCalendar.get(Calendar.YEAR);
        beginTimeCalendar.add(Calendar.YEAR, years);
        if (beginTimeCalendar.after(endCalendar)) {
            years--;
        }
        return years;
    }

    public static boolean compareCurrentDateBetweenStartDateAndEndDate(Date currentDate, Date startDate, Date endDate) {
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        Calendar currentCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        endCalendar.setTime(endDate);
        currentCalendar.setTime(currentDate);
        if (currentCalendar.compareTo(startCalendar) >= 0 && currentCalendar.compareTo(endCalendar) <= 0) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean compareSameDate(Date firstDate, Date secondDate) {
        Calendar firstCalendar = Calendar.getInstance();
        Calendar secondCalendar = Calendar.getInstance();
        firstCalendar.setTime(firstDate);
        secondCalendar.setTime(secondDate);
        if (firstCalendar.get(Calendar.DATE) == secondCalendar.get(Calendar.DATE)) {
            return true;
        }
        return false;
    }

    public static boolean compareDate(Date firstDate, Date secondDate) {
        Calendar firstCalendar = Calendar.getInstance();
        Calendar secondCalendar = Calendar.getInstance();
        firstCalendar.setTime(firstDate);
        secondCalendar.setTime(secondDate);
        if (firstCalendar.compareTo(secondCalendar) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Date getDayStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getDayEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getHourStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getHourEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }


    public static Date getNextDayStart(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date time = null;
        try {
            time = format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar currentDate = Calendar.getInstance();
        if (time != null) {
            currentDate.setTime(time);
        }
        int day = currentDate.get(Calendar.DATE);
        currentDate.set(Calendar.DATE, day + 1);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        return (Date) currentDate.getTime().clone();
    }


    public static Date getCustomDate(Date date, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }


    public static Date getDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date);
        return getDate(dateString, pattern);
    }

    public static LocalDate getLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }


    public static LocalDateTime getLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }
}
