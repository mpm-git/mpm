package cn.com.mwsn.mpm.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cz.utils.gson.GsonUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.mpm.entity.InpatientCell;
import cn.com.mwsn.mpm.entity.RFIDHistory;
import cn.com.mwsn.mpm.service.CurrentPositionService;


/**
 * 该类存放系统中使用的全局常量
 * @author wuda
 *
 */
public class Constant {
	
	//用于在session中存放成功登陆系统的用户信息
	public static String LOGINED_USER="login_user";
	public static final int ICON_X = 20; // px
	public static final int ICON_Y = 20; // px
	public final static int STATUS_IN = 0;       //进门
	public final static int STATUS_OUT = 1;        //出门
	public final static int STATUS_INVALID = -1;   //初始无意义值
	public final static int DEPT = 1;              //科室
	public final static int INPATIENTAREA = 0;     //病房
	
	public static List<Object> buffer = new ArrayList<Object>();      //缓冲区
	
	
	//动作代码
	public static String ACTION_IN_CODE = "in";
	public static String ACTION_OUT_CODE = "out";
	public static String ACTION_LOBBY_CODE = "lobby";
	//动作描述
	public static String ACTION_IN_DESC = "进入";
	public static String ACTION_OUT_DESC = "走出";
	public static String ACTION_LOBBY_DESC = "在走廊";
	
	//规定查房次数
	public static int doctorOrder = 50;
	public static int nurseOrder = 100;
	public static int minCheckWardTime = 5 * 1000;           //最短查房时间5s
	
	
	public static class ROLE {
		public static String DOCTOR = "0"; 
		public static String NURSE = "1";
		public static String PATIENT = "2";
	};
	
	
	/**
	 * 就诊形式，多个时用逗号隔开
	 * @author haiyanzhihun
	 *
	 */
	public static enum INHOSMODEL_CODE {DEFAULT, CLINIC, EMERGENCY, HOSPITALIZED, TEST};          
	public static enum RECORD_STATE {START, STOP};
	public static enum MOBILEFLAG {FINDING, FOUND};
	public static enum DATEFLAG {FINDBED, ALARM};
	public static String allXAxisNames_1="特级,一级,二级,三级,四级";
	public static String allXAxisNames_2="前庭大腺囊肿,卵巢恶性肿瘤,卵巢肿物,外阴恶性肿瘤,外阴血肿,子宫内膜恶性肿瘤,子宫内膜病变,子宫平滑肌瘤,子宫恶性肿瘤,子宫腺肌瘤,子宫颈CIN,子宫颈恶性肿瘤,子宫颈鳞状上皮不典型增生,异位妊娠,盆腔肿物";
	public static String allXAxisNames_3="10,20,30,40,50,60,70,80,90,100";
	public static String allXAxisNames_5="普通护士,责任护士";
	public static String allXAxisNamesValue_1="0,1,2,3,4";
	public static String allXAxisNamesValue_2="前庭大腺囊肿,卵巢恶性肿瘤,卵巢肿物,外阴恶性肿瘤,外阴血肿,子宫内膜恶性肿瘤,子宫内膜病变,子宫平滑肌瘤,子宫恶性肿瘤,子宫腺肌瘤,子宫颈CIN,子宫颈恶性肿瘤,子宫颈鳞状上皮不典型增生,异位妊娠,盆腔肿物";
	public static String allXAxisNamesValue_3="10,20,30,40,50,60,70,80,90,100";
	public static String allXAxisNamesValue_5="普通护士,责任护士";
	public static String executeType="["
			+ "{name:'晨间护理',info:'整理床单位'},"
			+ "{name:'协助进食水',info:'协助进食水'},"
			+ "{name:'晚间护理 ',info:'整理床单位'},"
			+ "{name:'卧位护理',info:'协助患者翻身 '},"
			+ "{name:'排泄护理',info:'失禁护理、床上使用便器、尿管护理'},"
			+ "{name:'床上擦浴',info:'床上擦浴'},"
			+ "{name:'生命体征',info:'体温测量、脉搏测量、呼吸测量、血压测量'},"
			+ "{name:'各种治疗',info:'血氧饱合度测量、静脉输液、静脉输血、动静脉采血、口服给药、肌肉注射、皮试、静脉注射、胰岛素注射、心电监护、微量泵注射、灌肠、导尿'},"
			+ "{name:'标本核对',info:'血液标本、尿标本、便标本 '},"
			+ "{name:'各种护理',info:'物理降温、雾化护理、胃管护理、尿管护理、口腔护理、皮肤护理'},"
			+ "{name:'出入量统计',info:'出入量统计'},"
			+ "{name:'尿量统计',info:'尿量统计'},"
			+ "{name:'巡视病房',info:'输液观察、病情观察、床单位整理、心理护理、安全隐患排查'},"
			+ "{name:'健康教育',info:'入院指导、专科宣教、饮食宣教、药物宣教、安全宣教、功能锻炼、春腺活检、中药熏蒸'},"
			+ "{name:'出院随访',info:'出院指导、用药指导、保健指导'},"
			+ "{name:'巡床',info:'普通巡床'},"
			+ "{name:'执行',info:'60滴分'},"
			+ "{name:'查收',info:'1'},"
			+ "{name:'结束',info:'1'},"
			+ "{name:'暂停',info:'1'},"
			+ "{name:'刻写',info:'1'}"
			+ "]";
	
