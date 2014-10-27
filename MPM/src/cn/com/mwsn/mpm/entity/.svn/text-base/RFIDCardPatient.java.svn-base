package cn.com.mwsn.mpm.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import cn.com.mwsn.dataplatform.devicemgmt.entity.RFIDCard;

/**
 * 病人RFID卡实体类,本地配置
 * 
 * @author wuda
 * 
 */
@Entity
public class RFIDCardPatient extends RFIDCard {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4117529611271250717L;

	private RFIDCard card;
	private Patient patient;

	@OneToOne
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public RFIDCard getCard() {
		return card;
	}

	public void setCard(RFIDCard card) {
		this.card = card;
	}

}
