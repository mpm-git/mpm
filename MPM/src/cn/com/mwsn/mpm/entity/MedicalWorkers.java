package cn.com.mwsn.mpm.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cn.com.mwsn.frame.entity.IdEntity;


/**
 * 医生实体类
 * @author wuda
 *
 */

//@Entity
public class MedicalWorkers extends IdEntity {

	private static final long serialVersionUID = -588618421825231329L;
	
	private String name;
	private String descc;            
	private String sex;
	private String jobNumber;         //工号
	private String type;			//1:护士;2:医生
	private String medicalWorkersType;//医护类型
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
	@OneToMany(mappedBy="medicalWorkers")
	public Set<Patient> getPatients() {
		return patients;
	}
	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getMedicalWorkersType() {
		return medicalWorkersType;
	}
	public void setMedicalWorkersType(String medicalWorkersType) {
		this.medicalWorkersType = medicalWorkersType;
	}
	
}
