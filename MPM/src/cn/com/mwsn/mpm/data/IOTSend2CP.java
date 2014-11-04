package cn.com.mwsn.mpm.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import cn.com.mwsn.mpm.action.TestAction;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.common.Constant.MOBILEFLAG;
import cn.com.mwsn.mpm.common.Constant.ROLE;
import cn.com.mwsn.mpm.common.IDGenerator;
import cn.com.mwsn.mpm.entity.Doctor;
import cn.com.mwsn.mpm.entity.Nurse;
import cn.com.mwsn.mpm.entity.Patient;
import cn.com.mwsn.mpm.entity.RFIDCardPerson;
import cn.com.mwsn.mpm.entity.RFIDHistory;
import cn.com.mwsn.mpm.service.DoctorService;
import cn.com.mwsn.mpm.service.NurseService;
import cn.com.mwsn.mpm.service.PatientService;
import cn.com.mwsn.mpm.service.RFIDCardPersonService;

public class IOTSend2CP {
	private static final Logger log = Logger.getLogger(IOTSend2CP.class);
	
	private RFIDCardPersonService rfidCardPersonService;
	private DoctorService doctorService;
	private NurseService nurseService;
	private PatientService patientService;
	
	private HttpClient httpclient = new DefaultHttpClient();
	private HttpResponse httpResponse = null;
	
	private ApplicationContext applicationContext;

	public IOTSend2CP() {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		rfidCardPersonService = (RFIDCardPersonService)applicationContext.getBean("rfidCardPersonService");
		doctorService = (DoctorService)applicationContext.getBean("doctorService");
		nurseService = (NurseService)applicationContext.getBean("nurseService");
		patientService = (PatientService)applicationContext.getBean("patientService");
	}
	

