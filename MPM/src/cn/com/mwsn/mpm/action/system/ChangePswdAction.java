package cn.com.mwsn.mpm.action.system;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.action.mainpage.MainPageAction;

import com.opensymphony.xwork2.ActionSupport;

@Results({
	@Result(name="success",type="redirect", location="/mainpage/main-page.action"),
	@Result(name="error", location = "/error/error.jsp")
})
public class ChangePswdAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3628486153614198368L;
	private static Logger log = Logger.getLogger(ChangePswdAction.class);

	@Override
	public String execute() throws Exception {
		
		String account = (String)Struts2Utils.getSession().getAttribute("account");
		log.debug(account);
		
		
		return this.SUCCESS;
	}

}
