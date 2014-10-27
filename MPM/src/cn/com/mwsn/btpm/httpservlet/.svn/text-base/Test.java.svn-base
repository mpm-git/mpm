package cn.com.mwsn.btpm.httpservlet;

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


public class Test {
	
	private static HttpClient httpclient = new DefaultHttpClient();
	private static HttpResponse httpResponse = null;
	
	private static String s = "5555"; 
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		
		String str;
		str = "http://192.168.22.104:8080/DataPlatform/getAllClinicNos";
		//System.out.println(str);
		try {
			load(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
//		s = "lokjsdshdj";
//		System.out.println(s.replace("shd","1111"));
	
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
            System.out.println(strResult);
			String[] temp1 = new String[strResult.length()];
			String[][] data = new String[strResult.length()][11];
			
	        temp1 = strResult.split("\n");
	        for(int i=0;i<temp1.length;i++){	
	    		if(temp1[i].length()>=1){
	    			data[i] = temp1[i].split("&");
	    		}
	    		for(int j=0;j<data[i].length;j++){
		        	if(data[i][j].length()>=2){
		        		//System.out.println(i+""+j+":"+ data[i][j]);
		        		String[] key_value = data[i][j].split("=");
		        		if(key_value[0].equals("bedNo")){
		        			System.out.println(key_value[1]);
		        		}
		        	}
		        }
	        }
			
//			String[] strArray = new String[11];
//			strArray = strResult.split("&");
//			for(String strtmp:strArray){
//				System.out.println(strtmp);
//			}
//			
//			for(String strtmp:strArray){
//				strtmp = strtmp.substring(strtmp.indexOf("=")+1);
//				System.out.println(strtmp);
//			}
			
		} else {
			isloadsuccess = false;
		}
		httpRequest.abort();
	}
}