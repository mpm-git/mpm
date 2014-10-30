package cn.com.mwsn.mpm.entity;

import javax.persistence.Entity;

import cn.com.mwsn.frame.entity.IdEntity;
@Entity
public class MH_NurseExecuteRecord extends IdEntity {
		/**
	 * 
	 */
	private static final long serialVersionUID = 686430299455956410L;
		private String execute;
		private String executeInfo;
		private String NurseID;
		private long executeTime;
		private String executeType;
		private String clinicNo;
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
		public String getNurseID() {
			return NurseID;
		}
		public void setNurseID(String nurseID) {
			NurseID = nurseID;
		}
		public long getExecuteTime() {
			return executeTime;
		}
		public void setExecuteTime(long executeTime) {
			this.executeTime = executeTime;
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
		
}
