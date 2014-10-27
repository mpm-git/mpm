package cn.com.mwsn.mpm.action.path;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.ActionReport;
import cn.com.mwsn.mpm.entity.SickroomEquipment;
import cn.com.mwsn.mpm.service.ActionReportService;

import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "success", location = "/WEB-INF/pages/path/SickroomEquip.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class SickroomEquipAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7724163925796337816L;
	private static final Logger log = Logger.getLogger(ActionReport.class);
	
	@Autowired
	private ActionReportService actionservice;
	
	public String execute(){
		HttpServletRequest request = Struts2Utils.getRequest();
    	List<ActionReport> actionreport = actionservice.findAll(ActionReport.class);
		request.setAttribute("ActionReport", actionreport);
		
		//查询病房设备联动信息
		List<SickroomEquipment> list = new ArrayList<SickroomEquipment>();
		SickroomEquipment equip = new SickroomEquipment();
		//应该从数据库获得
		equip.setNo("1");
		equip.setEquipNo("1000002");
		equip.setTime("2014-07-16 14:02:34");
		equip.setMessage("请注意多喝水、多休息");
		list.add(equip);
		
		SickroomEquipment equip2 = new SickroomEquipment();
		//应该从数据库获得
		equip2.setNo("2");
		equip2.setEquipNo("1000005");
		equip2.setTime("2014-07-16 14:05:20");
		equip2.setMessage("请注意多喝水、多休息");
		list.add(equip2);
		
		request.setAttribute("equipList", list);
		return SUCCESS;
	}

}
