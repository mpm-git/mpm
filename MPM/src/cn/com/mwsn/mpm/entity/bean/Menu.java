package cn.com.mwsn.mpm.entity.bean;

import java.util.List;

import cn.com.mwsn.mpm.entity.HealthContent;

public class Menu {
	
	private String menusName;
	
	private List<HealthContent> list;

	public List<HealthContent> getList() {
		return list;
	}

	public void setList(List<HealthContent> list) {
		this.list = list;
	}

	public String getMenusName() {
		return menusName;
	}

	public void setMenusName(String menusName) {
		this.menusName = menusName;
	}

}
