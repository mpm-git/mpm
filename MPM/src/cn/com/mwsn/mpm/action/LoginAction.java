package cn.com.mwsn.mpm.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.cz.utils.gson.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.service.QueryResult;
import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.entity.CurrentPosition;
import cn.com.mwsn.mpm.entity.InpatientCell;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_patient;
import cn.com.mwsn.mpm.entity.RFIDCardPerson;
import cn.com.mwsn.mpm.entity.User;
import cn.com.mwsn.mpm.service.MH_NurseExecuteRecordService;
import cn.com.mwsn.mpm.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

@Results({
	@Result(name="success",type="redirect", location="/mainpage/main-page.action"),
	@Result(name="error", location = "/index.jsp")
})
public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2579048843100130420L;
	
	private static final Logger log = Logger.getLogger(LoginAction.class);
	
	
	@Autowired
	private UserService userService;

	
	
	public String execute(){
		
		HttpServletRequest request = Struts2Utils.getRequest();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		Struts2Utils.getSession().setAttribute("account", account);
		
		log.debug("loginfo");
		log.debug("account = "+account);
		log.debug("password = "+password);
		
		if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
			return this.ERROR;
		}else{
			int validateState = this.userService.validateAccount(account, password);
			if (validateState == 0){
				log.info(account + " 成功登陆系统");			
				User user = this.userService.queryUser(account);
				if (user == null){
					log.error("数据库出错");
					return this.ERROR;
				}else{
					//将账户信息存入session
					HttpSession session = Struts2Utils.getSession();
					session.setAttribute("userName", user.getName());
					session.setAttribute("userRole", user.getUserRole());
				}
				
				return this.SUCCESS;
			}else if (validateState == 1){
				log.info(account + " 登陆失败");
				request.setAttribute("loginMsg", "用户名或密码错误！");
				return this.ERROR;
			}else if (validateState == 2){
				log.info(account + " 尝试登陆系统");
				request.setAttribute("loginMsg", "该用户不允许登陆，请联系管理员！");
				return this.ERROR;
			}else {
				request.setAttribute("loginMsg", "该用户不存在！");
				return this.ERROR;
			}
		}
	}

}
