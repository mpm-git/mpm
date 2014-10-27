package cn.com.mwsn.mpm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.CurrentPosition;
import cn.com.mwsn.mpm.service.InpatientAreaMonitorService;


@Service
@Transactional
public class InpatientAreaMonitorServiceImpl extends TransactionalServiceImpl
		implements InpatientAreaMonitorService {
	
	
	@Override
	public List<CurrentPosition> queryAllCurrentPosition() {
		return this.findAll(CurrentPosition.class);
	}

}
