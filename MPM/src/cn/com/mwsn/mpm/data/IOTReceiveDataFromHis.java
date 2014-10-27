package cn.com.mwsn.mpm.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.mwsn.btpm.common.Constant;

public class IOTReceiveDataFromHis extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
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
		returnStr = "return_code=200&bed_number=2&patient_name=李闯&Patient_sex&Patient_age=26&doctor=张华&nurse=张艳艳&Care_level=3&diet_care=不要吃辛辣的食物&time_in=0&department=内科";
		String url = Constant.PATIENTINFO + "=" + URLEncoder.encode(returnStr, "UTF-8");
		
		  
		  
        out.println(url);
     /*   out.println("<html>");
        out.println("<head>");
        out.println("<title>SimpleServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Hello</p>");
        out.println("<p>this is servlet!</p>");
        out.println("</body>");
        out.println("</html>");*/
    }
}
