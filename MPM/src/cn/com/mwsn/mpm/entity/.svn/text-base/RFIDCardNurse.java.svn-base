package cn.com.mwsn.mpm.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import cn.com.mwsn.dataplatform.devicemgmt.entity.RFIDCard;

/**
 * 护士RFID卡实体类，本地配置
 * @author wuda
 *
 */
@Entity
public class RFIDCardNurse extends RFIDCard {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5811967678467552874L;
	
	private RFIDCard card;
	private Nurse nurse;

	@OneToOne
	public Nurse getNurse() {
		return nurse;
	}
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}
	public RFIDCard getCard() {
		return card;
	}
	public void setCard(RFIDCard card) {
		this.card = card;
	}
	
}
