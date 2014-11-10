package cn.com.mwsn.mpm.action.stat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.cz.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.service.QueryResult;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_view;
import cn.com.mwsn.mpm.entity.Nurse;
import cn.com.mwsn.mpm.entity.Patient;
import cn.com.mwsn.mpm.entity.Patient_view;
import cn.com.mwsn.mpm.service.MH_NurseExecuteRecordService;
import cn.com.mwsn.mpm.service.NurseService;
import cn.com.mwsn.mpm.service.PatientService;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 数据挖掘数据分析
 * @author Administrator
 *
 */
@Results({ @Result(name = "success", location = "/WEB-INF/pages/stat/dataMiningStat.jsp"),
	@Result(name = "error", location = "/error/error.jsp") ,
	@Result(name = "result", location = "/WEB-INF/pages/result4Json.jsp")})
public class DataMiningStatAction extends ActionSupport {

	private static final long serialVersionUID = 5377050751703222726L;
	private String patientArea = "B1";//病区
	@Autowired
	private NurseService nurseService;
	@Autowired
//	private MH_NurseExecuteRecordService mH_NurseExecuteRecordService;
	private PatientService patientService;
//	private MH_NurseExecuteRecordService mH_NurseExecuteRecordService;
	//初始化数据
	private static String[] diseases;//疾病
	private static Map<Integer, Map<String,String>> allAlgorithms;//所有算法常量

	private static Map<String,String> allPatientBehavior;

	private static Map<String,String> allDepartments;

	private static Map<String,String> allXAxisNames;

	private static Map<String,String> allXAxisNamesValue;

	private Map<String,String> algorithms;//当前算法

	private String[] patientBehavior;//选中的护理行为

	private String beginStatDate;
	private String endStatDate;
	//查询条件
	private List<String> patientCondition;//选中的病人的分析项
	private String select_disease="子宫平滑肌瘤";//选中的疾病
	private String algorithm;//选中的算法
	private int suport = 70;//支持度
	private int confidence = 30 ;//置信度

	//数据
	private List<List<String>> tabledata;//表格的数据
	private Map<String, List<List<String>>> chartdata;//图表的数据
	private Map<String, List<List<String>>> chartdata2;//图表2的数据（柱状图）

	private List<List<String>> relateChartData = new ArrayList<List<String>>();//关联图标的得病率

	//查询类型
	private int searchType = 1; //1:关联分析2:聚类分析
	private int lastSearchType = 1;

	private String[] patientBehaviorValue;

	private Map<String,String> nurseList;

