package cn.com.mwsn.mpm.service.impl;

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

}
