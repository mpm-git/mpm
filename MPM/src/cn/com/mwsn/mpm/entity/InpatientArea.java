package cn.com.mwsn.mpm.entity;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import cn.com.mwsn.frame.entity.IdEntity;


/**
 * 病区实体类，从his处获得
 * @author wuda
 *
 */
@Entity
public class InpatientArea extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4299686741296080265L;
	
	private String name;                //病区代号
	private String descc;               //病区描述
	private Set<InpatientCell> cells;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy="inpatientArea")
	public Set<InpatientCell> getCells() {
		return cells;
	}
	public void setCells(Set<InpatientCell> cells) {
		this.cells = cells;
	}
	public String getDescc() {
		return descc;
	}
	public void setDescc(String descc) {
		this.descc = descc;
	}

	
}
