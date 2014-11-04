package cn.com.mwsn.mpm.action.stat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.cz.utils.HttpUtil;
import org.cz.utils.gson.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.service.QueryResult;
import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_nurse;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_patient;
import cn.com.mwsn.mpm.service.MH_NurseExecuteRecordService;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 护理工作强度
 * @author Administrator
 *
 */
@ParentPackage(value="json-default")
@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/nursingStrengthStat.jsp"),
	@Result(name = "error", location = "/error/error.jsp") ,
	@Result(name = "result", location = "/WEB-INF/pages/result4Json.jsp")})
public class MH_NurseExecuteRecordAction extends ActionSupport {
	
	private static final long serialVersionUID = 5377050751703222726L;
	@Autowired
	private MH_NurseExecuteRecordService mH_NurseExecuteRecordService;
	private static final Logger log = Logger.getLogger(MH_NurseExecuteRecordAction.class);

	@Action(value="mh_nurseExecuteRecordAction")
	public String execute() throws Exception {
		ServletContext session = Struts2Utils.getApplication();
		HttpServletRequest request = Struts2Utils.getRequest();
		Map<String, String> parameters = HttpUtil.getParameters(request, null);
		String _page_index = parameters.get("page_index");
		String _page_size = parameters.get("page_size");
		String order = parameters.get("order");
		String order_by = parameters.get("order_by");
		int page_index=_page_index==null||_page_index.trim().equals("")?1:(Integer.parseInt(_page_index));
		int page_size=_page_size==null||_page_size.trim().equals("")?0:(Integer.parseInt(_page_size));
		Map<String ,Object> qParm=new HashMap<String, Object>();
		qParm.put("order", order);
		qParm.put("order_by", order_by);
		qParm.put("conditions", parameters.get("conditions"));

		QueryResult<List<MH_NurseExecuteRecord_patient>> find_patient = mH_NurseExecuteRecordService.find_patient(qParm, page_index, page_size);
		request.setAttribute("result",GsonUtil.objectToJson(find_patient) );
		return "result";
	}
	@Action(value="mh_nurse_execute_record_patient")
	public String mh_nurse_execute_record_patient() throws Exception {
		ServletContext session = Struts2Utils.getApplication();
		HttpServletRequest request = Struts2Utils.getRequest();
		Map<String, String> parameters = HttpUtil.getParameters(request, "UTF-8");
		System.out.println(parameters);
		String _page_index = parameters.get("page_index");
		String _page_size = parameters.get("page_size");
		String order = parameters.get("order");
		String order_by = parameters.get("order_by");
		int page_index=_page_index==null||_page_index.trim().equals("")?1:(Integer.parseInt(_page_index));
		int page_size=_page_size==null||_page_size.trim().equals("")?0:(Integer.parseInt(_page_size));
		Map<String ,Object> qParm=new HashMap<String, Object>();
		qParm.put("order", order);
		qParm.put("order_by", order_by);
		qParm.put("name", parameters.get("name"));
		qParm.put("execute", parameters.get("execute"));
		qParm.put("conditions", parameters.get("conditions"));
		QueryResult<List<MH_NurseExecuteRecord_patient>> find_patient = mH_NurseExecuteRecordService.find_patient(qParm, page_index, page_size);
		request.setAttribute("result",GsonUtil.objectToJson(find_patient) );
		return "result";
	}
	@Action(value="mh_nurse_execute_record_nurse")
	public String mh_nurse_execute_record_nurse() throws Exception {
		ServletContext session = Struts2Utils.getApplication();
		HttpServletRequest request = Struts2Utils.getRequest();
		Map<String, String> parameters = HttpUtil.getParameters(request, null);
		String _page_index = parameters.get("page_index");
		String _page_size = parameters.get("page_size");
		String order = parameters.get("order");
		String order_by = parameters.get("order_by");
		int page_index=_page_index==null||_page_index.trim().equals("")?1:(Integer.parseInt(_page_index));
		int page_size=_page_size==null||_page_size.trim().equals("")?0:(Integer.parseInt(_page_size));
		Map<String ,Object> qParm=new HashMap<String, Object>();
		qParm.put("order", order);
		qParm.put("order_by", order_by);
		qParm.put("conditions", parameters.get("conditions"));

		QueryResult<List<MH_NurseExecuteRecord_nurse>> find_nurse = mH_NurseExecuteRecordService.find_nurse(qParm, page_index, page_size);
		request.setAttribute("result",GsonUtil.objectToJson(find_nurse) );
		return "result";
	}
	@Action(value="mh_nurse_execute_record_level")
	public String mh_nurse_execute_record_level() throws Exception {
		ServletContext session = Struts2Utils.getApplication();
		HttpServletRequest request = Struts2Utils.getRequest();
		Map<String, String> parameters = HttpUtil.getParameters(request, null);
		String _page_index = parameters.get("page_index");
		String _page_size = parameters.get("page_size");
		String order = parameters.get("order");
		String order_by = parameters.get("order_by");
		int page_index=_page_index==null||_page_index.trim().equals("")?1:(Integer.parseInt(_page_index));
		int page_size=_page_size==null||_page_size.trim().equals("")?0:(Integer.parseInt(_page_size));
		Map<String ,Object> qParm=new HashMap<String, Object>();
		qParm.put("order", order);
		qParm.put("order_by", order_by);
		qParm.put("conditions", parameters.get("conditions"));

		QueryResult<List<MH_NurseExecuteRecord_nurse>> find_nurse = mH_NurseExecuteRecordService.find_level(qParm, page_index, page_size);
		request.setAttribute("result",GsonUtil.objectToJson(find_nurse) );
		return "result";
	}
	@Action(value="mh_nurse_execute_record")
	public String mh_nurse_execute_record() throws Exception {
		ServletContext session = Struts2Utils.getApplication();
		HttpServletRequest request = Struts2Utils.getRequest();
		QueryResult<List<MH_NurseExecuteRecord>> find = mH_NurseExecuteRecordService.find(null, 0, 0);
		request.setAttribute("result",GsonUtil.objectToJson(find) );
		return "result";
	}
	
	/**
	 * 根据病区联动出病人的方法,json方法
	 * @return
	 */
	@Action(value="listNurses", results={@Result(name="listNurses", type="json")})
	public String listPatients(){
		return "listNurses";
	}
	
	/**
	 * 导出数据
	 * @return
	 * @throws Exception
	 */
	@Action(value = "nursingStrengthExportExcel", results = { @Result(name = "success", type = "stream", params = {  
            "contentType", "application/vnd.ms-excel", "inputName",  
            "excelFile", "contentDisposition",  
            "attachment;filename=${downloadFileName}.xls", "bufferSize", "1024" }) })
	public String exportExcel() throws Exception{
		return "success";
	} 
	
}