	private QueryResult<List<Patient_view>> find_view;
	private String nurses;
	private HashMap<String, Object> guanlian;
	private HashMap<String, Object> juLeiRes;


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
	static{
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
			allXAxisNames.put("5", "普通护士,责任护士");//护士类型
		}
		if(allXAxisNamesValue == null){
			allXAxisNamesValue = new HashMap<String, String>();
			allXAxisNamesValue.put("1", "0,1,2,3,4");//护理等级
			allXAxisNamesValue.put("2", "子宫平滑肌瘤,异位妊娠,子宫颈恶性肿瘤,子宫内膜恶性肿瘤");//主要诊断
			allXAxisNamesValue.put("3", "10,20,30,40,50,60,70,80,90,100");//年龄
			allXAxisNamesValue.put("5", "普通护士,责任护士");//护士类型
		}
	}

	@Action(value="dataMiningStat")
	public String execute() throws Exception
	{
//		if(patientBehavior == null){
//			Set<String> tmp = allPatientBehavior.keySet();
//			patientBehavior = tmp.toArray(new String[tmp.size()]);
//		}
		Map<String,Object> parm=new HashMap<String, Object>();
//		if(patientBehavior == null){
//			Set<String> tmp = allPatientBehavior.keySet();
//			patientBehavior = tmp.toArray(new String[tmp.size()]);
//		}
//		if(Util.isNULL(beginStatDate))
//		{
//			Date d=new Date();
//			d.setMonth(d.getMonth()-1);
//			beginStatDate=Util.date2Str(d, "yyyy-MM-dd");
//		}
//		if(Util.isNULL(endStatDate))
//		{
//			endStatDate=Util.date2Str(new Date(), "yyyy-MM-dd");
//		}
		String conditions="";
//		if(!Util.isNULL(beginStatDate))
//		{
//			conditions+="and executeStartTime>"+Util.str2Date(beginStatDate,"yyyy-MM-dd").getTime()+" ";
//		}
//		if(!Util.isNULL(endStatDate))
//		{
//			conditions+="and  executeStartTime<"+Util.str2Date(endStatDate,"yyyy-MM-dd").getTime()+" ";
//		}
//		String _patientBehaviorValue="";
//		patientBehaviorValue=new String [patientBehavior.length];
//		for (int i=0;i<patientBehavior.length;i++) {
//			String pB =patientBehavior[i];
//			patientBehaviorValue[i]=allPatientBehavior.get(pB);
//			_patientBehaviorValue+="'"+allPatientBehavior.get(pB)+"',";
//		}
//		if(!Util.isNULL(_patientBehaviorValue))
//		{
//			_patientBehaviorValue=_patientBehaviorValue.replaceAll(",$", "");
//			conditions+="and execute in ("+_patientBehaviorValue+") ";
//		}
//		dealNurses();
//		String _nurseNos="";
//		if(!Util.isNULL(nurseList)&&nurseList.size()>0)
//		{
//			for (String key : nurseList.keySet()) {
//				_nurseNos+="'"+key+"',";
//			}
//			_nurseNos=_nurseNos.replaceAll(",$", "");
//			conditions+=" and nurseNo in ("+_nurseNos+") ";
//		}
		String zhenDuan = allXAxisNamesValue.get("2");
		String[] zhenDuans = zhenDuan.split(",");
		String _zhenDuans="";
		for (String k : zhenDuans) {
			_zhenDuans+="'"+k+"',";
		}
		_zhenDuans=_zhenDuans.replaceAll(",$", "");
		conditions+=" and ( diagnose1 in ("+_zhenDuans+") OR diagnose2 in ("+_zhenDuans+")) ";
		parm.put("conditions",conditions);
//		find_view = mH_NurseExecuteRecordService.find_view(parm, 0, 0);
		find_view = patientService.find(parm, 0, 0);
		if(searchType == 1){
			relate();
		}
		else if(searchType == 2)
		{
			juLei();
			generateData();
			generateData2();
		}
		return SUCCESS;
	}
	private void juLei() {
		List<Patient_view> list = find_view.getResult();
		Long total = find_view.getTotal();
		String zhenDuan = allXAxisNamesValue.get("2");
		String _ageIntervals = allXAxisNamesValue.get("3");
		String[] zhenDuans = zhenDuan.split(",");
		String[] ageIntervals = _ageIntervals.split(",");
		Map<String,Integer> map4All=new HashMap<String, Integer>();
		Map<String,Integer> map=new HashMap<String, Integer>();
		for (Patient_view p : list) {
			String diagnose1 = p.getDiagnose1().trim();
			int ageInterval = p.getAgeInterval();
			if(map.containsKey(diagnose1))
			{
				int s = map.get(diagnose1);
				map.put(diagnose1, s+1);
			}
			else
			{
				map.put(diagnose1, 1);
			}
			String key=diagnose1+"_"+ageInterval;
			if(map4All.containsKey(key))
			{
				int s = map4All.get(key);
				map4All.put(key, s+1);
			}
			else
			{
				map4All.put(key, 1);
			}
			//{糖尿病=[[糖尿病, 20, 72.4], [糖尿病, 30, 46.3], [糖尿病, 40, 43.1], [糖尿病, 50, 71.0], [糖尿病, 60, 54.3], [糖尿病, 70, 54.4], [糖尿病, 80, 74.4]], 
			//心脏病=[[心脏病, 20, 57.5], [心脏病, 30, 46.5], [心脏病, 40, 42.8], [心脏病, 50, 80.6], [心脏病, 60, 49.0], [心脏病, 70, 62.9], [心脏病, 80, 66.1]], 
			//高血脂=[[高血脂, 20, 78.1], [高血脂, 30, 68.8], [高血脂, 40, 43.5], [高血脂, 50, 65.1], [高血脂, 60, 71.5], [高血脂, 70, 69.4], [高血脂, 80, 89.9]], 
			//高血压=[[高血压, 20, 65.9], [高血压, 30, 80.0], [高血压, 40, 74.8], [高血压, 50, 83.1], [高血压, 60, 76.1], [高血压, 70, 73.4], [高血压, 80, 45.2]]}
		}
		juLeiRes=new HashMap<String, Object>();
		for(int i=0;i<zhenDuans.length;i++)
		{
			List<List<String>> l=new ArrayList<List<String>>();
			for(int j=0;j<ageIntervals.length;j++)
			{
				String k=zhenDuans[i].trim()+"_"+ageIntervals[j];
				if(map4All.containsKey(k))
				{
					int size = map4All.get(k);
					double d=map.get(zhenDuans[i])==0?0:size/(map.get(zhenDuans[i])+0.0)*100;
					List<String> ls=new ArrayList<String>();
					ls.add(zhenDuans[i]);
					ls.add(ageIntervals[j]);
					ls.add(d+"");
//					Object os[]=new Object[]{zhenDuans[i],ageIntervals[j],d};
					l.add(ls);
				}
			}
			juLeiRes.put(zhenDuans[i], l);
		}
	}
	private void relate()
	{
		Long total = find_view.getTotal();
		List<Patient_view> result = find_view.getResult();
		String zhenDuan = allXAxisNamesValue.get("2");
		String[] zhenDuans = zhenDuan.split(",");
		Map<String,Integer> map4All=new HashMap<String, Integer>();
		Map<String,Integer> map=new HashMap<String, Integer>();

		for (Patient_view p : result) {
			String diagnose1 = p.getDiagnose1().trim();
			String diagnose2 = p.getDiagnose2().trim();
			if(map.containsKey(diagnose1))
			{
				int s = map.get(diagnose1);
				map.put(diagnose1, s+1);
				
			}
			if(map.containsKey(diagnose2))
			{
				int s = map.get(diagnose2);
				map.put(diagnose2, s+1);
			}
			if(!map.containsKey(diagnose1))
			{
				map.put(diagnose1, 1);
			}
			if(!map.containsKey(diagnose2))
			{
				map.put(diagnose2, 1);
			}
			String key= getKey(diagnose2,diagnose1);
			if(key!=null)
			{
				if(map4All.containsKey(key))
				{
					int size = map4All.get(key);
					size++;
					map4All.put(key, size);
				}
				else
				{
					map4All.put(key, 1);
				}
			}
			else
			{
				total--;
			}
		}
//		System.out.println(map);
//		System.out.println(map4All);
		List<Map<String,Object>> diseases=new ArrayList<Map<String,Object>>();
		for(int i=0;i<zhenDuans.length;i++)
		{
			if(zhenDuans[i].trim().equals(select_disease))
				continue;
			String key = getKey(select_disease,zhenDuans[i].trim());
			int size = map4All.get(key)==null?0: map4All.get(key);
			int total2 = map.get(select_disease)==null?0:map.get(select_disease);
			Map<String,Object> m=new HashMap<String, Object>();
			int confidence=total2==0?0:(int)((size/(total2+0.0))*100);
			int suport=total==0?0:(int)((size/(total+0.0))*100);
//			System.out.println(size+"|"+total2+"|"+confidence);
			m.put("name", zhenDuans[i]);
			m.put("confidence", confidence);
			m.put("suport", suport);
			diseases.add(m);
		}
		guanlian=new HashMap<String, Object>();
		guanlian.put("select_disease", select_disease);
		guanlian.put("suport", suport);
		guanlian.put("confidence", confidence);
		guanlian.put("diseases", diseases);

		/*
		 * select_disease:"心脏病",
	suport:70,
	confidence:30,
		 * */


	}
	private String getKey(String diagnose1,String diagnose2)
	{
		if(diagnose1.hashCode()>diagnose2.hashCode())
			return diagnose1+"_"+diagnose2;
		else if(diagnose1.hashCode()<diagnose2.hashCode())
			return diagnose2+"_"+diagnose1;
		else
			return null;


	}
	private void dealNurses() {
		List<Nurse> nursesList = nurseService.findNurseByInpatientArea(patientArea+"");
		System.out.println("nurse:"+nursesList);
		nurseList = new HashMap<String, String>();
		String defaultNurses="";
		for(Nurse n : nursesList){
			nurseList.put(n.getNurseNo(), n.getName());
			defaultNurses += n.getName() + ", ";
		}
		//初始进入页面时默认一些选择条件
		if(nurses == null  || "".equals(nurses.toString().trim())){
			if(defaultNurses==null||defaultNurses.trim().equals(""))
			{
				nurses="";
			}
			else
			{
				nurses = defaultNurses.substring(0, defaultNurses.length() - 2);
			}
		}
	}
	//	@Action(value="dataMiningStat")
	public String execute2() throws Exception {
		select_disease=select_disease==null?"子宫平滑肌瘤":select_disease.trim();
		//默认是一直选中的
		//		patientCondition = new ArrayList<String>();
		//		patientCondition.add("1");
		//		patientCondition.add("2");
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

	public int getSuport() {
		return suport;
	}


	public void setSuport(int suport) {
		this.suport = suport;
	}


	public int getConfidence() {
		return confidence;
	}


	public void setConfidence(int confidence) {
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


	public String getSelect_disease() {
		return select_disease;
	}


	public void setSelect_disease(String select_disease) {
		this.select_disease = select_disease;
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


	public HashMap<String, Object> getJuLeiRes() {
		return juLeiRes;
	}
	public void setJuLeiRes(HashMap<String, Object> juLeiRes) {
		this.juLeiRes = juLeiRes;
	}
	public Map<String, List<List<String>>> getChartdata2() {
		return chartdata2;
	}


	public HashMap<String, Object> getGuanlian() {
		return guanlian;
	}
	public void setGuanlian(HashMap<String, Object> guanlian) {
		this.guanlian = guanlian;
	}
	public  Map<String, String> getAllXAxisNamesValue() {
		return allXAxisNamesValue;
	}
	public  void setAllXAxisNamesValue(Map<String, String> allXAxisNamesValue) {
		DataMiningStatAction.allXAxisNamesValue = allXAxisNamesValue;
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
