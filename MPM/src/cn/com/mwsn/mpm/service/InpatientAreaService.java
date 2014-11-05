package cn.com.mwsn.mpm.service;

import java.util.List;

import cn.com.mwsn.frame.service.TransactionalService;
import cn.com.mwsn.mpm.entity.InpatientArea;

public interface InpatientAreaService extends TransactionalService {
	
	/**
	 * 查询所有的病区
	 * @return
	 */
	List<InpatientArea> findAllInpatientAreas();

	InpatientArea findInpatientAreasByname(String name);

	
}
