package cn.com.mwsn.mpm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.Nurse;
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
}
