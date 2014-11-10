package cn.com.mwsn.mpm.entity;

import javax.persistence.Entity;

import cn.com.mwsn.frame.entity.IdEntity;
@Entity
public class MH_NurseExecuteRecord_view extends IdEntity implements Cloneable{
		/**
	 * 	
id					int(11)NO
patientName			varchar(255)YES
clinicNo			varchar(255)YES
nurseName			varchar(255)YES
nurseNo				varchar(255)YES
careLevel			varchar(255)YES
age					bigint(21)YES
ageInterval		int(3)YES
execute				varchar(255)YES
executeStartTime	bigint(20)YES
executeEndTime		bigint(20)YES
haoShibig			int(21)YES
	 */
	private static final long serialVersionUID = 686430299455956410L;
		private String patientName;//病人名字
		private String clinicNo;//护士号
		private String nurseName;//病人名字
		private String nurseNo;//护士号
		private String nurseType;//护士类型
		private String careLevel;//执行动作
		private int age;//执行动作
		private int ageInterval;//执行动作
		private String execute;//执行动作
		private long executeStartTime;//执行最早时间
		private long executeEndTime;//执行最早时间
		private long haoShi;//执行耗时
		private long avgHaoShi;//平均执行耗时
		private long maxHaoShi;//最大执行耗时
		private long minHaoShi;//最小执行耗时
		private int ciShu;//执行耗时
		private String diagnose1;//主要诊断1
		private String diagnose2;//主要诊断2
		public String getPatientName() {
			return patientName;
		}
		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}
		public String getClinicNo() {
			return clinicNo;
		}
		public void setClinicNo(String clinicNo) {
			this.clinicNo = clinicNo;
		}
		public String getNurseName() {
			return nurseName;
		}
		public void setNurseName(String nurseName) {
			this.nurseName = nurseName;
		}
		public String getNurseNo() {
			return nurseNo;
		}
		public void setNurseNo(String nurseNo) {
			this.nurseNo = nurseNo;
		}
		public String getCareLevel() {
			return careLevel;
		}
		@Override
		public String toString() {
			return "[patientName=" + patientName
					+ ", clinicNo=" + clinicNo + ", nurseName=" + nurseName
					+ ", nurseNo=" + nurseNo + ", nurseType=" + nurseType
					+ ", careLevel=" + careLevel + ", age=" + age
					+ ", ageInterval=" + ageInterval + ", execute=" + execute
					+ ", executeStartTime=" + executeStartTime
					+ ", executeEndTime=" + executeEndTime + ", haoShi="
					+ haoShi + ", avgHaoShi=" + avgHaoShi + ", maxHaoShi="
					+ maxHaoShi + ", minHaoShi=" + minHaoShi + ", ciShu="
					+ ciShu + ", diagnose1=" + diagnose1 + ", diagnose2="
					+ diagnose2 + "]\n";
		}
		public void setCareLevel(String careLevel) {
			this.careLevel = careLevel;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public int getAgeInterval() {
			return ageInterval;
		}
		public void setAgeInterval(int ageInterval) {
			this.ageInterval = ageInterval;
		}
		public String getExecute() {
			return execute;
		}
		public void setExecute(String execute) {
			this.execute = execute;
		}
		public long getExecuteStartTime() {
			return executeStartTime;
		}
		public void setExecuteStartTime(long executeStartTime) {
			this.executeStartTime = executeStartTime;
		}
		public String getNurseType() {
			return nurseType;
		}
		public void setNurseType(String nurseType) {
			this.nurseType = nurseType;
		}
		public long getExecuteEndTime() {
			return executeEndTime;
		}
		public void setExecuteEndTime(long executeEndTime) {
			this.executeEndTime = executeEndTime;
		}
		public long getHaoShi() {
			return haoShi;
		}
		public void setHaoShi(long haoShi) {
			this.haoShi = haoShi;
		}
		public int getCiShu() {
			return ciShu;
		}
		public void setCiShu(int ciShu) {
			this.ciShu = ciShu;
		}
		public long getAvgHaoShi() {
			return avgHaoShi;
		}
		public void setAvgHaoShi(long avgHaoShi) {
			this.avgHaoShi = avgHaoShi;
		}
		public long getMaxHaoShi() {
			return maxHaoShi;
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
		public void setMaxHaoShi(long maxHaoShi) {
			this.maxHaoShi = maxHaoShi;
		}
		public long getMinHaoShi() {
			return minHaoShi;
		}
		public void setMinHaoShi(long minHaoShi) {
			this.minHaoShi = minHaoShi;
		}
		public MH_NurseExecuteRecord_view clone(){
			MH_NurseExecuteRecord_view o = null;
		        try{
		            o = (MH_NurseExecuteRecord_view)super.clone();
		        }catch(CloneNotSupportedException e){
		            e.printStackTrace();
		        }
		        return o;
		    }
}
