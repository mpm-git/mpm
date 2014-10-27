package cn.com.mwsn.mpm.service;

import java.util.List;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.TransactionalService;
import cn.com.mwsn.mpm.service.WeeklyWardRoundNumberCheckService;
import cn.com.mwsn.mpm.entity.WardRoundNumberByWeek;

public interface WeeklyWardRoundNumberCheckService extends TransactionalService {
	
	/**
	 * 查询所有医护人员本周的查房信息
	 * @return
	 */
	public List<WardRoundNumberByWeek> queryAllWeeklyWardRoundNumber();
	
	/**
	 * 更新所有医护人员本周的查房信息
	 */
	public void updateWeeklyWardRoundNumber(Incident incident);
}
