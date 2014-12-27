package cn.com.mwsn.mpm.action.stat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.HealthContent;
import cn.com.mwsn.mpm.entity.HealthType;
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
	
//	private String types;   //菜单1，菜单2    类型1，类型2
	
	private List<String> types=new ArrayList<String>();
	
	private HealthContent healthContent1;
	
	@Override
	public String execute() throws Exception {
		return "success";
	}
	
	@Action(value = "save_html_content", results = { @Result(name = "success", type = "json") }	)
	public String saveHtmlContent(){
		HttpServletRequest request=Struts2Utils.getRequest();
		String id=request.getParameter("id");
		String htmlContent=request.getParameter("htmlContent");
		String title=request.getParameter("title");
		String type=request.getParameter("type");
		String imagePath=request.getParameter("imagePath");
		String descr=request.getParameter("descr");
		
		if(id!=null && !"".equals(id)){
			HealthContent healthContent=hService.findHealthPageByid(Integer.parseInt(id));
			healthContent.setJspContent(htmlContent);
			healthContent.setTitle(title);
			healthContent.setImagePath(imagePath);
			healthContent.setType(type);
			healthContent.setDescr(descr);
			hService.updateHealthContent(healthContent);
		}else{
			HealthContent healthContent=new HealthContent();
			healthContent.setJspContent(htmlContent);
			healthContent.setTitle(title);
			healthContent.setImagePath(imagePath);
			healthContent.setType(type);
			healthContent.setDescr(descr);
			//修改其他类型为type的为未使用
			//hService.setPageIsUsed("type",type, 0);
			//设置新添的内容为已使用
			healthContent.setIsUsed(1);//已使用
			try {
				hService.saveContent(healthContent);
			} catch (Exception e) {
				message="error";
				return this.ERROR;
			}
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
			String actionString=null;
			if(healthContent.getIsUsed()==0){
				ls.add("无效");
				actionString="<a href='javascript:void(0)' onclick='delete_page("+healthContent.getId()+")'>删除</a> <a href='javascript:void(0)' onclick='updateJsp("+healthContent.getId()+")'>修改</a><a title='设置无效' href='javascript:void(0)' onclick='setIsUsed("+healthContent.getId()+","+1+",\""+healthContent.getType()+"\")'> 有效</a>";
			}else if(healthContent.getIsUsed()==1){
				ls.add("有效");
				actionString="<a href='javascript:void(0)' onclick='delete_page("+healthContent.getId()+")'>删除</a> <a href='javascript:void(0)' onclick='updateJsp("+healthContent.getId()+")'>修改</a><a title='设置有效' href='javascript:void(0)' onclick='setIsUsed("+healthContent.getId()+","+0+",\""+healthContent.getType()+"\")'> 无效</a>";
			}else{
				ls.add("");
			}
			ls.add(healthContent.getType());
			ls.add(actionString);
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
		if(id!=null && !"".equals(id)){
			healthContent1=hService.findHealthPageByid(Integer.parseInt(id));
			message="success";
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
		List<HealthType> list=hService.getHealthTypes();
		for(HealthType healthType:list){
			types.add(healthType.getTypeName());
		}
		return "to_update_health_page";
	}
	
	@Action(value = "findtypes", results = { @Result(name = "success", type = "json") }	)
	public String findTypes(){
		List<HealthType> list=hService.getHealthTypes();
		for(HealthType healthType:list){
			types.add(healthType.getTypeName());
		}
		return "success";
	}
	
	@Action(value = "addtypes", results = { @Result(name = "success", type = "json") }	)
	public String addTypes(){
		HttpServletRequest request=Struts2Utils.getRequest();
		String typeName=request.getParameter("typeName");
		if(hService.findHealthType(typeName)==null){
			hService.addHealthType(typeName);
			message="success";
		}else{
			message="error_same_name";
		}
		return "success";
	}
	
	@Action(value = "deletetypes", results = { @Result(name = "success", type = "json") }	)
	public String deleteTypes(){
		HttpServletRequest request=Struts2Utils.getRequest();
		String typeName=request.getParameter("typeName");
		hService.deleteHealthType(typeName);
		message="success";
		return "success";
	}
	
	@Action(value = "set_page_used", results = { @Result(name = "success", type = "json") }	)
	public String setPageIsUsed(){
		HttpServletRequest request=Struts2Utils.getRequest();
		String id=request.getParameter("id");
		String isUsed=request.getParameter("isUsed");
		//String type=request.getParameter("type");
		if(isUsed!=null & !"".equals(isUsed)){
			int isUser_=Integer.parseInt(isUsed);
			//修改其他类型为type的为未使用
			//hService.setPageIsUsed("type",type, 0);
			hService.setPageIsUsed("id",id, isUser_);
			message="success";
		}else{
			message="error";
			return this.ERROR;
		}
		return "success";
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

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public HealthContent getHealthContent1() {
		return healthContent1;
	}

	public void setHealthContent1(HealthContent healthContent1) {
		this.healthContent1 = healthContent1;
	}

}
