package cn.com.mwsn.mpm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.binding.corba.wsdl.Object;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.HealthContent;
import cn.com.mwsn.mpm.entity.HealthType;
import cn.com.mwsn.mpm.service.HealthPopularizationService;

@Service
@Transactional
public class HealthPopularizationServiceImpl  extends TransactionalServiceImpl implements HealthPopularizationService{

	private static final Logger log = Logger.getLogger(HealthPopularizationServiceImpl.class);
	
	@Override
	public void saveContent(HealthContent htmlContent) {
		this.save(htmlContent);
	}

	@Override
	public List<HealthContent> getAllPages() {
		return this.findAll(HealthContent.class);
	}

	@Override
	public void deleteHealthPageById(int id) {
		this.remove(HealthContent.class, id);
	}

	@Override
	public HealthContent findHealthPageByid(int id) {
		HealthContent heal=null;
		try {
			heal=this.find(HealthContent.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return heal;
	}

	@Override
	public void setPageIsUsed(String columnName,String value, int isUsed) {
		String queryString="update HealthContent set isUsed="+isUsed+" where "+columnName+"='"+value+"'";
		try {
			this.update(queryString, new Object[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateHealthContent(HealthContent content) {
		this.merge(content);
	}

	@Override
	public List<HealthType> getHealthTypes() {
		return this.findAll(HealthType.class);
	}

	@Override
	public void deleteHealthType(String typeName) {
//		String query="delete HealthContent where typeName=:typeName";
//		Map<String, String> map=new HashMap<String, String>();
//		map.put("typeName", typeName);
//		try {
//			this.update(query, map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		this.remove(HealthType.class, this.findBy(HealthType.class, "typeName", typeName).getId());
	}

	@Override
	public void addHealthType(String typeName) {
		this.save(new HealthType(typeName));
	}

	@Override
	public HealthType findHealthType(String healthType) {
		return this.findBy(HealthType.class, "typeName", healthType);
	}

}
