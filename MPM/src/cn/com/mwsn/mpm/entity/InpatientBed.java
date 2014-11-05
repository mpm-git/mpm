package cn.com.mwsn.mpm.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import cn.com.mwsn.dataplatform.devicemgmt.entity.RFIDAntenna;
import cn.com.mwsn.frame.entity.IdEntity;

/**
 * 病床实体类
 * @author dell
 *
 */
@Entity
public class InpatientBed extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1603512678560820920L;

	private Integer type; // 0:病床 1:其他
	private String number;      //病床号  
	private String bedDesc;     //病床名称  如w1,w2
	private String inpatientArea;//病区名
	private String InpatientCell;//病房名


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}

//	@ManyToOne
//	public InpatientCell getInpatientCell() {
//		return inpatientCell;
//	}
//
//	public void setInpatientCell(InpatientCell inpatientCell) {
//		this.inpatientCell = inpatientCell;
//	}
//
//	@OneToOne
//	public RFIDAntenna getAntenna() {
//		return antenna;
//	}
//
//	public void setAntenna(RFIDAntenna antenna) {
//		this.antenna = antenna;
//	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

//	public String getBedCode() {
//		return bedCode;
//	}
//
//	public void setBedCode(String bedCode) {
//		this.bedCode = bedCode;
//	}

	public String getBedDesc() {
		return bedDesc;
	}

	public void setBedDesc(String bedDesc) {
		this.bedDesc = bedDesc;
	}


	public String getInpatientArea() {
		return inpatientArea;
	}


	public void setInpatientArea(String inpatientArea) {
		this.inpatientArea = inpatientArea;
	}


	public String getInpatientCell() {
		return InpatientCell;
	}


	public void setInpatientCell(String inpatientCell) {
		InpatientCell = inpatientCell;
	}
}
