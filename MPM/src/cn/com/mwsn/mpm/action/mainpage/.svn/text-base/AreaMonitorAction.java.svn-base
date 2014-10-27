package cn.com.mwsn.mpm.action.mainpage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.CurrentPosition;
import cn.com.mwsn.mpm.entity.WardRoundNumberByDay;
import cn.com.mwsn.mpm.entity.WardRoundNumberByWeek;
import cn.com.mwsn.mpm.service.DailyWardRoundNumberCheckService;
import cn.com.mwsn.mpm.service.InpatientAreaMonitorService;
import cn.com.mwsn.mpm.service.WeeklyWardRoundNumberCheckService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * JSP主页面每隔3秒调用该Action以获取所有人员最新的位置信息
 * 
 * @author zhoujie
 * 
 */

public class AreaMonitorAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2412863196867708449L;

	private static Logger log = Logger.getLogger(AreaMonitorAction.class);

	@Autowired
	InpatientAreaMonitorService pams;

	@Autowired
	WeeklyWardRoundNumberCheckService wwrncs;

	@Autowired
	DailyWardRoundNumberCheckService dwrncs;

	public String execute() {

		// 获取所有人员当前的位置信息并通过response返回
		List<CurrentPosition> currentPositions = pams.queryAllCurrentPosition();
		// Struts2Utils.getRequest().setAttribute("currentPositions",
		// currentPositions);

		String result = "";
		if (currentPositions == null || currentPositions.size() == 0) {
			result += 0;
		} else {
			result += currentPositions.size() + ",";
			for (int i = 0; i < currentPositions.size(); ++i) {

				String rfid = currentPositions.get(i).getRfid();
				String role = currentPositions.get(i).getRole();
				String name = currentPositions.get(i).getName();

				int x = currentPositions.get(i).getX();
				int y = currentPositions.get(i).getY();

				if (i == currentPositions.size() - 1) {
					result += rfid + "," + role + "," + name + "," + x + ","
							+ y;
				} else {
					result += rfid + "," + role + "," + name + "," + x + ","
							+ y + ",";
				}
			}

		}

		// 获取所有医护人员本周的查房信息并存入到request中
		List<WardRoundNumberByWeek> weeklyWardRoundNumbers = wwrncs
				.queryAllWeeklyWardRoundNumber();

		// 获取所有医护人员本日的查房信息并存入到request中
		List<WardRoundNumberByDay> dailyWardRoundNumbers = dwrncs
				.queryAllDailyWardRoundNumber();

		result += "&";
		if (dailyWardRoundNumbers == null || dailyWardRoundNumbers.size() == 0) {
			result += 0;
		} else {
			result += dailyWardRoundNumbers.size();
			result += ",";
			for (int i = 0; i < dailyWardRoundNumbers.size(); i++) {
				WardRoundNumberByDay w = dailyWardRoundNumbers.get(i);
				result += w.getName();
				result += ",";
				result += w.getNumreal();
				if (i != (dailyWardRoundNumbers.size() - 1)) {
					result += ",";
				}
			}
		}

		result += "&";
		if (weeklyWardRoundNumbers == null
				|| weeklyWardRoundNumbers.size() == 0) {
			result += 0;
		} else {
			result += weeklyWardRoundNumbers.size();
			result += ",";
			for (int i = 0; i < weeklyWardRoundNumbers.size(); i++) {
				WardRoundNumberByWeek w = weeklyWardRoundNumbers.get(i);
				result += w.getName();
				result += ",";
				result += w.getNumreal();
				if (i != (weeklyWardRoundNumbers.size() - 1)) {
					result += ",";
				}
			}
		}

		try {
			Struts2Utils.getResponse().setCharacterEncoding("utf-8");
			Struts2Utils.getResponse().getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	

	public String execute1() {

		// 获取所有人员当前的位置信息并通过response返回

		ServletContext session = Struts2Utils.getApplication();
		List<CurrentPosition> currentPositions = new ArrayList<CurrentPosition>();

		if (session.getAttribute("p-zh") != null) {
			String s = (String) session.getAttribute("p-zh");
			String x = s.split("-")[0];
			String y = s.split("-")[1];
			CurrentPosition cp = new CurrentPosition();
			cp.setRfid("1");
			cp.setName("张华");
			cp.setRole("2");
			cp.setX(Integer.valueOf(x));
			cp.setY(Integer.valueOf(y));
			currentPositions.add(cp);
		}

		if (session.getAttribute("p-zyy") != null) {
			String s = (String) session.getAttribute("p-zyy");
			String x = s.split("-")[0];
			String y = s.split("-")[1];
			CurrentPosition cp = new CurrentPosition();
			cp.setRfid("2");
			cp.setName("张艳艳");
			cp.setRole("1");
			cp.setX(Integer.valueOf(x));
			cp.setY(Integer.valueOf(y));
			currentPositions.add(cp);
		}

		if (session.getAttribute("p-lc") != null) {
			String s = (String) session.getAttribute("p-lc");
			String x = s.split("-")[0];
			String y = s.split("-")[1];
			CurrentPosition cp = new CurrentPosition();
			cp.setRfid("1");
			cp.setName("李闯");
			cp.setRole("3");
			cp.setX(Integer.valueOf(x));
			cp.setY(Integer.valueOf(y));
			currentPositions.add(cp);
		}

		// List<CurrentPosition> currentPositions =
		// pams.queryAllCurrentPosition();
		// Struts2Utils.getRequest().setAttribute("currentPositions",
		// currentPositions);

		String result = "";
		if (currentPositions == null || currentPositions.size() == 0) {
			result += 0;
		} else {
			result += currentPositions.size() + ",";
			for (int i = 0; i < currentPositions.size(); ++i) {

				String rfid = currentPositions.get(i).getRfid();
				String role = currentPositions.get(i).getRole();
				String name = currentPositions.get(i).getName();

				int x = currentPositions.get(i).getX();
				int y = currentPositions.get(i).getY();

				if (i == currentPositions.size() - 1) {
					result += rfid + "," + role + "," + name + "," + x + ","
							+ y;
				} else {
					result += rfid + "," + role + "," + name + "," + x + ","
							+ y + ",";
				}
			}

		}

		// 获取所有医护人员本周的查房信息并存入到request中
		List<WardRoundNumberByWeek> weeklyWardRoundNumbers = new ArrayList<WardRoundNumberByWeek>();
		List<WardRoundNumberByDay> dailyWardRoundNumbers = new ArrayList<WardRoundNumberByDay>();

		if (session.getAttribute("r-zyy") != null) {
			WardRoundNumberByWeek w = new WardRoundNumberByWeek();
			w.setName("张艳艳");
			w.setNumreal(Integer.valueOf((String) session.getAttribute("r-zyy")));
			weeklyWardRoundNumbers.add(w);
		}

		if (session.getAttribute("r-zh") != null) {
			WardRoundNumberByDay w = new WardRoundNumberByDay();
			w.setName("张华");
			w.setNumreal(Integer.valueOf((String) session.getAttribute("r-zh")));
			dailyWardRoundNumbers.add(w);
		}

		// List<WardRoundNumberByWeek> weeklyWardRoundNumbers = wwrncs
		// .queryAllWeeklyWardRoundNumber();
		//
		// // 获取所有医护人员本日的查房信息并存入到request中
		// List<WardRoundNumberByDay> dailyWardRoundNumbers = dwrncs
		// .queryAllDailyWardRoundNumber();

		result += "&";
		if (dailyWardRoundNumbers == null || dailyWardRoundNumbers.size() == 0) {
			result += 0;
		} else {
			result += dailyWardRoundNumbers.size();
			result += ",";
			for (int i = 0; i < dailyWardRoundNumbers.size(); i++) {
				WardRoundNumberByDay w = dailyWardRoundNumbers.get(i);
				result += w.getName();
				result += ",";
				result += w.getNumreal();
				if (i != (dailyWardRoundNumbers.size() - 1)) {
					result += ",";
				}
			}
		}

		result += "&";
		if (weeklyWardRoundNumbers == null
				|| weeklyWardRoundNumbers.size() == 0) {
			result += 0;
		} else {
			result += weeklyWardRoundNumbers.size();
			result += ",";
			for (int i = 0; i < weeklyWardRoundNumbers.size(); i++) {
				WardRoundNumberByWeek w = weeklyWardRoundNumbers.get(i);
				result += w.getName();
				result += ",";
				result += w.getNumreal();
				if (i != (weeklyWardRoundNumbers.size() - 1)) {
					result += ",";
				}
			}
		}

		try {
			Struts2Utils.getResponse().setCharacterEncoding("utf-8");
			Struts2Utils.getResponse().getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	

	
}
