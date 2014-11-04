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
	private int number;      //病床号  
	private String name;      //病床名称  如w1,w2
	private String bedCode;   //cell代码   如201,202
	private String bedDesc; // cell描述
	private InpatientCell inpatientCell;
	private RFIDAntenna antenna;

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

	@ManyToOne
	public InpatientCell getInpatientCell() {
		return inpatientCell;
	}

	public void setInpatientCell(InpatientCell inpatientCell) {
		this.inpatientCell = inpatientCell;
	}

	@OneToOne
	public RFIDAntenna getAntenna() {
		return antenna;
	}

	public void setAntenna(RFIDAntenna antenna) {
		this.antenna = antenna;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getBedCode() {
		return bedCode;
	}

	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}

	public String getBedDesc() {
		return bedDesc;
	}

	public void setBedDesc(String bedDesc) {
		this.bedDesc = bedDesc;
	}
}
