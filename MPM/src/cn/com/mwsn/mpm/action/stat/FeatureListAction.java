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

import com.opensymphony.xwork2.ActionSupport;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.User;
import cn.com.mwsn.mpm.service.UserService;

/**
 * 用户权限管理
 * @author Administrator
 *
 */
@ParentPackage(value="json-default")
@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/right.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class FeatureListAction  extends ActionSupport {
	private static final long serialVersionUID = 7028939684771417972L;
	private static final Logger log = Logger.getLogger(FeatureListAction.class);
	@Autowired
	private UserService userService;
	
	private List<List<String>> aaData;
	
	private String message;
	
	@Action(value = "get_all_users_features", results = { @Result(name = "success", type = "json") }	)
	public String getUsersFeatures(){
		System.out.println("11111");
//		HttpServletRequest request = Struts2Utils.getRequest();
//		String page=request.getParameter("page");
//		String rows=request.getParameter("rows");
//		log.info("page:"+page+"---rows:"+rows);
//		String query="select * from User";
//		List<User> list=userService.find(query, null, page, rows);
		List<User> list=userService.findAll(User.class);
		aaData=new ArrayList<List<String>>();
		for(User user:list){
			List<String> ls=new ArrayList<String>();
			ls.add(user.getStaffNum());
			ls.add(user.getStaffNum());
			ls.add(user.getUserRole());
			ls.add("<a href='javascript:void(0)' onclick='delete_feature("+user.getId()+")'>删除</a> <a href='javascript:void(0)'>修改</a>");
			ls.add("这里是备注内容！");
//			aaData+=ls.toString()+",";
			aaData.add(ls);
		}
//		aaData="{"+aaData.substring(0, aaData.length()-1)+"}";
//		System.out.println("aaData1:"+aaData.toString());
		return "success";
		
	}
	
	@Action(value = "delete_feature", results = { @Result(name = "success", type = "json") }	)
	public String delteFeature(){
		HttpServletRequest request = Struts2Utils.getRequest();
		String id=request.getParameter("id");
		System.out.println("id:"+id);
		if(null!=id && !"".equals(id)){
				userService.remove(User.class, Integer.parseInt(id));
				message="delete_success";
		}else{
			message="delete_faile";
		}
		return "success";
	}

	public List<List<String>> getAaData() {
		return aaData;
	}

	public void setAaData(List<List<String>> aaData) {
		this.aaData = aaData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}




	
}
