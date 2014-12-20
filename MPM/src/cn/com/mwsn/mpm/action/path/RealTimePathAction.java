package cn.com.mwsn.mpm.action.path;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.cz.utils.HttpUtil;
import org.cz.utils.Util;
import org.cz.utils.gson.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.service.QueryResult;
import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.ActionReport;
import cn.com.mwsn.mpm.entity.ActionReportStat;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord;
import cn.com.mwsn.mpm.entity.Person;
import cn.com.mwsn.mpm.entity.R_sutTagRecord_view;
import cn.com.mwsn.mpm.service.R_Suttagrecord_ViewService;
import cn.com.mwsn.mpm.service.RealTimePathService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 显示医生、护士的实际位置
 * @author Administrator
 *
 */
@Results({
	@Result(name = "success",location = "/WEB-INF/pages/path/RealTimePath.jsp"),
	@Result(name = "error",location = "/error/error.jsp"),
	@Result(name = "result", location = "/WEB-INF/pages/result4Json.jsp")
})
public class RealTimePathAction extends ActionSupport {
    @Autowired
	private  RealTimePathService realTimePathService;
    @Autowired
    private  R_Suttagrecord_ViewService r_Suttagrecord_ViewService;
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
	@Action(value="r_person_realtime_location")
	public String getRealTimeRecord()
	{
		ServletContext session = Struts2Utils.getApplication();
		HttpServletRequest request = Struts2Utils.getRequest();
		Map<String, String> parameters = HttpUtil.getParameters(request, null);
		String empNO = parameters.get("empNO");
		R_sutTagRecord_view r_sutTagRecord_view = r_Suttagrecord_ViewService.getRealTimeRecord(empNO);
		request.setAttribute("result",GsonUtil.objectToJson(r_sutTagRecord_view) );
		return "result";
	}
	
	@Action(value="r_person_history_location")
	public String getHistoryLocation()
	{
		ServletContext session = Struts2Utils.getApplication();
		HttpServletRequest request = Struts2Utils.getRequest();
		Map<String,Object> param=new HashMap<String, Object>();
		Map<String, String> parameters = HttpUtil.getParameters(request, null);
		String empNO = parameters.get("empNO");
		String _start_time = parameters.get("start_time");
		String _end_time = parameters.get("end_time");
//		String start_time=null;
//		String end_time=null;
		if(Util.isNULL(_start_time))
		{
			Date d=new Date();
			d.setHours(d.getHours()-1);
			_start_time=Util.date2Str(new Date(), "yyyy-MM-dd HH:mm");
		}
		if(Util.isNULL(_end_time))
		{
			_end_time=Util.date2Str(new Date(), "yyyy-MM-dd HH:mm");
		}
		String conditions="";
		if(!Util.isNULL(_start_time))
		{
			conditions+="and nTime>"+Util.str2Date(_start_time,"yyyy-MM-dd HH:mm").getTime()+" ";
		}
		if(!Util.isNULL(_end_time))
		{
			conditions+="and  nTime<"+Util.str2Date(_end_time,"yyyy-MM-dd HH:mm").getTime()+" ";
		}
		conditions+="and empNO='"+empNO+"'";
		conditions=conditions.replaceAll("^and ", " ");
		param.put("conditions",conditions);
		param.put("order_by","nTime");
		param.put("order","asc");
		QueryResult<List<R_sutTagRecord_view>> find_r_suttagrecord_view = r_Suttagrecord_ViewService.find_r_suttagrecord_view(param, 0, 0);
		request.setAttribute("result",GsonUtil.objectToJson(find_r_suttagrecord_view) );
		return "result";
	}
	@Action(value="r_person")
	public String getPerson()
	{
		ServletContext session = Struts2Utils.getApplication();
		HttpServletRequest request = Struts2Utils.getRequest();
		Map<String, String> parameters = HttpUtil.getParameters(request, null);
		List<Person> list = r_Suttagrecord_ViewService.findAll(Person.class);
		request.setAttribute("result",GsonUtil.objectToJson(list) );
		return "result";
	}
}
