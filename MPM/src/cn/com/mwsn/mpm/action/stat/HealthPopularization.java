package cn.com.mwsn.mpm.action.stat;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.HealthContent;
import cn.com.mwsn.mpm.service.HealthPopularizationService;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 健康普及
 * @author 
 *
 */
@ParentPackage(value="json-default")
@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/healthManagement.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class HealthPopularization extends ActionSupport{
	
	private static final long serialVersionUID = 7028939684771417971L;
	
	@Autowired
	private HealthPopularizationService hService;
	
	private String message;
	
	@Override
	public String execute() throws Exception {
		return "success";
	}
	
	@Action(value = "save_content", results = { @Result(name = "success", type = "json") }	)
	private String saveHtmlContent(){
		System.out.println("in saveHtmlContent");
		HttpServletRequest request=Struts2Utils.getRequest();
		String htmlContent=request.getParameter("htmlContent");
		HealthContent healthContent=new HealthContent(htmlContent);
		try {
			hService.saveContent(healthContent);
		} catch (Exception e) {
			message="error";
			return this.ERROR;
		}
		message="success";
		return "success";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
