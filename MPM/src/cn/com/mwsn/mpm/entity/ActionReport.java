package cn.com.mwsn.mpm.entity;

import javax.persistence.Entity;
import cn.com.mwsn.frame.entity.IdEntity;

@Entity
public class ActionReport extends IdEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8977303512761772779L;
	private String USEROFFICETYPE_CODE; // 姓名
	private String USER_RFID; // 只对病房
	private String USER_CODE; // 事件类型
	private String USER_DESC;
	private Long ACTION_TIME;
	private String ACTIONEVENT_CODE;//100 医生   200护士
	private String ACTIONPLACE_CODE;//房间
	private String CURBED;       //床号
	private Integer CheckNum;

	public String getUSEROFFICETYPE_CODE() {
		return USEROFFICETYPE_CODE;
	}

	public void setUSEROFFICETYPE_CODE(String USEROFFICETYPE_CODE) {
		this.USEROFFICETYPE_CODE = USEROFFICETYPE_CODE;
	}

	public String getUSER_RFID() {
		return USER_RFID;
	}

	public void setUSER_RFID(String USER_RFID) {
		this.USER_RFID = USER_RFID;
	}

	public String getUSER_CODE() {
		return USER_CODE;
	}

	public void setUSER_CODE(String USER_CODE) {
		this.USER_CODE = USER_CODE;
	}

	public String getUSER_DESC() {
		return USER_DESC;
	}

	public void setUSER_DESC(String USER_DESC) {
		this.USER_DESC = USER_DESC;
	}

	public Long getACTION_TIME() {
		return ACTION_TIME;
	}

	public void setACTION_TIME(Long ACTION_TIME) {
		this.ACTION_TIME = ACTION_TIME;
	}
	
	public String getACTIONEVENT_CODE() {
		return ACTIONPLACE_CODE;
	}

	public void setACTIONEVENT_CODE(String ACTIONPLACE_CODE) {
		this.ACTIONPLACE_CODE = ACTIONPLACE_CODE;
	}
	
	public String getACTIONPLACE_CODE() {
		return ACTIONEVENT_CODE;
	}

	public void setACTIONPLACE_CODE(String ACTIONEVENT_CODE) {
		this.ACTIONEVENT_CODE = ACTIONEVENT_CODE;
	}
	
	public String getCURBED() {
		return CURBED;
	}

	public void setCURBED(String CURBED) {
		this.CURBED = CURBED;
	}

	public Integer getCheckNum() {
		return CheckNum;
	}

	public void setCheckNum(Integer CheckNum) {
		this.CheckNum = CheckNum;
	}
}
