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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import cn.com.mwsn.frame.util.ExportExcelUtil;
import cn.com.mwsn.mpm.entity.Nurse;

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
public class NursingStrengthStatAction extends ActionSupport {
	
	private static final long serialVersionUID = 5377050751703222726L;
	
	//作为展示基础数据用
	private static Map<String,List<Nurse>> allNursesList;//所有护士列表 key为病区
	private static Map<String,String> allPatientBehavior;//所有护理行为
	private static Map<String,String> allDepartments;;//所有病区
	private static Map<String,String> allXAxisNames;//所有x轴护理维度设定
	//查询条件
	private int patientArea = 1;//病区
	private Map<String,String> nurseList;//病区对应的护士列表
	private String nurses;//选中护士
	private String beginStatDate;
	private String endStatDate;
	private String[] patientBehavior;//选中的护理行为
	
	//数据
	private List<List<String>> tableData;//表格的数据
	private List<List<String>> chartData;//图形的数据
	
	//查询类型
	private int searchType = 1; //1:护理时间与工作时间分析   2:任职时间与护理时间分析   3:护士职称与护理时间分析
	
	private InputStream excelFile;  
	private String downloadFileName;
	
	//初始化数据
	static {
		if(allNursesList == null){
			allNursesList = new HashMap<String, List<Nurse>>();
			String[] names = new String[]{"张晨曦","鲁川","汪美菊","黄  静","史立梅",
					"孙 虹 ","费春玲","李桂芳","彭丽丽","邰靖婧",
					"金爱兰","李小梅","刘 宏","鲁和俊","王 梅",
					"王顺凤","王 薇","徐礼霞","杨 旭","俞静静"};
			String[] bedNos = new String[]{"020103","020136","020478","020114","020106",
					"020182","020178","020175","020760","020810",
					"020440","020119","020118","020122","020121",
					"020199","020563","010914","020196","020197"};
			
			List<Nurse> list = new ArrayList<Nurse>();
			int j = 1;
			//0-4骨科  5-9呼吸科  10-14胸心外科  15-19心血管内科一病区  
			for(int i = 0; i < names.length;i++){
				Nurse p = new Nurse();
				p.setName(names[i]);
				p.setNurseNo(bedNos[i]);//工号
				list.add(p);
				if((i+1) % 5 == 0){
					allNursesList.put(j+"", list);
					list = new ArrayList<Nurse>();
					j++;
				}
			}
		}	
		
		if(allPatientBehavior == null){
			allPatientBehavior = new HashMap<String, String>();
			allPatientBehavior.put("1", "输液");
			allPatientBehavior.put("2", "测量体温");
			allPatientBehavior.put("3", "巡床");
			allPatientBehavior.put("4", "填写护理单");
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
			allXAxisNames.put("1", "1-2年,3-5年,5-7年,7年以上");//任职时间x轴
			allXAxisNames.put("2", "护士,护士长");//职称的x轴
		}
	}

