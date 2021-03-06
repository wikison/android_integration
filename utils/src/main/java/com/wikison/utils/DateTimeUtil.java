package com.wikison.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 日期操作工具类，主要实现了日期的常用操作。
 * 在工具类中经常使用到工具类的格式化描述，这个主要是一个日期的操作类，所以日志格式主要使用 SimpleDateFormat的定义格式.
 * 格式的意义如下： 日期和时间模式 <br>
 * 日期和时间格式由日期和时间模式字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z'
 * 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。"''"
 * 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在分析时与输入字符串进行匹配。
 * 定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）： <br>
 * 模式字母通常是重复的，其数量确定其精确表示：
 */
public final class DateTimeUtil implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3098985139095632110L;
    /**
     * 根据时间戳获取描述性时间，如3分钟前，1天前
     *
     * @param timestamp
     * 时间戳 单位为毫秒
     * @return 时间字符串
     */
    private static final int YEAR = 365 * 24 * 60 * 60;
    private static final int MONTH = 30 * 24 * 60 * 60;
    private static final int WEEK = 7 * 24 * 60 * 60;
    private static final int THREE_DAY = 3 * 24 * 60 * 60;
    private static final int TWO_DAY = 2 * 24 * 60 * 60;
    private static final int DAY = 24 * 60 * 60;
    private static final int HOUR = 60 * 60;
    private static final int MINUTE = 60;

    private DateTimeUtil() {
    }

    public static void main(String[] args) {
        System.out.println(strPubDiffTime("2016-06-03 16:49:40"));
        System.out.println(strPubDiffTime("2016-06-03 16:42:00"));
        System.out.println(strPubDiffTime("2016-06-02 19:45:00"));
        System.out.println(strPubDiffTime("2016-05-03 14:45:00"));
        System.out.println(strPubDiffTime("2015-05-03 14:45:00"));
    }

    /**
     * 格式化日期显示格式yyyy-MM-dd
     *
     * @param sdate 原始日期格式
     * @return yyyy-MM-dd格式化后的日期显示
     */
    public static String dateFormat(String sdate) {
        return dateFormat(sdate, "yyyy-MM-dd");
    }

    /**
     * 格式化日期显示格式
     *
     * @param sdate  原始日期格式
     * @param format 格式化后日期格式
     * @return 格式化后的日期显示
     */
    public static String dateFormat(String sdate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        java.sql.Date date = java.sql.Date.valueOf(sdate);

        return formatter.format(date);
    }

    /**
     * 求两个日期相差天数
     *
     * @param sd 起始日期，格式yyyy-MM-dd
     * @param ed 终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public static long getIntervalDays(String sd, String ed) {
        return ((java.sql.Date.valueOf(ed)).getTime() - (java.sql.Date
                .valueOf(sd)).getTime())
                / (3600 * 24 * 1000);
    }

    /**
     * 起始年月yyyy-MM与终止月yyyy-MM之间跨度的月数
     *
     * @return int
     */
    public static int getInterval(String beginMonth, String endMonth) {
        int intBeginYear = Integer.parseInt(beginMonth.substring(0, 4));
        int intBeginMonth = Integer.parseInt(beginMonth.substring(beginMonth
                .indexOf("-") + 1));
        int intEndYear = Integer.parseInt(endMonth.substring(0, 4));
        int intEndMonth = Integer.parseInt(endMonth.substring(endMonth
                .indexOf("-") + 1));

        return ((intEndYear - intBeginYear) * 12)
                + (intEndMonth - intBeginMonth) + 1;
    }

    public static Date getDate(String sDate, String dateFormat) {
        SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
        ParsePosition pos = new ParsePosition(0);

        return fmt.parse(sDate, pos);
    }

    /**
     * 取得当前日期的年份，以yyyy格式返回.
     *
     * @return 当年 yyyy
     */
    public static String getCurrentYear() {
        return getFormatCurrentTime("yyyy");
    }

    /**
     * 自动返回上一年。例如当前年份是2007年，那么就自动返回2006
     *
     * @return 返回结果的格式为 yyyy
     */
    public static String getBeforeYear() {
        String currentYear = getFormatCurrentTime("yyyy");
        int beforeYear = Integer.parseInt(currentYear) - 1;
        return "" + beforeYear;
    }

    /**
     * 取得当前日期的月份，以MM格式返回.
     *
     * @return 当前月份 MM
     */
    public static String getCurrentMonth() {
        return getFormatCurrentTime("MM");
    }

    /**
     * 取得当前日期的天数，以格式"dd"返回.
     *
     * @return 当前月中的某天dd
     */
    public static String getCurrentDay() {
        return getFormatCurrentTime("dd");
    }

    /**
     * 返回当前时间字符串。
     * 格式：yyyy-MM-dd
     *
     * @return String 指定格式的日期字符串.
     */
    public static String getCurrentDate() {
        return getFormatDateTime(new Date(), "yyyy-MM-dd");
    }

    /**
     * 返回给定时间字符串。
     * 格式：yyyy-MM-dd
     *
     * @param date 日期
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatDate(Date date) {
        return getFormatDateTime(date, "yyyy-MM-dd");
    }

    /**
     * 根据制定的格式，返回日期字符串。例如2007-05-05
     *
     * @param format "yyyy-MM-dd" 或者 "yyyy/MM/dd"
     * @return 指定格式的日期字符串。
     */
    public static String getFormatDate(String format) {
        return getFormatDateTime(new Date(), format);
    }

    /**
     * 返回当前时间字符串。
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return String 指定格式的日期字符串.
     */
    public static String getCurrentTime() {
        return getFormatDateTime(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentTime2() {
        return getFormatDateTime(new Date(), "HH:mm:ss");
    }

    /**
     * 返回给定时间字符串。
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatTime(Date date) {
        return getFormatDateTime(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据给定的格式，返回时间字符串。
     * 格式参照类描绘中说明.
     *
     * @param format 日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatCurrentTime(String format) {
        return getFormatDateTime(new Date(), format);
    }

    /**
     * 根据给定的格式与时间(Date类型的)，返回时间字符串<br>
     *
     * @param date   指定的日期
     * @param format 日期格式字符串
     * @return String 指定格式的日期字符串.
     */
    public static String getFormatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 取得指定年月日的日期对象.
     *
     * @param year  年
     * @param month 月注意是从1到12
     * @param day   日
     * @return 一个java.util.Date()类型的对象
     */
    public static Date getDateObj(int year, int month, int day) {
        Calendar c = new GregorianCalendar();
        c.set(year, month - 1, day);
        return c.getTime();
    }

    /**
     * 取得指定分隔符分割的年月日的日期对象.
     *
     * @param args  格式为"yyyy-MM-dd"
     * @param split 时间格式的间隔符，例如“-”，“/”
     * @return 一个java.util.Date()类型的对象
     */
    public static Date getDateObj(String args, String split) {
        String[] temp = args.split(split);
        int year = new Integer(temp[0]).intValue();
        int month = new Integer(temp[1]).intValue();
        int day = new Integer(temp[2]).intValue();
        return getDateObj(year, month, day);
    }

    /**
     * 取得给定字符串描述的日期对象，描述模式采用pattern指定的格式.
     *
     * @param dateStr 日期描述
     * @param pattern 日期模式
     * @return 给定字符串描述的日期对象。
     */
    public static Date getDateFromString(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date resDate = null;
        try {
            resDate = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resDate;
    }

    /**
     * 取得当前Date对象.
     *
     * @return Date 当前Date对象.
     */
    public static Date getDateObj() {
        Calendar c = new GregorianCalendar();
        return c.getTime();
    }

    /**
     * @return 当前月份有多少天；
     */
    public static int getDaysOfCurMonth() {
        int curyear = new Integer(getCurrentYear()).intValue(); // 当前年份
        int curMonth = new Integer(getCurrentMonth()).intValue();// 当前月份
        int mArray[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31};
        // 判断闰年的情况 ，2月份有29天；
        if ((curyear % 400 == 0)
                || ((curyear % 100 != 0) && (curyear % 4 == 0))) {
            mArray[1] = 29;
        }
        return mArray[curMonth - 1];
        // 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
        // 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
    }

    /**
     * 根据指定的年月 返回指定月份（yyyy-MM）有多少天。
     *
     * @param time yyyy-MM
     * @return 天数，指定月份的天数。
     */
    public static int getDaysOfCurMonth(final String time) {
        String[] timeArray = time.split("-");
        int curyear = new Integer(timeArray[0]).intValue(); // 当前年份
        int curMonth = new Integer(timeArray[1]).intValue();// 当前月份
        int mArray[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31};
        // 判断闰年的情况 ，2月份有29天；
        if ((curyear % 400 == 0)
                || ((curyear % 100 != 0) && (curyear % 4 == 0))) {
            mArray[1] = 29;
        }
        if (curMonth == 12) {
            return mArray[0];
        }
        return mArray[curMonth - 1];
        // 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
        // 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
    }

    /**
     * 返回指定为年度为year月度为month的月份内，第weekOfMonth个星期的第dayOfWeek天。<br>
     * 00 00 00 01 02 03 04 <br>
     * 05 06 07 08 09 10 11<br>
     * 12 13 14 15 16 17 18<br>
     * 19 20 21 22 23 24 25<br>
     * 26 27 28 29 30 31 <br>
     * 2006年的第一个周的1到7天为：05 06 07 01 02 03 04 <br>
     * 2006年的第二个周的1到7天为：12 13 14 08 09 10 11 <br>
     * 2006年的第三个周的1到7天为：19 20 21 15 16 17 18 <br>
     * 2006年的第四个周的1到7天为：26 27 28 22 23 24 25 <br>
     * 2006年的第五个周的1到7天为：02 03 04 29 30 31 01 。本月没有就自动转到下个月了。
     *
     * @param year        形式为yyyy <br>
     * @param month       形式为MM,参数值在[1-12]。<br>
     * @param weekOfMonth 在[1-6],因为一个月最多有6个周。<br>
     * @param dayOfWeek   数字在1到7之间，包括1和7。1表示星期天，7表示星期六<br>
     *                    -6为星期日-1为星期五，0为星期六
     */
    public static int getDayofWeekInMonth(String year, String month,
                                          String weekOfMonth, String dayOfWeek) {
        Calendar cal = new GregorianCalendar();
        // 在具有默认语言环境的默认时区内使用当前时间构造一个默认的 GregorianCalendar。
        int y = new Integer(year).intValue();
        int m = new Integer(month).intValue();
        cal.clear();// 不保留以前的设置
        cal.set(y, m - 1, 1);// 将日期设置为本月的第一天。
        cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, new Integer(weekOfMonth)
                .intValue());
        cal.set(Calendar.DAY_OF_WEEK, new Integer(dayOfWeek).intValue());
        // System.out.print(cal.get(Calendar.MONTH)+" ");
        // System.out.print("当"+cal.get(Calendar.WEEK_OF_MONTH)+"\t");
        // WEEK_OF_MONTH表示当天在本月的第几个周。不管1号是星期几，都表示在本月的第一个周
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据指定的年月日小时分秒，返回一个java.Util.Date对象。
     *
     * @param year      年
     * @param month     月 0-11
     * @param date      日
     * @param hourOfDay 小时 0-23
     * @param minute    分 0-59
     * @param second    秒 0-59
     */
    public static Date getDate(int year, int month, int date, int hourOfDay,
                               int minute, int second) {
        Calendar cal = new GregorianCalendar();
        cal.set(year, month, date, hourOfDay, minute, second);
        return cal.getTime();
    }

    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param year
     * @param month month是从1开始的12结束
     * @param day
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(String year, String month, String day) {
        Calendar cal = new GregorianCalendar(new Integer(year).intValue(),
                new Integer(month).intValue() - 1, new Integer(day).intValue());
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param date "yyyy/MM/dd",或者"yyyy-MM-dd"
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(String date) {
        String[] temp = null;
        if (date.indexOf("/") > 0) {
            temp = date.split("/");
        }
        if (date.indexOf("-") > 0) {
            temp = date.split("-");
        }
        return getDayOfWeek(temp[0], temp[1], temp[2]);
    }

    /**
     * 返回当前日期是星期几。例如：星期日、星期一、星期六等等。
     *
     * @param date 格式为 yyyy/MM/dd 或者 yyyy-MM-dd
     * @return 返回当前日期是星期几
     */
    public static String getChinaDayofWeek(String date) {
        String[] weeks = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int week = getDayOfWeek(date);
        return weeks[week - 1];
    }

    public static String getWeekDayOfWeek(String date) {
        String[] weeks = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        int week = getDayOfWeek(date);
        return weeks[week - 1];
    }

    /**
     * 返回当前日期是星期几。例如：星期日、星期一、星期六等等。
     *
     * @param date 格式为 yyyy/MM/dd 或者 yyyy-MM-dd
     * @return 返回当前日期是星期几
     */
    public static String getChinaDayOfWeek(Date date) {
        String[] weeks = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int week = getDayOfWeek(date);
        return weeks[week - 1];
    }

    /**
     * 返回当前日期是星期几。例如：星期日、星期一、星期六等等。
     *
     * @param date 格式为 yyyy/MM/dd 或者 yyyy-MM-dd
     * @return 返回当前日期是星期几
     */
    public static String getWeekDayOfWeek(Date date) {
        String[] weeks = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        int week = getDayOfWeek(date);
        return weeks[week - 1];
    }


    /**
     * 如果选择日期是今天显示当天
     */
    public static String getWeekDayOfWeekisToday(Date date) {
        String[] weeks = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        int week = getDayOfWeek(date);
        String sday = getFormatDateTime(date, "dd");
        String cday = getFormatDateTime(new Date(), "dd");
        if ((sday.equals(cday))) {
            return "今天";
        } else {
            return weeks[week - 1];
        }

    }


    /**
     * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
     *
     * @param date
     * @return 返回一个代表当期日期是星期几的数字。1表示星期天、2表示星期一、7表示星期六。
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 返回制定日期所在的周是一年中的第几个周。<br>
     * created by wangmj at 20060324.<br>
     *
     * @param year
     * @param month 范围1-12<br>
     * @param day
     * @return int
     */
    public static int getWeekOfYear(String year, String month, String day) {
        Calendar cal = new GregorianCalendar();
        cal.clear();
        cal.set(new Integer(year).intValue(),
                new Integer(month).intValue() - 1, new Integer(day).intValue());
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param date   给定的日期对象
     * @param amount 需要添加的天数，如果是向前的天数，使用负数就可以.
     * @return Date 加上一定天数以后的Date对象.
     */
    public static Date getDateAdd(Date date, int amount) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return cal.getTime();
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param date   给定的日期对象
     * @param amount 需要添加的天数，如果是向前的天数，使用负数就可以.
     * @param format 输出格式.
     * @return Date 加上一定天数以后的Date对象.
     */
    public static String getFormatDateAdd(Date date, int amount, String format) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return getFormatDateTime(cal.getTime(), format);
    }

    /**
     * 获得当前日期固定间隔天数的日期，如前60天dateAdd(-60)
     *
     * @param amount 距今天的间隔日期长度，向前为负，向后为正
     * @param format 输出日期的格式.
     * @return java.lang.String 按照格式输出的间隔的日期字符串.
     */
    public static String getFormatCurrentAdd(int amount, String format) {

        Date d = getDateAdd(new Date(), amount);

        return getFormatDateTime(d, format);
    }

    /**
     * 取得给定格式的昨天的日期输出
     *
     * @param format 日期输出的格式
     * @return String 给定格式的日期字符串.
     */
    public static String getFormatYestoday(String format) {
        return getFormatCurrentAdd(-1, format);
    }

    /**
     * 返回指定日期的前一天。
     *
     * @param sourceDate
     * @param format     yyyy MM  dd  hh mm  ss
     * @return 返回日期字符串，形式和formcat一致。
     */
    public static String getYestoday(String sourceDate, String format) {
        return getFormatDateAdd(getDateFromString(sourceDate, format), -1,
                format);
    }

    /**
     * 返回明天的日期
     *
     * @param format
     * @return 返回日期字符串，形式和formcat一致。
     */
    public static String getFormatTomorrow(String format) {
        return getFormatCurrentAdd(1, format);
    }

    /**
     * 返回指定日期的后一天。
     *
     * @param sourceDate
     * @param format
     * @return 返回日期字符串，形式和formcat一致。
     */
    public static String getFormatDateTommorrow(String sourceDate, String format) {
        return getFormatDateAdd(getDateFromString(sourceDate, format), 1,
                format);
    }

    /**
     * 根据主机的默认 TimeZone，来获得指定形式的时间字符串。
     *
     * @param dateFormat
     * @return 返回日期字符串，形式和formcat一致。
     */
    public static String getCurrentDateString(String dateFormat) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(cal.getTime());
    }

    /**
     * @return String
     * @deprecated 不鼓励使用。
     * 返回当前时间串 格式:yyMMddhhmmss,在上传附件时使用
     */
    public static String getCurDate() {
        GregorianCalendar gcDate = new GregorianCalendar();
        int year = gcDate.get(GregorianCalendar.YEAR);
        int month = gcDate.get(GregorianCalendar.MONTH) + 1;
        int day = gcDate.get(GregorianCalendar.DAY_OF_MONTH);
        int hour = gcDate.get(GregorianCalendar.HOUR_OF_DAY);
        int minute = gcDate.get(GregorianCalendar.MINUTE);
        int sen = gcDate.get(GregorianCalendar.SECOND);
        String y;
        String m;
        String d;
        String h;
        String n;
        String s;
        y = new Integer(year).toString();

        if (month < 10) {
            m = "0" + new Integer(month).toString();
        } else {
            m = new Integer(month).toString();
        }

        if (day < 10) {
            d = "0" + new Integer(day).toString();
        } else {
            d = new Integer(day).toString();
        }

        if (hour < 10) {
            h = "0" + new Integer(hour).toString();
        } else {
            h = new Integer(hour).toString();
        }

        if (minute < 10) {
            n = "0" + new Integer(minute).toString();
        } else {
            n = new Integer(minute).toString();
        }

        if (sen < 10) {
            s = "0" + new Integer(sen).toString();
        } else {
            s = new Integer(sen).toString();
        }

        return "" + y + m + d + h + n + s;
    }

    /**
     * 根据给定的格式，返回时间字符串。 和getFormatDate(String format)相似。
     *
     * @param format yyyy  MM dd  hh mm ss
     * @return 返回一个时间字符串
     */
    public static String getCurTimeByFormat(String format) {
        Date newdate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(newdate);
    }

    /**
     * 获取两个时间串时间的差值，单位为秒
     *
     * @param startTime 开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间 yyyy-MM-dd HH:mm:ss
     * @return 两个时间的差值(秒)
     */
    public static long getDiff(String startTime, String endTime) {
        long diff = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startDate = ft.parse(startTime);
            Date endDate = ft.parse(endTime);
            diff = startDate.getTime() - endDate.getTime();
            diff = diff / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    /**
     * 获取小时/分钟/秒
     *
     * @param second 秒
     * @return 包含小时、分钟、秒的时间字符串，例如3小时23分钟13秒。
     */
    public static String getHour(long second) {
        long hour = second / 60 / 60;
        long minute = (second - hour * 60 * 60) / 60;
        long sec = (second - hour * 60 * 60) - minute * 60;

        return hour + "小时" + minute + "分钟" + sec + "秒";

    }

    /**
     * 返回指定时间字符串。
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return String 指定格式的日期字符串.
     */
    public static String getDateTime(long microsecond) {
        return getFormatDateTime(new Date(microsecond), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回当前时间加实数小时后的日期时间。
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return Float 加几实数小时.
     */
    public static String getDateByAddFltHour(float flt) {
        int addMinute = (int) (flt * 60);
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(GregorianCalendar.MINUTE, addMinute);
        return getFormatDateTime(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回指定时间加指定分钟数后的日期时间。
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return 时间.
     */
    public static String getDateByAddMinute(String datetime, int minute) {
        String returnTime = null;
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = ft.parse(datetime);
            cal.setTime(date);
            cal.add(GregorianCalendar.MINUTE, minute);
            returnTime = getFormatDateTime(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnTime;

    }

    /**
     * 获取两个时间串时间的差值，单位为小时
     *
     * @param startTime 开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间 yyyy-MM-dd HH:mm:ss
     * @return 两个时间的差值(秒)
     */
    public static int getDiffHour(String startTime, String endTime) {
        long diff = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startDate = ft.parse(startTime);
            Date endDate = ft.parse(endTime);
            diff = startDate.getTime() - endDate.getTime();
            diff = diff / (1000 * 60 * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Long(diff).intValue();
    }

    /**
     * 返回年份的下拉框。
     *
     * @param selectName 下拉框名称
     * @param value      当前下拉框的值
     * @param startYear  开始年份
     * @param endYear    结束年份
     * @return 年份下拉框的html
     */
    public static String getYearSelect(String selectName, String value,
                                       int startYear, int endYear) {
        int start = startYear;
        int end = endYear;
        if (startYear > endYear) {
            start = endYear;
            end = startYear;
        }
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\">");
        for (int i = start; i <= end; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 返回年份的下拉框。
     *
     * @param selectName 下拉框名称
     * @param value      当前下拉框的值
     * @param startYear  开始年份
     * @param endYear    结束年份
     *                   例如开始年份为2001结束年份为2005那么下拉框就有五个值。（2001、2002、2003、2004、2005）。
     * @return 返回年份的下拉框的html。
     */
    public static String getYearSelect(String selectName, String value,
                                       int startYear, int endYear, boolean hasBlank) {
        int start = startYear;
        int end = endYear;
        if (startYear > endYear) {
            start = endYear;
            end = startYear;
        }
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\">");
        if (hasBlank) {
            sb.append("<option value=\"\"></option>");
        }
        for (int i = start; i <= end; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 返回年份的下拉框。
     *
     * @param selectName 下拉框名称
     * @param value      当前下拉框的值
     * @param startYear  开始年份
     * @param endYear    结束年份
     * @param js         这里的js为js字符串。例如 " onchange=\"changeYear()\" "
     *                   ,这样任何js的方法就可以在jsp页面中编写，方便引入。
     * @return 返回年份的下拉框。
     */
    public static String getYearSelect(String selectName, String value,
                                       int startYear, int endYear, boolean hasBlank, String js) {
        int start = startYear;
        int end = endYear;
        if (startYear > endYear) {
            start = endYear;
            end = startYear;
        }
        StringBuffer sb = new StringBuffer("");

        sb.append("<select name=\"" + selectName + "\" " + js + ">");
        if (hasBlank) {
            sb.append("<option value=\"\"></option>");
        }
        for (int i = start; i <= end; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 返回年份的下拉框。
     *
     * @param selectName 下拉框名称
     * @param value      当前下拉框的值
     * @param startYear  开始年份
     * @param endYear    结束年份
     * @param js         这里的js为js字符串。例如 " onchange=\"changeYear()\" "
     *                   ,这样任何js的方法就可以在jsp页面中编写，方便引入。
     * @return 返回年份的下拉框。
     */
    public static String getYearSelect(String selectName, String value,
                                       int startYear, int endYear, String js) {
        int start = startYear;
        int end = endYear;
        if (startYear > endYear) {
            start = endYear;
            end = startYear;
        }
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\" " + js + ">");
        for (int i = start; i <= end; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 获取月份的下拉框
     *
     * @param selectName
     * @param value
     * @param hasBlank
     * @return 返回月份的下拉框。
     */
    public static String getMonthSelect(String selectName, String value,
                                        boolean hasBlank) {
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\">");
        if (hasBlank) {
            sb.append("<option value=\"\"></option>");
        }
        for (int i = 1; i <= 12; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 获取月份的下拉框
     *
     * @param selectName
     * @param value
     * @param hasBlank
     * @param js
     * @return 返回月份的下拉框。
     */
    public static String getMonthSelect(String selectName, String value,
                                        boolean hasBlank, String js) {
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\" " + js + ">");
        if (hasBlank) {
            sb.append("<option value=\"\"></option>");
        }
        for (int i = 1; i <= 12; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 获取天的下拉框，默认的为1-31。
     * 注意：此方法不能够和月份下拉框进行联动。
     *
     * @param selectName
     * @param value
     * @param hasBlank
     * @return 获得天的下拉框
     */
    public static String getDaySelect(String selectName, String value,
                                      boolean hasBlank) {
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\">");
        if (hasBlank) {
            sb.append("<option value=\"\"></option>");
        }
        for (int i = 1; i <= 31; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 获取天的下拉框，默认的为1-31
     *
     * @param selectName
     * @param value
     * @param hasBlank
     * @param js
     * @return 获取天的下拉框
     */
    public static String getDaySelect(String selectName, String value,
                                      boolean hasBlank, String js) {
        StringBuffer sb = new StringBuffer("");
        sb.append("<select name=\"" + selectName + "\" " + js + ">");
        if (hasBlank) {
            sb.append("<option value=\"\"></option>");
        }
        for (int i = 1; i <= 31; i++) {
            if (!value.trim().equals("") && i == Integer.parseInt(value)) {
                sb.append("<option value=\"" + i + "\" selected>" + i
                        + "</option>");
            } else {
                sb.append("<option value=\"" + i + "\">" + i + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    /**
     * 计算两天之间有多少个周末（这个周末，指星期六和星期天，一个周末返回结果为2，两个为4，以此类推。）
     * （此方法目前用于统计司机用车记录。）
     *
     * @param startDate 开始日期 ，格式"yyyy/MM/dd"
     * @param endDate   结束日期 ，格式"yyyy/MM/dd"
     * @return int
     */
    public static int countWeekend(String startDate, String endDate) {
        int result = 0;
        Date sdate = null;
        Date edate = null;
        sdate = getDateObj(startDate, "/"); // 开始日期
        edate = getDateObj(endDate, "/");// 结束日期
        // 首先计算出都有那些日期，然后找出星期六星期天的日期
        int sumDays = Math.abs(getDiffDays(startDate, endDate));
        int dayOfWeek = 0;
        for (int i = 0; i <= sumDays; i++) {
            dayOfWeek = getDayOfWeek(getDateAdd(sdate, i)); // 计算每过一天的日期
            if (dayOfWeek == 1 || dayOfWeek == 7) { // 1 星期天 7星期六
                result++;
            }
        }
        return result;
    }

    /**
     * 返回两个日期之间相差多少天。
     *
     * @param startDate 格式"yyyy/MM/dd"
     * @param endDate   格式"yyyy/MM/dd"
     * @return 整数。
     */
    public static int getDiffDays(String startDate, String endDate) {
        long diff = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date sDate = ft.parse(startDate + " 00:00:00");
            Date eDate = ft.parse(endDate + " 00:00:00");
            diff = eDate.getTime() - sDate.getTime();
            diff = diff / 86400000;// 1000*60*60*24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) diff;

    }


    /**
     * 返回两个日期之间相差多少天。
     *
     * @param startDate 格式"yyyy/MM/dd"
     * @param endDate   格式"yyyy/MM/dd"
     * @return 整数。
     */
    public static int getDiffDays2(String startDate, String endDate) {
        long diff = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date sDate = ft.parse(startDate + " 00:00:00");
            Date eDate = ft.parse(endDate + " 00:00:00");
            diff = eDate.getTime() - sDate.getTime();
            diff = diff / 86400000;// 1000*60*60*24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) diff;

    }


    /**
     * 返回两个日期之间的详细日期数组(包括开始日期和结束日期)。
     * 例如：2007/07/01 到2007/07/03 ,那么返回数组
     * {"2007/07/01","2007/07/02","2007/07/03"}
     *
     * @param startDate 格式"yyyy/MM/dd"
     * @param endDate   格式"yyyy/MM/dd"
     * @return 返回一个字符串数组对象
     */
    public static String[] getArrayDiffDays(String startDate, String endDate) {
        int LEN = 0; //用来计算两天之间总共有多少天
        //如果结束日期和开始日期相同
        if (startDate.equals(endDate)) {
            return new String[]{startDate};
        }
        Date sdate = null;
        sdate = getDateObj(startDate, "/"); // 开始日期
        LEN = getDiffDays(startDate, endDate);
        String[] dateResult = new String[LEN + 1];
        dateResult[0] = startDate;
        for (int i = 1; i < LEN + 1; i++) {
            dateResult[i] = getFormatDateTime(getDateAdd(sdate, i), "yyyy/MM/dd");
        }

        return dateResult;
    }

    /**
     * 根据当前时间返回发布时间信息
     * 刚刚/1~59分钟前/1~24小时前/昨天 23:59/06-01/2015-06-03
     *
     * @param strPubTime 格式"yyyy-MM-dd HH:mm:SS"
     * @return 返回一个字符串数组对象
     */
    public static String strPubDiffTime(String strPubTime) {
        if (null == strPubTime) return "";
        String result = "";
        String strCurTime = getCurrentTime();
        Date dtPubTime = getDate(strPubTime, "yyyy-MM-dd HH:mm:SS");
        long lSeconds = getDiff(strCurTime, strPubTime);
        if (lSeconds < 60) {
            result = "刚刚";
        } else {
            long lMinutes = lSeconds / 60;
            if (lMinutes < 60) {
                result = lMinutes + "分钟前";
            } else {
                long lHours = lMinutes / 60;
                if (lHours < 24 - Integer.valueOf(getFormatDateTime(dtPubTime, "HH"))) {
                    result = lHours + "小时前";
                } else {
                    long lDay = lHours / 24;
                    if (lDay < 1) {
                        result = "昨天" + getFormatDateTime(dtPubTime, "HH:mm");
                    } else {
                        if (getFormatDateTime(dtPubTime, "yyyy").equals(getFormatDateTime(new Date(), "yyyy"))) {
                            result = getFormatDateTime(dtPubTime, "MM-dd");
                        } else {
                            result = getFormatDateTime(dtPubTime, "yyyy-MM-dd");
                        }
                    }
                }
            }
        }
        return result;
    }

    public static String strPubEndDiffTime(String strEndTime) {
        if (null == strEndTime) return "";
        String result = "";
        String strCurTime = getCurrentTime();
        Date dtEndTime = getDate(strEndTime, "yyyy-MM-dd HH:mm:SS");
        long lSeconds = getDiff(strEndTime, strCurTime);
        if (lSeconds > 0) {
            if (lSeconds < 60) {
                result = "即将";
            } else {
                long lMinutes = lSeconds / 60;
                if (lMinutes < 60) {
                    result = lMinutes + "分钟后";
                } else {
                    long lHours = lMinutes / 60;
                    if (lHours < 24 - Integer.valueOf(getFormatDateTime(new Date(), "HH"))) {
                        result = lHours + "小时后";
                    } else {
                        long lDay = lHours / 24;
                        if (lDay < 1) {
                            result = "明天" + getFormatDateTime(dtEndTime, "HH:mm");
                        } else {
                            if (getFormatDateTime(dtEndTime, "yyyy").equals(getFormatDateTime(new Date(), "yyyy"))) {
                                result = getFormatDateTime(dtEndTime, "MM-dd");
                            } else {
                                result = getFormatDateTime(dtEndTime, "yyyy-MM-dd");
                            }
                        }
                    }
                }
            }
        } else {
            lSeconds = getDiff(strCurTime, strEndTime);
            if (lSeconds < 60) {
                result = "刚刚";
            } else {
                long lMinutes = lSeconds / 60;
                if (lMinutes < 60) {
                    result = lMinutes + "分钟前";
                } else {
                    long lHours = lMinutes / 60;
                    if (lHours < 24 - Integer.valueOf(getFormatDateTime(new Date(), "HH"))) {
                        result = lHours + "小时前";
                    } else {
                        long lDay = lHours / 24;
                        if (lDay < 1) {
                            result = "昨天" + getFormatDateTime(dtEndTime, "HH:mm");
                        } else {
                            if (getFormatDateTime(dtEndTime, "yyyy").equals(getFormatDateTime(new Date(), "yyyy"))) {
                                result = getFormatDateTime(dtEndTime, "MM-dd");
                            } else {
                                result = getFormatDateTime(dtEndTime, "yyyy-MM-dd");
                            }
                        }
                    }
                }
            }

        }
        return result;
    }

    public static String getDescriptionTimeFromTimestamp(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long timeGap = (currentTime - timestamp) / 1000;// 与现在时间相差秒数
        // System.out.println("timeGap: " + timeGap);
        String timeStr = null;
        if (timeGap > YEAR) {
            timeStr = timeGap / YEAR + "年前";
        } else if (timeGap > MONTH) {
            timeStr = timeGap / MONTH + "个月前";
        } else if (timeGap > DAY) {// 1天以上
            timeStr = timeGap / DAY + "天前";
        } else if (timeGap > HOUR) {// 1小时-24小时
            timeStr = timeGap / HOUR + "小时前";
        } else if (timeGap > MINUTE) {// 1分钟-59分钟
            timeStr = timeGap / MINUTE + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚" + timeGap + "秒";
        }
        return timeStr;
    }

    public static String getTimeGapInfo(String strTime) {
        long currentTime = System.currentTimeMillis();
        String result = "";
        if (StringUtils.isBlank(strTime))
            return "";

        Date taskTime = getDate(strTime, "yyyy-MM-dd HH:mm:SS");
        long timeGap = (currentTime - taskTime.getTime()) / 1000;
        if (timeGap > 0) {
            if (timeGap > YEAR) {
                result = timeGap / YEAR + "年前";
            } else if (timeGap > MONTH) {
                result = timeGap / MONTH + "个月前";
            } else if (timeGap > DAY) {// 1天以上
                result = timeGap / DAY + "天前";
            } else if (timeGap > HOUR) {// 1小时-24小时
                result = timeGap / HOUR + "小时前";
            } else if (timeGap > MINUTE) {// 1分钟-59分钟
                result = timeGap / MINUTE + "分钟前";
            } else {// 1秒钟-59秒钟
                result = "刚刚";
            }
        } else {
            timeGap = (taskTime.getTime() - currentTime) / 1000;
            if (timeGap > YEAR) {
                result = timeGap / YEAR + "年后";
            } else if (timeGap > MONTH) {
                result = timeGap / MONTH + "个月后";
            } else if (timeGap > DAY) {// 1天以上
                result = timeGap / DAY + "天后";
            } else if (timeGap > HOUR) {// 1小时-24小时
                result = timeGap / HOUR + "小时后";
            } else if (timeGap > MINUTE) {// 1分钟-59分钟
                result = timeGap / MINUTE + "分钟后";
            } else {// 1秒钟-59秒钟
                result = "即将";
            }
        }


        return result;
    }

    public static final String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static final String deadlineInfo(String strDeadline) {
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = null;
        Date deadLineDate = null;
        try {
            nowDate = new Date();
            deadLineDate = format.parse(strDeadline);
            //毫秒ms
            long diff = deadLineDate.getTime() - nowDate.getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            result = String.format("剩余%s天%s小时%s分钟", diffDays, diffHours, diffMinutes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //订单创建时间后minute分钟后失效, 计算失效剩余时间
    public static String strLeftTime(String createTime, int minute) {
        String result = "";
        int longDiff = (int) DateTimeUtil.getDiff(DateTimeUtil.getDateByAddMinute(createTime, minute), DateTimeUtil.getCurrentTime());
        int iDiffMinute = (int) (longDiff / 60);
        int iDiffSecond = longDiff % 60;
        result = iDiffMinute + "分" + iDiffSecond + "秒";
        return result;
    }

    public static String formatDate(String mdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(mdate);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String datestr = sdf2.format(date);

        return datestr;
    }


    /**
     * 0:00-11:29 显示11:30
     * 11:30 - 17:59 显示18:00
     * 18:00 - 19:59 显示 20:00
     * 20:00 - 23:59 显示 当前时间
     * 日期为当天
     * 如果是当天 显示今天
     * 如果不是当天 显示周几
     * 格式 yyyy-MM-dd(week) HH:mm
     */
    public static String getOrderTime() {
        int currenthour = Integer.parseInt(getFormatDateTime(new Date(), "HH"));
        int currentmin = Integer.parseInt(getFormatDateTime(new Date(), "mm"));
        String retTime = "";
        if (currenthour >= 0 && currenthour <= 11) {
            if (currenthour == 11 && currentmin >= 0 && currentmin <= 29) {
                retTime = getCurrentDate() + " (今天) 11:30";
            } else {
                retTime = getCurrentDate() + " (今天) 11:30";
            }

        }

        if (currenthour >= 11 && currenthour <= 17) {
            if (currenthour == 11 && currentmin >= 30 && currentmin <= 59) {
                retTime = getCurrentDate() + " (今天) 18:00";
            } else if (currenthour == 11 && currentmin >= 0 && currentmin <= 29) {
                retTime = getCurrentDate() + " (今天) 11:30";
            } else {
                retTime = getCurrentDate() + " (今天) 18:00";
            }
        }

        if (currenthour >= 18 && currenthour <= 19) {
            if (currentmin >= 00 && currentmin <= 59) {
                retTime = getCurrentDate() + " (今天) 20:00";
            }
        }

        if (currenthour >= 20 && currenthour <= 23) {
            if (currentmin >= 00 && currentmin <= 59) {
                retTime = getCurrentDate() + " (今天) " + currenthour + ":" + currentmin;
            }
        }

        return retTime;

    }


    public static String getDateFromWeekString(String dateStr) {
        Date dateInfo = DateTimeUtil.getDateFromString(dateStr, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(dateInfo);
        String reservationTimeInfo = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " (" + DateTimeUtil.getWeekDayOfWeekisToday(dateInfo) + ") " + dateStr.substring(11, 16);
        return reservationTimeInfo;
    }


}
