package org.cz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	   private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	    /**
		  * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
		  * @param str 字符串
		  * @param format 日期格式
		  * @return 日期
		  * @throws java.text.ParseException
		  */
		public static Date str2Date(String str){
			if (null == str || "".equals(str)) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
			Date date = null;
			try {
				date = sdf.parse(str);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
		 * @param str 字符串
		 * @param format 日期格式
		 * @return 日期
		 * @throws java.text.ParseException
		 */
		public static Date str2Date(String str,String format){
			if (null == str || "".equals(str)) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = null;
			try {
				date = sdf.parse(str);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}

		 /** 日期转换为字符串
		  * @param date 日期
		  * @param format 日期格式
		  * @return 字符串
		  */
		public static String date2Str(Date date) {
			if (null == date) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
			return sdf.format(date);
		}
		public static String date2Str(Date date,String format) {
			if (null == date||format==null||"".equals(format.trim())) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
}
