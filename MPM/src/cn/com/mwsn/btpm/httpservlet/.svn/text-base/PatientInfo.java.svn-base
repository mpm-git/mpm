package cn.com.mwsn.btpm.httpservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.com.mwsn.btpm.common.*;
import cn.com.mwsn.frame.web.action.Struts2Utils;

public class PatientInfo extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		String patid = request.getParameter("patid");
		String patname = request.getParameter("patname");
		
		/*Enumeration params = request.getParameterNames();
		String str = "";
		while(params.hasMoreElements()) {
			String name = (String)params.nextElement();
			String value = request.getParameter(name);
			str += name + "=" + value + ";";
		}
		System.out.println(str);*/
		  
		response.setContentType("text/html; charset=GBK");
		PrintWriter out = response.getWriter();
		String returnStr = "";
		returnStr = "return_code=200&bed_number=2&patient_name=李闯&Patient_sex=男&Patient_age=26&doctor=张华&nurse=张艳艳&Care_level=3&diet_care=不要吃辛辣的食物&time_in=2012-4-26&department=妇瘤科";
		String url =  URLEncoder.encode(returnStr, "UTF-8");
		
		out.println(url);
		
		out.println(patid);
		out.println(patname);
		
//		System.out.println(url);

//		System.out.println(patid);
//		System.out.println(patname);
	}
}