	//地点代码
	
	public static String PLACE_201_CODE = "201";
	public static String PLACE_202_CODE = "202";
	//地点描述
	public static String PLACE_201 = "Vip病区，201室";
	public static String PLACE_202 = "Vip病区，202室";
	
	//与曼荼罗接口
	public static String localhost = "192.168.1.117";
	public static String URL_PREFIX_RFID = "http://localhost/WS_CPIOT_ADAPTER/WS_CPIOT.asmx/IOTRFID_DATAIMPORT?rfidMsg";    
	public static String URL_PREFIX_USER = "http://localhost/WS_CPIOT_ADAPTER/WS_CPIOT.asmx/IOTUSER_DATAIMPORT?userMsg"; 
	public static String URL_PREFIX_PATIENT = "http://localhost/WS_CPIOT_ADAPTER/WS_CPIOT.asmx/IOTPATIENT_DATAIMPORT?patientMsg"; 
	
	
	public static List<RFIDHistory> updatebuf = new ArrayList<RFIDHistory>();      //上传数据缓冲区
	
	/**
	 * 发送的RFID数据
	 *@IOT|物理感知|V100|UNIT_CODE^000|UNIT_DESC^|CATEGORY_CODE^|CATEGORY_DESC^|TYPE_CODE^|TYPE_DESC^|ID^|CODE^|
	 *DESCX^|SPELL^|WBX^|ACTION_TIME^|ACTIONPLACETYPE_CODE^|ACTIONDEPT_CODE^|ACTIONDEPT_DESC^| ACTIONWARD_CODE^|
	 *ACTIONWARD_DESC^|ACTIONPLACE_CODE^|ACTIONSTATE_CODE^|
     *ACTIONSTATE_DESC^|REMARK^|SORT_ID^|IS_STOP^|IS_UPDATE^|UPDATE_TIME^
	 */
	
