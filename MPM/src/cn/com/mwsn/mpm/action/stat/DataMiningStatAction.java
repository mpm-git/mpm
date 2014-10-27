package cn.com.mwsn.mpm.action.stat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 数据挖掘数据分析
 * @author Administrator
 *
 */
@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/dataMiningStat.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class DataMiningStatAction extends ActionSupport {
	
	private static final long serialVersionUID = 5377050751703222726L;
	
	//初始化数据
	private static String[] diseases;//疾病
	private static Map<Integer, Map<String,String>> allAlgorithms;//所有算法常量
	
	private Map<String,String> algorithms;//当前算法
	
	//查询条件
	private List<String> patientCondition;//选中的病人的分析项
	private String algorithm;//选中的算法
	private String suport = "70";//支持度
	private String confidence = "30" ;//置信度
	
	//数据
	private List<List<String>> tabledata;//表格的数据
	private Map<String, List<List<String>>> chartdata;//图表的数据
	private Map<String, List<List<String>>> chartdata2;//图表2的数据（柱状图）
	
	private List<List<String>> relateChartData = new ArrayList<List<String>>();//关联图标的得病率
	
	//查询类型
	private int searchType = 1; //1:护理时间与工作时间分析   2:任职时间与护理时间分析   3:护士职称与护理时间分析
	private int lastSearchType = 1;
	
	static{
		diseases = new String[]{"心脏病","糖尿病","高血脂","高血压"};
		
		allAlgorithms = new HashMap<Integer, Map<String,String>>();
		Map<String,String> tmp = new LinkedHashMap<String, String>();
		tmp.put("1", "Apriori");
		tmp.put("2", "FP-Tree");
		allAlgorithms.put(1, tmp);
		tmp = new LinkedHashMap<String, String>();
		tmp.put("1", "BIRCH");
		tmp.put("2", "DBSCAN");
		tmp.put("3", "CURE");
		//tmp.put("4", "K-poto");
		//tmp.put("5", "CLARANS");
		//tmp.put("6", "CUQUE");
		allAlgorithms.put(2, tmp);
	}

	@Action(value="dataMiningStat")
	public String execute() throws Exception {
		//默认是一直选中的
		patientCondition = new ArrayList<String>();
		patientCondition.add("1");
		patientCondition.add("2");
		if(algorithm == null){
			algorithm = "1";
		}
		if(lastSearchType != searchType){
			algorithm = "1";
			//suport = "";
			//confidence = "";
		}
		algorithms = allAlgorithms.get(searchType);
		generateData();
		generateData2();
		if(searchType == 1){
			generateRelateChartData();
		}
		return SUCCESS;
	}

	
	/**
	 * 根据现有的选项随机生成数据
	 */
	private void generateData(){
		//聚合分析
		tabledata = new ArrayList<List<String>>();
		chartdata = new HashMap<String, List<List<String>>>();
		int size = 5;//数据大小
		DecimalFormat df = new DecimalFormat("#.0");
		for(int j = 0; j < diseases.length; j++){
			String d = diseases[j];
			List<List<String>> chart_tmp = new ArrayList<List<String>>();
			if(searchType == 1){
				//关联挖掘
				size = generateRandom(5, 10);//数据大小
			}else{
				//聚合分析
				size = generateRandom(10, 30);//数据大小
			}
			//年龄大的密集一点
			for(int i = 0;i < size; i++){
				List<String> table_tmp = new ArrayList<String>();
				table_tmp.add(d);//疾病
				table_tmp.add(generateRandom(55, 80) + "");//年龄
				double percent = Math.random() * 50 + 40;
				table_tmp.add(df.format(percent) + "");//百分比
				if(searchType == 1){
					//关联挖掘
					table_tmp.add(generateRandom(30, 100) + "");//TODO 随便写个值，代表大小
				}
				tabledata.add(table_tmp);
				chart_tmp.add(table_tmp);
			}
			//年龄小的稀疏一点
			for(int i = 0;i < 6; i++){
				List<String> table_tmp = new ArrayList<String>();
				table_tmp.add(d);//疾病
				table_tmp.add(generateRandom(20, 54) + "");//年龄
				double percent = Math.random() * 60;
				table_tmp.add(df.format(percent) + "");//百分比
				if(searchType == 1){
					//关联挖掘
					table_tmp.add(generateRandom(10, 50) + "");//TODO 随便写个值，代表大小
				}
				tabledata.add(table_tmp);
				chart_tmp.add(table_tmp);
			}
			chartdata.put(d, chart_tmp);
		}
	}
	
	
	/**
	 * 根据现有的选项随机生成数据
	 */
	private void generateData2(){
		//聚合分析
		tabledata = new ArrayList<List<String>>();
		chartdata2 = new HashMap<String, List<List<String>>>();
		int size = 8;//数据大小
		DecimalFormat df = new DecimalFormat("#.0");
		for(int j = 0; j < diseases.length; j++){
			String d = diseases[j];
			List<List<String>> chart_tmp = new ArrayList<List<String>>();

			
			for(int i = 2;i <= size; i++){
				List<String> table_tmp = new ArrayList<String>();
				table_tmp.add(d);//疾病
				table_tmp.add(i*10 + "");//年龄
				double percent = Math.random() * 50 + 40;
				table_tmp.add(df.format(percent) + "");//百分比
				if(searchType == 1){
					//关联挖掘
					table_tmp.add(generateRandom(30, 100) + "");//TODO 随便写个值，代表大小
				}
				tabledata.add(table_tmp);
				chart_tmp.add(table_tmp);
			}
	
			chartdata2.put(d, chart_tmp);
		}
	}
	
	 /**
     * 获取某个范围内的随机数
     */
    public static int generateRandom(int min, int max) {
        int temp = 0;
        try {
            temp = new Random().nextInt(max - min);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp + min;
    }

    /**
     * 关联挖掘图表数据2
     */
    private void generateRelateChartData(){
    	//每个疾病的数据，之前的数值小些，之后的数值大些
    	DecimalFormat df = new DecimalFormat("#.0");
    	for(int i = 0; i< diseases.length;i++){
    		//疾病
    		List<String> tmp = new ArrayList<String>();
    		tmp.add(diseases[i]);
    		for(int j = 0; j < 7;j++){
    			//年龄段 20-80
    			if(j <= 3){
    				//年龄小，数值小
    				double percent = Math.random() * 20;
    				tmp.add(df.format(percent) + "");
    			}else{
    				//年龄大，数值大
    				double percent = Math.random() * 40 + 40;
    				tmp.add(df.format(percent) + "");
    			}
    		}
    		relateChartData.add(tmp);
    	}
    	//平均得病率
    	List<String> tmp = new ArrayList<String>();
    	tmp.add("平均得病率");
    	for(int j = 0; j < 7;j++){
    		if(j <= 3){
				//年龄小，数值小
				tmp.add(generateRandom(0, 20) + "");
			}else{
				//年龄大，数值大
				tmp.add(generateRandom(40, 80) + "");
			}
    	}
    	relateChartData.add(tmp);
    }

	public String getSuport() {
		return suport;
	}


	public void setSuport(String suport) {
		this.suport = suport;
	}


	public String getConfidence() {
		return confidence;
	}


	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}


	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public String[] getDiseases() {
		return diseases;
	}


	public List<List<String>> getTabledata() {
		return tabledata;
	}


	public void setTabledata(List<List<String>> tabledata) {
		this.tabledata = tabledata;
	}


	public Map<String, List<List<String>>> getChartdata() {
		return chartdata;
	}


	public void setChartdata(Map<String, List<List<String>>> chartdata) {
		this.chartdata = chartdata;
	}


	public List<String> getPatientCondition() {
		return patientCondition;
	}


	public void setPatientCondition(List<String> patientCondition) {
		this.patientCondition = patientCondition;
	}


	public Map<String, String> getAlgorithms() {
		return algorithms;
	}


	public void setAlgorithms(Map<String, String> algorithms) {
		this.algorithms = algorithms;
	}


	public String getAlgorithm() {
		return algorithm;
	}


	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}


	public int getLastSearchType() {
		return lastSearchType;
	}


	public void setLastSearchType(int lastSearchType) {
		this.lastSearchType = lastSearchType;
	}


	public Map<String, List<List<String>>> getChartdata2() {
		return chartdata2;
	}


	public void setChartdata2(Map<String, List<List<String>>> chartdata2) {
		this.chartdata2 = chartdata2;
	}


	public List<List<String>> getRelateChartData() {
		return relateChartData;
	}


	public void setRelateChartData(List<List<String>> relateChartData) {
		this.relateChartData = relateChartData;
	}
	
	
	
}
