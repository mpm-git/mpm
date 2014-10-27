package cn.com.mwsn.mpm.action.stat;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.ActionReport;
import cn.com.mwsn.mpm.service.ActionReportService;


/**
 * 护士查房次数统计
 * @author wuda
 *
 */
@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/NurseRoundsStat.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class NurseRoundsStatAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3135161766672675586L;
	private static final Logger log = Logger.getLogger(ActionReport.class);
	
	@Autowired
	private ActionReportService actionservice;
	
	public String execute(){
		HttpServletRequest request = Struts2Utils.getRequest();
    	List<ActionReport> actionreport = actionservice.findAll(ActionReport.class);
		request.setAttribute("ActionReport", actionreport);
		return this.SUCCESS;
	}


}
