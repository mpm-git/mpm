package cn.com.mwsn.mpm.action.stat;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 病人被查房次数统计
 * @author wuda
 *
 */
@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/PatientRoundedStat.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class PatientRoundedStatAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -932945851191087717L;
	
	
	public String execute(){
		return this.SUCCESS;
	}

}
