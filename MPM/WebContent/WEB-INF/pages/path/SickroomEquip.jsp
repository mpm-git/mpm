<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统－轨迹管理－病房设备联动</title>

<link rel="stylesheet" type="text/css" href="<s:url value='/styles/h.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/index.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/smoothness/jquery-ui-1.8.16.custom.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/jquery_table_jui.css'/>"  >

<script type="text/javascript" src="<s:url value='/javascript/jquery-1.7.min.js'/>"></script>
<script src="<s:url value='/javascript/h.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery-ui-1.8.16.custom.min.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery.dataTables.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/highcharts.js'/>" type="text/javascript"></script>

<script type="text/javascript">

$(function(){
	G.req(["gPopMenu"],function(j){j("#aMenu");});
	G.req(["gPopMenu"],function(j){j("#bMenu");});
	G.req(["gPopMenu"],function(j){j("#cMenu");});
	G.req(["gPopMenu"],function(j){j("#mMenu");});
	G.req(["gPopMenu"],function(j){j("#dMenu");});
	G.req(["gPopMenu"],function(j){j("#fMenu");});

	$( "#beginStatDate" ).datepicker();
	$( "#endStatDate" ).datepicker();
	$( "input:submit", ".wrap" ).button();
	$('#example').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers"
	});
	
	$( "#dialog" ).dialog({ 
		autoOpen: false, 
		width: 'auto'
	});
	
});


var chart;
$(document).ready(function() {
	chart = new Highcharts.Chart({
		chart: {
			renderTo: 'chartDiv',
			defaultSeriesType: 'column'
		},
		title: {
			text: '医生查房统计'
		},
		subtitle: {
			text: ''
		},
		xAxis: {
			categories: [
				'201', 
				'202', 
				'203', 
				'204', 
				'205', 
				'206', 
				'207'
			]
		},
		yAxis: {
			min: 0,
			title: {
				text: '次数'
			}
		},
		legend: {
			layout: 'vertical',
			backgroundColor: '#FFFFFF',
			align: 'left',
			verticalAlign: 'top',
			x: 100,
			y: 70,
			floating: true,
			shadow: true
		},
		tooltip: {
			formatter: function() {
				return ''+
					this.x +' 查房 '+ this.y +' 次';
			}
		},
		plotOptions: {
			column: {
				pointPadding: 0.2,
				borderWidth: 0
			}
		},
	        series: [{
			name: '杨杰',
			data: [3, 6, 2, 6, 5, 6, 4]
	
		}]
	});
	
	
});


function detailClick(){
	$( "#dialog" ).dialog("open");
}

</script>

</head>
<body>


<div class="container">

<s:include value="../header1.jsp"></s:include>

              
  <div class="wrap">
    <div class="wrap">
      <div class="side-title">
        <h2>· 您现在所在的位置：&nbsp;&nbsp;轨迹管理&nbsp;&nbsp;→&nbsp;&nbsp;病房设备联动</h2>
      </div>
      
      <ul class="titles">
        <li>
          <form>
            选择病区:
            <select name="some_name" id="some_name" style="width:90px;">
              <option value="option1">病区1</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            起始时间:
            <input type="text" id="beginStatDate" name="beginStatDate" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            截止时间:
            <input type="text" id="endStatDate" name="endStatDate" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" value="查询"/>
          </form>
          <p class="titles-b"></p>
        </li>
      </ul>

      <div style="padding-top: 20px">
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
          <thead>
            <tr>
              <th width="15%">编号</th>
              <th width="12%">时间</th>
              <th width="13%">设备ID</th>
              <th width="12%">驱动消息</th>
              <!-- 
              <th width="16%">事件</th>
              <th width="18%">查房时间</th>
              <th width="14%">详细</th>
               -->
            </tr>
          </thead>
          <tbody>
			<s:iterator value="#request.equipList">
			<tr>
				<td><s:property value="no"/></td>
				<td><s:property value="time"/></td>
				<td><s:property value="equipNo"/></td>
				<td><s:property value="message"/></td>
			</tr>
			</s:iterator>
          </tbody>
        </table>
      </div>
      
      
      
    </div>
  </div>
  
  <s:include value="../footer.jsp"></s:include>
 </div>

</body>
</html>
