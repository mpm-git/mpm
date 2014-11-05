package cn.com.mwsn.mpm.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import cn.com.mwsn.dataplatform.devicemgmt.entity.RFIDAntenna;
import cn.com.mwsn.frame.entity.IdEntity;

/**
 * 病房实体类
 * @author dell
 *
 */
@Entity
public class InpatientCell extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1603512678560820920L;

	private Integer type; // 0:病房 1:走廊
	private String name;      //病房名称  如w1,w2
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer high;
	private String curroomCode;   //cell代码   如201,202
	private String curroomDesc; // cell描述
	private String inpatientArea;
	private String antennaNo;

//	@OneToMany(mappedBy="inpatientCell")
//	public Set<InpatientBed> getBeds() {
//		return beds;
//	}
//	public void setBeds(Set<InpatientBed> beds) {
//		this.beds = beds;
//	}
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

	public String getInpatientArea() {
		return inpatientArea;
	}

	public void setInpatientArea(String inpatientArea) {
		this.inpatientArea = inpatientArea;
	}

	public String getAntennaNo() {
		return antennaNo;
	}

	public void setAntennaNo(String antennaNo) {
		this.antennaNo = antennaNo;
	}

//	@ManyToOne
//	public InpatientArea getInpatientArea() {
//		return inpatientArea;
//	}
//
//	public void setInpatientArea(InpatientArea inpatientArea) {
//		this.inpatientArea = inpatientArea;
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

}
