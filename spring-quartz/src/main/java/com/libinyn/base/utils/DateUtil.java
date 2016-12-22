package com.libinyn.base.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
 
/**
 * 日期帮助类
 *
 */
public class DateUtil {
	/**
	 * Ext默认时间字符串格式 月/日/年(09/20/2011)
	 */
	public static final String EXT_DEFAULT_PATTERN = "MM/dd/yyyy";
	/**
	 * 年月日模式字符串
	 */
	public static final String YMD_PATTERN = "yyyy-MM-dd";
	/**
	 * 农行扣划年月日模式字符串
	 */
	public static final String YYYYMMDD_PATTERN = "yyyyMMdd";
	/**
	 * 时分秒模式
	 */
	public static final String HMS_PATTERN = "HH:mm:ss";
	
	/**
	 * 时分
	 */
	public static final String HH_MM = "HHmm";
	/**
	 * 年月日时分秒模式
	 */
	public static final String YMDHMS_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String YMDHMS_PATTERN1 = "yyyy/MM/dd HH:mm:ss";
	public static final String YMDHM_PATTERN = "yyyy-MM-dd HH:mm";
	public static final String YMDHMSD_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

	private static final String YYYYMMDDHHMMSS_PATTERN="yyyyMMddHHmmss";
	/**
	 * 返回当前日期
	 * @return Date 
	 */
	public static Date currentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 返回当前日期的年月日模式字符串
	 * @return String
	 */
	public static String currentYMDDateStr() {
		return format(currentDate(), YMD_PATTERN);
	}
	/**
	 * 返回当前日期的年月日模式字符串
	 * @return String
	 */
	public static String currentYMDDateStr01() {
		return format(currentDate(), YYYYMMDD_PATTERN);
	}
	/**
	 * 返回当前日期的年月日时分秒模式字符串
	 * @return String 
	 */
	public static String currentYMDHMSDateStr() {
		return format(currentDate(), YMDHMS_PATTERN);
	}
	
	/**
	 * @return String
	 */
	public static String currentYYYYMMDDHHMMSSDateStr(){
		return format(currentDate(), YYYYMMDDHHMMSS_PATTERN);
	}

	/**
	 * 返回当前日期的时分秒模式字符串
	 * 
	 * @return String 
	 */
	public static String currentHMSDateStr() {
		return format(currentDate(), HMS_PATTERN);
	}

	/**
	 * 将传入时间类型数据格式化为指定字符串模式
	 * 
	 * @param date 
	 * @param pattern 
	 * @return String 
	 */
	public static String format(final Date date, final String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		if (date == null) {
			return df.format(new Date());
		} 
		return df.format(date);
	}
	/**
	 * 将传入时间格式化为年月日字符串模式
	 * 
	 * @param date 
	 * @return String 
	 */
	public static String formatYMDateStr(final Date date) {
		String sDate = format(date, YMD_PATTERN);
		return sDate.equals("") ? "" : sDate.substring(0, 7);
	}
	/**
	 * 将传入时间格式化为年月日字符串模式
	 * 
	 * @param date 
	 * @return String
	 */
	public static String formatYMDDateStr(final Date date) {
		return format(date, YMD_PATTERN);
	}

	/**
	 * 将传入时间格式化为年月日时分秒字符串模式
	 * 
	 * @param date 
	 * @return String
	 */
	public static String formatYMDHMSDateStr(final Date date) {
		return format(date, YMDHMS_PATTERN);
	}

