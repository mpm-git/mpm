package cn.com.mwsn.mpm.service.impl;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.entity.ActionReport;
import cn.com.mwsn.mpm.entity.PatientAlert;
import cn.com.mwsn.mpm.entity.RFIDCardPerson;
import cn.com.mwsn.mpm.service.ActionReportService;

@Service
@Transactional
public class ActionReportServiceImpl extends TransactionalServiceImpl 
		implements ActionReportService{
	
	private static Logger log = Logger.getLogger(ActionReportServiceImpl.class);
    //数据接口
	private static HttpClient httpclient = new DefaultHttpClient();
	private static HttpResponse httpResponse = null;
	
	@Override
	public void updateActionReport(Incident incident){
		
		log.debug("进入ActionReportServiceImpl");
		RFIDCardPerson person = this.findBy(RFIDCardPerson.class, "tagID", incident.getTagID());

		if(person.getRole().equals(Constant.ROLE.DOCTOR) && incident.getType() == Constant.STATUS_OUT){	
			
			ActionReport actionReport = new ActionReport();
			actionReport.setUSER_DESC(person.getName());
			actionReport.setACTION_TIME(incident.getTime());
			actionReport.setUSEROFFICETYPE_CODE("DOC");
			actionReport.setACTIONPLACE_CODE(incident.getWardName());
			actionReport.setUSER_CODE(person.getEmpNO());
			actionReport.setUSER_RFID(incident.getTagID());
			actionReport.setACTIONEVENT_CODE("100");
				
			log.debug("测试2 ------- " + actionReport);
			this.save(actionReport);

		}
		else if(incident.getType() == Constant.STATUS_OUT && person.getRole().equals(Constant.ROLE.NURSE)){
			long currentTime = System.currentTimeMillis();
			
   			ActionReport actionReport = new ActionReport();
   			actionReport.setUSER_DESC(person.getName());
   			actionReport.setACTION_TIME(currentTime);
   			actionReport.setUSEROFFICETYPE_CODE("NUR");
	        actionReport.setACTIONPLACE_CODE(incident.getWardName());
	        actionReport.setUSER_CODE(person.getEmpNO());
	        actionReport.setUSER_RFID(incident.getTagID());
	        actionReport.setACTIONEVENT_CODE("200");
	        actionReport.setCURBED("1");
                        
	        log.debug("测试2 ------- " + actionReport);		
	        save(actionReport);
						
		}
		
	}


	
	private String load(String Url) throws ParseException, IOException {
		String strResult = "";
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
			strResult = EntityUtils.toString(httpResponse.getEntity());
			strResult = URLDecoder.decode(strResult, "UTF-8");						
		} else {
			isloadsuccess = false;
		}
		httpRequest.abort();
		return strResult;
	}
	
}
