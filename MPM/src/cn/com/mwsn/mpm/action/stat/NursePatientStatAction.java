package cn.com.mwsn.mpm.action.stat;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 护士和病人接触次数统计
 * @author wuda
 *
 */
@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/NursePatientStat.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class NursePatientStatAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8880667429050294002L;
	
	
	public String execute(){
		return this.SUCCESS;
	}

}
