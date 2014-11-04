<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,org.cz.utils.gson.*"%>
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
<%-- <link rel="stylesheet" type="text/css" href="<s:url value='/javascript/DataTables-1.10.3/media/css/jquery.dataTables.css'/>"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<s:url value='/javascript/DataTables-1.10.3/examples/resources/syntax/shCore.css'/>"> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<s:url value='/javascript/DataTables-1.10.3/examples/resources/demo.css'/>"> --%>
<script type="text/javascript" src="<s:url value='/javascript/jquery-1.7.min.js'/>"></script>
<script src="<s:url value='/javascript/h.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery-ui-1.8.16.custom.min.js'/>" type="text/javascript" charset="utf-8"></script>
<%--  	<script type="text/javascript" language="javascript" src="<s:url value='/javascript/DataTables-1.10.3/media/js/jquery.js'/>"></script> --%>
	<script type="text/javascript" language="javascript" src="<s:url value='/javascript/DataTables-1.10.3/media/js/jquery.dataTables.min.js'/>"></script>
<%-- 	<script type="text/javascript" language="javascript" src="<s:url value='/javascript/DataTables-1.10.3/examples/resources/syntax/shCore.js'/>"></script> --%>
<%-- 	<script type="text/javascript" language="javascript" src="<s:url value='/javascript/DataTables-1.10.3/examples/resources/demo.js'/>"></script> --%>
<script src="<s:url value='/javascript/highcharts-4.0.js'/>" type="text/javascript"></script>
<%-- <script src="<s:url value='/javascript/jquery.dataTables.js'/>" type="text/javascript" charset="utf-8"></script> --%>




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

var type = ${searchType} - 1;
var xAxisTitle = "病人";
if(type == 1){
	xAxisTitle = "护理等级";
}else if(type == 2){
	xAxisTitle = "主要诊断";
}else if(type == 3){
	xAxisTitle = "年龄";
}
$(document).ready(function() {
// 	$('#info_table').dataTable();
// 	$('#show_table').dataTable( {
// 		//"bAutoWidth": false, //不自动计算列宽度

// 	     //"aoColumns": aoColumns,

// 	/*     "bSort": false,

// 	     "bProcessing": true, //加载数据时显示正在加载信息

// 	     "bServerSide": true, //指定从服务器端获取数据

// 	     "bFilter": false, //不使用过滤功能

// 	     "bLengthChange": false, //用户不可改变每页显示数量

// 	     "iDisplayLength": 16, //每页显示10条数据

// 	     "sPaginationType": "full_numbers", //翻页界面类型

// 	     "headerClickable":false,*/

// 	     //"sortable":false
// 		//"bServerSide": true,                    //指定从服务器端获取数据    
// 		"ajax":  {
//             "url": "mh_nurse_execute_record_patient.action",
//             "dataSrc": "result"
            
//         },
//         "columns": [
//                     { "data": "name" },
//                     { "data": "execute" },
//                     { "data": "ciShu" },
//                     { "data": "" }
//                 ]
//     } );
		//初始tab状态
		$(".main_tab li:eq("+type+")").attr("class","current");
		///alert(tmp);
		$( "#beginStatDate" ).datepicker({dateFormat:'yy-mm-dd'});
		$( "#endStatDate" ).datepicker({dateFormat:'yy-mm-dd'});
		//病区切换后，重新获取病区内所有病人
		$("#patientArea").change(function(){
			var a = $(this).val();
			$.ajax({    
		        url : 'listPatients.action',    
		        type : 'post',    
		        data : {'patientArea': a},    
		        dataType : 'json',    
		        success : function(data) {    
		            //重新设置病人的checkbox
		            $("#patientSpan").empty();
		            var s = "";
		            $.each(data.patientList,function(key,value){
		            	s += "<input type='checkbox' style='margin: 0 3px 0 15px;' name='patients' value='"+value+"'/>" + value;
		            }); 
	            	$("#patientSpan").append(s);
		        }
		    });   
		});
		
		
		$("input[name='patients']").each(function(){
			var a = '<%=request.getAttribute("patients")%>'; ///中文问题
			if(a.indexOf($(this).val()) > -1){
				$(this).prop("checked","checked");
			}
		});
		
		$('#chartDiv').highcharts({
			credits: {enabled: false},
			chart: { type: 'column' },
			legend: { align: 'right', layout :'vertical',verticalAlign:'middle'},
			title: {text: '单项护理强度分析',margin:25, style:{'fontSize':'20px'}},
			xAxis: {
				title:{text:xAxisTitle},
				categories: ['张三', '李四', '王五', '赵六']
			},
			yAxis: {
				min: 0,
				title: {
					text: '护理时间占工作时间比重(%)'
				}
			},
			tooltip: {
				pointFormat: '<b>{series.name}</b>  <b>{point.y}</b> ',
				useHTML:true
			}
		});
		var chart = $('#chartDiv').highcharts();
		genertateData("chartDiv");
		chart.yAxis[0].setTitle({
			text: $("input[name='yAxisName']:checked").next("label").text()
        });
		
		$("#info_table").dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers"
// 			,"bFilter":false
		});
		
        $("#excelBtn").click(function(){
        	$("#searchForm").attr("action","singleNursingExportExcel.action");
        	$("#searchForm").submit();
        });
        
        $(".main_tab li").click(function(){
        	 var index = parseInt($(this).index()) + 1;
        	 $("#searchType").val(index);
        	 $("#searchForm").attr("action","patientSingleMain.action");
        	 $("#searchForm").submit();
        });
});

