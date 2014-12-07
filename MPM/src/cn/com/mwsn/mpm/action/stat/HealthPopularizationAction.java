package cn.com.mwsn.mpm.action.stat;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/HealthListStat.jsp"),
	@Result(name = "error", location = "/error/error.jsp"),
	@Result(name = "to_update_health_page", location = "/WEB-INF/pages/stat/healthManagement.jsp")})
public class HealthPopularizationAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8479567996952946833L;

	private static final Logger log = Logger.getLogger(HealthPopularizationAction.class);
	
	@Autowired
	private HealthPopularizationService hService;
	
	private String message;
	
	private List<List<String>> aaData=new ArrayList<List<String>>();
	
	private int pageId;
	
	@Override
	public String execute() throws Exception {
		return "success";
	}
	
	@Action(value = "save_html_content", results = { @Result(name = "success", type = "json") }	)
	public String saveHtmlContent(){
		HttpServletRequest request=Struts2Utils.getRequest();
		String htmlContent=request.getParameter("htmlContent");
		HealthContent healthContent=new HealthContent();
		healthContent.setJspContent(htmlContent);
		try {
			hService.saveContent(healthContent);
		} catch (Exception e) {
			message="error";
			return this.ERROR;
		}
		message="success";
		return "success";
	}
	
	@Action(value = "get_all_healthpages", results = { @Result(name = "success", type = "json") }	)
	public String getAllHealthPages(){
		List<HealthContent> list=hService.getAllPages();
		for(HealthContent healthContent:list){
			List<String> ls=new ArrayList<String>();
			ls.add(healthContent.getTitle());
			ls.add(healthContent.getImagePath());
			if(healthContent.getIsUsed()==0){
				ls.add("未使用");
			}else if(healthContent.getIsUsed()==1){
				ls.add("使用中");
			}else{
				ls.add("");
			}
			if(healthContent.getType()==0){
				ls.add("页面");
			}else if(healthContent.getType()==1){
				ls.add("图片");
			}else{
				ls.add("");
			}
			ls.add("<a href='javascript:void(0)' onclick='delete_page("+healthContent.getId()+")'>删除</a> <a href='javascript:void(0)' onclick='updateJsp("+healthContent.getId()+")'>修改</a>");
			aaData.add(ls);
		}
		
		return "success";
	}
	
	@Action(value = "delete_healthpage", results = { @Result(name = "success", type = "json") }	)
	public String delteheathPage(){
		HttpServletRequest request=Struts2Utils.getRequest();
		String id=request.getParameter("id");
		if(id!=null && !"".equals(id)){
			hService.deleteHealthPageById(Integer.parseInt(id));
			message="delete_success";
		}else{
			return this.ERROR;
		}
		return "success";
	}
	
	@Action(value = "find_health_content", results = { @Result(name = "success", type = "json") }	)
	public String findHealthContent(){
		HttpServletRequest request=Struts2Utils.getRequest();
		String id=request.getParameter("id");
		if(id!=null && "".equals(id)){
		}else{
			return this.ERROR;
		}
		return "success";
	}
	
	@Action(value = "to_update_health_page")
	public String redirectPage(){
		HttpServletRequest request=Struts2Utils.getRequest();
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id))
		pageId=Integer.parseInt(id);
		return "to_update_health_page";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<List<String>> getAaData() {
		return aaData;
	}

	public void setAaData(List<List<String>> aaData) {
		this.aaData = aaData;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	
	

}
