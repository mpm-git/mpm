<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统</title>

<link rel="stylesheet" type="text/css" href="<s:url value='/styles/h.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/index.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/smoothness/jquery-ui-1.8.16.custom.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/jquery_table_jui.css'/>"  >

<script type="text/javascript" src="<s:url value='/javascript/jquery-1.7.min.js'/>"></script>
<script src="<s:url value='/javascript/h.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery-ui-1.8.16.custom.min.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/highcharts-4.0.js'/>" type="text/javascript"></script>
<script src="<s:url value='/javascript/highcharts-more.js'/>" type="text/javascript"></script>
<script src="<s:url value='/javascript/jquery.dataTables.js'/>" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">

$(function(){
	G.req(["gPopMenu"],function(j){j("#aMenu");});
	G.req(["gPopMenu"],function(j){j("#bMenu");});
	G.req(["gPopMenu"],function(j){j("#cMenu");});
	G.req(["gPopMenu"],function(j){j("#mMenu");});
	G.req(["gPopMenu"],function(j){j("#dMenu");});
	G.req(["gPopMenu"],function(j){j("#eMenu");});
	G.req(["gPopMenu"],function(j){j("#fMenu");});
});

var guanlian={
	select_disease:"心脏病",
	suport:70,
	confidence:30,
	diseases:[
	          	{
	        	  name:'糖尿病',
	        	  confidence:12,
	        	  suport:21
	        	},
	        	{
		        	  name:'高血压',
		        	  confidence:72,
		        	  suport:41
		        },
		        {
		        	  name:'高血脂',
		        	  confidence:62,
		        	  suport:41
		        },
		        {
		        	  name:'高血糖',
		        	  confidence:90,
		        	  suport:80
		        }
	          ]
};
$(document).ready(function() {
	    
	    var type = ${searchType};
	    $("#lastSearchType").val(type);
		//初始tab状态
		$(".main_tab li:eq("+(type-1)+")").attr("class","current");
		
		
		if(type == 2){
			//聚合分析
			$('#chartDiv').highcharts({ 
				    credits: {enabled: false},
			        chart: {                                                                             
			            type: 'scatter',                                                                 
			            zoomType: 'xy'                                                                   
			        },                                                                                   
			        title: {                                                                             
			            text: '疾病与诊断数据分析'                        
			        },                                                                                   
			        //subtitle: {                                                                          
			            ///text: '统计年份：2008-2010'                                                      
			        //},                                                                                   
			        xAxis: {                                                                             
			            title: {                                                                         
			                enabled: true,                                                               
			                text: '年龄'                                                          
			            },                                                                               
			            startOnTick: true,                                                               
			            endOnTick: true,                                                                 
			            showLastLabel: true,
			        },                                                                                   
			        yAxis: {                                                                             
			            title: {                                                                         
			                text: '疾病指数'                                                          
			            }                                                                                
			        },                                                                                   
			        plotOptions: {                                                                       
			            scatter: {                                                                       
			                marker: {                                                                    
			                    radius: 5,                                                               
			                    states: {                                                                
			                        hover: {                                                             
			                            enabled: true,                                                   
			                            lineColor: 'rgb(100,100,100)'                                    
			                        }                                                                    
			                    }                                                                        
			                },                                                     
			                tooltip: {                                                                   
			                    headerFormat: '<b>{series.name}</b><br>',                                
			                    pointFormat: '{point.x}岁, {point.y}%',
			                    useHTML:true
			                }                                                                            
			            }                                                                                
			        }                                                                                 
			});
			//genertateData('chartDiv', 1);
		}else{
			//关联挖掘
// 			$('#chartDiv').highcharts({ 
// 				chart: { type: 'bubble', zoomType: 'xy' }, 
// 				title: { text: '疾病间数据分析' },
// 				credits: {enabled: false},
// 				yAxis: {                                                                             
// 		            title: {                                                                         
// 		                text: '百分比(%)'                                                          
// 		            }                                                                                
// 		        }  
// 			});
			var _categories=[];
			var _confidence=[];
			var _suport=[];
			for(var i=0;i<guanlian.diseases.length;i++)
			{
				_categories.push(guanlian.diseases[i].name);
				_confidence.push(guanlian.diseases[i].confidence);
				_suport.push(guanlian.diseases[i].suport);
			}
			$('#relate_chart').highcharts({ 
				chart: { type: 'column' }, 
				title: { text: guanlian.select_disease+'与其他疾病数据分析' },
				credits: {enabled: false},
				xAxis: { 
					title:{text:"病种"},
					categories: _categories
				}, 
				yAxis: { 
					min: 0, 
					title: { text: '百分比(%)' } ,
				    tickInterval:10,
				    plotLines: [{   //一条延长到全部画图区的线，标记住轴中一个特定值。
	                    color: "#FF0000",
	                    dashStyle: "Dash", //Dash，Dot，Solid，默认Solid
	                    width: 1.5,
	                    value:guanlian.confidence ,  //y轴显示
	                    zIndex: 5
	                },
	                {   //一条延长到全部画图区的线，标记住轴中一个特定值。
	                    color: "#0000ff",
	                    dashStyle: "Dash", //Dash，Dot，Solid，默认Solid
	                    width: 2.5,
	                    value:guanlian.suport ,  //y轴显示
	                    zIndex: 5
	                }]
				}, 
				tooltip: {
					pointFormat: '<b>{series.name}</b>  <b>{point.y}%</b> ',
					useHTML:true
				},
				plotOptions: { column: { pointPadding: 0.2, borderWidth: 0 } }
			});
			var chart22 = $("#relate_chart").highcharts();
	          chart22.addSeries({
		    	    type: 'column', 
			        name:"支持度", 
			        data: _suport
		       });
            chart22.addSeries({
	    	    type: 'spline', 
		        name: '置信度', 
		        data: _confidence
	         });
  
			//genertateData2();
		}
		
		//genertateData('chartDiv', 1);


		
		
		$("#info_table").dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"bFilter":false
		});
		
		$(".main_tab li").click(function(){
       	 var index = parseInt($(this).index()) + 1;
       	 $("#searchType").val(index);
       	 $("#searchForm").submit();
       });
		
});

