package cn.com.mwsn.mpm.service;

import java.util.List;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.TransactionalService;
import cn.com.mwsn.mpm.entity.PatientAlert;

public interface PatientAlertService extends TransactionalService {
	
	/**
	 * 查询所有护士的告警信息
	 * @return
	 */
	public List<PatientAlert> queryAllPatientAlert();
	
	/**
	 * 更新病人告警信息表
	 */
	public void updatePatientAlert(Incident incident);

}
