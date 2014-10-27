package cn.com.mwsn.dataplatform.eventengine.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.mwsn.dataplatform.eventengine.EventEngine;
import cn.com.mwsn.dataplatform.eventengine.EventHandler;
import cn.com.mwsn.dataplatform.eventengine.common.Status;
import cn.com.mwsn.dataplatform.eventengine.entity.Event;
import cn.com.mwsn.frame.bean.BeanFactory;
import cn.com.mwsn.mpm.receiver.EventReceiver;


@Service
public class EventHandlerImpl implements EventHandler {
	
	private static final Logger log = Logger.getLogger(EventHandlerImpl.class);
	
	@Autowired
	private EventEngine eventEngine;

	@Override
	public void handle(Event event) {
		//发送到mpm webservice端
		
		EventReceiver er = (EventReceiver)BeanFactory.getBean("wsEventReceiver");
		er.handle(
				event.getTagID(),
				event.getAntennaID(),
				event.getTime(),
				event.getStatus());
		
		//交由eventengine进行进一步处理，通过eventengine的处理，产生incident
		if(!Status.bufferAntenna.equals(event.getAntennaID()) || Status.bufferAntenna == "") {
			System.out.println("缓存是" + Status.bufferAntenna + ";  " + "进来是" + event.getAntennaID());
			eventEngine.handle(event);
		}
		Status.bufferAntenna = event.getAntennaID();
	}

}
