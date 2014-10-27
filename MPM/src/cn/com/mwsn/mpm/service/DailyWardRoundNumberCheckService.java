package cn.com.mwsn.mpm.service;

import java.util.List;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.TransactionalService;
import cn.com.mwsn.mpm.service.DailyWardRoundNumberCheckService;
import cn.com.mwsn.mpm.entity.WardRoundNumberByDay;

public interface DailyWardRoundNumberCheckService extends TransactionalService {
	
	/**
	 * 查询所有医护人员本日的查房信息
	 * @return
	 */
	public List<WardRoundNumberByDay> queryAllDailyWardRoundNumber();
	
	/**
	 * 更新医护人员日查房记录
	 */
	public void updateDailyWardRoundNumber(Incident incident);

}
