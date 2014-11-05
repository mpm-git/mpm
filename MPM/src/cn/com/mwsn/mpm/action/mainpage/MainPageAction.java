package cn.com.mwsn.mpm.action.mainpage;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.InpatientArea;
import cn.com.mwsn.mpm.entity.NurseAlert;
import cn.com.mwsn.mpm.entity.PatientAlert;
import cn.com.mwsn.mpm.entity.WardRoundNumberByDay;
import cn.com.mwsn.mpm.entity.WardRoundNumberByWeek;
import cn.com.mwsn.mpm.service.DailyWardRoundNumberCheckService;
import cn.com.mwsn.mpm.service.InpatientAreaMonitorService;
import cn.com.mwsn.mpm.service.InpatientAreaService;
import cn.com.mwsn.mpm.service.NurseAlertService;
import cn.com.mwsn.mpm.service.PatientAlertService;
import cn.com.mwsn.mpm.service.WeeklyWardRoundNumberCheckService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 登陆成功后，自动跳转到该Action 该Action的主要功能是初始化主页面的相关信息
 * 
 * @author wuda
 * 
 */
@Results({ @Result(name = "success", location = "/WEB-INF/pages/main.jsp"),
		@Result(name = "error", location = "/error/error.jsp") })
public class MainPageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8018124434150700742L;

	private static Logger log = Logger.getLogger(MainPageAction.class);

	@Autowired
	InpatientAreaService ias;
	
	@Autowired
	InpatientAreaMonitorService pams;

	@Autowired
	WeeklyWardRoundNumberCheckService wwrncs;

	@Autowired
	DailyWardRoundNumberCheckService dwrncs;

	@Autowired
	NurseAlertService nas;

	@Autowired
	PatientAlertService pas;

	public String execute() {
		// 获取当前所有的病区并存入到application中, 由于目前只有一个病区，因此该段代码注释
		 List<InpatientArea> inpatientAreas = ias.findAllInpatientAreas();
		 Struts2Utils.getApplication().setAttribute("inpatientAreas",
		 inpatientAreas);
		 
		// 获取所有医护人员本周的查房信息并存入到request中
		List<WardRoundNumberByWeek> weeklyWardRoundNumbers = wwrncs
				.queryAllWeeklyWardRoundNumber();
		Struts2Utils.getRequest().setAttribute("weeklyWardRoundNumbers",
				weeklyWardRoundNumbers);

		// 获取所有医护人员本日的查房信息并存入到request中
		List<WardRoundNumberByDay> dailyWardRoundNumbers = dwrncs
				.queryAllDailyWardRoundNumber();
		Struts2Utils.getRequest().setAttribute("dailyWardRoundNumbers",
				dailyWardRoundNumbers);

		// 获取所有护士的告警信息并存入到request中
		List<NurseAlert> nurseAlerts = nas.queryAllNurseAlert();
		Struts2Utils.getRequest().setAttribute("nurseAlerts", nurseAlerts);

		// 获取所有病人的告警信息并存入到request中
		List<PatientAlert> patientAlerts = pas.queryAllPatientAlert();
		Struts2Utils.getRequest().setAttribute("patientAlerts", patientAlerts);

		return this.SUCCESS;
	}

}
