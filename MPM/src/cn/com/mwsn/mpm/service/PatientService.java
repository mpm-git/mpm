package cn.com.mwsn.mpm.service;

import java.util.List;
import java.util.Map;

import cn.com.mwsn.frame.service.QueryResult;
import cn.com.mwsn.frame.service.TransactionalService;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_view;
import cn.com.mwsn.mpm.entity.Patient;
import cn.com.mwsn.mpm.entity.Patient_view;

public interface PatientService extends TransactionalService{

	public Patient queryPatient(String empNO);

	QueryResult<List<Patient_view>> find(Map<String, Object> map,
			int page_index, int page_size);
}
