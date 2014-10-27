package cn.com.mwsn.mpm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import cn.com.mwsn.frame.entity.IdEntity;

/**
 * 护士告警类，本地生成
 * 
 * @author zhangke
 * 
 */
@Entity
public class NurseAlert extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6835360701884190796L;
	private String name; // 姓名
	private InpatientArea inpatientArea; // 病区
	private Integer type; // 事件类型
	private Date startTime;
	private Date endTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	public InpatientArea getInpatientArea() {
		return inpatientArea;
	}

	public void setInpatientArea(InpatientArea inpatientArea) {
		this.inpatientArea = inpatientArea;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	

}