	/**
	 * 根据传入的日期格式化patter将传入的字符串转换成日期对象
	 * 
	 * @param dateStr 
	 * @param pattern 
	 * @return Date 
	 */
	public static Date parse(final String dateStr, final String pattern) {
		try {
			if (dateStr == null || "".equals(dateStr.trim())) {
				return null;
			}
			DateFormat df = new SimpleDateFormat(pattern);
			return df.parse(dateStr);
		} catch (ParseException e) {
			return tryParse(dateStr);
		}
	}
	/**
	 * 
	 * @param dateStr 
	 * @return Date 
	 */
	public static Date tryParse(final String dateStr) {
		if (dateStr == null || "".equals(dateStr.trim())) {
			return null;
		}
		DateFormat df = null;
		String[] tryPatterns = new String[] {YMDHMS_PATTERN, 
				YMD_PATTERN, EXT_DEFAULT_PATTERN, YMDHMSD_PATTERN};
		for(String pattern : tryPatterns){
			try {
				df = new SimpleDateFormat(pattern);
				return df.parse(dateStr);
			} catch (ParseException e) {
				//nothing, next try
			}
		}
		return null;
	}
	/**
	 * @param arg0 
	 * @param arg1 
	 * @return boolean 
	 */
	public static boolean compareYM(Date arg0, Date arg1){
		String date0 = formatYMDateStr(arg0);
		String date1 = formatYMDateStr(arg1);
		return date0.equalsIgnoreCase(date1);
	}
	/**
	 * 根据传入的日期格式化patter将传入的字符串转换成日期对象
	 * 
	 * @param dateStr 
	 * @param pattern 
	 * @return Timestamp 
	 */
	public static Timestamp parseTimestamp(final String dateStr, final String pattern) {
		Date date = parse(dateStr, pattern);
		if(date != null){
			return new Timestamp(date.getTime());
		}else{
			return null;
		}

	}
	
	/**
	 * @param dateStr 
	 * @return Timestamp 
	 */
	public static Timestamp parseTimestamp(final String dateStr){
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String dateStr1 = dateStr;
		try { 
			if(dateStr.split(" ").length>2){
				dateStr1 = (dateStr.substring(0, dateStr.lastIndexOf(" ")) 
						+ "." + dateStr.substring(dateStr.lastIndexOf(" ") + 1, 
								dateStr.length()));
			}
            ts = Timestamp.valueOf(dateStr1); 
        } catch (Exception e) {  
            ts = new Timestamp(parseDefaultDate(dateStr).getTime());
        }  
		return ts;
	}

	
	/**
	 * 将传入的字符串按照默认（yyyy-MM-dd）格式转换为日期对象
	 * @param dateStr 
	 * @return Date 对象 
	 */
	public static Date parseDefaultDate(final String dateStr) {
		return parse(dateStr, parsePattern(dateStr));
	}

	/**
	 * 将传入的字符串按照yyyy-MM-dd HH:mm:ss格式转换为日期对象
	 * 
	 * @param dateStr 
	 * @return Date 对象
	 */
	public static Date parseFullDate(final String dateStr) {
		return parse(dateStr, YMDHMS_PATTERN);
	}

