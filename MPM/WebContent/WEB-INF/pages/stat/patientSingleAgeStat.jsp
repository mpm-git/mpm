<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<script type="text/javascript">

$(function(){
	G.req(["gPopMenu"],function(j){j("#aMenu");});
	G.req(["gPopMenu"],function(j){j("#bMenu");});
	G.req(["gPopMenu"],function(j){j("#cMenu");});
	G.req(["gPopMenu"],function(j){j("#mMenu");});
	G.req(["gPopMenu"],function(j){j("#dMenu");});
	G.req(["gPopMenu"],function(j){j("#eMenu");});
	G.req(["gPopMenu"],function(j){j("#fMenu");});

	$( "#beginStatDate" ).datepicker();
	$( "#endStatDate" ).datepicker();
	
});


$(document).ready(function() {
	$('#chartDiv').highcharts({
		credits: {enabled: false},
		title: {text: '年龄与单向护理行为耗时分析'},
		xAxis: {
			title:{text:'年龄'},
			categories: ['10', '20', '30', '40', '50', '60', '70', '80', '90', '100']
		},
		yAxis: {
			min: 0,
			title: {
				text: '平均耗时(分)'
			}
		},
		legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle', borderWidth: 0 },
		tooltip: {
			pointFormat: '<b>{series.name}</b> 耗时 <b>{point.y:.1f}</b> 分钟',
			useHTML:true
		},
	    series: [{
			name: '巡床',
			data: [5, 3, 2.5, 3, 2.5, 2.5, 4, 5, 6, 6]
	
		},{
			name: '晨间护理',
			data: [5, 3, 2, 2,2.5, 3, 4, 5, 6, 6]
	
		},{
			name: '床上擦浴',
		    data: [15, 10, 8, 15, 10, 8, 15, 10, 8]	
		},{
			name: '执行',
			data: [2, 1, 1, 2, 1, 1, 2, 1, 2]
	
		}]
	});
	
	
	
});

</script>

</head>
<body>


<div class="container">

<s:include value="../header1.jsp"></s:include>

              
  <div class="wrap">
    <div class="wrap">
      <div class="side-title">
        <h2>· 您现在所在的位置：&nbsp;&nbsp;决策支持&nbsp;&nbsp;→&nbsp;&nbsp;<a href="patientSingleMain.action">病人单向护理行为分析</a>→&nbsp;&nbsp;年龄耗时分析</h2>
      </div>

      <div id="chartDiv" style="padding-top: 20px; width: 800px; height: 300px; margin: 0 auto; margin-bottom: 20px;">
      	
      </div>
      
    </div>
  </div>
  
  <s:include value="../footer.jsp"></s:include>
 </div>

</body>
</html>
