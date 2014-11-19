package cn.com.mwsn.mpm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.Doctor;
import cn.com.mwsn.mpm.entity.User;
import cn.com.mwsn.mpm.service.DoctorService;



@Service("doctorService")
@Transactional
public class DoctorServiceImpl extends TransactionalServiceImpl implements
		DoctorService {
	
	@Override
	public List<Doctor> queryAllDoctors() {
		List<Doctor> result = this.findAll(Doctor.class);
		return result;
	}

	@Override
	public Doctor queryDoctor(String empNO) {
		return this.findBy(Doctor.class, "doctorId", empNO);
	}

	@Override
	public List<Doctor> findNotBingDoctor() {
		return this.find("from Doctor where doctorId not in(select staffNum from User)", new Object[]{});
	}

}
