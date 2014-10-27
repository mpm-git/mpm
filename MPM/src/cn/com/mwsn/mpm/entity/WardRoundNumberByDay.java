package cn.com.mwsn.mpm.entity;

import javax.persistence.Entity;

import cn.com.mwsn.frame.entity.IdEntity;
import cn.com.mwsn.mpm.common.Constant.ROLE;

/**
 * 每天查询统计类，从his处获得
 * 
 * @author zhangke
 * 
 */
@Entity
public class WardRoundNumberByDay extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1361186633974617211L;
	private String name;                           // 姓名
	private String empID;                          //医生/护士的工号
	private String type;                          // 
	private Integer numorder;                      // 规定查房次数
	private Integer numreal;                       // 已查房次数

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNumorder() {
		return numorder;
	}

	public void setNumorder(Integer numorder) {
		this.numorder = numorder;
	}

	public Integer getNumreal() {
		return numreal;
	}

	public void setNumreal(Integer numreal) {
		this.numreal = numreal;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

}