	/**
	 * 匹配传入字符型时间格式的格式化模式
	 * 
	 * @param dateStr 
	 * @return String 
	 */
	private static String parsePattern(String dateStr) {
		String pattern = "";
		boolean b = false;
		String ext = "(\\d{2}/){2}\\d{4}"; // Ext时间字符串格式 09/20/2011
//		String def = "\\d{4}(-\\d{2}){2}"; // 通常时间字符串格式 2011-09-20
		try {
			Pattern compiler = Pattern.compile(ext);
			Matcher m = compiler.matcher(dateStr);
			b = m.matches();
			if (b) {
				pattern = EXT_DEFAULT_PATTERN;
			} else {
				pattern = YMD_PATTERN;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pattern;
	}
	/**
	 * 计算两个日期之间的天数
	 * @param d1  
	 * @param d2 
	 * @return int 返指定的两日期之间的天数
	 */
	public static int getDaysBetween(Date d1, Date d2) {
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		c1.setTime(d1);
		c2.setTime(d2);

		if (c1.after(c2)) { // swap dates so that c1 is start and c2 is end
			Calendar swap = c1;
			c1 = c2;
			c2 = swap;
		}
		int days = 0;
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		if(y1==y2){
			days = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
		}else{
			days = c1.getActualMaximum(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
			//c1 = (Calendar) c1.clone();
			int count = 1;
			Date c1Date = c1.getTime();
			c1.add(Calendar.YEAR, count++);
			while(c1.get(Calendar.YEAR) != y2) {
			    days += c1.getActualMaximum(Calendar.DAY_OF_YEAR);
			    c1.setTime(c1Date);
			    c1.add(Calendar.YEAR, count++);
			}
			days += c2.get(Calendar.DAY_OF_YEAR);
		}
		return days;
	}
	
	/**
	 * 
	 * @param date1 
	 * @param date2 
	 * @return int
	 */
	public static int getDaysDiff(Date date1, Date date2){
		Calendar calendarFm = Calendar.getInstance();
		Calendar calendarTo = Calendar.getInstance();
		
		calendarFm.setTime(date1);
		calendarTo.setTime(date2);
		calendarFm.set(Calendar.HOUR_OF_DAY, 0);
		calendarFm.set(Calendar.MINUTE, 0);
		calendarFm.set(Calendar.SECOND, 0);
		calendarFm.set(Calendar.MILLISECOND, 0);
		  
		calendarTo.set(Calendar.HOUR_OF_DAY, 0);
		calendarTo.set(Calendar.MINUTE, 0);
		calendarTo.set(Calendar.SECOND, 0);
		calendarTo.set(Calendar.MILLISECOND, 0);
		
		int days = 0;
		int stepSize = date1.after(date2) ? -1 : 1;
		boolean isBreak = false;
		while (true){
			calendarFm.add(Calendar.DAY_OF_MONTH, stepSize);
			days += stepSize;
			
			isBreak = (stepSize == 1 &&  calendarFm.after(calendarTo)) || (stepSize == -1 && calendarFm.before(calendarTo));
			if (isBreak){
				break;
			}
		}
		return days;
	}
	

	/**
	 * 
	 * @param date1 
	 * @param date2 
	 * @return int 
	 */
	public static int getOverdueDays(Date date1, Date date2){
		if (date1.after(date2)){
			return 0;
		}
		Calendar calendarFm = Calendar.getInstance();
		Calendar calendarTo = Calendar.getInstance();
		
		calendarFm.setTime(date1);
		calendarTo.setTime(date2);
		calendarFm.set(Calendar.HOUR_OF_DAY, 0);
		calendarFm.set(Calendar.MINUTE, 0);
		calendarFm.set(Calendar.SECOND, 0);
		calendarFm.set(Calendar.MILLISECOND, 0);
		  
		calendarTo.set(Calendar.HOUR_OF_DAY, 0);
		calendarTo.set(Calendar.MINUTE, 0);
		calendarTo.set(Calendar.SECOND, 0);
		calendarTo.set(Calendar.MILLISECOND, 0);
		
		int days = 0;
		int stepSize = date1.after(date2) ? -1 : 1;
		boolean isBreak = false;
		while (true){
			calendarFm.add(Calendar.DAY_OF_MONTH, stepSize);
			days += stepSize;	
			
			isBreak = (stepSize == 1 &&  calendarFm.after(calendarTo));
			if (isBreak){
				break;
			}
		}
		return days;
	}
	

	/**
	 * 违约金按10天一期时的天 数计算
	 * @param date1 
	 * @param date2 
	 * @return int
	 */
	public static int getOverdueTermsDays(Date date1, Date date2){
		if (date1.after(date2)){
			return 0;
		}
		Calendar calendarFm = Calendar.getInstance();
		Calendar calendarTo = Calendar.getInstance();
		
		calendarFm.setTime(date1);
		calendarTo.setTime(date2);
		calendarFm.set(Calendar.HOUR_OF_DAY, 0);
		calendarFm.set(Calendar.MINUTE, 0);
		calendarFm.set(Calendar.SECOND, 0);
		calendarFm.set(Calendar.MILLISECOND, 0);
		  
		calendarTo.set(Calendar.HOUR_OF_DAY, 0);
		calendarTo.set(Calendar.MINUTE, 0);
		calendarTo.set(Calendar.SECOND, 0);
		calendarTo.set(Calendar.MILLISECOND, 0);
		
		int days = 0;
		int stepSize = date1.after(date2) ? -1 : 1;
		boolean isBreak = false;
		int lastDayOfMonth = 0;
		int currDayOfMonth = 0;
		while (true){
			lastDayOfMonth = calendarFm.get(Calendar.DAY_OF_MONTH);
			calendarFm.add(Calendar.DAY_OF_MONTH, stepSize);
			currDayOfMonth = calendarFm.get(Calendar.DAY_OF_MONTH);
			days += stepSize;	
			
			isBreak = (stepSize == 1 &&  calendarFm.after(calendarTo));
			if (!isBreak){				
				if (currDayOfMonth == 1) {
					//跨月修正，25号到月底，多或少于5天，以5天计算 
					switch (lastDayOfMonth) {
						case 28 :  days += 2; break;
						case 29 :  days += 1; break;
						case 30 :  break;
						case 31 :  days -= 1; break;
						default:
							break;
					}
				}
			} else {
				break;
			}
		}
		return days;
	}
	
	/**
	 * 计算两个日期之间的月份数
	 * @param d1  
	 * @param d2 
	 * @return Integer 返指定的两日期之间的月份数
	 */
	public static Integer getMonthsBetween(Date d1, Date d2) {
		if (d1 == null || d2 == null){
			return null;
		}
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		c1.setTime(d1);
		c2.setTime(d2);

		if (c1.after(c2)) { // swap dates so that c1 is start and c2 is end
			Calendar swap = c1;
			c1 = c2;
			c2 = swap;
		}
		
		int months = 0;
		while(true){
			c1.add(Calendar.MONTH, 1);
			if (c1.before(c2)){
				months++;
			} else {
				break;
			}
		}

		return months;
	}
	
	/**
	 * 
	 * @param date 
	 * @param days 
	 * @return Date
	 */
	public static Date addDay(java.util.Date date, int days) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();

	}
	
	/**
	 * 
	 * @param date 
	 * @param months 
	 * @return Date 
	 */
	public static Date addMonth(java.util.Date date, int months) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);

		return cal.getTime();

	}
	
