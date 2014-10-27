package cn.com.mwsn.mpm.entity;

import javax.persistence.OneToOne;

import cn.com.mwsn.dataplatform.devicemgmt.entity.RFIDCard;


/**
 * 医生RFID卡实体类，本地配置
 * @author wuda
 *
 */
public class RFIDCardDoctor extends RFIDCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3698038089177583996L;
	
	private RFIDCard card;
	private Doctor doctor;

	@OneToOne
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public RFIDCard getCard() {
		return card;
	}
	public void setCard(RFIDCard card) {
		this.card = card;
	}
	
	
	

}
