package cn.com.mwsn.mpm.action.alert;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.PatientAlert;
import cn.com.mwsn.mpm.service.PatientAlertService;


@Results({ @Result(name = "success", location = "/WEB-INF/pages/alert/patientLeaveAlert.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class PatientLeaveAlertAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2173083065603689671L;
	private static final Logger log = Logger.getLogger(PatientAlert.class);
	
	@Autowired
	private PatientAlertService alertservice;

	@Override
	public String execute() throws Exception{
		HttpServletRequest request = Struts2Utils.getRequest();
    	List<PatientAlert> patientalert = alertservice.findAll(PatientAlert.class);
		request.setAttribute("LeaveAlert", patientalert);
		return this.SUCCESS;
	}
	
}
