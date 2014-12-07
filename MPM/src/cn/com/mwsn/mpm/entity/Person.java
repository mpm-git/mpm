package cn.com.mwsn.mpm.entity;


import javax.persistence.Entity;

import cn.com.mwsn.frame.entity.IdEntity;


/**
 * 员工实体
 * @author wuda
 *
 */
@Entity
public class Person extends IdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5348929411218354434L;
	private String name;
	private String descr;
	private String sex;
	private int birthday;
	private String inpatientArea;//病区
	private String empNO;//员工号
	private int tagID;	//定位标签号
	private String type;	//类型
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getBirthday() {
		return birthday;
	}
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}
	public String getEmpNO() {
		return empNO;
	}
	public void setEmpNO(String empNO) {
		this.empNO = empNO;
	}
	public int getTagID() {
		return tagID;
	}
	public void setTagID(int tagID) {
		this.tagID = tagID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInpatientArea() {
		return inpatientArea;
	}
	public void setInpatientArea(String inpatientArea) {
		this.inpatientArea = inpatientArea;
	}

}
