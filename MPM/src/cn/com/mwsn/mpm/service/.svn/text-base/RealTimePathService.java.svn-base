package cn.com.mwsn.mpm.service;

import java.util.List;

import cn.com.mwsn.mpm.entity.ActionReportStat;

public interface RealTimePathService {
	/**获取所有轨迹
	 * 修改接口，ls
	 * 添加参数，用户编号，开始时间与结束时间
	 * @param userCode 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ActionReportStat> queryAllPath(String userCode,Long startTime,Long endTime);
	
	/**
	 * 查询最后1条数据，时间暂时不考虑
	 * @param userCode
	 * @param queryTime
	 * @return
	 */
	public ActionReportStat querySpecificPath(String userCode,Long queryTime,String userType);
}
