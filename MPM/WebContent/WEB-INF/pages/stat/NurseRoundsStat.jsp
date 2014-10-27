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
<script src="<s:url value='/javascript/jquery.dataTables.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/highcharts.js'/>" type="text/javascript"></script>

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
			text: '护士查房统计'
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
			name: '符维文',
			data: [4, 5, 5, 6, 7, 6, 4]
	
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
        <h2>· 您现在所在的位置：&nbsp;&nbsp;统计分析&nbsp;&nbsp;→&nbsp;&nbsp;护士查房统计</h2>
      </div>
      
      <ul class="titles">
        <li>
          <form>
            选择病区:
            <select name="some_name" id="some_name" style="width:90px;">
              <option value="option1">病区1</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            选择护士:
            <select name="some_name2" id="some_name" style="width:90px;">
              <option value="option1">张华</option>
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
              <th width="15%">工号</th>
              <th width="12%">姓名</th>
              <th width="13%">房间号</th>
              <th width="12%">床号</th>
              <th width="16%">事件</th>
              <th width="18%">巡床时间</th>
              <th width="14%">详细</th>
            </tr>
          </thead>
          <tbody>
            <!-- struts读取数据代码 -->      
	           <s:iterator value="#request.ActionReport">	           
	              <% 
	               String checkEvent = request.getAttribute("ACTIONEVENT_CODE").toString();
	               if(checkEvent.equals("200")) {%> 
	               <tr class="gradeA">  
	               <td><s:property value="USER_CODE"/></td>
	               <td><s:property value="USER_DESC"/></td>  
	               <% 
	               	   String roleType1 = request.getAttribute("ACTIONPLACE_CODE").toString();
	                   if(roleType1.equals("w2")) {%> 
	               <td>202</td>
	                  <%} %>
	               <% 
	                   String roleType2 = request.getAttribute("ACTIONPLACE_CODE").toString();
	                   if(roleType2.equals("w1")) {%> 
	               <td>201</td>
	                   <%} %>	                
	               <td><s:property value="CURBED"/></td>   	                
	               <td>巡床</td>  
	                <% java.sql.Date endDate = new java.sql.Date(Long.parseLong(request.getAttribute("ACTION_TIME").toString())*1000);
	                  java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                  out.println("<td>"+sdf2.format(endDate)+"</td>"); %>
	               <td class="center"><a href="javascript:detailClick();">详情</a></td>
	               <%} %>
	               </tr>
	           </s:iterator>
          </tbody>
        </table>
      </div>
      
      <div id="chartDiv" style="padding-top: 20px; width: 800px; height: 300px; margin: 0 auto; margin-bottom: 20px;">
      	
      </div>
      
      <div id="dialog" title="查房相信信息">
		<table border="1">
			<thead>
				<tr>
					<td>房间号</td>
					<td>开始查房时间</td>
					<td>结束查房时间</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>201</td>
					<td>2011-12-23 18:26:23</td>
					<td>2011-12-23 18:40:28</td>
				</tr>
				<tr>
					<td>201</td>
					<td>2011-12-24 6:26:23</td>
					<td>2011-12-23 6:40:28</td>
				</tr>
				<tr>
					<td>201</td>
					<td>2011-12-25 13:26:23</td>
					<td>2011-12-25 13:40:28</td>
				</tr>
				<tr>
					<td>201</td>
					<td>2011-12-26 14:26:23</td>
					<td>2011-12-26 14:40:28</td>
				</tr>
			
			</tbody>
		</table>
	  </div>
      
    </div>
  </div>
  
  <s:include value="../footer.jsp"></s:include>
 </div>

</body>
</html>
