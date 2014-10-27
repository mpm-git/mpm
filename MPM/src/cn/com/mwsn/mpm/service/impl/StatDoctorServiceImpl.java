package cn.com.mwsn.mpm.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.service.StatDoctorService;



@Service
@Transactional
public class StatDoctorServiceImpl extends TransactionalServiceImpl implements
		StatDoctorService {

}
