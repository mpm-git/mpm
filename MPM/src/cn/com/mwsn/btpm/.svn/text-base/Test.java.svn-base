package cn.com.mwsn.btpm;

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
	 */
	public static void main(String[] args) throws ParseException, IOException {
		
		String str;
		str = "http://localhost:8080/MPM/patientinfo?patid=2011&patname=张三";
		try {
			load(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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
			String strResult = EntityUtils.toString(httpResponse.getEntity());
			strResult = URLDecoder.decode(strResult, "UTF-8");
			/*System.out.println(strResult);
			
			String[] strArray = new String[11];
			strArray = strResult.split("&");
			for(String strtmp:strArray){
				System.out.println(strtmp);
			}
			
			for(String strtmp:strArray){
				strtmp = strtmp.substring(strtmp.indexOf("=")+1);
				System.out.println(strtmp);
			}*/
			
		} else {
			isloadsuccess = false;
		}
	}
}