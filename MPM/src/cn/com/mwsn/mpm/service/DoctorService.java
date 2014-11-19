package cn.com.mwsn.mpm.service;

import java.util.List;

import cn.com.mwsn.frame.service.TransactionalService;
import cn.com.mwsn.mpm.entity.Doctor;

public interface DoctorService extends TransactionalService {
	
	/**
	 * 查询所有的医生
	 * @return
	 */
	public List<Doctor> queryAllDoctors();
	
	/**
	 * 根据员工好查询所有医生
	 * @return
	 */
	public Doctor queryDoctor(String empNO);
	
	
	/**
	 * 查询所有未绑定权限的医生
	 * @return
	 */
	public List<Doctor> findNotBingDoctor();
}

