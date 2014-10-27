package cn.com.mwsn.mpm.action.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.common.Constant;

import com.opensymphony.xwork2.ActionSupport;

@Results({
		@Result(name = "success", type = "redirect", location = "/mainpage/main-page.action"),
		@Result(name = "error", location = "/error/error.jsp") })
public class SettingAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -545184853102702967L;
	private static final Logger log = Logger.getLogger(SettingAction.class);

	@Override
	public String execute() throws Exception {

		HttpServletRequest request = Struts2Utils.getRequest();
		String mintime = request.getParameter("mintime");
		String role = request.getParameter("role");
		String danwei = request.getParameter("danwei").equals("0") ? "每天"
				: "每周";
		String cishu = request.getParameter("cishu");
		String ip = request.getParameter("ip");

		if (mintime.equals("") || role.equals("") || danwei.equals("")
				|| cishu.equals("") || ip.equals("")) {
			return this.ERROR;
		}
		Constant.minCheckWardTime = Integer.parseInt(mintime) * 60 * 1000;
		if ("每天".equals(danwei)) {
			Constant.nurseOrder = Integer.parseInt(cishu);
		} else {
			Constant.doctorOrder = Integer.parseInt(cishu);
		}
		Constant.localhost = ip;

		return this.SUCCESS;
	}

}
