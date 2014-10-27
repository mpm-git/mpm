package cn.com.mwsn.dataplatform.eventengine;

import cn.com.mwsn.dataplatform.eventengine.entity.Event;




public interface EventEngine {
	
	
	public void handle(Event event);

}
