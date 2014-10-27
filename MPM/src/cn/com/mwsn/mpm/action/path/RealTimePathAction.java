package cn.com.mwsn.mpm.action.path;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.ActionReport;
import cn.com.mwsn.mpm.entity.ActionReportStat;
import cn.com.mwsn.mpm.service.RealTimePathService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 显示医生、护士的实际位置
 * @author Administrator
 *
 */
@Results({
	@Result(name = "success",location = "/WEB-INF/pages/path/RealTimePath.jsp"),
	@Result(name = "error",location = "/error/error.jsp")
})
public class RealTimePathAction extends ActionSupport {
    @Autowired
	private  RealTimePathService realTimePathService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ActionReport.class);

	public String execute() throws Exception {

		HttpServletRequest request = Struts2Utils.getRequest();
		String docCode = request.getParameter("doctor");
		String nurCode = request.getParameter("nurse");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String startTimeStr = request.getParameter("beginStatDate");
		String endTimeStr = request.getParameter("endStatDate");

		Long startTimeStamp = null;
		Long endTimeStamp = null;
		if (startTimeStr != null && !"".equals(startTimeStr)) {
			Date dateStart = sdf.parse(startTimeStr);
			startTimeStamp = dateStart.getTime();
			request.setAttribute("startTimeStr", startTimeStr);
		}
		if (endTimeStr != null && !"".equals(endTimeStr)) {
			Date dateEnd = sdf.parse(endTimeStr);
			endTimeStamp = dateEnd.getTime();
			request.setAttribute("endTimeStr", endTimeStr);
		}
		
		
		String docTp = "100";
		String nurTp = "200";
		//当前医生所在位置
		ActionReportStat ac = realTimePathService.querySpecificPath(docCode, null,docTp);
		List<ActionReportStat> realTimePathList = new ArrayList<ActionReportStat>();
		if(ac != null){
			realTimePathList.add(ac);
		}

		//当前某护士的所在位置
		ActionReportStat ac_nurse = realTimePathService.querySpecificPath(nurCode, null,nurTp);
		List<ActionReportStat> realTimePath_nurse = new ArrayList<ActionReportStat>();
		if(ac_nurse != null){
			realTimePath_nurse.add(ac_nurse);
		}

		request.setAttribute("allPath", realTimePathList);
		request.setAttribute("nursePath", realTimePath_nurse);

		// ActionReportStat actionReportStat =
		// realTimePathService.querySpecificPath(userCode,null);
		// request.setAttribute("actionReportStat", actionReportStat );

		return SUCCESS;
	}
	
	
	
}