	@Action(value="nursingStrengthStat")
	public String execute() throws Exception {
		//查找护士
		String defaultNurses = "";
		List<Nurse> ps = allNursesList.get(patientArea+"");
		nurseList = new HashMap<String, String>();
		for(Nurse p : ps){
			nurseList.put(p.getNurseNo(), p.getName());
			defaultNurses += p.getName() + ", ";
		}
		
		//初始进入页面时默认一些选择条件
		if(nurses == null  || "".equals(nurses)){
			nurses = defaultNurses.substring(0, defaultNurses.length() - 2);
		}
		//TODO 页面无此选项
		if(patientBehavior == null){
			Set<String> tmp = allPatientBehavior.keySet();
			patientBehavior = tmp.toArray(new String[tmp.size()]);
		}
		
		//根据查询条件更新图示数据
		if(!(nurses == null || "".equals(nurses))){
			List<String> line1 = new ArrayList<String>();
			List<List<String>> result = null;
			//1、护理工作时间与总服务时间
			if(searchType == 1){
				chartData = new ArrayList<List<String>>();
				//第一行  护士名称
				line1 = Arrays.asList(nurses.split(", ")); 
				chartData.add(line1);
				//下面是各个护理行为项的值和总计的值
				result = generateData(40, 200, line1.size(), patientBehavior);
				if(result != null){
					for(List<String> a : result){
						chartData.add(a);
					}
				}
				tableData = composeDate(chartData, "护士");
			}else if(searchType > 1){
				chartData = new ArrayList<List<String>>();
				line1 = Arrays.asList(allXAxisNames.get((searchType-1) + "").split(",")); 
				chartData.add(line1);
				result = generateData(80, 400, allXAxisNames.get((searchType-1) + "").split(",").length, patientBehavior);
				if(result != null){
					for(List<String> a : result){
						chartData.add(a);
					}
				}
				String title = "任职时间";
	        	if(searchType == 3){
	        		title = "护士职称";
	        	}
	        	tableData = composeDate(chartData, title);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 根据病区联动出病人的方法,json方法
	 * @return
	 */
	@Action(value="listNurses", results={@Result(name="listNurses", type="json")})
	public String listPatients(){
		List<Nurse> patients = allNursesList.get(patientArea+"");
		nurseList = new HashMap<String, String>();
		for(Nurse p : patients){
			nurseList.put(p.getNurseNo(), p.getName());
		}
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
    	String main_title = "护理时间与工作时间分析";
		if(searchType == 2){
			main_title += "任职时间与护理时间分析";
		}else if(searchType == 3){
			main_title += "护士职称与护理时间分析";
    	}
		//TODO 可与查询共用=============造数据=================================
    	//所有病人综合分析
    	//初始化数据
		if(!(nurses == null || "".equals(nurses))){
			List<String> line1 = new ArrayList<String>();
			List<List<String>> result = null;
			//1、护理工作时间与总服务时间
			//TODO 页面无此选项
			if(patientBehavior == null){
				Set<String> tmp = allPatientBehavior.keySet();
				patientBehavior = tmp.toArray(new String[tmp.size()]);
			}
			if(searchType == 1){
				chartData = new ArrayList<List<String>>();
				//第一行  护士名称
				line1 = Arrays.asList(nurses.split(", ")); 
				chartData.add(line1);
				//下面是各个护理行为项的值和总计的值
				result = generateData(40, 200, line1.size(), patientBehavior);
				if(result != null){
					for(List<String> a : result){
						chartData.add(a);
					}
				}
				tableData = composeDate(chartData, "工作时间");
			}else if(searchType > 1){
				chartData = new ArrayList<List<String>>();
				line1 = Arrays.asList(allXAxisNames.get((searchType-1) + "").split(",")); 
				chartData.add(line1);
				result = generateData(80, 400, allXAxisNames.get((searchType-1) + "").split(",").length, patientBehavior);
				if(result != null){
					for(List<String> a : result){
						chartData.add(a);
					}
				}
				String title = "任职时间";
	        	if(searchType == 3){
	        		title = "护士职称";
	        	}
				tableData = composeDate(chartData, title);
			}
		}
		//=======================导出excel======================
		HSSFWorkbook workbook = ExportExcelUtil.exportExcel(tableData, main_title);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		workbook.write(output);
		byte[] ba = output.toByteArray();
		excelFile = new ByteArrayInputStream(ba);
		output.flush();
		output.close();
		return "success";
	} 
	
	
	/**
	 * 根据现有的选项随机生成值
	 */
	private List<List<String>> generateData(int minVal, int maxVal, int columns, String[] head){
		List<List<String>> result = null;
		if(columns != 0){
			result = new ArrayList<List<String>>();
			DecimalFormat df = new DecimalFormat("#.0");
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
						line.add(df.format(a));
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
	private List<List<String>> composeDate(List<List<String>> g_data,  String title){
		Random random = new Random();
		List<List<String>> data = new ArrayList<List<String>>();
		//耗时
		List<String> up_line1 = new ArrayList<String>();
		up_line1.add(title);
		up_line1.add("护理行为");
		up_line1.add("最大耗时");
		up_line1.add("平均耗时");
		up_line1.add("最小耗时");
		up_line1.add("备注");
		data.add(up_line1);
		
		List<String> lineOther = null;
		List<String> line_pati = g_data.get(0);
		
		for(int j=0; j<line_pati.size(); j++){
			for(int i=1; i<g_data.size()-1; i++){
				List<String> line_other = g_data.get(i);
				lineOther = new ArrayList<String>();
				lineOther.add(line_pati.get(j));
				lineOther.add(line_other.get(0));
				lineOther.add((int)Float.parseFloat(line_other.get(1+j)) + random.nextInt(6) + "");
				lineOther.add(line_other.get(1+j));
				lineOther.add((int)Float.parseFloat(line_other.get(1+j)) - random.nextInt(2) + "");
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

	public Map<String, String> getNurseList() {
		return nurseList;
	}

	public void setNurseList(Map<String, String> nurseList) {
		this.nurseList = nurseList;
	}

	public String getNurses() {
		return nurses;
	}

	public void setNurses(String nurses) {
		this.nurses = nurses;
	}

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

	public List<List<String>> getTableData() {
		return tableData;
	}

	public void setTableData(List<List<String>> tableData) {
		this.tableData = tableData;
	}

	public Map<String, String> getAllPatientBehavior() {
		return allPatientBehavior;
	}

	public void setAllPatientBehavior(Map<String, String> allPatientBehavior) {
		NursingStrengthStatAction.allPatientBehavior = allPatientBehavior;
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
		//TODO 可与导出共用
		String main_title = "护理时间与工作时间分析";
		if(searchType == 2){
			main_title = "任职时间与护理时间分析";
		}else if(searchType == 3){
			main_title = "护士职称与护理时间分析";
    	}
        String downloadFileName = main_title + "-" + (sf.format(new Date()).toString());  
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
