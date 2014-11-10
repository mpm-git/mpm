package cn.com.mwsn.mpm.action.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.action.LoginAction;
import cn.com.mwsn.mpm.entity.User;
import cn.com.mwsn.mpm.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

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

}
