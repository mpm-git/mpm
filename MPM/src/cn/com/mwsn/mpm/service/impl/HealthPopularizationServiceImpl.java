package cn.com.mwsn.mpm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.binding.corba.wsdl.Object;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.HealthContent;
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
		System.out.println("in findHealthPageByid method");
		HealthContent heal=null;
		try {
			heal=this.find(HealthContent.class, id);
			System.out.println(heal.getTitle());
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

}
