package cn.com.mwsn.mpm.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.NurseAlert;
import cn.com.mwsn.mpm.service.NurseAlertService;


@Service
@Transactional
public class NurseAlertServiceImpl extends TransactionalServiceImpl
		implements NurseAlertService {
	
	private static final Logger log = Logger.getLogger(NurseAlertServiceImpl.class);
	@Override
	public List<NurseAlert> queryAllNurseAlert() {
		return this.findAll(NurseAlert.class);
	}

	@Override
	public void updateNurseAlert() {
		// TODO Auto-generated method stub
		log.debug("进入NurseAlertServiceImpl");
	}

}
