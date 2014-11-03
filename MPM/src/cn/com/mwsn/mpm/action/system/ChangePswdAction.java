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
import cn.com.mwsn.mpm.action.mainpage.MainPageAction;
import cn.com.mwsn.mpm.entity.User;
import cn.com.mwsn.mpm.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value="json-default")
@Results({
	@Result(name="success",type="redirect", location="/mainpage/main-page.action"),
	@Result(name="error", location = "/error/error.jsp"),
})
public class ChangePswdAction extends ActionSupport {
	
	@Autowired
	private UserService userService;

	private String message;
	
	private String oldpswd;
	
	private String status;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3628486153614198368L;
	private static Logger log = Logger.getLogger(ChangePswdAction.class);

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		String account = (String)Struts2Utils.getSession().getAttribute("account");
		User user=userService.findBy(User.class, "account", account);
		String password=null;
		if(user!=null){
			password=user.getPassword();
		}
		String oldpswd = request.getParameter("oldpswd");
		String newpswd = request.getParameter("newpswd");
		if(!oldpswd.equals(password)){
			return this.ERROR;
		}
		try {
			user.setPassword(newpswd);
			userService.merge(user);
		} catch (Exception e) {
			e.printStackTrace();
			return this.ERROR;
		}
		log.debug(account);
		
		
		return this.SUCCESS;
	}
	
	@Action(value = "validatePassword", results = { @Result(name = "validate_oldpswd", type = "json") }	)  
	public String validateOldPassWord() throws Exception{
		HttpServletRequest request = Struts2Utils.getRequest();
		String oldpassword=request.getParameter("oldpswd");
		String account = (String)Struts2Utils.getSession().getAttribute("account");
		User user=userService.findBy(User.class, "account", account);
		String password=null;
		if(user!=null){
			password=user.getPassword();
		}
		if(oldpassword.equals(password)){
			status="success";
			message="Right password!";
		}else{
			status="error_old_password";
			message="Wrong password!";
		}
		
		return "validate_oldpswd";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOldpswd() {
		return oldpswd;
	}

	public void setOldpswd(String oldpswd) {
		this.oldpswd = oldpswd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
