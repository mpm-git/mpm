package cn.com.mwsn.mpm.action.stat;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.mpm.entity.bean.Menu;
import cn.com.mwsn.mpm.service.MenuService;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value="json-default")
public class MenusAction extends ActionSupport{

	private static final long serialVersionUID = 5469264744042194241L;
	
	@Autowired
	private MenuService menuService;
	
	private List<Menu> menus;
	
	private String message;
	
	@Action(value = "get_all_menus", results = { @Result(name = "success", type = "json") }	)
	public String getAllMenus(){
		menus=menuService.findMenus();
		message="success";
		return "success";
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
