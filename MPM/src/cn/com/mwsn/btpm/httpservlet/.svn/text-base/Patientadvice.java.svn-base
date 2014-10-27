package cn.com.mwsn.btpm.httpservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Patientadvice extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/html; charset=GBK");
		PrintWriter out = response.getWriter();
		String returnStr = "";
		returnStr = "return_code=200&Safe_notice=心情舒畅平和&Drug_allergy=李闯青霉素过敏";
		String url =  URLEncoder.encode(returnStr, "UTF-8");
		out.println(url);
	}
}
