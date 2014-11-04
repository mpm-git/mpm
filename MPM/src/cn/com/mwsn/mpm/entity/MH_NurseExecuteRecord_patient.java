package cn.com.mwsn.mpm.entity;

import javax.persistence.Entity;

import cn.com.mwsn.frame.entity.IdEntity;

public class MH_NurseExecuteRecord_patient extends IdEntity {
		/**
	 * 	
		name		varchar(255)	YES
		nurseNo		varchar(255)	YES
		execute		varchar(255)	YES
		min_start	bigint(20)		YES
		haoShi		decimal(42,0)	YES
		ciShu		bigint(21)		NO
	 */
	private static final long serialVersionUID = 686430299455956410L;
		private String name;//病人名字
		private String clinicNo;//护士号
		private String execute;//执行动作
		private long min_start;//执行最早时间
		private long haoShi;//执行耗时
		private long ciShu;//执行次数
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getClinicNo() {
			return clinicNo;
		}
		public void setClinicNo(String clinicNo) {
			this.clinicNo = clinicNo;
		}
		public String getExecute() {
			return execute;
		}
		public void setExecute(String execute) {
			this.execute = execute;
		}
		public long getMin_start() {
			return min_start;
		}
		public void setMin_start(long min_start) {
			this.min_start = min_start;
		}
		public long getHaoShi() {
			return haoShi;
		}
		public void setHaoShi(long haoShi) {
			this.haoShi = haoShi;
		}
		public long getCiShu() {
			return ciShu;
		}
		public void setCiShu(long ciShu) {
			this.ciShu = ciShu;
		}
		
}
