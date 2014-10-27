package cn.com.mwsn.mpm.service;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.TransactionalService;

public interface ActionReportService extends TransactionalService{
	
	public void updateActionReport(Incident incident);

}
