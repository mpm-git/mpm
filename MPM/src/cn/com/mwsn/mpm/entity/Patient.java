package cn.com.mwsn.mpm.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import cn.com.mwsn.frame.entity.IdEntity;
import cn.com.mwsn.mpm.common.Constant.INHOSMODEL_CODE;


/**
 * 病人实体类
 * @author haiyanzhihun
 *
 */
@Entity
public class Patient extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7607570094697743802L;
	
	private String name;
	private String descc;
	private String diagnose1;//主要诊断1
	private String diagnose2;//主要诊断2
	private String sex;                  //0:男   1:女
	private Date birthday;	//生日
	private String clinicNo;              //住院号
	private String caseNo;                //病案号
	private String hisinnerNo;            //His内部标示
	private String hisouterNo;            //His外部标示
	private String curBed;                 	//床号
	private String curdeptCode;        		//科室
	private String inpatientArea;        //病区
	
	private String curdeptDesc;      
	private String careLevel;              //护理级别
	private Date inhospitalTime;           //入院时间 
	private Date leaveHospitalTime;        //出院时间 
	private String dietCare;               //饮食注意
	private String type;                  //病人类型名称
	private INHOSMODEL_CODE inhospital;   //入院方式
	private String patientNo;             //就诊号
	private String registerNo;            //门诊号
	
	//床头终端需要
//	private InpatientBed bed;
//	private Doctor doctorCharge;           //主治医生
//	private Nurse nurseCharge;             //责任护士
	private String deviceID;               //与之关联的床头终端ID
	private String safeNotice;             //安全告知
	private String drugAllergy;           //药物过敏
	private String bloodPressure;          //血压数据
	
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "[name=" + name + ", descc=" + descc + ", diagnose1="
				+ diagnose1 + ", diagnose2=" + diagnose2 + ", sex=" + sex
				+ ", birthday=" + birthday + ", clinicNo=" + clinicNo
				+ ", caseNo=" + caseNo + ", hisinnerNo=" + hisinnerNo
				+ ", hisouterNo=" + hisouterNo + ", curBed=" + curBed
				+ ", curdeptCode=" + curdeptCode + ", inpatientArea="
				+ inpatientArea + ", curdeptDesc=" + curdeptDesc
				+ ", careLevel=" + careLevel + ", inhospitalTime="
				+ inhospitalTime + ", leaveHospitalTime=" + leaveHospitalTime
				+ ", dietCare=" + dietCare + ", type=" + type + ", inhospital="
				+ inhospital + ", patientNo=" + patientNo + ", registerNo="
				+ registerNo + ", deviceID=" + deviceID + ", safeNotice="
				+ safeNotice + ", drugAllergy=" + drugAllergy
				+ ", bloodPressure=" + bloodPressure + "]\n";
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
//	@OneToOne
//	public InpatientBed getBed() {
//		return bed;
//	}
//	public void setBed(InpatientBed bed) {
//		this.bed = bed;
//	}
	public String getSex() {
		return sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public String getDiagnose1() {
		return diagnose1;
	}
	public void setDiagnose1(String diagnose1) {
		this.diagnose1 = diagnose1;
	}
	public String getDiagnose2() {
		return diagnose2;
	}
	public void setDiagnose2(String diagnose2) {
		this.diagnose2 = diagnose2;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	public INHOSMODEL_CODE getInhospital() {
		return inhospital;
	}
	public void setInhospital(INHOSMODEL_CODE inhospital) {
		this.inhospital = inhospital;
	}
	public String getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}
	public String getClinicNo() {
		return clinicNo;
	}
	public void setClinicNo(String clinicNo) {
		this.clinicNo = clinicNo;
	}
	public String getRegisterNo() {
		return registerNo;
	}
	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getHisinnerNo() {
		return hisinnerNo;
	}
	public void setHisinnerNo(String hisinnerNo) {
		this.hisinnerNo = hisinnerNo;
	}
	public String getHisouterNo() {
		return hisouterNo;
	}
	public void setHisouterNo(String hisouterNo) {
		this.hisouterNo = hisouterNo;
	}
	public String getCurBed() {
		return curBed;
	}
	public void setCurBed(String curBed) {
		this.curBed = curBed;
	}
	public Date getLeaveHospitalTime() {
		return leaveHospitalTime;
	}
	public void setLeaveHospitalTime(Date leaveHospitalTime) {
		this.leaveHospitalTime = leaveHospitalTime;
	}
	public String getCurdeptCode() {
		return curdeptCode;
	}
	public void setCurdeptCode(String curdeptCode) {
		this.curdeptCode = curdeptCode;
	}
	public String getCurdeptDesc() {
		return curdeptDesc;
	}
	public void setCurdeptDesc(String curdeptDesc) {
		this.curdeptDesc = curdeptDesc;
	}
//	@ManyToOne
//	public Doctor getDoctorCharge() {
//		return doctorCharge;
//	}
//	public void setDoctorCharge(Doctor doctorCharge) {
//		this.doctorCharge = doctorCharge;
//	}
//	@ManyToOne
//	public Nurse getNurseCharge() {
//		return nurseCharge;
//	}
//	public void setNurseCharge(Nurse nurseCharge) {
//		this.nurseCharge = nurseCharge;
//	}
	public String getCareLevel() {
		return careLevel;
	}
	public void setCareLevel(String careLevel) {
		this.careLevel = careLevel;
	}
	public String getDietCare() {
		return dietCare;
	}
	public void setDietCare(String dietCare) {
		this.dietCare = dietCare;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public Date getInhospitalTime() {
		return inhospitalTime;
	}
	public void setInhospitalTime(Date inhospitalTime) {
		this.inhospitalTime = inhospitalTime;
	}
	public String getSafeNotice() {
		return safeNotice;
	}
	public void setSafeNotice(String safeNotice) {
		this.safeNotice = safeNotice;
	}
	public String getDrugAllergy() {
		return drugAllergy;
	}
	public void setDrugAllergy(String drugAllergy) {
		this.drugAllergy = drugAllergy;
	}
	public String getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public String getInpatientArea() {
		return inpatientArea;
	}
	public void setInpatientArea(String inpatientArea) {
		this.inpatientArea = inpatientArea;
	}
	
}
