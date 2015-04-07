package com.mt.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.Calendar;

public class DateFormatUtil {


    /**
     * 日期转为指定格式的字符串
     *
     * @param date
     * @param pattern for example yyyy-MM-dd HH:mm:ss
     * @return 格式化的时间
     */
    public static String dateToStr(Date date, String pattern) {
        if (null == date) {

            date = new Date();
        }
        DateFormat format = new SimpleDateFormat(pattern);
        String str = format.format(date);
        return str;
    }

    /**
     * 日期转为指定格式的字符串
     *
     * @param date
     * @param pattern for example yyyy-MM-dd HH:mm:ss
     * @return 格式化的时间
     */
    public static String dateToStr(Date date, int second, String pattern) {
        if (null == date) {
            date = new Date();
        }
        date.setTime(date.getTime() + second * 1000);
        DateFormat format = new SimpleDateFormat(pattern);
        String str = format.format(date);
        return str;
    }


    /**
     * 日期转为指定格式的字符串
     *
     * @param minute
     * @param showTime for example yyyy-MM-dd HH:mm:ss
     * @return 格式化的时间
     */
    public static boolean dateCompare(String showTime, int minute) {
        Date curDate = new Date();
        Calendar curCalendar = Calendar.getInstance();
        curCalendar.setTime(curDate);

        curCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(showTime.substring(0, 2)));
        curCalendar.set(Calendar.MINUTE, Integer.parseInt(showTime.substring(2, 4)));
        curCalendar.set(Calendar.SECOND, 0);
        Date showDate = curCalendar.getTime();

