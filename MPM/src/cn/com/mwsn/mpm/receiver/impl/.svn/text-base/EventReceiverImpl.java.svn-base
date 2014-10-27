package cn.com.mwsn.mpm.receiver.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import cn.com.mwsn.dataplatform.eventengine.entity.Event;
import cn.com.mwsn.mpm.receiver.EventReceiver;
import cn.com.mwsn.mpm.service.EventService;

@WebService(endpointInterface = "cn.com.mwsn.mpm.receiver.EventReceiver")
public class EventReceiverImpl extends SpringBeanAutowiringSupport implements
		EventReceiver {
	
	
	private static final Logger log = Logger.getLogger(EventReceiverImpl.class);

	@Autowired
	private EventService eventService;

	@Override
	public void say(String str) {
		log.debug("**************************"+str);
		
	}


	@Override
	public void handle(String tagID, String antennaID, Long time, Integer status) {
		
		Event event = new Event();
		event.setAntennaID(antennaID);
		event.setStatus(status);
		event.setTagID(tagID);
		event.setTime(time);
		log.debug(tagID);
		log.debug(antennaID);
		log.debug(time);
		log.debug(status);
		
		eventService.updateEvent(event);   
	}


}