	/**
	 *  
	 * @param date 
	 * @return Date 
	 */
	 public static Date truncToDay(Date date) {
		  java.util.Calendar c = java.util.Calendar.getInstance();
		  c.setTime(date);
		  c.set(Calendar.HOUR_OF_DAY, 0);
		  c.set(Calendar.MINUTE, 0);
		  c.set(Calendar.SECOND, 0);
		  c.set(Calendar.MILLISECOND, 0);
		  return c.getTime();
	 }

	 /**
	  * 获取日期所有月的每一天
	  * @param date 
	  * @return Date 
	  */
	 public static Date getMonthFirstDay(Date date) {
		  java.util.Calendar c = java.util.Calendar.getInstance();
		  c.setTime(date);
		  c.set(Calendar.DAY_OF_MONTH, 1);
		  c.set(Calendar.HOUR_OF_DAY, 0);
		  c.set(Calendar.MINUTE, 0);
		  c.set(Calendar.SECOND, 0);
		  c.set(Calendar.MILLISECOND, 0);
		  return c.getTime();
	 }
	
	 /**
	  * 获取日期所在月的最后一天
	  * @param date 
	  * @return Date 
	  */
	 public static Date getMonthLastDay(Date date) {
		  java.util.Calendar c = java.util.Calendar.getInstance();
		  c.setTime(date);
		  c.set(Calendar.DAY_OF_MONTH, 1);
		  c.set(Calendar.HOUR_OF_DAY, 23);
		  c.set(Calendar.MINUTE, 59);
		  c.set(Calendar.SECOND, 59);
		  c.set(Calendar.MILLISECOND, 999);
		  c.add(Calendar.MONTH, 1);
		  c.add(Calendar.DAY_OF_MONTH, -1);
		  
		  return c.getTime();
	 }
	
     /**
      * 获取日期的零点，置时、分、称、千分秒为0
      * @param date 
      * @return Date 
      */
	 public static Date getDayStart(Date date) {
		  java.util.Calendar c = java.util.Calendar.getInstance();
		  c.setTime(date);
		  c.set(Calendar.HOUR_OF_DAY, 0);
		  c.set(Calendar.MINUTE, 0);
		  c.set(Calendar.SECOND, 0);
		  c.set(Calendar.MILLISECOND, 0);
		  return c.getTime();
	 }
	
