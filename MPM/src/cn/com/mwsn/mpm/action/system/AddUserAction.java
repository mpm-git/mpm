package cn.com.mwsn.mpm.action.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.action.LoginAction;
import cn.com.mwsn.mpm.entity.Doctor;
import cn.com.mwsn.mpm.entity.Nurse;
import cn.com.mwsn.mpm.entity.User;
import cn.com.mwsn.mpm.service.DoctorService;
import cn.com.mwsn.mpm.service.NurseService;
import cn.com.mwsn.mpm.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
@ParentPackage(value="json-default")
@Results({
	@Result(name="success",type="redirect", location="/mainpage/main-page.action"),
	@Result(name="error", location = "/error/error.jsp")
})
public class AddUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7028939684771417972L;
	private static final Logger log = Logger.getLogger(AddUserAction.class);
	@Autowired
	private UserService userService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private NurseService nurseService;
	
	private List staffNums;
	
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = Struts2Utils.getRequest();
		String staffNum = request.getParameter("staffNum");
		String account = request.getParameter("account");
		String password = request.getParameter("passwd");
		String utype = request.getParameter("utype");
		
		if(account.equals("") || staffNum.equals("") || password.equals("") || utype.equals("")) {
			return this.ERROR;
		}
		
		User user = new User();
		user.setAccount(account);
		user.setIsAllowLogin(true);
		user.setName(staffNum);
		user.setPassword(password);
		user.setUserRole(utype);
		user.setStaffNum(staffNum);
		
		log.debug(account);
		log.debug(staffNum);
		log.debug(password);
		log.debug(utype);
		userService.save(user);
		return this.SUCCESS;
	}
	
	@Action(value = "findStaffNumByType", results = { @Result(name = "success_find", type = "json") }	)
	public String findStaffNum(){
		HttpServletRequest request=Struts2Utils.getRequest();
		String jobType=request.getParameter("jobType");
		if("医生".equals(jobType)){
			staffNums=doctorService.findNotBingDoctor();
		}else if("护士".equals(jobType)){
			staffNums=nurseService.findNotBingNurse();
		}else{
			return "error";
		}
		return "success_find";
	}

	public List getStaffNums() {
		return staffNums;
	}

	public void setStaffNums(List staffNums) {
		this.staffNums = staffNums;
	}
	
	

}