function genertateData(chartId){
	var chart = $("#"+chartId).highcharts();
	var xcategories = new Array();
	var totalRowData = new Array();
	<%
	    List<List<String>> data = (List<List<String>>)request.getAttribute("chartData");
	String ss=GsonUtil.objectToJson(data);
		if(data != null)
		{
		  //先设置总计的柱状图
		  List<String> totalRow = data.get(data.size()-1);
		  for(int j = 1 ;j < totalRow.size();j++){
	%>
	          totalRowData.push(<%=totalRow.get(j)%>);	
	<%
		  }
    %>
    console.info(<%=ss%>);
		     chart.addSeries({
	    	    type: 'column', 
		        name: '总计', 
		        data: totalRowData
	         });
	var pieData=[];
    <%
    int searchType=(Integer)(request.getAttribute("searchType"));
          //循环每行数据
		  for(int i = 0 ;i < (data.size()-1);i++)
		  {
			 List<String> oneRow = data.get(i);
			 if(i == 0){
				 //x轴数据设置
				 for(int j = 0 ;j < oneRow.size();j++){
	%>
	                 xcategories.push('<%=oneRow.get(j)%>');
	<%
				 }
			 }else{
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
				if(searchType==1)
				{%>
				pieData.push(
						{name: onelinetitle, 
					        y: onelinedata[0]
					    });
				   
				<%}
				else{%>
				   	chart.addSeries({
			    	    type: 'spline', 
				        name: onelinetitle, 
				        data: onelinedata
			       	});
					<%}
			 }
		  }
			if(searchType==1)
			{%>
			console.info(pieData);
				chart.addSeries({
				    type: 'pie', 
				    name: ' ',
					data:pieData
			   	});
			   
			<%}

		}
	%>
	chart.xAxis[0].setCategories(xcategories);
}


</script>
<style type="text/css">
div.menu_btn{font-size: 15px;font-weight:bold; display:inline-block;text-align: center;margin: 35px;cursor:pointer;}
div.menu_btn span{font-family: "微软雅黑";}
ul.titles li{padding:5px;line-height: 30px;}
/* ul.titles li{height:30px;padding:5px;line-height: 30px;}  */
.checkboxLabel{margin-right: 10px;}
.date_text{height:21px;line-height: 21px; width: 160px;}
</style> 
</head>
<body>


<div class="container">

<s:include value="../header1.jsp"></s:include>

              
  <div class="wrap">
      <div class="side-title">
        <h2>· 您现在所在的位置：&nbsp;&nbsp;决策支持&nbsp;&nbsp;→&nbsp;&nbsp;病人护理行为分析</h2>
      </div>
      <form action="patientSingleMain.action" method="post" id="searchForm">
	      <ul class="titles">
	        <li>
	                              病区：
	          <select name="patientArea" id="patientArea" style="width:160px;height:22px;">
	              <option value="1" <s:if test="patientArea==1">selected="selected"</s:if>>8B</option>
	              <!-- 
	              <option value="2" <s:if test="patientArea==2">selected="selected"</s:if>>呼吸科(一院)</option>
	              <option value="3" <s:if test="patientArea==3">selected="selected"</s:if>>胸心外科(一院)</option>
	              <option value="4" <s:if test="patientArea==4">selected="selected"</s:if>>心血管内科一病区(一院)</option>
	               -->
	            </select>
	        </li>
