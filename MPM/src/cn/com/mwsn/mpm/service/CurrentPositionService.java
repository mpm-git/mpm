package cn.com.mwsn.mpm.service;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.TransactionalService;

public interface CurrentPositionService extends TransactionalService{
/**
 *根据下层数据，更新当前位置表
 */
	public void updateCurrentPos(Incident incident);
}
