package cn.com.mwsn.mpm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.entity.IdEntity;
import cn.com.mwsn.frame.exception.ServiceException;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.common.Constant.ROLE;
import cn.com.mwsn.mpm.entity.RFIDCardPerson;
import cn.com.mwsn.mpm.service.RFIDCardPersonService;

@Service("rfidCardPersonService")
@Transactional
public class RFIDCardPersonServiceImpl extends TransactionalServiceImpl implements 
		RFIDCardPersonService {

	@Override
	public RFIDCardPerson queryPerson(String tagID) {
		System.out.println("测试" + tagID);
		return this.findBy(RFIDCardPerson.class, "tagID", tagID);
	} 
}
