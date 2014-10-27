package cn.com.mwsn.btpm.httpservlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.bean.BeanFactory;
import cn.com.mwsn.mpm.receiver.IncidentReceiver;
import cn.com.mwsn.mpm.service.CurrentPositionService;

public class GetActionFromDP extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6636393920471284202L;

//	private static Logger log = Logger.getLogger(GetActionFromDP.class);
	
	@Autowired
	private CurrentPositionService currentPositionService;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/html; charset=GBK");
		PrintWriter out = response.getWriter();
		String returnStr = "";
		returnStr = "200";
		String url =  URLEncoder.encode(returnStr, "UTF-8");
		out.println(url);
		
		
		String tagID = request.getParameter("tagID");
		String time = request.getParameter("time");
		String type = request.getParameter("type");
		String wardName = request.getParameter("wardName");
		System.out.println("tagID=" + tagID + "\n" + "time=" + time + "\n" + "type=" + type + "\n" + "wardName=" + wardName);
		
		
		
		Incident incident = new Incident();
		incident.setTagID(tagID);
		incident.setTime(Long.parseLong(time));
		incident.setType(Integer.parseInt(type));
		incident.setWardName(wardName);
		
		IncidentReceiver ir = (IncidentReceiver)BeanFactory.getBean("wsIcidentReceiver");
		ir.handle(incident);
		
		
		//发送给曼荼罗
		/*IOTSend2CP iotSend2CP = new IOTSend2CP();
		RFIDHistory rfidHistory = new RFIDHistory();
		rfidHistory.setValues(incident);
		iotSend2CP.loadRFID(rfidHistory);*/
	}

}