function genertateData(chartId, attrId){
	var chart = $("#"+chartId).highcharts();
	var index = 0;
	if(attrId == 1){
	<%
	    Map<String, List<List<String>>> data = (Map<String, List<List<String>>>)request.getAttribute("chartdata");
		if(data != null)
		{
		  String last_title = "";
          //循环每行数据
		  for(String title : data.keySet())
		  {
			    List<List<String>> oneitemdata = data.get(title);
	%>
				var itemdata = new Array();
				index = 0;
	<%
			    for(int j = 0 ;j < oneitemdata.size();j++){
			    	List<String> one = oneitemdata.get(j);
	%>
					//series数据设置
					var onedata = new Array();
	<%
			    	for(int k = 1 ; k < one.size() ; k++){
	%>
	                    onedata.push(<%=one.get(k)%>);	
	<%
			    	}
	%>
					itemdata[index] = onedata;
				    index = index + 1;
	<%
			    }
	%>
				chart.addSeries({
			        name: '<%=title%>', 
			        data: itemdata
		        });
	<%
		   }
	    }
	%>
	}else {
		<%
	    Map<String, List<List<String>>> data2 = (Map<String, List<List<String>>>)request.getAttribute("chartdata2");
		if(data2 != null)
		{
		  String last_title = "";
          //循环每行数据
		  for(String title : data2.keySet())
		  {
			    List<List<String>> oneitemdata = data2.get(title);
	%>
				var itemdata = new Array();
				index = 0;
	<%
			    for(int j = 0 ;j < oneitemdata.size();j++){
			    	List<String> one = oneitemdata.get(j);
	%>
					//series数据设置
					var onedata = new Array();
	<%
			    	for(int k = 1 ; k < one.size() ; k++){
	%>
	                    onedata.push(<%=one.get(k)%>);	
	<%
			    	}
	%>
					itemdata[index] = onedata;
				    index = index + 1;
	<%
			    }
	%>
				chart.addSeries({
			        name: '<%=title%>', 
			        data: itemdata
		        });
	<%
		   }
	    }
	%>

	}
}

function genertateData2(){
	var chart22 = $("#relate_chart").highcharts();
	var totalRowData = new Array();
	<%
	    List<List<String>> relate_data = (List<List<String>>)request.getAttribute("relateChartData");
		if(relate_data != null && relate_data.size() > 0)
		{
		  //先设置总计的柱状图
		  List<String> totalRow = relate_data.get(data.size()-1);
		  for(int j = 1 ;j < totalRow.size();j++){
	%>
	          totalRowData.push(<%=totalRow.get(j)%>);	
	<%
		  }
    %>
             chart22.addSeries({
	    	    type: 'spline', 
		        name: '平均得病率', 
		        data: totalRowData
	         });
    <%
          //循环每行数据
		  for(int i = 0 ;i < (relate_data.size()-1);i++)
		  {
			    List<String> oneRow = relate_data.get(i);
	%>
				//series数据设置
				var onelinedata = new Array();
				var onelinetitle = '<%=oneRow.get(0)%>';
	<%
			    for(int j = 1 ;j < oneRow.size();j++){
	%>
	               onelinedata.push(<%=oneRow.get(j)%>);		               
	<%
			    }
	%>
	               chart22.addSeries({
			    	    type: 'column', 
				        name: onelinetitle, 
				        data: onelinedata
			       });
	<%
		  }
		}
	%>
}

