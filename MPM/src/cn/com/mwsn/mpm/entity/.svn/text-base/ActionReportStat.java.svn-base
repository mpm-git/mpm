package cn.com.mwsn.mpm.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import cn.com.mwsn.dataplatform.devicemgmt.entity.RFIDAntenna;
import cn.com.mwsn.frame.entity.IdEntity;

@Entity
public class ActionReportStat extends IdEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8977303512761772779L;
	private String USEROFFICETYPE_CODE; // 姓名
	private String USER_RFID; // RFID编号
	private String USER_CODE; // 用户工号
	private String USER_DESC;  ///姓名
	private Long ACTION_TIME;
	private String ACTIONEVENT_CODE;//100 医生   200护士
	private String ACTIONPLACE_CODE;//房间
	private String CURBED;       //床号
	private Integer CheckNum;
	
	private Integer type; // 0:病房 1:走廊
	private String name;      //病房名称  如w1,w2
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer high;
	private String curroomCode;   //cell代码   如201,202
	private String curroomDesc; // cell描述
	private InpatientArea inpatientArea;
	private RFIDAntenna antenna;
	
	private String timedesc;   //yyyy-mm-dd HH;mm

	public Integer getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHigh() {
		return high;
	}

	public void setHigh(Integer high) {
		this.high = high;
	}

	public String getCurroomCode() {
		return curroomCode;
	}

	public void setCurroomCode(String curroomCode) {
		this.curroomCode = curroomCode;
	}

	public String getCurroomDesc() {
		return curroomDesc;
	}

	public void setCurroomDesc(String curroomDesc) {
		this.curroomDesc = curroomDesc;
	}

	@ManyToOne
	public InpatientArea getInpatientArea() {
		return inpatientArea;
	}

	public void setInpatientArea(InpatientArea inpatientArea) {
		this.inpatientArea = inpatientArea;
	}

	@OneToOne
	public RFIDAntenna getAntenna() {
		return antenna;
	}

	public void setAntenna(RFIDAntenna antenna) {
		this.antenna = antenna;
	}

	

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

	public String getTimedesc() {
		return timedesc;
	}

	public void setTimedesc(String timedesc) {
		this.timedesc = timedesc;
	}
	
}
