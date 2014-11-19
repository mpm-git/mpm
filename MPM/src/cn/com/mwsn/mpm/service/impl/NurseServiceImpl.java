package cn.com.mwsn.mpm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.Doctor;
import cn.com.mwsn.mpm.entity.Nurse;
import cn.com.mwsn.mpm.entity.User;
import cn.com.mwsn.mpm.service.NurseService;

@Service("nurseService")
@Transactional
public class NurseServiceImpl extends TransactionalServiceImpl implements
		NurseService {

	@Override
	public Nurse findNurseByNo(String nurseNo) {
		return this.findBy(Nurse.class, "nurseNo", nurseNo);
	}
	@Override
	public List<Nurse> findNurseByInpatientArea(String InpatientArea) {
		return this.findByField(Nurse.class, "inpatientArea", InpatientArea);
	}
	@Override
	public List<Doctor> findNotBingNurse() {
		return this.find("from Nurse where nurseNo not in(select staffNum from User)", new Object[]{});
	}
}
