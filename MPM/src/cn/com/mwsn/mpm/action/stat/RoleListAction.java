package cn.com.mwsn.mpm.action.stat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.Doctor;
import cn.com.mwsn.mpm.entity.User;
import cn.com.mwsn.mpm.service.DoctorService;
import cn.com.mwsn.mpm.service.NurseService;
import cn.com.mwsn.mpm.service.UserService;

/**
 * 用户权限管理
 * @author Administrator
 *
 */
@ParentPackage(value="json-default")
@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/right.jsp"),
	@Result(name = "error", location = "/error/error.jsp"),
	@Result(name="to_update_page", location="/WEB-INF/pages/stat/rightUpdate.jsp"),})
public class RoleListAction  extends ActionSupport {
	private static final long serialVersionUID = 7028939684771417972L;
	private static final Logger log = Logger.getLogger(RoleListAction.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private NurseService nurseService;
	
	private List<List<String>> aaData=new ArrayList<List<String>>();;
	
	private int userId;
	
	private String message;
	
	private User user;
	
	@Action(value = "get_all_users_features", results = { @Result(name = "success", type = "json") }	)
	public String getUsersFeatures(){
//		HttpServletRequest request = Struts2Utils.getRequest();
//		String page=request.getParameter("page");
//		String rows=request.getParameter("rows");
//		log.info("page:"+page+"---rows:"+rows);
//		String query="select * from User";
//		List<User> list=userService.find(query, null, page, rows);
		List<Map> list=userService.findUserAndDoctor();
		for(Map map:list){
			List<String> ls=new ArrayList<String>();
			ls.add(map.get("name")==null?"":map.get("name").toString());
			ls.add(map.get("staffNum")==null?"":map.get("staffNum").toString());
			ls.add(map.get("userRole")==null?"":map.get("userRole").toString());
			Object obj=map.get("userId");
			if(obj!=null){
				int userId=Integer.parseInt(obj.toString());
				ls.add("<a href='javascript:void(0)' onclick='delete_feature("+userId+")'>删除</a> <a href='javascript:void(0)' onclick='redirectJsp("+userId+")'>修改</a>");
			}else{
				return this.ERROR;
			}
			ls.add(map.get("description")==null?"":map.get("description").toString());
			aaData.add(ls);
		}
		List<Map> list1=userService.findUserAndNurse();
		for(Map map:list1){
			List<String> ls=new ArrayList<String>();
			ls.add(map.get("name")==null?"":map.get("name").toString());
			ls.add(map.get("staffNum")==null?"":map.get("staffNum").toString());
			ls.add(map.get("userRole")==null?"":map.get("userRole").toString());
			Object obj=map.get("userId");
			if(obj!=null){
				int userId=Integer.parseInt(obj.toString());
				ls.add("<a href='javascript:void(0)' onclick='delete_feature("+userId+")'>删除</a> <a href='javascript:void(0)' onclick='redirectJsp("+userId+")'>修改</a>");
			}else{
				return this.ERROR;
			}
			ls.add(map.get("description")==null?"":map.get("description").toString());
			aaData.add(ls);
		}
		
//		List<User> list=userService.findAll(User.class);
//		aaData=new ArrayList<List<String>>();
//		for(User user:list){
//			List<String> ls=new ArrayList<String>();
//			ls.add(user.getStaffNum());
//			ls.add(user.getStaffNum());
//			ls.add(user.getUserRole());
//			ls.add("<a href='javascript:void(0)' onclick='delete_feature("+user.getId()+")'>删除</a> <a href='javascript:void(0)' onclick='redirectJsp("+user.getId()+")'>修改</a>");
//			ls.add("这里是备注内容！");
////			aaData+=ls.toString()+",";
//			aaData.add(ls);
//		}
		
//		aaData="{"+aaData.substring(0, aaData.length()-1)+"}";
//		System.out.println("aaData1:"+aaData.toString());
		return "success";
		
	}
	
	@Action(value = "delete_feature", results = { @Result(name = "success", type = "json") }	)
	public String delteFeature(){
		HttpServletRequest request = Struts2Utils.getRequest();
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id)){
				userService.remove(User.class, Integer.parseInt(id));
				message="delete_success";
		}else{
			message="delete_faile";
		}
		return "success";
	}

	@Action(value = "get_user_by_id", results = { @Result(name = "success", type = "json") }	)
	public String redirectPage(){
		HttpServletRequest request = Struts2Utils.getRequest();
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id)){
			user=userService.findBy(User.class, "id", Integer.parseInt(id));
		}else{
			return "error";
		}
		return "success";
	}
	
	@Action(value = "update_user_role", results = { @Result(name = "success", type = "json") }	)
	public String updateUserRole(){
		HttpServletRequest request = Struts2Utils.getRequest();
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id)){
			user=userService.findBy(User.class, "id", Integer.parseInt(id));
		}
		String userRole=request.getParameter("userRole");
		if(null!=userRole && !"".equals(userRole)){
			user.setUserRole(userRole);
			userService.merge(user);
		}else{
			return "error";
		}
		return "success";
	}
	
	@Override
	public String execute() throws Exception {

		HttpServletRequest request = Struts2Utils.getRequest();
		String id=request.getParameter("id");
		if(null!=id && !"".equals(id))
		userId=Integer.parseInt(id);
		return "to_update_page";
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}




	
}
