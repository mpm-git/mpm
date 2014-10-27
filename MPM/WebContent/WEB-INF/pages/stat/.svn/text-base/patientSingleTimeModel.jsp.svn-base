<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统－统计分析－护士查房统计</title>

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

});


$(document).ready(function() {
	$('#chartDiv').highcharts({
		credits: {enabled: false},
		chart: { type: 'column' },
		title: {text: '单向护理行为耗时分析'},
		xAxis: {
			title:{text:'单向护理行为'},
			categories: ['巡床', '晨间护理', '床上擦浴', '执行']
		},
		yAxis: {
			min: 0,
			title: {
				text: '耗时分析(分)'
			}
		},
		legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle', borderWidth: 0 },
		tooltip: {
			pointFormat: '耗时 <b>{point.y:.1f}</b> 分钟',
			useHTML:true
		},
	    series: [{
	    	name: '单向护理行为',
			data: [4,3,10,2]
		}]
	});
	
	$("#analyseBtn").click(function(){
		$("#resultDiv").show();
	});
});

</script>
<style type="text/css">
div#info{text-align: center;width: 100%;}
#datatable{width: 350px;}
#datatable thead tr th{font-weight: bold;}
#datatable tr td,th{font-weight: normal;padding:10px;text-align: center}
#datatable tbody tr:nth-child(2n+1) td, #datatable tbody tr:nth-child(2n+1) th{background-color:#F9F9F9}
#datatable th,td {
    border: 1px solid #DDDDDD;
}
.titles{display:inline-block;}
.titles li{height:28px;font-size: 18px;padding: 5px;border: none;}
.big_btn{
    font-size: 24px;
    font-weight: bold;
    height: 68px;
    margin: 5px 0 5px 100px;
    vertical-align: top;
    width: 87px;
}
</style>
</head>
<body>


<div class="container">

<s:include value="../header1.jsp"></s:include>

              
  <div class="wrap">
    <div class="wrap">
      <div class="side-title">
        <h2>· 您现在所在的位置：&nbsp;&nbsp;决策支持&nbsp;&nbsp;→&nbsp;&nbsp;<a href="patientSingleMain.action">病人单向护理行为分析</a>→&nbsp;&nbsp;耗时分析模型</h2>
      </div>
      
      <form>
	      <ul class="titles">
	        <li>
	                                     护理等级：<select name="nursing_level" id="nursing_level" style="width:90px;margin-right: 100px;">
	              <option value="0">特级</option>
	              <option value="1">一级</option>
	              <option value="2">二级</option>
	              <option value="3">三级</option>
	              <option value="4">四级</option>
	            </select>
	                                      年龄：<input type="text" id="age" name="age"/>
	        </li>
	        <li>
	                                      主要诊断：<input type="text" id="diagnosis" name="diagnosis" style="width: 600px;"/>
	        </li>
	      </ul>
          <input type="button" value="分析" id="analyseBtn" class="big_btn"/>
     </form>
     
     <div style="border-bottom: 1px dashed #CCCCCC"></div>
      
      <div id="resultDiv" style="display: none;">
	      <div id="chartDiv" style="padding-top: 20px; width: 800px; height: 300px; margin: 0 auto; margin-bottom: 20px;">
	      	
	      </div>
	      
	       <div id="info">
			    <table id="datatable" rules="all" frame="box" align="center"> 
			       <thead>
			           <tr> 
			             <th>单向护理行为</th> 
			             <th>耗时(分)</th> 
			           </tr>
			       </thead>
			       <tbody> 
				       <tr>
					       <th width="60%">巡床</th> 
					       <td width="40%">4</td> 
				       </tr> 
				       <tr>
					        <th>晨间护理</th> 
					        <td>3</td> 
				       </tr> 
				       <tr>
					        <th>床上擦浴</th>
					        <td>10</td>
		               </tr>
		               <tr>
					        <th>执行</th>
					        <td>2</td>
		               </tr>
	               </tbody> 
	             </table>
		  </div>
	  </div>
    </div>
  </div>
  
  <s:include value="../footer.jsp"></s:include>
 </div>

</body>
</html>
