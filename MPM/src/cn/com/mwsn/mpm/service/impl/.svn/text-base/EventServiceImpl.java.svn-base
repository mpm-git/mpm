package cn.com.mwsn.mpm.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.dataplatform.eventengine.entity.Event;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.service.EventService;



@Service("eventService")
@Transactional
public class EventServiceImpl extends TransactionalServiceImpl implements 
		EventService {

	private static final Logger log = Logger.getLogger(EventServiceImpl.class);
	@Override
	public void updateEvent(Event event) {
		
		this.save(event);
	}

}
