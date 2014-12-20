package cn.com.mwsn.mpm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.mwsn.mpm.data.IOTSend2CP;
import cn.com.mwsn.mpm.entity.RFIDHistory;
import cn.com.mwsn.mpm.service.RFIDCardPersonService;
import cn.com.mwsn.mpm.service.RFIDHistoryService;

public class Test {

	enum SS {DOCTOR, NURSE, PATIENT};
	
	private static RFIDHistoryService rfidHistoryService;
	private static HttpClient httpclient = new DefaultHttpClient();
	private static HttpResponse httpResponse = null;
	
	private static String s = "5555"; 
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws java.text.ParseException 
	 */
	public static void main(String[] args) throws ParseException, IOException, java.text.ParseException {
		// TODO Auto-generated method stub
		/*String str;
		try {
			str = "http://192.168.1.117/WS_CPIOT_ADAPTER/WS_CPIOT.asmx/IOTUSER_DATAIMPORT?userMsg=" + URLEncoder.encode("@IOT|人员信息|V100|", "UTF-8") + "UNIT_CODE%5E%7CUNIT_DESC%5E%7CRFID_ID%5E888833B2DDD9014000000078%7CID%5E0016%7CCODE%5E0016%7CDESCX%5E%E6%9D%8E%E9%97%AF%7CSPELL%5E%7CWBX%5E%7CCATEGORY_CODE%5E2%7CCATEGORY_DESC%5E%E6%99%AE%E9%80%9A%E7%97%85%E4%BA%BA%7CINHOSMODEL_CODE%5E3%7CSEX%5E%E7%94%B7%7CCLINIC_NO%5E0798%7CPATIENT_NO%5E0016%7CREGISTER_NO%5E04645%7CCASE_NO%5E069%7CHISINNER_NO%5E%7CHISOUTER_NO%5E%7CCURDEPT_CODE%5E013%7CCURDEPT_DESC%5E%E5%86%85%E7%A7%91%7CCURWARD_CODE%5Earea-1%7CCURWARD_DESC%5EVIP%E7%97%85%E5%8C%BA%7CCURROOM_CODE%5E201%7CCURROOM_DESC%5E201%E6%88%BF%E9%97%B4%7CCURBED%5E2%7CIS_STOP%5E%7CIS_UPDATE%5E%7CUPDATE_TIME%5E";
			System.out.println(str);
			load(str);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println(new Date().getTime());
		String str;
		str = "http://localhost:8080/MPM/patientinfo?patid=2011&patname=sssssssss";
		//System.out.println(str);
		try {
			load(str);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		s = "lokjsdshdj";
//		System.out.println(s.replace("shd","1111"));
		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start_date=new Date(0);
		System.out.println(myfmt.parse("1970-01-01 00:00:00").getTime());
		System.out.println(myfmt.parse("1970-01-01 06:00:00").getTime());
		System.out.println(myfmt.parse("1970-01-01 12:00:00").getTime());
		System.out.println(myfmt.parse("1970-01-01 18:00:00").getTime());
		System.out.println(myfmt.parse("1970-01-01 24:00:00").getTime());
		System.out.println(myfmt.format(start_date));
		
		Date d=myfmt.parse("2014-01-06 00:00:00");
		d.setMonth(d.getMonth()-1);
		System.out.println(myfmt.format(d));
		System.out.println(myfmt.format(new Date(70,0,1,start_date.getHours(),start_date.getMinutes(),start_date.getSeconds())));
	
	}

	private String format(long time) {
		SimpleDateFormat myfmt = new SimpleDateFormat("yyyyMMddhhmmss");
		return myfmt.format(new Date(time)).toString();
	}
	
	private static void load(String Url) throws ParseException, IOException {
		boolean isloadsuccess = false;
		HttpGet httpRequest = new HttpGet(Url);
		try {
			httpResponse = httpclient.execute(httpRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			isloadsuccess = true;
//			String str2 = URLDecoder.decode(httpResponse.toString(), "UTF-8");
//			System.out.println(httpResponse.getParams().getParameter("return_code"));
			 String strResult = EntityUtils.toString(httpResponse.getEntity()); 
			 System.out.println(URLDecoder.decode(strResult, "UTF-8"));
		} else {
			isloadsuccess = false;
		}
	}
}