	 /**
	  * 获取日期的午夜时间，置时、分、称、千分秒为23，59，59，999
	  * @param date 
	  * @return Date 
	  */
	 public static Date getDayEnd(Date date) {
		  java.util.Calendar c = java.util.Calendar.getInstance();
		  c.setTime(date);
		  c.set(Calendar.HOUR_OF_DAY, 23);
		  c.set(Calendar.MINUTE, 59);
		  c.set(Calendar.SECOND, 59);
		  c.set(Calendar.MILLISECOND, 999);
		  
		  return c.getTime();
	 }
	/**
	 * 将时间的day换成指定的day
	 * @param day 
	 * @param date 
	 * @return Date 
	 */
	public static Date getAppointDayDate(Integer day, Date date){
			 java.util.Calendar c = java.util.Calendar.getInstance();
			 c.setTime(date);
			 c.set(Calendar.DAY_OF_MONTH, day);
			 return c.getTime();
	}
	 
//	
//	public static void main(String[] args) {
//		String str = null;
//		String s1 = "2011-05-09 11:49:45.424";
//		String s5 = "2013-07-16 10:52:28 000";
//		if(s5.split(" ").length>2){
//			s5 = (s5.substring(0,s5.lastIndexOf(" "))+ "." + s5.substring(s5.lastIndexOf(" ") +1,s5.length()));
//		}
//		String s2 = "2011-05-09 11:49:45";
//		String s3 = "2011-05-09";
//		String s4 = "2011/05/09";
//		 
//		System.out.println(DateUtil.parseTimestamp(s5));
//		System.out.println(DateUtil.parseTimestamp(s1));
//		System.out.println(DateUtil.parseTimestamp(s2));
//		System.out.println(DateUtil.parseTimestamp(s3));
//		//System.out.println(DateUtil.parseTimestamp(s4));
//		System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
//		return;
//
//	}
	
	/**
	 * 
	 * @param dateStr 
	 * @return Date 
	 */
	public static Date stringToDate(String dateStr){
		DateFormat df = null;
		
		//先判断是否有“:” 在判断是否有分割符
		if (-1 == dateStr.indexOf(":")) {
			//没有“:”
			if (-1 != dateStr.indexOf("/")) {
				//格式为2013/01/01
				df = new SimpleDateFormat("yyyy/MM/dd");
			}else if(-1 != dateStr.indexOf("-")){
				//格式为2013-01-01
				df = new SimpleDateFormat("yyyy-MM-dd");
			}else{
				//格式为20130101
				df = new SimpleDateFormat("yyyyMMdd");
			}
		}else{
			//有“:”
			if (-1 != dateStr.indexOf("/")) {
				//格式为2013/01/01 HH:mm:ss
				df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			}else if(-1 != dateStr.indexOf("-")){
				//格式为2013-01-01  HH:mm:ss
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}else{
				//格式为20130101 HH:mm:ss
				df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			}
		}
		
		Date date=null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			date=null;
		}

		return date;
	}

	/**
	 * 
	 * @Name: diffDate
	 * @Description:计算时间差
	 * @Author: WeiHui.Zhang
	 * @Version: V1.00
	 * @Date: 2016年5月18日下午4:57:52
	 * @param after 
	 * @param before 
	 * @return 时间
	 * @Return: String
	 */
	public static String diffDate(Date before, Date after) {
		DateTime dt1 = new DateTime(after);
  		DateTime dt2 = new DateTime(before);
  		StringBuffer sb = new StringBuffer();
  		sb.append(Days.daysBetween(dt1, dt2).getDays() + "天");
  		sb.append(Hours.hoursBetween(dt1, dt2).getHours() % 24 + "小时");
  		sb.append(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + "分钟");
  		sb.append(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60+ "秒.");
  		return sb.toString();
	}
	
	/**
	 * 格式化当前时间    
	 * @author mali    
	 * @date: 2016年6月30日 上午11:32:35
	 * @Title: diffNow    
	 * @param before 
	 * @return String 返回值
	 */
	public static String diffNow(Date before) {
		DateTime dt1 = new DateTime(before);
  		DateTime dt2 = new DateTime(new Date());
  		StringBuffer sb = new StringBuffer();
  		sb.append(Days.daysBetween(dt1, dt2).getDays() + "天");
  		sb.append(Hours.hoursBetween(dt1, dt2).getHours() % 24 + "小时");
  		sb.append(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + "分钟");
  		sb.append(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60+ "秒.");
  		return sb.toString();
	}
	
//	public static void main(String[] args) {
//		System.out.println(diffNow(new Date()));
//		
//	}
	
	/**
	 * 时间戳转换为日期字符串
	 * @param seconds 时间戳字符串
	 * @param format 显示格式
	 * @return 日期字符串
	 * @author guohui.zhang
	 */
	public static String timeStamp2Date(String seconds, String format) {
		String result = null;
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return result;  
        }  
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
        	result = sdf.format(new Timestamp(Long.valueOf(seconds)));
		} catch (NumberFormatException e) {
			
		}
        return result;
    }
	
    /**
     * Date转化为字符串
     * @param date 日期
     * @param format 格式
     * @return String
     */
    public static String dateToStr(Date date, String format) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }
}