        curCalendar.add(Calendar.MINUTE, minute);
        Date limitDate = curCalendar.getTime();
        return curDate.before(limitDate);

    }

    /**
     * 日期转为指定格式的字符串
     *
     * @param second
     * @param showTime for example yyyy-MM-dd HH:mm:ss
     * @return 格式化的时间
     */
    public static Date dateAddTime(Date showTime, int second) {

        Calendar curCalendar = Calendar.getInstance();
        curCalendar.setTime(showTime);

        curCalendar.add(Calendar.SECOND, second);
        return curCalendar.getTime();


    }

    /**
     *
     */
    /**
     * 输出格式: 2011-8-15
     */

    public static String getDefaultDate(Date d) {
        if (d == null) {
            d = new Date();
        }
        return DateFormat.getDateInstance(DateFormat.DEFAULT).format(d);

    }

    /**
     *
     */
    /**
     * 输出格式: 2011年8月15日 星期六
     */

    public static String getFullDate(Date d) {
        if (d == null) {
            d = new Date();
        }
        return DateFormat.getDateInstance(DateFormat.FULL).format(d);
    }

    /**
     *
     */
    /**
     * 输出格式: 2011-8-15
     */
    public static String getMediumDate(Date d) {
        if (d == null) {
            d = new Date();
        }
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
    }


    /**
     *
     * @param d
     * @return
     */
    /**
     * 输出格式: 11-8-15
     */

    public static String getShortDate(Date d) {
        if (d == null) {
            d = new Date();
        }
        return DateFormat.getDateInstance(DateFormat.SHORT).format(d);
    }


    /**
     * 字符串根据模型转换成Date
     *
     * @param time    for example 2011-11-11 11:11:11
     * @param pattern for example yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date dateParse(String time, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }


    public static String FeedTimeShow(String str) {
        java.util.regex.Pattern p_time;
        java.util.regex.Matcher m_time;
        String regEx_time = "timestamp=[0-9]{13}>";
        p_time = Pattern.compile(regEx_time, Pattern.CASE_INSENSITIVE);
        m_time = p_time.matcher(str);
        StringBuffer buffer = new StringBuffer();
        long feedTime = 0;
        while (m_time.find()) {
            //System.out.println("feedTime="+feedTime);
            feedTime = Long.parseLong(m_time.group().split("=")[1].substring(0, 13));
            //System.out.println("feedTime="+feedTime);
            m_time.appendReplacement(buffer, m_time.group() + longTimeConver(feedTime));
        }
        m_time.appendTail(buffer);
        // System.out.println(" buffer.toString()="+ buffer.toString());


        return buffer.toString();
    }

    public static long getOrginFeedId(String str) {
        java.util.regex.Pattern p_time;
        java.util.regex.Matcher m_time;
        String regEx_time = "feedid=\"[0-9]{1,}";
        p_time = Pattern.compile(regEx_time, Pattern.CASE_INSENSITIVE);
        m_time = p_time.matcher(str);
        StringBuffer buffer = new StringBuffer();
        long feedId = 0;
        byte i = 0;
        while (m_time.find()) {
            if (i == 1)
                return Long.parseLong(m_time.group().split("=")[1].trim().substring(1));
            else {
                i++;
            }

        }
        return 0;
    }

    public static String longTimeConver(long time) {
        Date curDate = new Date();
        Date feedDate = new Date(time);
        Calendar curCalendar = Calendar.getInstance();
        curCalendar.setTime(curDate);
        Calendar feedCalendar = Calendar.getInstance();
        feedCalendar.setTime(feedDate);

        long feedSpace = (long) (Math.ceil((curDate.getTime() - time) / 1000));
        //System.out.println("feedSpace="+feedSpace);
        if (feedSpace < 60 * 60 && feedSpace >= 60) {
            return (long) (Math.ceil((feedSpace) / 60)) + "分钟前";
        } else if (feedSpace < 60) {
            return "刚刚";
        } else if (feedSpace >= 60 * 60 * 48) {
            return dateToStr(feedDate, "MM月dd日 HH:mm");
        } else if (feedSpace >= 60 * 60 && feedSpace < 60 * 60 * 24
                && curCalendar.get(Calendar.DAY_OF_MONTH) == feedCalendar.get(Calendar.DAY_OF_MONTH)) {
            return "今天 " + dateToStr(feedDate, "HH:mm");
        } else if (feedSpace >= 60 * 60 && feedSpace < 60 * 60 * 48
                && curCalendar.get(Calendar.DAY_OF_MONTH) == feedCalendar.get(Calendar.DAY_OF_MONTH) + 1) {
            return "昨天 " + dateToStr(feedDate, "HH:mm");
        }
        return dateToStr(feedDate, "MM月dd日 HH:mm");

    }

    public static Date getOffsetDay(Date data, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + offset);
        return cal.getTime();
    }

    public static Date getOffsetDay(int offset) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + offset);
        return cal.getTime();
    }

    /**
     * 2009-01-23 18:12:30  转成  1月23日
     *
     * @param date
     * @return
     */
    public static String dateToCNString(String date) {
        String cnString = "";
        String str_1 = date.substring(5, 7);
        String str_2 = date.substring(8, 10);

        if (str_1.equals("01")) {
            cnString += "1月";
        } else if (str_1.equals("02")) {
            cnString += "2月";
        } else if (str_1.equals("03")) {
            cnString += "3月";
        } else if (str_1.equals("04")) {
            cnString += "4月";
        } else if (str_1.equals("05")) {
            cnString += "5月";
        } else if (str_1.equals("06")) {
            cnString += "6月";
        } else if (str_1.equals("07")) {
            cnString += "7月";
        } else if (str_1.equals("08")) {
            cnString += "8月";
        } else if (str_1.equals("09")) {
            cnString += "9月";
        } else {
            cnString += str_1 + "月";
        }

        if (str_2.equals("01")) {
            cnString += "1日";
        } else if (str_2.equals("02")) {
            cnString += "2日";
        } else if (str_2.equals("03")) {
            cnString += "3日";
        } else if (str_2.equals("04")) {
            cnString += "4日";
        } else if (str_2.equals("05")) {
            cnString += "5日";
        } else if (str_2.equals("06")) {
            cnString += "6日";
        } else if (str_2.equals("07")) {
            cnString += "7日";
        } else if (str_2.equals("08")) {
            cnString += "8日";
        } else if (str_2.equals("09")) {
            cnString += "9日";
        } else {
            cnString += str_2 + "日";
        }
        // 	System.out.println(cnString);

        return cnString;
    }

    public static Date stringFormatToDate(String str, String matter) {


        SimpleDateFormat format = new SimpleDateFormat(matter);
        try {
            Date date = format.parse(str);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public static boolean isDayOfWeek(int dayOff) {
        Calendar curCalendar = Calendar.getInstance();
        curCalendar.get(Calendar.DAY_OF_WEEK);
        return dayOff == curCalendar.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean isHourOfDay(int startHour, int endHour) {
        Calendar curCalendar = Calendar.getInstance();
        if (startHour <= curCalendar.get(Calendar.HOUR_OF_DAY) && endHour > curCalendar.get(Calendar.HOUR_OF_DAY))
            return true;
        return false;
    }

    /**
     * 将Date类型的时间或者字符串(yyyy-MM-dd HH:mm:ss)转换成Long型的数字
     * 如果str=null||str="",则将date转换成long型
     * 如果str!=null&&str.length()>0,则将str转换成long型
     *
     * @param date
     * @param str
     * @return 毫秒数
     */
    public static Long getLongByDateOrString(Date date, String str) {
        long lMofifyTime = 0;
        DateFormat gmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.ENGLISH);
        try {
            if (str == null || str.length() <= 0) {
                str = gmt.format(date);
            }
            lMofifyTime = gmt.parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lMofifyTime;
    }

    /**
     * 字符时间转换格式
     *
     * @param str
     * @param strType str的格式类型
     * @param type    转换后的格式类型
     * @return
     */
    public static String getDate(String str, String strType, String type) {
        String dateStr = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(strType);
            Date date = dateFormat.parse(str);
            SimpleDateFormat sdf = new SimpleDateFormat(type);
            dateStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }
    
    /**
     * 字符时间转换格式
     *
     * @param str
     * @param type
     * @return
     */
    public static String getDate(String str, String type) {
        String dateStr = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = dateFormat.parse(str);
            SimpleDateFormat sdf = new SimpleDateFormat(type);
            dateStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }


    /**
     * 获取星期数
     *
     * @param date
     * @return 1周日，2周一，3周二，4周三，5周四，6周五，7周六
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取星期数
     *
     * @param date
     * @return 1周日，2周一，3周二，4周三，5周四，6周五，7周六
     */
    public static int getDay(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date _date = dateFormat.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(_date);
            return cal.get(Calendar.DAY_OF_WEEK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取新的日期
     *
     * @param dateCount 增加的天数
     * @return
     */
    public static String getNextDate(int dateCount) {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, dateCount);
        d = ca.getTime();
        String backTime = format.format(d);
        return backTime;
    }


    /**
     * 获取某日期是当月的第几周
     *
     * @param str （2012-12-21）
     * @return
     */
    public static int getWeek(String str) {
        int weekFlag = 0;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("CTT"));
            calendar.setTime(formatter.parse(str));
            weekFlag = calendar.get(Calendar.WEEK_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weekFlag;
    }


    /**
     * 转换时间类型 xxxx年xx月xx日
     *
     * @param str
     * @param strType    str的类型格式
     * @param returnType 转换的类型格式
     * @return
     */
    public static String getNewDate(String str, String strType, String returnType) {
        str = getDate(str, strType, "yyyy-MM-dd HH:mm:ss");
        String newDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat(returnType);
        try {
            newDate = sdf.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }


    /**
     * 如何用一个固定日期加上一个变量天数得出新的日期
     *
     * @param now 当前日期
     * @param day 变量天数
     * @return
     */
    public static String addDate(String now, int day) {
        Calendar fromCal = Calendar.getInstance();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(now);
            fromCal.setTime(date);
            fromCal.add(Calendar.DATE, day);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateFormat.format(fromCal.getTime());
    }

    /**
     * 将毫秒数转换成时间
     *
     * @param time
     * @return
     */
    public static String getDate(long time) {
        Date d = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }

    /**
     * 日期转为指定格式的字符串
     *
     * @param weekTime 6|10:10
     * @param pattern  for example yyyy-MM-dd HH:mm:ss
     * @return 格式化的时间
     */
    public static String weekTimeFormat(String weekTime, boolean beforeFlag, String pattern) {


        String[] dateParams = weekTime.split("\\|");
        int dayOfWeek = Integer.parseInt(dateParams[0]);
        int hour = Integer.parseInt(dateParams[1].split(":")[0]);
        int minute = Integer.parseInt(dateParams[1].split(":")[1]);
        int second = Integer.parseInt(dateParams[1].split(":")[2]);
        Calendar curCalendar = Calendar.getInstance();
        int nowDayOfWeek = curCalendar.get(Calendar.DAY_OF_WEEK);

        int nowWeekOfMonth = curCalendar.get(Calendar.WEEK_OF_MONTH);
        if (beforeFlag) {

            if (nowDayOfWeek > dayOfWeek) {
                curCalendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                curCalendar.set(Calendar.HOUR_OF_DAY, hour);
                curCalendar.set(Calendar.MINUTE, minute);
                curCalendar.set(Calendar.SECOND, second);
            } else if (nowDayOfWeek == dayOfWeek) {
                if (dateToStr(curCalendar.getTime(), "HH:mm:ss").compareTo(dateParams[1]) >= 0) {
                    curCalendar.set(Calendar.HOUR_OF_DAY, hour);
                    curCalendar.set(Calendar.MINUTE, minute);
                    curCalendar.set(Calendar.SECOND, second);
                } else {
                    curCalendar.set(Calendar.WEEK_OF_MONTH, nowWeekOfMonth - 1);
                    curCalendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                    curCalendar.set(Calendar.HOUR_OF_DAY, hour);
                    curCalendar.set(Calendar.MINUTE, minute);
                    curCalendar.set(Calendar.SECOND, second);
                }
            } else {
                curCalendar.set(Calendar.WEEK_OF_MONTH, nowWeekOfMonth - 1);
                curCalendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                curCalendar.set(Calendar.HOUR_OF_DAY, hour);
                curCalendar.set(Calendar.MINUTE, minute);
                curCalendar.set(Calendar.SECOND, second);
            }
        } else {

            if (nowDayOfWeek > dayOfWeek) {

                curCalendar.set(Calendar.WEEK_OF_MONTH, nowWeekOfMonth + 1);
                curCalendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                curCalendar.set(Calendar.HOUR_OF_DAY, hour);
                curCalendar.set(Calendar.MINUTE, minute);
                curCalendar.set(Calendar.SECOND, second);
            } else if (nowDayOfWeek == dayOfWeek) {

                if (dateToStr(curCalendar.getTime(), "HH:mm:ss").compareTo(dateParams[1]) >= 0) {

                    curCalendar.set(Calendar.WEEK_OF_MONTH, nowWeekOfMonth + 1);
                    curCalendar.set(Calendar.HOUR_OF_DAY, hour);
                    curCalendar.set(Calendar.MINUTE, minute);
                    curCalendar.set(Calendar.SECOND, second);
                } else {

                    curCalendar.set(Calendar.HOUR_OF_DAY, hour);
                    curCalendar.set(Calendar.MINUTE, minute);
                    curCalendar.set(Calendar.SECOND, second);
                }
            } else {

                curCalendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                curCalendar.set(Calendar.HOUR_OF_DAY, hour);
                curCalendar.set(Calendar.MINUTE, minute);
                curCalendar.set(Calendar.SECOND, second);
            }
        }

        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(curCalendar.getTime());


    }

    public static int getWeekOfYear(String date) {
        try {
            Calendar cal = Calendar.getInstance();
            if (date == null) {
                cal.setTime(new Date());
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date _date = dateFormat.parse(date);
                cal.setTime(_date);
            }
            return cal.get(Calendar.WEEK_OF_YEAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static int getYear(String date) {
        try {
            Calendar cal = Calendar.getInstance();
            if (date == null) {
                cal.setTime(new Date());
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date _date = dateFormat.parse(date);
                cal.setTime(_date);
            }
            return cal.get(Calendar.YEAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }



}
