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
		chart: { type: 'column' },
		title: {text: '护理等级与单向护理行为耗时分析'},
		xAxis: {
			title:{text:'护理等级'},
			categories: ['特级', '一级', '二级','三级', '四级']
		},
		yAxis: {
			min: 0,
			title: {
				text: '平均耗时(分)'
			}
		},
		tooltip: {
			pointFormat: '<b>{series.name}</b> 耗时 <b>{point.y:.1f}</b> 分钟',
			useHTML:true
		},
	    series: [{
			name: '巡床',
			data: [5, 3, 2.5, 3, 1]
	
		},{
			name: '晨间护理',
			data: [3, 2, 2, 1.5, 1.5]
	
		},{
			name: '床上擦浴',
		    data: [15, 10, 8, 6, 7]	
		},{
			name: '执行',
			data: [2, 1, 1, 1.5, 1]
	
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
        <h2>· 您现在所在的位置：&nbsp;&nbsp;决策支持&nbsp;&nbsp;→&nbsp;&nbsp;<a href="patientSingleMain.action">病人单向护理行为分析</a>→&nbsp;&nbsp;护理等级耗时分析</h2>
      </div>

      <div id="chartDiv" style="padding-top: 20px; width: 800px; height: 300px; margin: 0 auto; margin-bottom: 20px;">
      	
      </div>
      
    </div>
  </div>
  
  <s:include value="../footer.jsp"></s:include>
 </div>

</body>
</html>
