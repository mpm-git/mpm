package cn.com.mwsn.mpm.entity;

import cn.com.mwsn.frame.entity.IdEntity;

public class SickroomEquipment extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6220934392156920816L;
	
	private String no;  //编号
	private String time; //时间
	private String equipNo;  //设备ID
	private String message;  //信息
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getEquipNo() {
		return equipNo;
	}
	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	 



}
