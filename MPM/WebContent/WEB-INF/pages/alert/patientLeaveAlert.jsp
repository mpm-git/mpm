<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统-告警纪录－告警纪录－病人离开病区告警</title>

<link rel="stylesheet" type="text/css" href="<s:url value='/styles/h.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/index.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/smoothness/jquery-ui-1.8.16.custom.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/jquery_table_jui.css'/>"  >

<script type="text/javascript" src="<s:url value='/javascript/jquery-1.7.min.js'/>"></script>
<script src="<s:url value='/javascript/h.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery-ui-1.8.16.custom.min.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery.dataTables.js'/>" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">

$(function(){
	G.req(["gPopMenu"],function(j){j("#aMenu");});
	G.req(["gPopMenu"],function(j){j("#bMenu");});
	G.req(["gPopMenu"],function(j){j("#cMenu");});
	G.req(["gPopMenu"],function(j){j("#mMenu");});

	$( "#beginStatDate" ).datepicker();
	$( "#endStatDate" ).datepicker();
	$( "input:submit", ".wrap" ).button();
	$('#example').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers"
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
        <h2>· 您现在所在的位置：&nbsp;&nbsp;告警纪录&nbsp;&nbsp;→&nbsp;&nbsp;病人离开病区告警</h2>
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
      

      <div style="padding-top: 20px;">
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
          <thead>
            <tr>
              <th width="15%">病房</th>
              <th width="10%">病人</th>
              <th width="15%">编号</th>
              <th width="22%">离开时间</th>
              <th width="23%">回来时间</th>
              <th width="15%">操作</th>
            </tr>
          </thead>
          <tbody>
           <!-- struts读取数据代码 -->      
	           <s:iterator value="#request.LeaveAlert">
	               <% 
	                 if(request.getAttribute("endTime")==null) {
	                	long currentTime = System.currentTimeMillis()/1000;
	                	long startTime = Long.parseLong(request.getAttribute("startTime").toString());
	                    if((currentTime-startTime)>=30){%>
	               			<tr class="gradeA" style="background-color:#E3170D; color:#FFF;">
	                  <%}else{%>
	               			<tr class="gradeA">
	                  <%} %>
	               <%}else{
	            	   	long startTime = Long.parseLong(request.getAttribute("startTime").toString());
	                    long endTime = Long.parseLong(request.getAttribute("endTime").toString());
	                    if((endTime-startTime)>=30){%>
	                    	<tr class="gradeA" style="background-color:#E3170D; color:#FFF;">
	  	              <%}else{%>
	  	               		<tr class="gradeA">
	  	              <%}%>  
	  	          <%}%>	
	              
	              <% 
	               String roleType1 = request.getAttribute("wardName").toString();
	               if(roleType1.equals("w2")) {%> 
	               <td>202</td>
	              <%} %>
	              <% 
	               String roleType2 = request.getAttribute("wardName").toString();
	               if(roleType2.equals("w1")) {%> 
	               <td>201</td>
	              <%} %> 
	               <td><s:property value="name"/></td>  
	               <td><s:property value="patientNum"/></td>
	               <% java.sql.Date startDate = new java.sql.Date(Long.parseLong(request.getAttribute("startTime").toString())*1000);
	                  java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                  out.println("<td>"+sdf1.format(startDate)+"</td>"); 
	                  if(request.getAttribute("endTime")!=null){
	                  	  java.sql.Date endDate = new java.sql.Date(Long.parseLong(request.getAttribute("endTime").toString())*1000);
	                  	  java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                      out.println("<td>"+sdf2.format(endDate)+"</td>"); 
	                  }else{%>
	               			<td></td>
	                  <%} %>
	                      
	               <td>删除     编辑</td>
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
