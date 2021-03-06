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
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.service.QueryResult;
import cn.com.mwsn.frame.util.ExportExcelUtil;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_view;
import cn.com.mwsn.mpm.entity.Nurse;
import cn.com.mwsn.mpm.service.MH_NurseExecuteRecordService;
import cn.com.mwsn.mpm.service.NurseService;

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

	@Autowired
	private MH_NurseExecuteRecordService mH_NurseExecuteRecordService;
	@Autowired
	private NurseService nurseService;
	private static final Logger log = Logger.getLogger(NursingEfficiencyStatAction.class);

	//作为展示基础数据用
	private static Map<String,List<Nurse>> allNursesList;//所有护士列表 key为病区
	private static Map<String,String> allPatientBehavior;//所有护理行为
	private static Map<String,String> allDepartments;;//所有病区
	//查询条件
	private String patientArea = "B1";//病区
	private Map<String,String> nurseList;//病区对应的护士列表
	private String nurses;//选中护士
	private String beginStatDate;
	private String endStatDate;
	private String[] patientBehavior;//选中的护理行为
	private String[] yAxisName;//护理维度y轴显示

	private String[] xAxisName;//护理维度x轴显示

	private static Map<String,String> allXAxisNamesValue;
	private static Map<String,String> allXAxisNames;//所有x轴护理维度设定
	List<String> xAxisNames;//x轴
	List<String> xAxisNamesValue;//x轴
	String title=null;
	String fieldID=null;
	String fieldValue=null;
	String sumTitle=null;
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

	private String excel_main_title;

	private String fieldID2;

	private int workingDays;

	private int workingTime;

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
			allXAxisNames.put("1", Constant.allXAxisNames_1);//护理等级
			allXAxisNames.put("2", Constant.allXAxisNames_2);//主要诊断
			allXAxisNames.put("3", Constant.allXAxisNames_3);//年龄
			allXAxisNames.put("5", Constant.allXAxisNames_5);//护士类型
		}
		if(allXAxisNamesValue == null){
			allXAxisNamesValue = new HashMap<String, String>();
			allXAxisNamesValue.put("1", Constant.allXAxisNamesValue_1);//护理等级
			allXAxisNamesValue.put("2", Constant.allXAxisNamesValue_2);//主要诊断
			allXAxisNamesValue.put("3", Constant.allXAxisNamesValue_3);//年龄
			allXAxisNamesValue.put("5", Constant.allXAxisNamesValue_5);//护士类型
		}
	}
	@Action(value="nursingStrengthStat")
	public String execute()
	{
		if(patientBehavior == null){
			Set<String> tmp = allPatientBehavior.keySet();
			patientBehavior = tmp.toArray(new String[tmp.size()]);
		}
		Map<String,Object> parm=new HashMap<String, Object>();
		if(patientBehavior == null){
			Set<String> tmp = allPatientBehavior.keySet();
			patientBehavior = tmp.toArray(new String[tmp.size()]);
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
		String conditions="";
		if(!Util.isNULL(beginStatDate))
		{
			conditions+="and executeStartTime>"+Util.str2Date(beginStatDate,"yyyy-MM-dd").getTime()+" ";
		}
		if(!Util.isNULL(endStatDate))
		{
			conditions+="and  executeStartTime<"+Util.str2Date(endStatDate,"yyyy-MM-dd").getTime()+" ";
		}
		String _patientBehaviorValue="";
		patientBehaviorValue=new String [patientBehavior.length];
		for (int i=0;i<patientBehavior.length;i++) {
			String pB =patientBehavior[i];
			patientBehaviorValue[i]=allPatientBehavior.get(pB);
			_patientBehaviorValue+="'"+allPatientBehavior.get(pB)+"',";
		}
		if(!Util.isNULL(_patientBehaviorValue))
		{
			_patientBehaviorValue=_patientBehaviorValue.replaceAll(",$", "");
			conditions+="and execute in ("+_patientBehaviorValue+") ";
		}
		dealNurses();
		String _nurseNos="";
		if(!Util.isNULL(nurseList)&&nurseList.size()>0)
		{
			for (String key : nurseList.keySet()) {
				_nurseNos+="'"+key+"',";
			}
			_nurseNos=_nurseNos.replaceAll(",$", "");
			conditions+=" and nurseNo in ("+_nurseNos+") ";
		}
		parm.put("conditions",conditions);
		find_view = mH_NurseExecuteRecordService.find_view(parm, 0, 0);
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
			yAxisName = new String[]{"2"};
		}
		if(yAxisName[0].equals("1"))
		{
			invokeField="ciShu";
			sumTitle="总计次数";
		}
		else if(yAxisName[0].equals("2"))
		{
			if(searchType==1)
			{
				invokeField="haoShi";
				sumTitle="每日平均耗时";
			}
			if(searchType==2)
			{
				invokeField="avgHaoShi";
				sumTitle="平均耗时";
			}
			dealSum=new DealSum() {
				@Override
				public Object execute(Object old,Object news,Object o) {
					//				long haoShi=((MH_NurseExecuteRecord_view)o).getHaoShi();
					//				if(old==null)
					//					return haoShi;
					//				return Long.parseLong(old.toString().trim())+haoShi;
					if(old==null)
						return news;
					if(searchType==2)
					{
						return (Long.parseLong(old.toString().trim())+Long.parseLong(news.toString().trim()))/2;
					}else
					{
						return (Long.parseLong(old.toString().trim())+Long.parseLong(news.toString().trim()));
					}
				}

				@Override
				public Object changeNull(Object o) {
					// TODO Auto-generated method stub
					return null;
				}
			};
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
			workingTime=8*60/100;
			workingDays=DateUtil.getWorkingDays(Util.str2Date(beginStatDate, "yyyy-MM-dd"), true, Util.str2Date(endStatDate, "yyyy-MM-dd"), true);
			xAxisNames = new ArrayList<String>(nurseList.values());
			xAxisNamesValue = new ArrayList<String>(nurseList.keySet());
			title="护士";
			fieldID="nurseNo";
			fieldID2="execute";
			fieldValue="nurseName";
			 excel_main_title="按护士";
		}
//		else if(searchType == 2) 
//		{
//			xAxisNames = Arrays.asList(allXAxisNames.get("1").split(","));
//			xAxisNamesValue = Arrays.asList(allXAxisNamesValue.get("1").split(","));
//			title = "护理等级";
//			fieldID = "careLevel";
//			fieldValue="careLevel";
//		}
//		else if(searchType == 3){
//			xAxisNames = Arrays.asList(allXAxisNames.get("2").split(",")); 
//			xAxisNamesValue = Arrays.asList(allXAxisNamesValue.get("2").split(","));
//			title = "主要诊断";
//			fieldID="ageInterval";
//			fieldValue="ageInterval";
//		}else if(searchType == 4){
//			xAxisNames = Arrays.asList(allXAxisNames.get("3").split(",")); 
//			xAxisNamesValue = Arrays.asList(allXAxisNamesValue.get("3").split(","));
//			title = "年龄";
//			fieldID="ageInterval";
//			fieldValue="ageInterval";
//		}
		else if(searchType == 2) 
		{
			workingDays=1;
			workingTime=1;
			xAxisNames = Arrays.asList(allXAxisNames.get("5").split(","));
			xAxisNamesValue = Arrays.asList(allXAxisNamesValue.get("5").split(","));
			title = "护士职称";
			fieldID = "nurseType";
			fieldID2 = "execute";
			fieldValue="nurseType";
			 excel_main_title="按职称";
		}
		{
			String[] execute = new String[]{fieldID,fieldID2};
			find_view_map=changeFind_view4Map(find_view.getResult(),execute);
			generateData();
			tableData = composeDate();
		}
		return SUCCESS;

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
		System.out.println(find_view_map);
		return find_view_map;
	}
	private void generateData() {
		chartData.add(xAxisNames);
		List<String> lists=null;
		Map<String,Object> map4Sum=new HashMap<String, Object>();
		List<String> list4Sum=new ArrayList<String>();
		list4Sum.add(sumTitle);

		Map<String, MH_NurseExecuteRecord_view> _find_view_map =null;
		String add="";
		if(searchType==0)
		{
			String[] execute = new String[]{"nurseNo"};
			_find_view_map=changeFind_view4Map(find_view.getResult(),execute);
//			System.out.println(_find_view_map);
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
					lists.add(Integer.parseInt(ss.toString())/(workingDays*1.0*workingTime)+"");
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
				list4Sum.add(Integer.parseInt(o.toString())/(workingDays*1.0*workingTime)+"");
			}
		}
		chartData.add(list4Sum);
		System.out.println("chartData:\n");
		System.out.println(chartData);
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
			if(searchType==1)
			{
				up_line1.add("最大耗时");
				up_line1.add("平均耗时");
//				up_line1.add("每天平均耗时");
				up_line1.add("总耗时");
			}
			if(searchType==2)
			{
				up_line1.add("最大耗时");
				up_line1.add("平均耗时");
				up_line1.add("最小耗时");
			}
			data.add(up_line1);

			List<String> lineOther = null;
			Set<String> keySet = find_view_map.keySet();
			for (String key : keySet) {
				lineOther = new ArrayList<String>();
				lineOther.add(ClassUtil.invokeGet(find_view_map.get(key), fieldValue).toString());
				lineOther.add(find_view_map.get(key).getExecute());
				if(searchType==1)
				{
					lineOther.add(find_view_map.get(key).getMaxHaoShi()+"");
					lineOther.add(find_view_map.get(key).getAvgHaoShi()+"");
//					lineOther.add(find_view_map.get(key).getHaoShi()/(workingDays*1.0*workingTime)+"");
					lineOther.add(find_view_map.get(key).getHaoShi()+"");
				}
				if(searchType==2)
				{
					lineOther.add(find_view_map.get(key).getMaxHaoShi()+"");
					lineOther.add(find_view_map.get(key).getAvgHaoShi()+"");
					lineOther.add(find_view_map.get(key).getMinHaoShi()+"");
				}
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

	public String getPatientArea() {
		return patientArea;
	}

	public void setPatientArea(String patientArea) {
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
