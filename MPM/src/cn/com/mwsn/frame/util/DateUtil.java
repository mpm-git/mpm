package cn.com.mwsn.frame.util;

import java.util.Date;
import java.util.Calendar;

import java.text.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DateUtil {
	private static final Log logger = LogFactory.getLog(DateUtil.class);

	private DateUtil() {
	}

	public static String format(Object date, String pattern) {
		if (date == null)
			return "";

		SimpleDateFormat formater = new SimpleDateFormat(pattern);

		return formater.format(date);
	}

	/**
	 * 格式化日期 String
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Object date) {
		if (date == null) {
			return "";
		}
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 格式化时间 String
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Object date) {
		if (date == null) {
			return "";
		}
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 根据传入的pattern解析日期 格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseTime(String date) {
		return parse(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 根据传入的pattern解析日期 格式：yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDate(String date) {
		return parse(date, "yyyy-MM-dd");
	}

	/**
	 * 根据传入的pattern解析日期 pattern的格式： yyyy-MM-dd HH:mm:ss yyyy-MM-dd 等
	 * 
	 * @param date
	 * @return
	 */
	public static Date parse(String date, String pattern) {
		Date d = null;
		try {
			SimpleDateFormat sf = new SimpleDateFormat(pattern);
			d = sf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String[] paraseDateStr(String dateStr) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String[] dateParts = new String[6];
		try {
			Date date = sf.parse(dateStr);
			Calendar ca = Calendar.getInstance();
			ca.setTime(date);

			int MM = ca.get(Calendar.MONTH) + 1;
			int dd = ca.get(Calendar.DAY_OF_MONTH);
			int HH = ca.get(Calendar.HOUR_OF_DAY);
			int mm = ca.get(Calendar.MINUTE);
			int ss = ca.get(Calendar.SECOND);

			dateParts[0] = "" + ca.get(Calendar.YEAR);
			dateParts[1] = MM < 10 ? "0" + MM : "" + MM;
			dateParts[2] = dd < 10 ? "0" + dd : "" + dd;
			dateParts[3] = HH < 10 ? "0" + HH : "" + HH;
			dateParts[4] = mm < 10 ? "0" + mm : "" + mm;
			dateParts[5] = ss < 10 ? "0" + ss : "" + ss;
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return dateParts;
	}

	public static int compareDate(Date beforeDate, Date nowDate) {
		long cha = nowDate.getTime() - beforeDate.getTime();
		int num = (int) (cha / 86400000L);
		return num;
	}

	public static Date addDate(Date date, int addDays) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DATE, addDays);
		return ca.getTime();
	}

	public static void main(String[] args) {
		// Calendar ca = Calendar.getInstance();
		// int i = ca.get(Calendar.WEEK_OF_MONTH);
		// System.out.println("sunday:"+Calendar.SUNDAY);
		// System.out.println("week:"+i);
		//
		// Date date = new Date();
		// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// System.out.println(sf.format(date));

//		String[] ps = paraseDateStr("2004-11-20 00:00:00");
//		for (int i = 0; i < ps.length; i++) {
//			System.out.println(i + ":" + ps[i]);
//		}
		Date date = DateUtil.parse("2014-05-14 09:62:32", "yyyy-MM-dd HH:mm:ss");
		System.out.println(date.getTime());
		
	}

}
