package cn.com.mwsn.mpm.service;

import cn.com.mwsn.frame.service.TransactionalService;
import cn.com.mwsn.mpm.entity.Patient;

public interface PatientService extends TransactionalService{

	public Patient queryPatient(String empNO);
}
