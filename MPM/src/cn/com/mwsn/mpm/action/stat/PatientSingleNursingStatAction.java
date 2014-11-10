package cn.com.mwsn.mpm.action.stat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.cz.utils.ClassUtil;
import org.cz.utils.DateUtil;
import org.cz.utils.Util;
import org.cz.utils.gson.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.service.QueryResult;
import cn.com.mwsn.frame.util.ExportExcelUtil;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_view;
import cn.com.mwsn.mpm.entity.Patient;
import cn.com.mwsn.mpm.service.MH_NurseExecuteRecordService;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 病人护理行为分析
 * @author Administrator
 *
 */
@ParentPackage(value="json-default")
@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/patientSingleNurseStat.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class PatientSingleNursingStatAction extends ActionSupport {
	
	private static final long serialVersionUID = 5377050751703222726L;
	@Autowired
	private MH_NurseExecuteRecordService mH_NurseExecuteRecordService;
	private static final Logger log = Logger.getLogger(PatientSingleNursingStatAction.class);

	//作为展示基础数据用
//	private static Map<String,List<Patient>> allPatientList;//所有病人列表 key为病区
	private static Map<String,String> allPatientBehavior;//所有护理行为
	private static Map<String,String> allDepartments;;//所有病区
	//查询条件
	private int patientArea = 1;//病区
//	private Map<String,String> patientList;//病区对应的病人列表
//	private String patients;//选中病人
	private String beginStatDate;
	private String endStatDate;
	private String[] patientBehavior;//选中的护理行为
	private String[] yAxisName;//护理维度y轴显示
	
	private static Map<String,String> allXAxisNamesValue;
	private static Map<String,String> allXAxisNames;//所有x轴护理维度设定
	List<String> xAxisNames;//x轴
	List<String> xAxisNamesValue;//x轴
	String title=null;
	String fieldID=null;
	String fieldValue=null;
	private String[] patientBehaviorValue;//选中的护理行为
	
	QueryResult<List<MH_NurseExecuteRecord_view>> find_view;//所有数据
	private Map<String,MH_NurseExecuteRecord_view> find_view_map;//图形的数据
	DealSum dealSum;
	String invokeField=null;
	
	//数据
	private List<List<String>> tableData;//表格的数据
	private List<List<String>> chartData;//图形的数据
	
	//查询类型
	private int searchType = 1; //1:综合分析，2：按护理等级分析，3：按主要诊断分析，4：按年龄分析
	
	private InputStream excelFile;  
	private String downloadFileName;
	private String sumTitle;
	private String excel_main_title;
	
	
	//初始化数据
	static {
//		if(allPatientList == null){
//			allPatientList = new HashMap<String, List<Patient>>();
//			String[] names = new String[]{"汤小平","谢应时","蒋景刚","陶兰芝","张翔",
//					"桂庆舟","王元娃","庾二平","仁元英","王能忠",
//					"李志龙","吕阿华","陶德堂","纪雨泽","刘先本",
//					"高秋香","马敬武","居阳春","李志龙","黄德英"};
//			String[] bedNos = new String[]{"607","626","601","697","625",
//					"1142","1128","1102","1147","1117",
//					"756","07A3","07A2","726","709",
//					"1234","1203","1297","1295","1299"};
//			
//			List<Patient> list = new ArrayList<Patient>();
//			int j = 1;
//			//0-4骨科  5-9呼吸科  10-14胸心外科  15-19心血管内科一病区  
//			for(int i = 0; i < names.length;i++){
//				Patient p = new Patient();
//				p.setName(names[i]);
//				p.setCurBed(bedNos[i]);//床号
//				list.add(p);
//				if((i+1) % 5 == 0){
//					allPatientList.put(j+"", list);
//					list = new ArrayList<Patient>();
//					j++;
//				}
//			}
//		}	
		
		if(allPatientBehavior == null){
			allPatientBehavior = new HashMap<String, String>();
			List<Map<String, String>> executeType = Constant.getExecuteType();
			int i=0;
			for (Map<String, String> map : executeType) {
				i++;
				allPatientBehavior.put(i+"", map.get("name"));
			}
		}
		
		if(allDepartments == null){
			allDepartments = new HashMap<String, String>();
			allDepartments.put("1", "骨一(一院)");
			allDepartments.put("2", "呼吸科(一院)");
			allDepartments.put("3", "胸心外科(一院)");
			allDepartments.put("4", "心血管内科一病区(一院)");
		}
		
		if(allXAxisNames == null){
			allXAxisNames = new HashMap<String, String>();
			allXAxisNames.put("1", "特级,一级,二级,三级,四级");//护理等级
			allXAxisNames.put("2", "子宫平滑肌瘤,异位妊娠,子宫颈恶性肿瘤,子宫内膜恶性肿瘤");//主要诊断
			allXAxisNames.put("3", "10,20,30,40,50,60,70,80,90,100");//年龄
		}
		if(allXAxisNamesValue == null){
			allXAxisNamesValue = new HashMap<String, String>();
			allXAxisNamesValue.put("1", "0,1,2,3,4");//护理等级
			allXAxisNamesValue.put("2", "子宫平滑肌瘤,异位妊娠,子宫颈恶性肿瘤,子宫内膜恶性肿瘤");//主要诊断
			allXAxisNamesValue.put("3", "10,20,30,40,50,60,70,80,90,100");//年龄
		}
	}

	/***
	 * {
	 * 	condition:{
	 * 			   	patientBehavior://行为
	 * 				yAxis://维度
	 * 				beginStatDate://开始时间
	 * 				endStatDate://结束时间
	 * 				patientArea://病区
	 * 			  },
	 * 	result:{
	 * 			
	 * 			}
	 * }
	*/
	@Action(value="patientSingleMain")
	public String execute() throws Exception {
		Map<String,Object> parm=new HashMap<String, Object>();
		if(patientBehavior == null){
			Set<String> tmp = allPatientBehavior.keySet();
			patientBehavior = tmp.toArray(new String[tmp.size()]);
		}
		String _patientBehaviorValue="";
		patientBehaviorValue=new String [patientBehavior.length];
		for (int i=0;i<patientBehavior.length;i++) {
			String pB =patientBehavior[i];
			patientBehaviorValue[i]=allPatientBehavior.get(pB);
			_patientBehaviorValue+="'"+allPatientBehavior.get(pB)+"',";
		}
		if(Util.isNULL(beginStatDate))
		{
			Date d=new Date();
			d.setMonth(d.getMonth()-1);
			beginStatDate=Util.date2Str(d, "yyyy-MM-dd");
		}
		if(Util.isNULL(endStatDate))
		{
			endStatDate=Util.date2Str(new Date(), "yyyy-MM-dd");
		}
		_patientBehaviorValue=_patientBehaviorValue.replaceAll(",$", "");
		String conditions="";
		if(!Util.isNULL(beginStatDate))
		{
			conditions+="and executeStartTime>"+Util.str2Date(beginStatDate,"yyyy-MM-dd").getTime()+" ";
		}
		if(!Util.isNULL(endStatDate))
		{
			conditions+="and  executeStartTime<"+Util.str2Date(endStatDate,"yyyy-MM-dd").getTime()+" ";
		}
		if(!Util.isNULL(_patientBehaviorValue))
		{
			conditions+="and execute in ("+_patientBehaviorValue+") ";
		}
		parm.put("conditions",conditions);
		find_view = mH_NurseExecuteRecordService.find_view(parm, 0, 0);
//		System.out.println(GsonUtil.objectToJson(find_view));
		//查找病人
//		String defaultPatients = "";
//		System.out.println(beginStatDate);
		dealSum=new DealSum() {
			@Override
			public Object execute(Object old,Object news,Object o) {
				if(old==null)
					return news;
				return Long.parseLong(old.toString().trim())+Long.parseLong(news.toString().trim());
			}

			@Override
			public Object changeNull(Object o) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		if(yAxisName == null){
			yAxisName = new String[]{"1"};
		}
		if(yAxisName[0].equals("1"))
		{
			invokeField="ciShu";
			sumTitle="总计次数";
		}
		else if(yAxisName[0].equals("2"))
		{
			invokeField="avgHaoShi";
			sumTitle="平均耗时";
		}else if(yAxisName[0].equals("3"))
		{
			invokeField="executeStartTime";
			sumTitle="平均时间";
			dealSum=new DealSum() {
				@Override
				public Object execute(Object old,Object news,Object o) {
					if(old==null)
						return news;
					return (Long.parseLong(old.toString().trim())+Long.parseLong(news.toString().trim()))/2;
				}

				@Override
				public Object changeNull(Object o) {
					// TODO Auto-generated method stub
					return null;
				}
			};
		}
		chartData = new ArrayList<List<String>>();
		if(searchType == 1){
    		//第一行  病人名称
    		xAxisNames = Arrays.asList("所有病人"); 
    		xAxisNamesValue = Arrays.asList(""); 
			 title="病人";
			 fieldID="clinicNo";
			 fieldValue="patientName";
			 excel_main_title="所有病人";
		}
		else if(searchType == 2) 
		{
			xAxisNames = Arrays.asList(allXAxisNames.get("1").split(","));
			xAxisNamesValue = Arrays.asList(allXAxisNamesValue.get("1").split(","));
			title = "护理等级";
			fieldID = "careLevel";
			fieldValue="careLevel";
			 excel_main_title="护理等级";
		}
		else if(searchType == 3){
			xAxisNames = Arrays.asList(allXAxisNames.get("2").split(",")); 
			xAxisNamesValue = Arrays.asList(allXAxisNamesValue.get("2").split(","));
    		title = "主要诊断";
    		fieldID="diagnose1";
    		fieldValue="diagnose1";
    		 excel_main_title="按主要诊断";
    	}else if(searchType == 4){
    		xAxisNames = Arrays.asList(allXAxisNames.get("3").split(",")); 
    		xAxisNamesValue = Arrays.asList(allXAxisNamesValue.get("3").split(","));
    		title = "年龄";
    		fieldID="ageInterval";
    		fieldValue="ageInterval";
    		 excel_main_title="按年龄";
    	}
		{
			String[] execute = new String[]{fieldID,"execute"};
			find_view_map=changeFind_view4Map(find_view.getResult(),execute);
			System.out.println(find_view_map);
			generateData();
			tableData = composeDate();
		}
		return SUCCESS;
	}
//	
//	/**
//	 * 根据病区联动出病人的方法,json方法
//	 * @return
//	 */
//	@Action(value="listPatients", results={@Result(name="listPatients", type="json")})
//	public String listPatients(){
//		List<Patient> patients = allPatientList.get(patientArea+"");
//		patientList = new HashMap<String, String>();
//		for(Patient p : patients){
//			patientList.put(p.getCurBed(), p.getName());
//		}
//		return "listPatients";
//	}
	


	private Map<String, MH_NurseExecuteRecord_view> changeFind_view4Map(
			List<MH_NurseExecuteRecord_view> find_view,
			String[] execute) {
		
		List<MH_NurseExecuteRecord_view> list=new ArrayList<MH_NurseExecuteRecord_view>();
		Map<String, MH_NurseExecuteRecord_view> find_view_map =new HashMap<String, MH_NurseExecuteRecord_view>();
		if(find_view!=null&&find_view.size()>0)
		for (MH_NurseExecuteRecord_view f : find_view) {
			MH_NurseExecuteRecord_view r = f.clone();
			String key="";
			for (String s : execute) {
				Object invokeGet = ClassUtil.invokeGet(r, s);
				String i="";
				if(Util.isNULL(invokeGet))
					i="null";
				else
					i=invokeGet.toString().trim();
				key+=i+"_";
			}
			key=key.replaceAll("_$", "");
			MH_NurseExecuteRecord_view mh=find_view_map.get(key);
			r.setAvgHaoShi(r.getAvgHaoShi()/60000);
			r.setMinHaoShi(r.getMinHaoShi()/60000);
			r.setMaxHaoShi(r.getMaxHaoShi()/60000);
			r.setHaoShi(r.getHaoShi()/60000);
			Date start_date=new Date(r.getExecuteStartTime());
			r.setExecuteStartTime(new Date(70,0,1,start_date.getHours(),start_date.getMinutes(),start_date.getSeconds()).getTime());
			if(mh==null)
			{
				r.setCiShu(1);
				find_view_map.put(key, r);
				list.add(r);
			}
			else
			{
				MH_NurseExecuteRecord_view record_view = find_view_map.get(key);
				record_view.setCiShu(record_view.getCiShu()<=0?1:record_view.getCiShu()+1);
				record_view.setHaoShi(record_view.getHaoShi()+r.getHaoShi());
				record_view.setAvgHaoShi((record_view.getAvgHaoShi()+r.getAvgHaoShi())/2);
				record_view.setMinHaoShi(record_view.getMinHaoShi()<r.getMinHaoShi()?record_view.getMinHaoShi():r.getMinHaoShi());
				record_view.setMaxHaoShi(record_view.getMaxHaoShi()>r.getMaxHaoShi()?record_view.getMaxHaoShi():r.getMaxHaoShi());
				Date start_old=new Date(record_view.getExecuteStartTime());
				Date _start=new Date(r.getExecuteStartTime());
				//找出每天最早时间
				
				if(start_old.getHours()>_start.getHours()||start_old.getMinutes()>_start.getMinutes()||start_old.getSeconds()>_start.getSeconds())
				{
					record_view.setExecuteStartTime( r.getExecuteStartTime());
				}
			}
			
		}
		return find_view_map;
	}

	/**
	 * 导出数据
	 * @return
	 * @throws Exception
	 */
	@Action(value = "singleNursingExportExcel", results = { @Result(name = "success", type = "stream", params = {  
            "contentType", "application/vnd.ms-excel", "inputName",  
            "excelFile", "contentDisposition",  
            "attachment;filename=${downloadFileName}.xls", "bufferSize", "1024" }) })
	public String exportExcel() throws Exception{
		//=======================导出excel======================
		execute();
		HSSFWorkbook workbook = ExportExcelUtil.exportExcel(tableData, excel_main_title);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		workbook.write(output);
		byte[] ba = output.toByteArray();
		excelFile = new ByteArrayInputStream(ba);
		output.flush();
		output.close();
		return "success";
	} 
	
	private void generateData() {
		chartData.add(xAxisNames);
		List<String> lists=null;
		Map<String,Object> map4Sum=new HashMap<String, Object>();
		List<String> list4Sum=new ArrayList<String>();
		list4Sum.add(sumTitle);
		
		Map<String, MH_NurseExecuteRecord_view> _find_view_map =null;
		String add="";
		if(searchType==1)
		{
			String[] execute = new String[]{"execute"};
			_find_view_map=changeFind_view4Map(find_view.getResult(),execute);
			System.out.println(_find_view_map);
		}
		else
		{
			_find_view_map=find_view_map;
			add="_";
		}
		for (String be : patientBehaviorValue) {
			lists=new ArrayList<String>();
			lists.add(be);
			//				List<Object> l=new ArrayList<Object>();
			for(String x:xAxisNamesValue)
			{
				MH_NurseExecuteRecord_view record_view = _find_view_map.get(x.trim()+add+be.trim());
				if(record_view!=null)
				{

					Object ss = ClassUtil.invokeGet(record_view, invokeField);
					lists.add(ss.toString());
					Object o = map4Sum.get(x.trim());
					if(o==null)
						map4Sum.put(x.trim(), dealSum.execute(null,ss,record_view));
					else
					{
						map4Sum.put(x.trim(), dealSum.execute(o,ss,record_view));
					}
					//						l.add(ss);
				}
				else
				{
					lists.add("0");
					//						l.add(0);
				}
			}
			chartData.add(lists);
		}
		for(String x:xAxisNamesValue)
		{
			Object o = map4Sum.get(x.trim());
			if(o==null)
				list4Sum.add("0");
			else
			{
				list4Sum.add(o.toString());
			}
		}
		chartData.add(list4Sum);
		System.out.println("chartData:\n");
		System.out.println(chartData);
	}
	/**
	 * 根据现有的选项随机生成值
	 */
	private List<List<String>> generateData(int minVal, int maxVal, int columns, String[] head){
		List<List<String>> result = null;
		if(columns != 0){
			result = new ArrayList<List<String>>();
			DecimalFormat df = new DecimalFormat("#.#");
			double[] total = new double[columns];
			List<String> headList = new ArrayList<String>();
			if(head != null){
				for(String tmp : head){
					headList.add(allPatientBehavior.get(tmp));
				}
			}
			headList.add("总计");
			for(String r : headList){
				List<String> line = new ArrayList<String>();
				line.add(r);
				if(r.equals("总计") && headList.size() > 1){
					for(double tmp : total){
						line.add(df.format(tmp));
					}
				}else{
					for(int i = 0; i < columns;i++){
						double a = Math.random() * (maxVal - minVal) + minVal;
						line.add((int)a + "");
						total[i] += a;
					}
				}
				result.add(line);
			}
		}
		return result;
	}
	
	/**
	 * 修改数据, 改成可以在table中显示的
	 * @param g_data
	 * @param title
	 * @return
	 */
	private List<List<String>> composeDate(){
		List<List<String>> data = new ArrayList<List<String>>();
		if(yAxisName[0].equals("1")){
    		//次数
    		List<String> up_line1 = new ArrayList<String>();
    		up_line1.add(title);
    		up_line1.add("护理行为");
    		up_line1.add("次数");
    		up_line1.add("备注");
    		data.add(up_line1);
    		
    		List<String> lineOther = null;
    		Set<String> keySet = find_view_map.keySet();
    		for (String key : keySet) {
    			lineOther = new ArrayList<String>();
    			lineOther.add(ClassUtil.invokeGet(find_view_map.get(key), fieldValue).toString());
				lineOther.add(find_view_map.get(key).getExecute());
				lineOther.add(find_view_map.get(key).getCiShu()+"");
				lineOther.add("");
				data.add(lineOther);
			}
    	}else if(yAxisName[0].equals("2")){
    		//耗时
    		//第一行
    		List<String> up_line1 = new ArrayList<String>();
    		up_line1.add(title);
    		up_line1.add("护理行为");
    		up_line1.add("最大耗时");
    		up_line1.add("平均耗时");
    		up_line1.add("最小耗时");
    		up_line1.add("备注");
    		data.add(up_line1);
    		
    		List<String> lineOther = null;
    		Set<String> keySet = find_view_map.keySet();
    		for (String key : keySet) {
    			lineOther = new ArrayList<String>();
    			lineOther.add(ClassUtil.invokeGet(find_view_map.get(key), fieldValue).toString());
    			lineOther.add(find_view_map.get(key).getExecute());
				lineOther.add(find_view_map.get(key).getMaxHaoShi()+"");
				lineOther.add(find_view_map.get(key).getAvgHaoShi()+"");
				lineOther.add(find_view_map.get(key).getMinHaoShi()+"");
				lineOther.add(find_view_map.get(key).getHaoShi()+"");
				data.add(lineOther);
			}
    	}else if(yAxisName[0].equals("3")){
    		//开始时间
    		List<String> up_line1 = new ArrayList<String>();
    		up_line1.add(title);
    		up_line1.add("护理行为");
    		up_line1.add("开始时间");
    		up_line1.add("备注");
    		data.add(up_line1);
    		
    		List<String> lineOther = null;
    		
    		Set<String> keySet = find_view_map.keySet();
    		for (String key : keySet) {
    			lineOther = new ArrayList<String>();
    			lineOther.add(ClassUtil.invokeGet(find_view_map.get(key), fieldValue).toString());
				lineOther.add(find_view_map.get(key).getExecute());
				lineOther.add(DateUtil.date2Str(new Date(find_view_map.get(key).getExecuteStartTime()), "HH:mm:ss"));
				lineOther.add("");
				data.add(lineOther);
			}
    		
    	}
		return data;
	}
	

	public int getPatientArea() {
		return patientArea;
	}

	public void setPatientArea(int patientArea) {
		this.patientArea = patientArea;
	}

//	public Map<String, String> getPatientList() {
//		return patientList;
//	}
//
//	public  void setPatientList(Map<String, String> patientList) {
//		this.patientList = patientList;
//	}

	public String getBeginStatDate() {
		return beginStatDate;
	}

	public void setBeginStatDate(String beginStatDate) {
		this.beginStatDate = beginStatDate;
	}

	public String getEndStatDate() {
		return endStatDate;
	}

	public void setEndStatDate(String endStatDate) {
		this.endStatDate = endStatDate;
	}

	public String[] getPatientBehavior() {
		return patientBehavior;
	}

	public void setPatientBehavior(String[] patientBehavior) {
		this.patientBehavior = patientBehavior;
	}

	public String[] getYAxisName() {
		return yAxisName;
	}

	public void setYAxisName(String[] yAxisName) {
		this.yAxisName = yAxisName;
	}

	public List<List<String>> getTableData() {
		return tableData;
	}

	public void setTableData(List<List<String>> tableData) {
		this.tableData = tableData;
	}

	public  Map<String, String> getAllPatientBehavior() {
		return allPatientBehavior;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public List<List<String>> getChartData() {
		return chartData;
	}

	public void setChartData(List<List<String>> chartData) {
		this.chartData = chartData;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getDownloadFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd"); 
        String downloadFileName = "单向护理强度分析-" + (sf.format(new Date()).toString());  
        try {  
            downloadFileName = new String(downloadFileName.getBytes(),  
                    "ISO8859-1");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	
}
interface DealSum
{
	Object execute(Object old, Object news, Object record_view);
	Object changeNull(Object o);
}
