<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
   $(".menu_btn").click(function(){
	   window.location.href = $(this).attr("actionName") + ".action";
   });
});

</script>
<style type="text/css">
div.menu_btn{font-size: 15px;font-weight:bold; display:inline-block;text-align: center;margin: 35px;cursor:pointer;}
div.menu_btn span{font-family: "微软雅黑";}
</style>
</head>
<body>


<div class="container">

<s:include value="../header1.jsp"></s:include>

              
  <div class="wrap">
    <div class="wrap">
      <div class="side-title">
        <h2>· 您现在所在的位置：&nbsp;&nbsp;决策支持&nbsp;&nbsp;→&nbsp;&nbsp;病人单向护理行为分析1</h2>
      </div>
      <div style="text-align: center;">
	      <div class="menu_btn" actionName="nursingLevel">
	         <img src="<%=basePath%>styles/image/rr9.png" />
	         <span>护理等级耗时分析</span>
	      </div>
	      <div class="menu_btn" actionName="diagnosisResult">
	         <img src="<%=basePath%>styles/image/rr9.png" />
	         <span>诊断情况耗时分析</span>
	      </div>
	      <div class="menu_btn" actionName="ageStat">
	         <img src="<%=basePath%>styles/image/rr9.png" />
	         <span>年龄耗时分析</span>
	      </div>
	      <div class="menu_btn" actionName="timeModel">
	         <img src="<%=basePath%>styles/image/rr9.png" />
	         <span>耗时分析模型</span>
	      </div>
      </div>
    </div>
  </div>
  
  <s:include value="../footer.jsp"></s:include>
 </div>

</body>
</html>
