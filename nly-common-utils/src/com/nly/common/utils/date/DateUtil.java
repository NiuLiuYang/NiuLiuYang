package com.nly.common.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;

import com.nly.common.utils.base.LogUtil;



public class DateUtil {
	/** 10λ���ڸ�ʽ�� ***/
	public final static String DATE_FORMAT_10 = "yyyy-MM-dd";

	/** 8λ���ڸ�ʽ�� ***/
	public final static String DATE_FORMAT_8 = "yyyyMMdd";
	
	public final static String DATE_TIME_6 = "HHmmss";

	/** ʱ������ڸ�ʽ�� ***/
	public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * �ж������Ƿ���������
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
	 * ��ȡʱ�����Ϊ��
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
            between = (end.getTime() - begin.getTime());// �õ����ߵĺ�����
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return String.valueOf((between / 1000 ));
	}

	
	/* 
	 * ����ת����ʱ������� 
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
	        sb.append(day+"��");  
	    }  
	    if(hour > 0) {  
	        sb.append(hour+"Сʱ");  
	    }  
	    if(minute > 0) {  
	        sb.append(minute+"��");  
	    }  
	    if(second > 0) {  
	        sb.append(second+"��");  
	    }  
	    if(milliSecond > 0) {  
	        sb.append(milliSecond+"����");  
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
	 * ��Date ����ת�� Calendar 
	 * @param date 
	 * @return Calendar
	 */
	public static Calendar parseCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/*****
	 * ��10λ�����ַ�,ת��Calendar
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
	 * �������ϼ�days�죬�õ��µ�����
	 * @return
	 */
	public final static Date addDaysToDate(Date date,int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE,days);
		return c.getTime();
	}
	
	/**
	 * �������ϼ�days�죬�õ��µ�����
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
	 * ����������֮�������
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
	 * �������ϼ�days�죬�õ��µ�����
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
	 * ��ȡ��ǰ����ʱ��
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrDateTime() {
		return FastDateFormat.getInstance(DATE_TIME_FORMAT).format(Calendar.getInstance());
	}
	
	/**
	 * �����������date ,�ƶ�moveNum����λunit��õ�������
	 * 
	 * @param date
	 * @param moveNum �ƶ�����
	 * @param unit �ƶ���λ(D:�� M:�� Y:��)
	 * @return �ƶ�֮�����������(yyyy-MM-dd)
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
	
 
	
	/**�ַ���ת��Ϊʱ������
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
	 * ����yyyy-MM-dd���ַ�������ת������/��/�� ���ʹ�����
	 * 
	 * @param data_date ��yyyy-MM-dd��
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
	 * �������ڻ�ȡ����
	 * 
	 * @param strdate
	 * @return
	 */
	public static String getWeekDayByDate(String strdate) {
		final String dayNames[] = { "������", "����һ", "���ڶ�", "������", "������", "������", "������" };
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
	 * ����ָ����ʽ�ĵ�ǰ����ʱ���ַ���
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
	 * �õ���ǰʱ���ַ��� ��ʽ��HH:mm:ss��
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * �õ���ǰ���ں�ʱ���ַ��� ��ʽ��yyyy-MM-dd HH:mm:ss��
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * �õ���ǰ����ַ��� ��ʽ��yyyy��
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * �õ���ǰ�·��ַ��� ��ʽ��MM��
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * �õ������ַ��� ��ʽ��dd��
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * �õ���ǰ�����ַ��� ��ʽ��E�����ڼ�
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	

	/**
	 * ��ȡ��ȥ������
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * ��ȡ��ȥ��Сʱ
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * ��ȡ��ȥ�ķ���
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * ת��Ϊʱ�䣨��,ʱ:��:��.���룩
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
	 * ��ȡ��������֮�������
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
	 * �����ʺ���Ϣ
	 * 
	 * @return
	 */
	public static String getWelcomeMsg() {
		String welcome = "���Ϻ�";
		Integer timeInteger = new Integer(getDateTimeStr("HH"));
		if (timeInteger.intValue() >= 7 && timeInteger.intValue() <= 12) {
			welcome = "�����";
		} else if (timeInteger.intValue() > 12 && timeInteger.intValue() < 19) {
			welcome = "�����";
		}
		return welcome;
	}
}
