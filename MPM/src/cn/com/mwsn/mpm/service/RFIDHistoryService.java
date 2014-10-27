package cn.com.mwsn.mpm.service;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.TransactionalService;

public interface RFIDHistoryService extends TransactionalService {
	
	public void insertRFIDHistory(Incident incident); 

}
