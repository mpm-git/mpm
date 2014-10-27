package cn.com.mwsn.mpm.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.Patient;
import cn.com.mwsn.mpm.service.PatientService;

@Service("patientService")
@Transactional
public class PatientServiceImpl extends TransactionalServiceImpl implements
		PatientService {

	@Override
	public Patient queryPatient(String empNO) {
		return this.findBy(Patient.class, "patientNo", empNO);
	}

}