	public static String COMP = "@IOT";
	public static String RFID_TITLE = "物理感知";
	public static String VERSION = "V100";
	public static String UNIT_CODE = "UNIT_CODE";
	public static String UNIT_DESC = "UNIT_DESC";
	public static String CATEGORY_CODE = "CATEGORY_CODE";
	public static String CATEGORY_DESC = "CATEGORY_DESC";
	public static String TYPE_CODE = "TYPE_CODE";
	public static String TYPE_DESC = "TYPE_DESC";
	public static String ID = "ID";
	public static String CODE = "CODE";
	public static String DESCX = "DESCX";
	public static String SPELL = "SPELL";
	public static String WBX = "WBX";
	public static String ACTION_TIME = "ACTION_TIME";
	public static String ACTIONPLACETYPE_CODE = "ACTIONPLACETYPE_CODE";
	public static String ACTIONPLACETYPE_DESC = "ACTIONPLACETYPE_DESC";
	public static String ACTIONDEPT_CODE = "ACTIONDEPT_CODE";
	public static String ACTIONDEPT_DESC = "ACTIONDEPT_DESC";
	public static String ACTIONWARD_CODE = "ACTIONWARD_CODE";
	public static String ACTIONWARD_DESC = "ACTIONWARD_DESC";
	public static String ACTIONPLACE_CODE = "ACTIONPLACE_CODE";
	public static String ACTIONPLACE_DESC = "ACTIONPLACE_DESC";
	public static String ACTIONSTATE_CODE = "ACTIONSTATE_CODE";
	public static String ACTIONSTATE_DESC = "ACTIONSTATE_DESC";
	public static String REMARK = "REMARK";
	public static String MOBILEFLAG = "MOBILEFLAG";
	public static String DATEFLAG = "DATEFLAG";
	public static String SORT_ID = "SORT_ID";
	public static String IS_STOP = "IS_STOP";
	public static String IS_UPDATE = "IS_UPDATE";
	public static String UPDATE_TIME = "UPDATE_TIME";
	public static String PAT_RFID = "PAT_RFID";                     //病人的RFID
	public static String USERCATEGORY_CODE = "USERCATEGORY_CODE";
	public static String USERCATEGORY_DESC = "USERCATEGORY_DESC";
	public static String USEROFFICE_CODE = "USEROFFICE_CODE";
	public static String USEROFFICE_DESC = "USEROFFICE_DESC";
	public static String USEROFFICETYPE_CODE = "USEROFFICETYPE_CODE";
	public static String USEROFFICETYPE_DESC = "USEROFFICETYPE_DESC";
	public static String USER_RFID = "USER_RFID";                   //医生和护士的RFID
	
	
	
/**
 * 发送的人员信息数据
 * @IOT|人员信息|V100|UNIT_CODE^|UNIT_DESC^|RFID_ID^|USERTYPE_CODE^|USER_CODE^| USER_DESC ^|ID^|CODE^|NAME^|
 * PASSWORD^|SPELL^|WBX^|INHOSMODEL_CODE^|IS_STOP^|IS_UPDATE^|UPDATE_TIME^|SEX^|CLINIC_NO^|PATIENT_NO^|
 * REGISTER_NO^| CASE_NO^|HISINNER_NO^|HISOUTER_NO^| CURROOM_CODE^|CURROOM_DESC^|CURBED^|CURDEPT_CODE^|CURDEPT_DESC^|
 * CURWARD_CODE^|CURWARD_DESC^
 */
	public static String USER_TITLE = "人员信息";
	public static String RFID_ID = "RFID_ID";
	public static String USERTYPE_CODE = "USERTYPE_CODE";
	public static String USER_CODE = "USER_CODE";
	public static String USER_DESC = "USER_DESC";
	public static String NAME = "NAME";
	public static String PASSWORD = "PASSWORD";
	public static String INHOSMODEL_CODE = "INHOSMODEL_CODE";
	public static String SEX = "SEX";
	public static String CLINIC_NO = "CLINIC_NO";
	public static String PATIENT_NO = "PATIENT_NO";
	public static String REGISTER_NO = "REGISTER_NO";
	public static String CASE_NO = "CASE_NO";
	public static String HISINNER_NO = "HISINNER_NO";
	public static String HISOUTER_NO = "HISOUTER_NO";
	public static String CURROOM_CODE = "CURROOM_CODE";
	public static String CURROOM_DESC = "CURROOM_DESC";
	public static String CURBED = "CURBED";
	public static String CURDEPT_CODE = "CURDEPT_CODE";
	public static String CURDEPT_DESC = "CURDEPT_DESC";
	public static String CURWARD_CODE = "CURWARD_CODE";
	public static String CURWARD_DESC = "CURWARD_DESC";
	public static String PAT_NAME = "PAT_NAME";
	
	
	
	public static List<Map<String,String>> getExecuteType()
	{
		List<?> jsonToList = GsonUtil.jsonToList(Constant.executeType);
		List<Map<String,String>> lists =new ArrayList<Map<String,String>>();
		for (Object ss : jsonToList) {
			Map<String,String> map = (Map<String, String>) GsonUtil.jsonToMap(ss.toString());
			lists.add(map);
		}
		return lists;
	}
	
	
}
