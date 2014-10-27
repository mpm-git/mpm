<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统－卡片管理－病人卡片</title>

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
        <h2>· 您现在所在的位置：&nbsp;&nbsp;卡片管理&nbsp;&nbsp;→&nbsp;&nbsp;病人卡片</h2>
      </div>
      
      <ul class="titles">
        <li>
          <form>

            <select name="some_name" id="some_name" style="width:90px;">
              <option value="option1">选择病区</option>
              <option value="option1">area-1</option>
	          <option value="option1">area-2</option>
              <option value="option1">area-3</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            
            <select name="some_name2" id="some_name" style="width:90px;">
              <option value="option1">选择病房</option>
              <option value="option1">201</option>
              <option value="option1">202</option>
              <option value="option1">203</option>
              <option value="option1">204</option>
              <option value="option1">205</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" value="查询"/>
          
          </form>
          <form action="../stat/new-card-stat.action" method="get">
          	<input id="newrfid" type="submit" class="u659" value="开卡"  >
          </form>
          
          <p class="titles-b"></p>
        </li>
      </ul>
      <div style="padding-top: 20px">
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
          <thead>
            <tr>
              <th width="15%">ID</th>
              <th width="15%">工号</th>
              <th width="15%">姓名</th>
              <th width="15%">角色</th>
              <th width="30%">RFID</th>
              <th width="10%">操作</th>
            </tr>
          </thead>
           <tbody>
            <!-- struts读取数据代码 -->      
	           <s:iterator value="#request.CardManager"> 
	               <tr class="gradeA">  
	               <td><s:property value="id"/></td>  
	               <td><s:property value="empNO"/></td>  
	               <td><s:property value="name"/></td> 
	               <% 
	               String roleType = request.getAttribute("role").toString();
	               if(roleType.equals("0")) {%> 
	               <td>医生</td>
	               <%} %>
	               <% if(roleType.equals("1")) {%>
	               <td>护士</td>
	               <%} %>
	               <% if(roleType.equals("2")) {%>
	               <td>病人</td>
	               <%} %>
	               <td><s:property value="tagID"/></td>
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
