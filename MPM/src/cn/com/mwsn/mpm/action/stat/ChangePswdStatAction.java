package cn.com.mwsn.mpm.action.stat;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/changepswd.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class ChangePswdStatAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8357582128021414335L;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return this.SUCCESS;
	}

}
