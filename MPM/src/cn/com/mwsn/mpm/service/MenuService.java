package cn.com.mwsn.mpm.service;

import java.util.List;

import cn.com.mwsn.mpm.entity.bean.Menu;

public interface MenuService {
	
	/**
	 * 查询所有的menus
	 */
	List<Menu> findMenus();

}
