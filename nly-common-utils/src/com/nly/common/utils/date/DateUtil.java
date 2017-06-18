package com.nly.common.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;

import com.nly.common.utils.base.LogUtil;



public class DateUtil {
	/** 10位日期格式化 ***/
	public final static String DATE_FORMAT_10 = "yyyy-MM-dd";

	/** 8位日期格式化 ***/
	public final static String DATE_FORMAT_8 = "yyyyMMdd";
	
	public final static String DATE_TIME_6 = "HHmmss";

	/** 时间戳日期格式化 ***/
	public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 判断日期是否符合区间段
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean timeNotBetweenNow(Date startTime, Date endTime) {  
	        Date now = new Date();  
	  
	        if (startTime != null) {  
	            return now.before(startTime);  
	        }  
	  
	        if (endTime != null) {  
	            return now.after(endTime);  
	        }  
	  
	        return false;  
	    }  
	
	
	/**
	 * 获取时间差，结果为秒
	 * @param before
	 * @param after
	 * @return
	 */
	public static String getBetWeen(String before, String after){
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long between = 0;
        try {
            Date begin = dfs.parse(before);
            Date end = dfs.parse(after);
            between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return String.valueOf((between / 1000 ));
	}

	
	/* 
	 * 毫秒转化天时分秒毫秒 
	 */  
	public static String formatMsTime(Long ms) {  
	    Integer ss = 1000;  
	    Integer mi = ss * 60;  
	    Integer hh = mi * 60;  
	    Integer dd = hh * 24;  
	  
	    Long day = ms / dd;  
	    Long hour = (ms - day * dd) / hh;  
	    Long minute = (ms - day * dd - hour * hh) / mi;  
	    Long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
	    Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;  
	      
	    StringBuffer sb = new StringBuffer();  
	    if(day > 0) {  
	        sb.append(day+"天");  
	    }  
	    if(hour > 0) {  
	        sb.append(hour+"小时");  
	    }  
	    if(minute > 0) {  
	        sb.append(minute+"分");  
	    }  
	    if(second > 0) {  
	        sb.append(second+"秒");  
	    }  
	    if(milliSecond > 0) {  
	        sb.append(milliSecond+"毫秒");  
	    }  
	    return sb.toString();  
	}  

	
	
	 public static String formatDate(Date aTs_Datetime, String as_Pattern)
	    {
	      if (aTs_Datetime == null || as_Pattern == null)
	        return null;

	      SimpleDateFormat dateFromat = new SimpleDateFormat();
	      dateFromat.applyPattern(as_Pattern);
	      
	      return dateFromat.format(aTs_Datetime);
	    }
	    
	
	
	/****
	 * 将Date 类型转换 Calendar 
	 * @param date 
	 * @return Calendar
	 */
	public static Calendar parseCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/*****
	 * 将10位日期字符,转换Calendar
	 *
	 * @param data_date (yyyy-MM-dd)
	 * @return Calendar
	 * @throws ParseException
	 * @throws BusinessException
	 */
	public static Calendar parseCalendar(String data_date) {
		int[] yMDArr = parseDateYMDArr(data_date);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, yMDArr[0]);
		calendar.set(Calendar.MONTH, yMDArr[1]);
		calendar.set(Calendar.DATE, yMDArr[2]);

		return calendar;
	}
	
	/**
	 * 在日期上加days天，得到新的日期
	 * @return
	 */
	public final static Date addDaysToDate(Date date,int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE,days);
		return c.getTime();
	}
	
	/**
	 * 在日期上加days天，得到新的日期
	 * @return yyyy-MM-dd
	 */
	public final static String addDaysToDate1(Date date,int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}
	
	/**
	 * 计算两日期之间的天数
	 * @return
	 */
	public final static int getDaysBetweenDate(String date1,String date2){
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		try {
			d1 = sd.parse(date1);
		} catch (ParseException e) {
			 LogUtil.error(e.getMessage());
		}
		Date d2=null;
		try {
			d2 = sd.parse(date2);
		} catch (ParseException e) {
			 LogUtil.error(e.getMessage());
		}
		Calendar c1 = Calendar.getInstance();
		
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance(); 
		c2.setTime(d2);
		long diff = (c2.getTimeInMillis()-c1.getTimeInMillis())/(1000*60*60*24);
		return ((Long)diff).intValue();
	}
	
	/**
	 * 在日期上加days天，得到新的日期
	 * @return yyyyMMdd
	 */
	public final static String addDaysToDate2(Date date,int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(c.getTime());
	} 
		
 
	/****
	 * 获取当前日期时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrDateTime() {
		return FastDateFormat.getInstance(DATE_TIME_FORMAT).format(Calendar.getInstance());
	}
	
	/**
	 * 对输入的日期date ,移动moveNum个单位unit后得到的日期
	 * 
	 * @param date
	 * @param moveNum 移动数量
	 * @param unit 移动单位(D:天 M:月 Y:年)
	 * @return 移动之后的数据日期(yyyy-MM-dd)
	 * @throws ParseException
	 */
	public static Date moveDate(Date date, int moveNum, char unit) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		switch (unit) {
		case 'M':
		case 'm':
			cal.add(Calendar.MONTH, moveNum);
			break;
		case 'd':
		case 'D':
			cal.add(Calendar.DATE, moveNum);
			break;
		case 'Y':
		case 'y':
			cal.add(Calendar.YEAR, moveNum);
			break;
		}
		return cal.getTime();
	}
	
 
	
	/**字符串转换为时间类型
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		DateFormat dateFormat = null;
		try {
			dateFormat = new SimpleDateFormat(format);
			date = dateFormat.parse(dateStr);
		} catch (Exception e) {
			 LogUtil.error(e.getMessage());
		}
		return date;
	}
	
	/****
	 * 将（yyyy-MM-dd）字符串日期转换，年/月/日 整型串数组
	 * 
	 * @param data_date （yyyy-MM-dd）
	 * @return int[]
	 */
	private static int[] parseDateYMDArr(String data_date) {
		int year = Integer.valueOf(data_date.substring(0, 4));
		int month = Integer.valueOf(data_date.substring(5, 7)) - 1;
		int day = Integer.valueOf(data_date.substring(8, 10));
		int yMDArr[] = { year, month, day };
		return yMDArr;
	}
	
	/**
	 * 根据日期获取星期
	 * 
	 * @param strdate
	 * @return
	 */
	public static String getWeekDayByDate(String strdate) {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		try {
			date = sdfInput.parse(strdate);
		} catch (ParseException e) {
			 LogUtil.error(e.getMessage());
		}
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0;
		return dayNames[dayOfWeek];
	}
	
	/**
	 * 返回指定格式的当前日期时间字符串
	 * 
	 * @param format
	 * @return
	 */
	public static String getDateTimeStr(String format) {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}
	
	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	
	
	/**
	 * 生成问候信息
	 * 
	 * @return
	 */
	public static String getWelcomeMsg() {
		String welcome = "晚上好";
		Integer timeInteger = new Integer(getDateTimeStr("HH"));
		if (timeInteger.intValue() >= 7 && timeInteger.intValue() <= 12) {
			welcome = "上午好";
		} else if (timeInteger.intValue() > 12 && timeInteger.intValue() < 19) {
			welcome = "下午好";
		}
		return welcome;
	}
}
