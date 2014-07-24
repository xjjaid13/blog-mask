package com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

/**
 * 时间处理类
 */
public class TimeHandle {

	private static Logger log = Logger.getLogger(TimeHandle.class);

	public static AtomicInteger uploadordernum = new AtomicInteger(10000);

	/**
	 * 获得当前时间
	 * 
	 * @param style
	 *            String 按何种格式
	 * @return String
	 * */
	public static String currentTime(String style) {
		try {
			Calendar calendar = Calendar.getInstance();
			String time = "";
			Date date = calendar.getTime();
			SimpleDateFormat s = new SimpleDateFormat(style);
			time = s.format(date);
			return time;
		} catch (Exception e) {
			log.error("TimeHandle.currentTime发生异常:" + e.getMessage());
			return null;
		}
	}

	/**
	 * 获得当前时间 默认格式为yyyy-MM-dd hh:mm:ss
	 * */
	public static String currentTime() {
		try {
			Calendar calendar = Calendar.getInstance();
			String time = "";
			Date date = calendar.getTime();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time = s.format(date);
			return time;
		} catch (Exception e) {
			log.error("TimeHandle.currentTime发生异常:" + e.getMessage());
			return null;
		}
	}

	/**
	 * 获得上个月的日期(招聘信息使用)
	 * */
	public static String lastMonthTime() {
		String time = currentTime("HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		int lastMonth = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (lastMonth == 1) {
			lastMonth = 12;
			year = year - 1;
		} else {
			lastMonth = lastMonth - 1;
		}
		return "" + year + "-" + (lastMonth < 10 ? "0" + lastMonth : lastMonth)
				+ "-" + day + " " + time;
	}

	/**
	 * 增加减少日期
	 * 
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date handleDate(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -num);
		return calendar.getTime();
	}

	/**
	 * 获得下个月的信息
	 * */
	public static String nextMonthTime() {
		String time = currentTime("HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		int nextMonth = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (nextMonth == 12) {
			nextMonth = 1;
			year = year + 1;
		} else {
			nextMonth = nextMonth + 1;
		}
		return "" + year + "-" + (nextMonth < 10 ? "0" + nextMonth : nextMonth)
				+ "-" + day + " " + time;
	}

	public static String whereDate(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
		String time = "";
		Date date = calendar.getTime();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = s.format(date);
		return time;
	}

	/**
	 * 获得当前年
	 * */
	public static int getYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获得当前月
	 * */
	public static int getMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得当前天
	 * */
	public static int getDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static String returnTimePath() {
		return TimeHandle.getYear() + "/" + TimeHandle.getMonth() + "/"
				+ TimeHandle.getDay() + "/";
	}

	/**
	 * 比较2个字符串时间大小 返回true前一个时间早，返回false为后一个时间早 默认时间格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time1
	 *            String
	 * @param time2
	 *            String
	 * @return boolean
	 * */
	public static boolean CompareTime(String time1, String time2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean b = false;
		try {
			Date date1 = df.parse(time1);
			Date date2 = df.parse(time2);
			b = date1.before(date2);
		} catch (Exception e) {
			log.debug("TimeHandle.CompareTime() 发生异常:+" + e.getMessage());
		}
		return b;
	}

	public static boolean CompareTime(String time1, String time2, String format) {
		DateFormat df = new SimpleDateFormat(format);
		boolean b = false;
		try {
			Date date1 = df.parse(time1);
			Date date2 = df.parse(time2);
			b = date1.before(date2);
		} catch (Exception e) {
			log.debug("TimeHandle.CompareTime() 发生异常:+" + e.getMessage());
		}
		return b;
	}

	/**
	 * 和当前时间比较 返回true前一个时间比当前时间早，返回false比当前时间晚
	 * 
	 * @param time
	 *            String
	 * @return boolean
	 * */
	public static boolean CompareTime(String time) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean b = false;
		try {
			Date date1 = df.parse(time);
			Date date2 = df.parse(currentTime());
			b = date1.before(date2);
		} catch (Exception e) {
			log.debug("TimeHandle.CompareTime() 发生异常:+" + e.getMessage());
		}
		return b;
	}

	public static String getUploadOrderNum() {
		String currenttime = System.currentTimeMillis() + "";
		if (uploadordernum.get() == 20000) {
			uploadordernum = new AtomicInteger(10000);
		}
		uploadordernum.incrementAndGet();
		currenttime += uploadordernum;
		return currenttime;
	}

	/**
	 * 获得下月的今天
	 * */
	public static String nextMonth() {
		int year = getYear();// 取到年份值
		int month = getMonth();// 取到月份值
		int day = getDay();// 取到天值
		String time = currentTime().split(" ")[1];// 取到时间值
		if (month == 0) {
			year -= 1;
			month = 12;
		} else if (day > 28) {
			if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
					day = 29;
				} else {
					day = 28;
				}
			} else if ((month == 4 || month == 6 || month == 9 || month == 11)
					&& day == 31) {
				day = 30;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10) {
			m = "0" + (month + 1);
		} else {
			m = (month + 1) + "";
		}
		if (day < 10) {
			d = "0" + day;
		} else {
			d = day + "";
		}
		return y + "-" + m + "-" + d + " " + time;
	}

	public class TimeTest extends TimerTask {
		public void run() {
			System.out.println("run.....");
		}
	}

	public void test() {
		Timer timer = new Timer();
		timer.schedule(new TimeTest(), 0, 1000);
	}

}
