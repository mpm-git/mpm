package cn.com.mwsn.mpm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import cn.com.mwsn.frame.entity.IdEntity;
@Entity
public class MH_NurseExecuteRecord extends IdEntity {
		/**
	 * 
	 */
	private static final long serialVersionUID = 686430299455956410L;
		private String execute;//动作
		private String doctorAdviceSn;//医嘱序号
		private String executeInfo;//执行信息
		private String nurseNo;//执行人
		private Nurse nurse;//执行人
		private long executeStartTime;//执行开始时间
		private long executeEndTime;//执行结束时间
		private String executeType;//执行单类型
		private String clinicNo;//住院号
		public String getExecute() {
			return execute;
		}
		public void setExecute(String execute) {
			this.execute = execute;
		}
		public String getExecuteInfo() {
			return executeInfo;
		}
		public void setExecuteInfo(String executeInfo) {
			this.executeInfo = executeInfo;
		}
		public String getNurseNo() {
			return nurseNo;
		}
		public void setNurseNo(String nurseNo) {
			this.nurseNo = nurseNo;
		}
		public String getExecuteType() {
			return executeType;
		}
		public void setExecuteType(String executeType) {
			this.executeType = executeType;
		}
		public String getClinicNo() {
			return clinicNo;
		}
		public void setClinicNo(String clinicNo) {
			this.clinicNo = clinicNo;
		}
		public String getDoctorAdviceSn() {
			return doctorAdviceSn;
		}
		public void setDoctorAdviceSn(String doctorAdviceSn) {
			this.doctorAdviceSn = doctorAdviceSn;
		}
		@ManyToOne
		public Nurse getNurse() {
			return nurse;
		}
		public void setNurse(Nurse nurse) {
			this.nurse = nurse;
		}
		public long getExecuteStartTime() {
			return executeStartTime;
		}
		public void setExecuteStartTime(long executeStartTime) {
			this.executeStartTime = executeStartTime;
		}
		public long getExecuteEndTime() {
			return executeEndTime;
		}
		public void setExecuteEndTime(long executeEndTime) {
			this.executeEndTime = executeEndTime;
		}
		
}
