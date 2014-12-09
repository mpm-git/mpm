package cn.com.mwsn.mpm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.binding.corba.wsdl.Array;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.HealthContent;
import cn.com.mwsn.mpm.entity.bean.Menu;
import cn.com.mwsn.mpm.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl  extends TransactionalServiceImpl implements MenuService{
	
	private static final Logger log = Logger.getLogger(MenuServiceImpl.class);

	@Override
	public List<Menu> findMenus() {
		String queryString="select type from HealthContent group by type";
		List<String> list=this.find(queryString, new Object[]{});
		List<Menu> menus=new ArrayList<Menu>();
		for(String menuName:list){
			Menu menu=new Menu();
			menu.setMenusName(menuName);
			String queryStr="from HealthContent where type=:type";
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("type", menuName);
			List<HealthContent> pages=null;
			try {
				pages=this.find(queryStr, new Object[]{map});
			} catch (Exception e) {
				e.printStackTrace();
			}
			menu.setList(pages);
			menus.add(menu);
		}
		return menus;
	}
}
