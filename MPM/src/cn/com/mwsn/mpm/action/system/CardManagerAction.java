package cn.com.mwsn.mpm.action.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.User;
import cn.com.mwsn.mpm.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

@Results({
	@Result(name="success",type="redirect", location="/mainpage/main-page.action"),
	@Result(name="error", location = "/error/error.jsp")
})
public class CardManagerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7028939684771417972L;
	private static final Logger log = Logger.getLogger(CardManagerAction.class);
	@Autowired
	private UserService userService;
	
	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = Struts2Utils.getRequest();
		String cardNo = request.getParameter("gonghao");
		String password = request.getParameter("passwd");
		String utype = request.getParameter("utype");
		
		if(cardNo.equals("") || password.equals("") || utype.equals("")) {
			return this.ERROR;
		}
		
		User user = new User();
		user.setAccount(cardNo);
		user.setIsAllowLogin(true);
		user.setName(cardNo);
		user.setPassword(password);
		
		log.debug(cardNo);
		log.debug(password);
		log.debug(utype);
		userService.save(user);
		return this.SUCCESS;
	}

}