	public synchronized void loadRFID(RFIDHistory rfidHistory) {

		/**
		 * 发送RFID数据
		 */
		log.debug(Constant.localhost);
		
		
		RFIDCardPerson rfidPerson = rfidCardPersonService.queryPerson(rfidHistory.getRfid_id());
		if(! rfidPerson.isSend2CP()) {
			log.debug("测试1" + rfidHistory.getRfid_id());
			loadUser(rfidHistory.getRfid_id());
		}
		boolean isloadsuccess = false;
		String httpUrl = Constant.URL_PREFIX_RFID.replace("localhost", Constant.localhost);
		String postfix = "";
		boolean flag = true;
		if(rfidPerson.getRole().equals("2")) {
			flag = false;
		}
		
		postfix = Constant.COMP + "|" + Constant.RFID_TITLE + "|" + Constant.VERSION + "|"
		        + Constant.UNIT_CODE + "^" + "" + "|"
				+ Constant.UNIT_DESC + "^" + "" + "|" 
		        + Constant.CATEGORY_CODE + "^" + "" + "|"
				+ Constant.CATEGORY_DESC + "^" + "" + "|"
				+ Constant.TYPE_CODE + "^" + "" + "|"
				+ Constant.TYPE_DESC + "^" + "" + "|" 
				+ Constant.ID + "^" + IDGenerator.getUniqueID()  + "|"
				+ Constant.CODE + "^" + rfidHistory.getRfid_id() + "|" 
				+ Constant.DESCX + "^" + rfidHistory.getDescx() + "|"
				+ Constant.SPELL + "^" + "" + "|" 
				+ Constant.WBX + "^" + "" + "|"
				+ Constant.USERCATEGORY_CODE + "^" +  "" + "|"
				+ Constant.USERCATEGORY_DESC + "^" + "" + "|"
				+ Constant.USEROFFICE_CODE + "^" + "" + "|"
				+ Constant.USEROFFICE_DESC + "^" + "" + "|"
				+ Constant.USEROFFICETYPE_CODE + "^" + "" + "|"
				+ Constant.USEROFFICETYPE_DESC + "^" + "" + "|";
		if(flag == true) {
			postfix = postfix + Constant.USER_RFID + "^" + rfidHistory.getRfid_id() + "|"
							  + Constant.USER_CODE + "^" + rfidPerson.getEmpNO() +"|"
							  + Constant.USER_DESC + "^" + rfidPerson.getName() + "|";
		}
		else {
			postfix = postfix + Constant.USER_RFID + "^" + "" + "|"
					          + Constant.USER_CODE + "^" + "" +"|"
					          + Constant.USER_DESC + "^" + "" + "|";
		}	
		postfix = postfix 
				+ Constant.ACTION_TIME + "^" + format(rfidHistory.getAction_time()) + "|"
				+ Constant.ACTIONPLACETYPE_CODE + "^" + rfidHistory.getActionplacetype_code() + "|"
				+ Constant.ACTIONPLACETYPE_DESC + "^" + rfidHistory.getActionplacetype_desc() + "|"
				+ Constant.ACTIONDEPT_CODE + "^" + rfidHistory.getActiondept_code() + "|"
				+ Constant.ACTIONDEPT_DESC + "^" + rfidHistory.getActiondept_desc() + "|"
				+ Constant.ACTIONWARD_CODE + "^" + rfidHistory.getActionward_code() + "|"
				+ Constant.ACTIONWARD_DESC + "^" + rfidHistory.getActionward_desc() + "|"
				+ Constant.ACTIONPLACE_CODE + "^"+ rfidHistory.getActionplace_code() + "|" 
				+ Constant.ACTIONPLACE_DESC + "^"+ rfidHistory.getActionplace_desc() + "|"
				+ Constant.ACTIONSTATE_CODE + "^"+ rfidHistory.getActionstate_code() + "|"
				+ Constant.ACTIONSTATE_DESC + "^"+ rfidHistory.getActionstate_desc() + "|" ;
		if(flag == false) {
			postfix = postfix + Constant.PAT_RFID + "^" + rfidHistory.getRfid_id() + "|";
		}
		else {
			postfix = postfix + Constant.PAT_RFID + "^" + "" + "|";
		}	
		postfix = postfix 		
				+ Constant.PAT_NAME + "^" + "" + "|"
				+ Constant.CLINIC_NO + "^" + "" + "|"
				+ Constant.PATIENT_NO + "^" + "" + "|"
				+ Constant.REGISTER_NO + "^" + "" + "|"
				+ Constant.CASE_NO + "^" + "" + "|"
				+ Constant.HISINNER_NO + "^" + "" + "|"
				+ Constant.HISOUTER_NO + "^" + "" + "|"
				+ Constant.CURBED + "^" + "" + "|"
				+ Constant.REMARK + "^" + "" + "|" 
				+ Constant.MOBILEFLAG + "^" + "" + "|"
				+ Constant.DATEFLAG + "^" + "" + "|" 
				+ Constant.SORT_ID + "^" + "" + "|"
				+ Constant.IS_STOP + "^" + "0" + "|"
				+ Constant.IS_UPDATE + "^"+ "0" + "|" 
				+ Constant.UPDATE_TIME + "^" + "0";
		
		log.debug(httpUrl + "=" + postfix); 
		try {
			httpUrl = httpUrl + "="
					+ URLEncoder.encode(postfix, "UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		load(httpUrl);
	}
	
	private void loadUser(String tagID) {
		/**
		 * 发送的人员信息数据
		 */
		RFIDCardPerson rfidPerson = rfidCardPersonService.queryPerson(tagID);
		Doctor doctor;
		Nurse nurse;
		Patient patient;
		String httpUrl = "";//Constant.URL_PREFIX_USER;
		String postfix = "";
		if(rfidPerson != null) {
			
			if(rfidPerson.getRole().equals("0")) {
				doctor = doctorService.queryDoctor(rfidPerson.getEmpNO());
				httpUrl = Constant.URL_PREFIX_USER;
				postfix =  
						Constant.UNIT_CODE + "^" + "" + "|"
						+ Constant.UNIT_DESC + "^" + "" + "|"
						+ Constant.RFID_ID + "^" + tagID + "|"
						+ Constant.ID + "^" + IDGenerator.getUniqueID() + "|"
						+ Constant.CODE + "^" + doctor.getDoctorId() + "|"
						+ Constant.DESCX + "^" + doctor.getName() + "|"
						+ Constant.SPELL + "^" + "" + "|"
						+ Constant.WBX + "^" + "" + "|"
						+ Constant.CATEGORY_CODE + "^" + "0" + "|" 
						+ Constant.CATEGORY_DESC + "^" + doctor.getType() + "|"
						+ Constant.INHOSMODEL_CODE + "^" + "" + "|"
						+ Constant.CURDEPT_CODE + "^" + "" + "|"
						+ Constant.CURDEPT_DESC + "^" + "" + "|"
						+ Constant.CURWARD_CODE + "^" + "" + "|"
						+ Constant.CURWARD_DESC + "^" + "" + "|"
						+ Constant.IS_STOP + "^" + "0" + "|"
						+ Constant.IS_UPDATE + "^" + "0" + "|"
						+ Constant.UPDATE_TIME + "^" + "0";			
			}
			if(rfidPerson.getRole().equals("1")) {
				nurse = nurseService.queryNurse(rfidPerson.getEmpNO());
				httpUrl = Constant.URL_PREFIX_USER;
				postfix = 
						Constant.UNIT_CODE + "^" + "" + "|"
						+ Constant.UNIT_DESC + "^" + "" + "|"
						+ Constant.RFID_ID + "^" + tagID + "|"
						+ Constant.ID + "^" + IDGenerator.getUniqueID() + "|"
						+ Constant.CODE + "^" + nurse.getNurseNo() + "|"
						+ Constant.DESCX + "^" + nurse.getName() + "|"
						+ Constant.SPELL + "^" + "" + "|"
						+ Constant.WBX + "^" + "" + "|"
						+ Constant.CATEGORY_CODE + "^" + "0" + "|" 
						+ Constant.CATEGORY_DESC + "^" + nurse.getType() + "|"
						+ Constant.INHOSMODEL_CODE + "^" + "" + "|"
						+ Constant.CURDEPT_CODE + "^" + "" + "|"
						+ Constant.CURDEPT_DESC + "^" + "" + "|"
						+ Constant.CURWARD_CODE + "^" + "" + "|"
						+ Constant.CURWARD_DESC + "^" + "" + "|"
						+ Constant.IS_STOP + "^" + "0" + "|"
						+ Constant.IS_UPDATE + "^" + "0" + "|"
						+ Constant.UPDATE_TIME + "^" + "0";	
			}
			if(rfidPerson.getRole().equals("2")) {
				patient = patientService.queryPatient(rfidPerson.getEmpNO());
				httpUrl = Constant.URL_PREFIX_PATIENT.replace("localhost", Constant.localhost);
				postfix = 
						Constant.UNIT_CODE + "^" + "" + "|"
						+ Constant.UNIT_DESC + "^" + "" + "|"
						+ Constant.RFID_ID + "^" + tagID + "|"
						+ Constant.ID + "^" + IDGenerator.getUniqueID() + "|"
						+ Constant.CODE + "^" + patient.getPatientNo() + "|"
						+ Constant.DESCX + "^" + patient.getName() + "|"
						+ Constant.SPELL + "^" + "" + "|"
						+ Constant.WBX + "^" + "" + "|"
						+ Constant.INHOSMODEL_CODE + "^" + 3 + "|"
						+ Constant.SEX + "^" + patient.getSex() + "|"
						+ Constant.CLINIC_NO + "^" + patient.getClinicNo() + "|"
						+ Constant.PATIENT_NO + "^" + patient.getPatientNo() + "|"
						+ Constant.REGISTER_NO + "^" + patient.getRegisterNo() + "|"
						+ Constant.CASE_NO + "^" + patient.getCaseNo() + "|"
						+ Constant.HISINNER_NO + "^" + "" + "|"
						+ Constant.HISOUTER_NO + "^" + "" + "|"
						+ Constant.CURDEPT_CODE + "^" + patient.getCurdeptCode() + "|"
						+ Constant.CURDEPT_DESC + "^" + patient.getCurdeptDesc() + "|"
						+ Constant.CURWARD_CODE + "^" + patient.getBed().getInpatientCell().getInpatientArea().getName() + "|"
						+ Constant.CURWARD_DESC + "^" + patient.getBed().getInpatientCell().getInpatientArea().getDescc() + "|"
						+ Constant.CURROOM_CODE + "^" + patient.getBed().getInpatientCell().getCurroomCode() + "|"
						+ Constant.CURROOM_DESC + "^" + patient.getBed().getInpatientCell().getCurroomDesc() + "|"
						+ Constant.CURBED + "^" + patient.getCurBed() + "|"
						+ Constant.IS_STOP + "^" + "0" + "|"
						+ Constant.IS_UPDATE + "^" + "0" + "|"
						+ Constant.UPDATE_TIME + "^" + "0";			
			}
		}
		
		
		
		rfidPerson.setSend2CP(true);
		rfidCardPersonService.merge(rfidPerson);
		
		try {
			log.debug("测试4 " + httpUrl + "=" + Constant.COMP + "|" + Constant.USER_TITLE + "|" + Constant.VERSION + "|" + postfix);
			httpUrl = httpUrl + "=" + URLEncoder.encode(Constant.COMP + "|" + Constant.USER_TITLE + "|" + Constant.VERSION + "|" + postfix, "UTF-8");
			
			
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		
		
		load(httpUrl);
		
	}

	private void load(String Url) {
		/*boolean isloadsuccess = false;
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
			log.debug("上传成功");
		} else {
			log.debug("上传失败");
			isloadsuccess = false;
		}*/
	}
	private String format(long time) {
		SimpleDateFormat myfmt = new SimpleDateFormat("yyyyMMddhhmmss");
		return myfmt.format(new Date(time)).toString();
	}
}
