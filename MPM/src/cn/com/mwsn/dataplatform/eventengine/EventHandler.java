package cn.com.mwsn.dataplatform.eventengine;

import javax.jws.WebParam;
import javax.jws.WebService;

import cn.com.mwsn.dataplatform.eventengine.entity.Event;

public interface EventHandler {
	
	
	public void handle(Event event);

}
