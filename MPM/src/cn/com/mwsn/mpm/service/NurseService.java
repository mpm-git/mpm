package cn.com.mwsn.mpm.service;

import java.util.List;

import cn.com.mwsn.frame.service.TransactionalService;
import cn.com.mwsn.mpm.entity.Doctor;
import cn.com.mwsn.mpm.entity.Nurse;

public interface NurseService extends TransactionalService{
	
	Nurse findNurseByNo(String empNO);

	List<Nurse> findNurseByInpatientArea(String InpatientArea);
	
	/**
	 * 查询所有未绑定权限的护士
	 * @return
	 */
	public List<Doctor> findNotBingNurse();
}
