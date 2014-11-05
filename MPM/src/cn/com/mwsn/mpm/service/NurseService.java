package cn.com.mwsn.mpm.service;

import java.util.List;

import cn.com.mwsn.frame.service.TransactionalService;
import cn.com.mwsn.mpm.entity.Nurse;

public interface NurseService extends TransactionalService{
	
	Nurse findNurseByNo(String empNO);

	List<Nurse> findNurseByInpatientArea(String InpatientArea);
}
