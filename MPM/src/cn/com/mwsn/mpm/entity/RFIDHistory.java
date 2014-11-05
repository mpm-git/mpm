package cn.com.mwsn.mpm.entity;


import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.entity.IdEntity;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.common.Constant.DATEFLAG;
import cn.com.mwsn.mpm.common.Constant.MOBILEFLAG;
import cn.com.mwsn.mpm.common.Constant.RECORD_STATE;
import cn.com.mwsn.mpm.service.CurrentPositionService;
import cn.com.mwsn.mpm.service.RFIDCardPersonService;

/**
 * 记录历史位置信息
 * @author haiyanzhihun
 *
 */
public class RFIDHistory extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1716106923176660204L;
	private String rfid_id;
	private String code;                        
	private String descx;                       
	private String spell;
	private String wbx;
	private long action_time;
	private int actionplacetype_code;     //场所/地点类型代码    1=科室，0=病区
	private String actionplacetype_desc;
	private String actiondept_code;       //科室
	private String actiondept_desc;
	private String actionward_code;       //病区
	private String actionward_desc;
	private String actionplace_code;      //房间
	private String actionplace_desc;
	private String actionstate_code;      //动作
	private String actionstate_desc;
	private String remark;
	private MOBILEFLAG mobileflag;         //移动护理处理标志,默认值为0,寻床完毕后更改成1
	private DATEFLAG dataflag;              //0:移动护理巡床数据,1:移动护理病人预警数据
	private String sort_id;      
	private RECORD_STATE is_stop;
	private boolean is_update;
	private long update_time;
	
	private CurrentPositionService currentPositionService;

	
	public RFIDHistory() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		currentPositionService = (CurrentPositionService)applicationContext.getBean("currentPositionService");
	}
	public String getRfid_id() {
		return rfid_id;
	}
	public void setRfid_id(String rfid_id) {
		this.rfid_id = rfid_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescx() {
		return descx;
	}
	public void setDescx(String descx) {
		this.descx = descx;
	}
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	public String getWbx() {
		return wbx;
	}
	public void setWbx(String wbx) {
		this.wbx = wbx;
	}

	public long getAction_time() {
		return action_time;
	}
	public void setAction_time(long action_time) {
		this.action_time = action_time;
	}
	public String getActionplace_code() {
		return actionplace_code;
	}
	public void setActionplace_code(String actionplace_code) {
		this.actionplace_code = actionplace_code;
	}
	public String getActionplace_desc() {
		return actionplace_desc;
	}
	public void setActionplace_desc(String actionplace_desc) {
		this.actionplace_desc = actionplace_desc;
	}
	public String getActionstate_code() {
		return actionstate_code;
	}
	public void setActionstate_code(String actionstate_code) {
		this.actionstate_code = actionstate_code;
	}
	public String getActionstate_desc() {
		return actionstate_desc;
	}
	public void setActionstate_desc(String actionstate_desc) {
		this.actionstate_desc = actionstate_desc;
	}
	public RECORD_STATE getIs_stop() {
		return is_stop;
	}
	public void setIs_stop(RECORD_STATE is_stop) {
		this.is_stop = is_stop;
	}
	public boolean isIs_update() {
		return is_update;
	}
	public void setIs_update(boolean is_update) {
		this.is_update = is_update;
	}
	public long getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(long update_time) {
		this.update_time = update_time;
	}
	public int getActionplacetype_code() {
		return actionplacetype_code;
	}
	public void setActionplacetype_code(int actionplacetype_code) {
		this.actionplacetype_code = actionplacetype_code;
	}
	public String getActiondept_code() {
		return actiondept_code;
	}
	public void setActiondept_code(String actiondept_code) {
		this.actiondept_code = actiondept_code;
	}
	public String getActiondept_desc() {
		return actiondept_desc;
	}
	public void setActiondept_desc(String actiondept_desc) {
		this.actiondept_desc = actiondept_desc;
	}
	public String getActionward_code() {
		return actionward_code;
	}
	public void setActionward_code(String actionward_code) {
		this.actionward_code = actionward_code;
	}
	public String getActionward_desc() {
		return actionward_desc;
	}
	public void setActionward_desc(String actionward_desc) {
		this.actionward_desc = actionward_desc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSort_id() {
		return sort_id;
	}
	public void setSort_id(String sort_id) {
		this.sort_id = sort_id;
	}
	public String getActionplacetype_desc() {
		return actionplacetype_desc;
	}
	public void setActionplacetype_desc(String actionplacetype_desc) {
		this.actionplacetype_desc = actionplacetype_desc;
	}
	public MOBILEFLAG getMobileflag() {
		return mobileflag;
	}
	public void setMobileflag(MOBILEFLAG mobileflag) {
		this.mobileflag = mobileflag;
	}
	public DATEFLAG getDataflag() {
		return dataflag;
	}
	public void setDataflag(DATEFLAG dataflag) {
		this.dataflag = dataflag;
	}
	public void setValues(Incident incident) {
		
		InpatientCell cell = currentPositionService.findBy(InpatientCell.class, "name", incident.getWardName());
		
		this.setAction_time(incident.getTime());
		this.setRfid_id(incident.getTagID());
		this.setActiondept_code("");
		this.setActiondept_desc("");
		this.setActionplace_code(cell.getCurroomCode());
		this.setActionplace_desc(cell.getCurroomDesc());
		this.setActionplacetype_code(Constant.INPATIENTAREA);
		this.setActionplacetype_desc("病房");
		this.setActionstate_code(incident.getType() + "");
		if(incident.getType() == Constant.STATUS_IN)
			this.setActionstate_desc("进");
		else 
			this.setActionstate_desc("出");
		this.setActionward_code(cell.getInpatientArea());
		this.setActionward_desc(cell.getInpatientArea());
		this.setCode(incident.getTagID());
		this.setDescx("RFID数据");
		this.setId(incident.getId());	
	}
	
	
}
