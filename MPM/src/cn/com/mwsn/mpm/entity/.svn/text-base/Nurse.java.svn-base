package cn.com.mwsn.mpm.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cn.com.mwsn.frame.entity.IdEntity;


/**
 * 护士实体类，从his获得
 * @author wuda
 *
 */
@Entity
public class Nurse extends IdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5348929411218354434L;
	private String name;
	private String descc;
	private String sex;
	private String nurseId;         //护士工号
	private String type;			//护士类型名称
	private InpatientArea inpatientArea;
	
	private Set<Patient> patients;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescc() {
		return descc;
	}
	public void setDescc(String descc) {
		this.descc = descc;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@ManyToOne
	public InpatientArea getInpatientArea() {
		return inpatientArea;
	}
	public void setInpatientArea(InpatientArea inpatientArea) {
		this.inpatientArea = inpatientArea;
	}
	public String getNurseId() {
		return nurseId;
	}
	public void setNurseId(String nurseId) {
		this.nurseId = nurseId;
	}
	@OneToMany(mappedBy="nurseCharge")
	public Set<Patient> getPatients() {
		return patients;
	}
	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	
}
