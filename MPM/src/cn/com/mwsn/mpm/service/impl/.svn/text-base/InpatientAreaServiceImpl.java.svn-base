package cn.com.mwsn.mpm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.InpatientArea;
import cn.com.mwsn.mpm.service.InpatientAreaService;


@Service
@Transactional
public class InpatientAreaServiceImpl extends TransactionalServiceImpl
		implements InpatientAreaService {
	
	

	@Override
	public List<InpatientArea> queryAllInpatientAreas() {
		return this.findAll(InpatientArea.class);
	}

	
	
	

}