</script>
<style type="text/css">
div.menu_btn{font-size: 15px;font-weight:bold; display:inline-block;text-align: center;margin: 35px;cursor:pointer;}
div.menu_btn span{font-family: "微软雅黑";}
ul.titles li{height:30px;padding:5px;line-height: 30px;}
.checkboxLabel{margin-right: 10px;}
.date_text{height:21px;line-height: 21px; width: 160px;}
</style> 
</head>
<body>


<div class="container">

<s:include value="../header1.jsp"></s:include>

              
  <div class="wrap">
      <div class="side-title">
        <h2>· 您现在所在的位置：&nbsp;&nbsp;决策支持&nbsp;&nbsp;→&nbsp;&nbsp;临床决策支持</h2>
      </div>
      <ul class="main_tab" >
		<li><a href="javascript:void(0);"><span>关联挖掘</span></a></li>
		<li><a href="javascript:void(0);"><span>聚类分析</span></a></li>
      </ul>
      <form action="dataMiningStat.action" method="post" id="searchForm">
	      <ul class="titles">
	        <li>
	                              病人：
	          <s:checkboxlist theme="simple" list="#{'1':'年龄','2':'诊断'}" value="%{patientCondition}" listKey="key" listValue="value" name="patientCondition" />
	        </li>
	        <li>
	                              病种：
	          <select name="select_disease " id="select_disease" style="width:160px;height:22px;">
	              <option value="1" <s:if test="select_disease==1">selected="selected"</s:if>>心脏病</option>
	              
	              <option value="2" <s:if test="select_disease==2">selected="selected"</s:if>>糖尿病</option>
	              <option value="3" <s:if test="select_disease==3">selected="selected"</s:if>>高血压</option>
	              <option value="4" <s:if test="select_disease==4">selected="selected"</s:if>>高血脂</option>
	               
	            </select>
	        </li>
	        <!-- <li>
	                             护士：
	            <div id="patientSpan" style="display: inline;">             
		            <s:iterator value="nurseList" var="p">
		                <input type="checkbox" name="nurses" style="margin: 0 3px 0 15px;" value="<s:property value="#p.value"/>"/><s:property value="#p.value"/>
		            </s:iterator>
	            </div>
	        </li> -->
<!-- 	        <li> -->
<!-- 	                             算法： -->
<%-- 	          <s:radio cssStyle="margin: 0 2px 0 15px;" list="algorithms" value="#{algorithm}" listKey="key" listValue="value" name="algorithm" />   --%>
<!-- 	        </li> -->
	        <s:if test="searchType == 1">
	        <li>
	                             支持度：
	            <input type="text" value="<s:property value="suport"/>" id="suport" name="suport"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                             置信度：
	            <input type="text" value="<s:property value="confidence"/>" id="confidence" name="confidence"/>
	        </li>
	        </s:if>
	        <li>
		        <input type="submit"  style="margin-left: 10px;" class="ui-button ui-widget ui-state-default ui-corner-all" value="分析" id="analyseBtn" />
	        </li>
	      </ul>
	      <input type="hidden" id="searchType" name="searchType" value="${searchType}">
	      <input type="hidden" id="lastSearchType" name="lastSearchType" value="${lastSearchType}">
     </form>
     
     <div style="border-bottom: 1px dashed #CCCCCC"></div>
     
     <div id="resultDiv1" style="width: 800px;margin-left: auto;margin-right: auto;">
<!-- 	      <div id="chartDiv" style="padding-top: 20px; width: 800px; height: 300px; margin: 0 auto; margin-bottom: 20px;"> -->
<!-- 	      </div> -->
	      
	      
<!-- 	      <input type="button"  style="float: right;margin: 10px;" class="ui-button ui-widget ui-state-default ui-corner-all" value="导出excel" id="excelBtn" />
 -->	      <div id="infoDiv">
 <!-- 
			    <table  cellpadding="0" cellspacing="0" border="0" class="display" id="info_table">
		             <thead>
			           <tr> 
				           <th>疾病</th> 
				           <th>年龄</th>
				           <th>疾病指数</th> 
				           <s:if test="searchType == 1"><th>概率(%)</th></s:if> 
			           </tr>
		             </thead>
		             <tbody>
			             <s:iterator value="tabledata" id="data1" status="status"> 
				            <tr>
				               <s:iterator value="data1" id="info2">
							       <td><s:property value="info2"/></td> 
				               </s:iterator>
					        </tr> 
			             </s:iterator>
			         <tbody>
	          </table>
  -->
		 </div>
     </div>
     

     
     <s:if test="searchType == 1">
	      <div id="relate_chart" style="padding-top: 20px; width: 800px; height: 300px; margin: 0 auto; margin-bottom: 20px;">
	      </div>
	 </s:if>
    </div>
  <s:include value="../footer.jsp"></s:include>
 </div>

</body>
</html>
