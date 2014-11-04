package cn.com.mwsn.mpm.service;

import java.util.List;
import java.util.Map;

import cn.com.mwsn.frame.service.QueryResult;
import cn.com.mwsn.frame.service.TransactionalService;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_nurse;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_patient;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_view;
import cn.com.mwsn.mpm.entity.NurseAlert;

public interface MH_NurseExecuteRecordService extends TransactionalService  {
	/**
	 * 查询所有执行警告信息
	 * @return
	 */
	QueryResult<List<MH_NurseExecuteRecord>> find(Map<String, Object> map, int page, int rows);

	QueryResult<List<MH_NurseExecuteRecord_nurse>> find_nurse(
			Map<String, Object> map, int pageIndex, int pageSize);

	QueryResult<List<MH_NurseExecuteRecord_patient>> find_patient(
			Map<String, Object> map, int pageIndex, int pageSize);

	QueryResult<List<MH_NurseExecuteRecord_nurse>> find_level(
			Map<String, Object> qParm, int page_index, int page_size);
	QueryResult<List<MH_NurseExecuteRecord_view>> find_view(
			Map<String, Object> qParm, int page_index, int page_size);
	
}
