package cn.com.mwsn.dataplatform.devicemgmt.entity;

import javax.persistence.Entity;

import cn.com.mwsn.frame.entity.IdEntity;

/**
 * RFID卡 类
 * @author zhangke
 *
 */
@Entity
public class RFIDCard extends IdEntity {
	
	private static final long serialVersionUID = 6795602367506591878L;
	

	private String tagID;              //RFID卡的ID
	private Float frequency;			//频率，0:13.56; 1:433; 2:900	
	
	public String getTagID() {
		return tagID;
	}
	public void setTagID(String tagID) {
		this.tagID = tagID;
	}
	public Float getFrequency() {
		return frequency;
	}
	public void setFrequency(Float frequency0) {
		this.frequency = frequency0;
	}

}
