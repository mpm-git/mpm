package cn.com.mwsn.mpm.entity;

import javax.persistence.Entity;

import cn.com.mwsn.frame.entity.IdEntity;
import cn.com.mwsn.mpm.common.Constant.ROLE;

/**
 * tagid和role、name的对应关系表
 * @author zhangke
 *
 */
@Entity
public class RFIDCardPerson extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8989560841083704197L;
	
	private String role;                    //"0" "1" "2"
	private String name;
	private String tagID;
	private String empNO;                    //医生护士的工号，病人的住院号
	private boolean isSend2CP;               //该用户信息是否已经传给曼荼罗？默认0

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTagID() {
		return tagID;
	}
	public void setTagID(String tagID) {
		this.tagID = tagID;
	}
	public String getEmpNO() {
		return empNO;
	}
	public void setEmpNO(String empNO) {
		this.empNO = empNO;
	}
	public boolean isSend2CP() {
		return isSend2CP;
	}
	public void setSend2CP(boolean isSend2CP) {
		this.isSend2CP = isSend2CP;
	}
}
