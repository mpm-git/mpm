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
import cn.com.mwsn.mpm.entity.Patient;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 病人护理行为分析
 * @author Administrator
 *
 */
@ParentPackage(value="json-default")
@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/patientSingleNurseStat.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class CopyOfPatientSingleNursingStatAction extends ActionSupport {
	
	private static final long serialVersionUID = 5377050751703222726L;
	
	//作为展示基础数据用
	private static Map<String,List<Patient>> allPatientList;//所有病人列表 key为病区
	private static Map<String,String> allPatientBehavior;//所有护理行为
	private static Map<String,String> allDepartments;;//所有病区
	private static Map<String,String> allXAxisNames;//所有x轴护理维度设定
	//查询条件
	private int patientArea = 1;//病区
	private Map<String,String> patientList;//病区对应的病人列表
	private String patients;//选中病人
	private String beginStatDate;
	private String endStatDate;
	private String[] patientBehavior;//选中的护理行为
	private String[] yAxisName;//护理维度y轴显示
	
	//数据
	private List<List<String>> tableData;//表格的数据
	private List<List<String>> chartData;//图形的数据
	
	//查询类型
	private int searchType = 1; //1:综合分析，2：按护理等级分析，3：按主要诊断分析，4：按年龄分析
	
	private InputStream excelFile;  
	private String downloadFileName;
	
	
	//初始化数据
	static {
		if(allPatientList == null){
			allPatientList = new HashMap<String, List<Patient>>();
			String[] names = new String[]{"汤小平","谢应时","蒋景刚","陶兰芝","张翔",
					"桂庆舟","王元娃","庾二平","仁元英","王能忠",
					"李志龙","吕阿华","陶德堂","纪雨泽","刘先本",
					"高秋香","马敬武","居阳春","李志龙","黄德英"};
			String[] bedNos = new String[]{"607","626","601","697","625",
					"1142","1128","1102","1147","1117",
					"756","07A3","07A2","726","709",
					"1234","1203","1297","1295","1299"};
			
			List<Patient> list = new ArrayList<Patient>();
			int j = 1;
			//0-4骨科  5-9呼吸科  10-14胸心外科  15-19心血管内科一病区  
			for(int i = 0; i < names.length;i++){
				Patient p = new Patient();
				p.setName(names[i]);
				p.setCurBed(bedNos[i]);//床号
				list.add(p);
				if((i+1) % 5 == 0){
					allPatientList.put(j+"", list);
					list = new ArrayList<Patient>();
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
			allXAxisNames.put("1", "特级,一级,二级,三级,四级");//护理等级
			allXAxisNames.put("2", "基础疾病,糖尿病,心脏病");//主要诊断
			allXAxisNames.put("3", "10,20,30,40,50,60,70,80,90,100");//年龄
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
	@Action(value="patientSingleMain111")
	public String execute() throws Exception {
		//查找病人
		String defaultPatients = "";
		List<Patient> ps = allPatientList.get(patientArea+"");
		patientList = new HashMap<String, String>();
		for(Patient p : ps){
			patientList.put(p.getCurBed(), p.getName());
			defaultPatients += p.getName() + ", ";
		}
		
		//初始进入页面时默认一些选择条件
		if(patients == null  || "".equals(patients)){
			patients = defaultPatients.substring(0, defaultPatients.length() - 2);
		}
		if(patientBehavior == null){
			Set<String> tmp = allPatientBehavior.keySet();
			patientBehavior = tmp.toArray(new String[tmp.size()]);
		}
		if(yAxisName == null){
			yAxisName = new String[]{"1"};
		}
		
        if(searchType == 1){
        	//所有病人综合分析
        	//初始化数据
        	if(!(patients == null || "".equals(patients))){
        		chartData = new ArrayList<List<String>>();
        		//第一行  病人名称
        		List<String> xAxisNames = Arrays.asList("所有病人"); 
        		chartData.add(xAxisNames);
        		//下面是各个护理行为项的值和总计的值
        		if(null != yAxisName){
        			List<List<String>> result = null;
        			//如果是  yAxisName=次数  的话
        			if(yAxisName[0].equals("1")){
        				result = generateData(5, 15, xAxisNames.size(), patientBehavior);
        			}else if(yAxisName[0].equals("2")){
        				//yAxisName=耗时 
        				result = generateData(2, 10, xAxisNames.size(), patientBehavior);
        			}else if(yAxisName[0].equals("3")){
        				//yAxisName=开始时间 
        				result = generateData(7, 22, xAxisNames.size(), patientBehavior);
        			}
        			if(result != null){
        				for(List<String> a : result){
        					chartData.add(a);
        				}
        			}
        		}
        		ArrayList chartData1 = new ArrayList<List<String>>();
        		//第一行  病人名称
        		List<String> xAxisNames1 = Arrays.asList(patients.split(", ")); 
        		chartData1.add(xAxisNames1);
        		//下面是各个护理行为项的值和总计的值
        		if(null != yAxisName){
        			List<List<String>> result1 = null;
        			//如果是  yAxisName=次数  的话
        			if(yAxisName[0].equals("1")){
        				result1 = generateData(5, 15, xAxisNames1.size(), patientBehavior);
        			}else if(yAxisName[0].equals("2")){
        				//yAxisName=耗时 
        				result1 = generateData(2, 10, xAxisNames1.size(), patientBehavior);
        			}else if(yAxisName[0].equals("3")){
        				//yAxisName=开始时间 
        				result1 = generateData(7, 22, xAxisNames1.size(), patientBehavior);
        			}
        			if(result1 != null){
        				for(List<String> a : result1){
        					chartData1.add(a);
        				}
        			}
        		}
        		//上面部分表格数据
        		tableData = composeDate(chartData1, "病人");
        		
        	}
        }else if(searchType > 1){
        	//2:按护理等级分析    3:按主要诊断分析    4:按年龄分析
        	//yAxisName = new String[]{"2"};
        	chartData = new ArrayList<List<String>>();
        	//yAxisName=耗时 
        	List<String> xAxisNames = Arrays.asList(allXAxisNames.get((searchType-1) + "").split(",")); 
        	chartData.add(xAxisNames);
        	List<List<String>> result = generateData(8, 50, allXAxisNames.get((searchType-1) + "").split(",").length, patientBehavior);
        	if(result != null){
        		for(List<String> a : result){
        			chartData.add(a);
        		}
        	}
        	String title = "护理等级";
        	if(searchType == 3){
        		title = "主要诊断";
        	}else if(searchType == 4){
        		title = "年龄";
        	}
        	tableData = composeDate(chartData, title);
        }
//        else if(searchType == 3){
//        	//按主要诊断分析
//        }else if(searchType == 4){
//        	//按年龄分析
//        }
		
		return SUCCESS;
	}
	
	/**
	 * 根据病区联动出病人的方法,json方法
	 * @return
	 */
	@Action(value="listPatients", results={@Result(name="listPatients", type="json")})
	public String listPatients(){
		List<Patient> patients = allPatientList.get(patientArea+"");
		patientList = new HashMap<String, String>();
		for(Patient p : patients){
			patientList.put(p.getCurBed(), p.getName());
		}
		return "listPatients";
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
    	String main_title = "单向护理强度分析";
		if(searchType == 2){
			main_title += "-按护理等级";
		}else if(searchType == 3){
			main_title += "-按主要诊断";
    	}else if(searchType == 4){
    		main_title += "-按年龄";
    	}
		//=============造数据=================================
		if(searchType == 1){
        	//所有病人综合分析
        	//初始化数据
        	if(!(patients == null || "".equals(patients))){
        		chartData = new ArrayList<List<String>>();
        		//第一行  病人名称
        		List<String> xAxisNames = Arrays.asList(patients.split(", ")); 
        		chartData.add(xAxisNames);
        		//下面是各个护理行为项的值和总计的值
        		if(null != yAxisName){
        			List<List<String>> result = null;
        			//如果是  yAxisName=次数  的话
        			if(yAxisName[0].equals("1")){
        				result = generateData(5, 15, xAxisNames.size(), patientBehavior);
        			}else if(yAxisName[0].equals("2")){
        				//yAxisName=耗时 
        				result = generateData(2, 10, xAxisNames.size(), patientBehavior);
        			}else if(yAxisName[0].equals("3")){
        				//yAxisName=开始时间 
        				result = generateData(7, 22, xAxisNames.size(), patientBehavior);
        			}
        			if(result != null){
        				for(List<String> a : result){
        					chartData.add(a);
        				}
        			}
        		}
        		//上面部分表格数据
        		tableData = composeDate(chartData, "病人");
        	}
        }else if(searchType > 1){
        	//2:按护理等级分析    3:按主要诊断分析    4:按年龄分析
        	//yAxisName = new String[]{"2"};
        	chartData = new ArrayList<List<String>>();
        	//yAxisName=耗时 
        	List<String> xAxisNames = Arrays.asList(allXAxisNames.get((searchType-1) + "").split(",")); 
        	chartData.add(xAxisNames);
        	List<List<String>> result = generateData(8, 50, allXAxisNames.get((searchType-1) + "").split(",").length, patientBehavior);
        	if(result != null){
        		for(List<String> a : result){
        			chartData.add(a);
        		}
        	}
        	String title = "护理等级";
        	if(searchType == 3){
        		title = "主要诊断";
        	}else if(searchType == 4){
        		title = "年龄";
        	}
        	tableData = composeDate(chartData, title);
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
	private List<List<String>> composeDate(List<List<String>> g_data,  String title){
		Random random = new Random();
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
    		List<String> line_pati = g_data.get(0);
    		for(int j=0; j<line_pati.size(); j++){
    			for(int i=1; i< g_data.size()-1; i++){
    				List<String> line_other = g_data.get(i);
    				lineOther = new ArrayList<String>();
    				lineOther.add(line_pati.get(j));
    				lineOther.add(line_other.get(0));
    				lineOther.add((int)Float.parseFloat(line_other.get(1+j)) + "");
    				lineOther.add("");
    				data.add(lineOther);
    			}
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
    	}else if(yAxisName[0].equals("3")){
    		//开始时间
    		List<String> up_line1 = new ArrayList<String>();
    		up_line1.add(title);
    		up_line1.add("护理行为");
    		up_line1.add("开始时间");
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
    				lineOther.add((int)Float.parseFloat(line_other.get(1+j)) + "");
    				lineOther.add("");
    				data.add(lineOther);
    			}
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

	public Map<String, String> getPatientList() {
		return patientList;
	}

	public  void setPatientList(Map<String, String> patientList) {
		this.patientList = patientList;
	}

	public String getPatients() {
		return patients;
	}

	public void setPatients(String patients) {
		this.patients = patients;
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
