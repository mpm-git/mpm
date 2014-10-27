package cn.com.mwsn.mpm.action.path;

import java.text.SimpleDateFormat;
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

@Results( {
		@Result(name = "success", location = "/WEB-INF/pages/path/SpecificPath.jsp"),
		@Result(name = "error", location = "/error/error.jsp") })
public class SpecificPathAction extends ActionSupport {

	@Autowired
	private RealTimePathService realTimePathService;

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ActionReport.class);

	public String execute() throws Exception {
		// ApplicationContext ctx = new
		// ClassPathXmlApplicationContext("applicationContext.xml");
		// realTimePathService =
		// (RealTimePathService)ctx.getBean("RealTimePathServiceImpl");
		HttpServletRequest request = Struts2Utils.getRequest();
		String userCode = request.getParameter("doctor");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String startTimeStr = request.getParameter("beginStatDate");
		String endTimeStr = request.getParameter("endStatDate");
		
		//为演示，增加默认时间
		if(startTimeStr == null || "".equals(startTimeStr)){
			startTimeStr = "2014-05-14 00:00";
		}
		if(endTimeStr == null || "".equals(endTimeStr)){
			endTimeStr = "2014-05-15 00:00";
		}
		if(userCode == null || "".equals(userCode)){
			userCode = "3027";
		}

		
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

		/**
		 * 历史轨迹图
		 * 
		 */
		List<ActionReportStat> realTimePathList = realTimePathService
				.queryAllPath(userCode, startTimeStamp, endTimeStamp);

		Calendar cal = Calendar.getInstance();
		for (ActionReportStat action : realTimePathList) {
			// System.out.println(action.getACTION_TIME());
			// System.out.println(action.getACTIONPLACE_CODE());
			// System.out.println(action.getUSER_CODE());
			// System.out.println(action.getUSER_DESC());
			// System.out.println(action.getX());
			// System.out.println(action.getY());

			cal.setTimeInMillis(action.getACTION_TIME());
			action.setTimedesc(sdf2.format(cal.getTime()));

		}

		request.setAttribute("allPath", realTimePathList);

		// ActionReportStat actionReportStat =
		// realTimePathService.querySpecificPath(userCode,null);
		// request.setAttribute("actionReportStat", actionReportStat );

		return SUCCESS;
	}

}
