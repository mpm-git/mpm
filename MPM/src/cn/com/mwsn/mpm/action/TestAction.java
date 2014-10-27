package cn.com.mwsn.mpm.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.common.Constant.RECORD_STATE;
import cn.com.mwsn.mpm.data.IOTSend2CP;
import cn.com.mwsn.mpm.entity.RFIDHistory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "success", location = "/test.jsp"),
		@Result(name = "error", location = "/index.jsp") })
public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 514119442200741739L;

	private static final Logger log = Logger.getLogger(TestAction.class);
	private HttpClient httpclient = new DefaultHttpClient();
	private HttpResponse httpResponse = null;
	private IOTSend2CP iotSend2CP = new IOTSend2CP();

	public String execute() {

		ServletContext session = Struts2Utils.getApplication();
		HttpServletRequest request = Struts2Utils.getRequest();
		String state = request.getParameter("state");
		String roomno = request.getParameter("roomno");
		
		
		
		// 张华
		if (state.equals("11")) {
			if (roomno.equals("1")) {
				session.setAttribute("p-zh", "94-38");
				session.setAttribute("p-zh-room", "in1");
			}
			if (roomno.equals("2")) {
				session.setAttribute("p-zh", "200-40");
				session.setAttribute("p-zh-room", "in2");
			}
		} else if (state.equals("12")) {
			String inno = (String) session.getAttribute("p-zh-room");
			if (inno != null) {
				if (inno.equals("in1")) {
					session.setAttribute("p-zh", "70-210");
				}
				if (inno.equals("in2")) {
					session.setAttribute("p-zh", "190-210");
				}
			}
		} else if (state.equals("13")) {
			session.removeAttribute("p-zh");
		} else if (state.equals("14")) {
			if (session.getAttribute("r-zh") == null) {
				session.setAttribute("r-zh", "1");
			} else {
				String temp = (String) session.getAttribute("r-zh");
				session.setAttribute("r-zh",
						String.valueOf(Integer.valueOf(temp) + 1));
			}

		}

		// 张艳艳
		if (state.equals("21")) {
			if (roomno.equals("1")) {
				session.setAttribute("p-zyy", "54-38");
				session.setAttribute("p-zyy-room", "in1");
			}
			if (roomno.equals("2")) {
				session.setAttribute("p-zyy", "220-40");
				session.setAttribute("p-zyy-room", "in2");
			}
		} else if (state.equals("22")) {
			String inno = (String) session.getAttribute("p-zyy-room");
			if (inno != null) {
				if (inno.equals("in1")) {
					session.setAttribute("p-zyy", "50-210");
				}
				if (inno.equals("in2")) {
					session.setAttribute("p-zyy", "210-210");
				}
			}
		} else if (state.equals("23")) {
			session.removeAttribute("p-zyy");
		} else if (state.equals("24")) {
			if (session.getAttribute("r-zyy") == null) {
				session.setAttribute("r-zyy", "1");
			} else {
				String temp = (String) session.getAttribute("r-zyy");
				session.setAttribute("r-zyy",
						String.valueOf(Integer.valueOf(temp) + 1));
			}
		}

		// 李闯
		if (state.equals("31")) {
			if (roomno.equals("1")) {
				session.setAttribute("p-lc", "14-38");
				session.setAttribute("p-lc-room", "in1");
			}
			if (roomno.equals("2")) {
				session.setAttribute("p-lc", "180-40");
				session.setAttribute("p-lc-room", "in2");
			}
		} else if (state.equals("32")) {
			String inno = (String) session.getAttribute("p-lc-room");
			if (inno != null) {
				if (inno.equals("in1")) {
					session.setAttribute("p-lc", "100-210");
				}
				if (inno.equals("in2")) {
					session.setAttribute("p-lc", "230-210");
				}
			}
		} else if (state.equals("33")) {
			session.removeAttribute("p-lc");
		}

		return this.SUCCESS;
	}
}
