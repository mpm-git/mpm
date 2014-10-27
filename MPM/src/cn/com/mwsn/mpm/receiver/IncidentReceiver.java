package cn.com.mwsn.mpm.receiver;

import javax.jws.WebService;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;


@WebService
public interface IncidentReceiver {
	
	
	public void say(String str);
	
	public void handle(Incident incident);

}
