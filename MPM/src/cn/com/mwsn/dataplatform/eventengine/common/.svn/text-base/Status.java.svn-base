package cn.com.mwsn.dataplatform.eventengine.common;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Status {
	
	public static final int MIN_THREAD_NUM = 1;
	public static final int MAX_THREAD_NUM = 10;
	public static final long KEEPALIVETIME = 60000L;
	public static final char STATUS_CHECKED = '1';
	public static final char STATUS_UNCHECKED = '0';
	public static final long START_START = 100; // ms
	public static final long START_END = 150; // ms
	public static final int ICON_X = 20; // px
	public static final int ICON_Y = 20; // px
	public static final String ROLE_DOCTOR = "1";
	public static final String ROLE_NURSE = "2";
	public static final String ROLE_PATIENT = "3";
	public static final int ALERT_WORKOUT = 1;           //事件类型：下班
	public static final long WARDROUND_DEFTIME = 900000;  //15分钟  医生护士查房最低时间限制
	public final static int STATUS_OUT = 0;       //走出一个区域
	public final static int STATUS_IN = 1;        //进入一个区域
	public final static int STATUS_INVALID = -1;   //初始无意义值
	
	
	
	public final static String BEAN_EVENT_HANDLER = "eventHandlerImpl";
	public final static String BEAN_EVENT_ENGINE = "eventEngineImpl";
	
	public static List<Byte> buffer = new ArrayList<Byte>();
	public static String bufferAntenna = "";
	public static Long time = 0L;
	public static String tagID = "";

}