<!-- 	        <li> -->
<!-- 	                              病人： -->
<!-- 	            <div id="patientSpan" style="display: inline;">              -->
<%-- 		            <s:iterator value="patientList" var="p"> --%>
<%-- 		                <input type="checkbox" name="patients" style="margin: 0 3px 0 15px;" value="<s:property value="#p.value"/>"/><s:property value="#p.value"/> --%>
<%-- 		            </s:iterator> --%>
<!-- 	            </div> -->
<!-- 	        </li> -->
	        <li>
	                              时间：
	            <input type="text" class="date_text" readonly="readonly" value="<s:property value="beginStatDate"/>" id="beginStatDate" name="beginStatDate"/>&nbsp;~&nbsp;
	            <input type="text" class="date_text" readonly="readonly" value="<s:property value="endStatDate"/>" id="endStatDate" name="endStatDate"/>
	        </li>
	        <li>
	        <div style="float:left;width: 70px;">  护理行为：</div>
	            <div style="float:left">
	            <s:checkboxlist theme="simple" list="allPatientBehavior" value="patientBehavior" listKey="key" listValue="value" name="patientBehavior" id="patientBehavior"/>
	       		</div>
	        </li>
	        <li>
	                              护理维度：
	            <s:radio cssStyle="margin: 0 2px 0 15px;" value="yAxisName" list="#{'1':'次数','2':'耗时','3':'开始时间'}" listKey="key"  listValue="value" name="yAxisName" id="yAxisName" />
	        </li>
	        <li>
		        <input type="submit"  style="margin-left: 10px;" class="ui-button ui-widget ui-state-default ui-corner-all" value="分析" id="analyseBtn" />
	        </li>
	      </ul>
	      <input type="hidden" id="searchType" name="searchType" value="${searchType}">
     </form>
     <ul class="main_tab" style="margin-top: 10px;">
		<li><a href="javascript:void(0);"><span>护理强度综合分析</span></a></li>
		<li><a href="javascript:void(0);"><span>按护理等级分析</span></a></li>
		<li><a href="javascript:void(0);"><span>按主要诊断分析</span></a></li>
		<li><a href="javascript:void(0);"><span>按年龄分析</span></a></li>
     </ul>
     
     <div style="border-bottom: 1px dashed #CCCCCC">
     </div>
     
     <div id="resultDiv" style="width: 800px;margin-left: auto;margin-right: auto;">
	      <div id="chartDiv" style="padding-top: 20px; width: 800px; height: 300px; margin: 0 auto; margin-bottom: 20px;">
	      </div>
	      <div id="infoDiv">
	      <input type="button"  style="float: right;margin: 10px;" class="ui-button ui-widget ui-state-default ui-corner-all" value="导出excel" id="excelBtn" />
<!-- 	    <table id="show_table" class="display" cellspacing="0" width="100%"> -->
<!-- 		    <thead> -->
<!-- 		        <tr> -->
<!-- 		            <th>病人</th> -->
<!-- 		            <th>护理行为</th> -->
<!-- 		            <th>次数</th> -->
<!-- 		            <th>备注</th> -->
<!-- 		        </tr> -->
<!-- 		    </thead> -->
		 
<!-- 		    <tfoot> -->
<!-- 		        <tr> -->
<!-- 		            <th>病人</th> -->
<!-- 		            <th>护理行为</th> -->
<!-- 		            <th>次数</th> -->
<!-- 		            <th>备注</th> -->
<!-- 		        </tr> -->
<!-- 		    </tfoot> -->
<!-- 		</table>   -->
			    <table cellpadding="0" cellspacing="0" border="0" class="display" id="info_table">
			     <s:iterator value="tableData" id="data1" status="status"> 
			         <s:if test="#status.index == 0">
			             <thead>
				           <tr> 
				               <s:iterator value="data1" id="info">
					             <th><s:property value="info"/></th> 
					           </s:iterator>
				           </tr>
			             </thead>
			         </s:if>
			         <s:else>
			            <tr>
			               <s:iterator value="data1" id="info2">
						       <td><s:property value="info2"/></td> 
			               </s:iterator>
				       </tr> 
			         </s:else>
			     </s:iterator>
	          </table>
		 </div>
     </div>
     
     <!--  <form action="patientSingleMain.action" method="post">
	      <ul class="titles">
	        <li>
              <s:radio cssStyle="margin: 0 2px 0 15px;" value="xAxisName" list="#{'1':'护理等级','2':'主要诊断','3':'年龄'}" listKey="key"  listValue="value" name="xAxisName" id="xAxisName" />
            </li>
            <li>
		        <input type="submit"  style="margin-left: 10px;" class="ui-button ui-widget ui-state-default ui-corner-all" value="分析" id="analyseBtn" />
	        </li>
          </ul>
     </form>-->
    </div>
  <s:include value="../footer.jsp"></s:include>
 </div>

</body>
</html>
