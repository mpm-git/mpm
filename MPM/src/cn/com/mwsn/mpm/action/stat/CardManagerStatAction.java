package cn.com.mwsn.mpm.action.stat;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.RFIDCardPerson;
import cn.com.mwsn.mpm.service.RFIDCardPersonService;

@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/CardManager.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class CardManagerStatAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5377050751703222726L;
	private static final Logger log = Logger.getLogger(RFIDCardPerson.class);
	
	@Autowired
	private RFIDCardPersonService rfidpersonservice;

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
    	List<RFIDCardPerson> rfidcardperson = rfidpersonservice.findAll(RFIDCardPerson.class);
		request.setAttribute("CardManager", rfidcardperson);
		
		return this.SUCCESS;
	}

}
