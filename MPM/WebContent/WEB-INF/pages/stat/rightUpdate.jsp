<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统－系统设置-用户权限管理</title>
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
G.req(["gPopMenu"],function(j){j("#aMenu");});
G.req(["gPopMenu"],function(j){j("#bMenu");});
G.req(["gPopMenu"],function(j){j("#cMenu");});
G.req(["gPopMenu"],function(j){j("#mMenu");});
G.req(["gPopMenu"],function(j){j("#dMenu");});
G.req(["gPopMenu"],function(j){j("#eMenu");});
G.req(["gPopMenu"],function(j){j("#fMenu");});
</script>
<body>
<div class="container">
	<s:include value="../header1.jsp"></s:include>
		<div>
			<table bordercolor="#000000" border=0 cellpadding="0"
							cellspacing="0" style="margin-top: 100px; margin-left: 90px;">
				<tr>
					<td>名字:</td>
					<td></td>
				</tr>
				<tr>
					<td>工号:</td>
					<td></td>
				</tr>
				<tr>
					<td>请选择角色:</td>
					<td>
						<select id="rightUpdate_select_role">
							<option value="1">18-21</option>   
							<option value="2">22-25</option>   
						</select>
					</td>
				</tr>
							
			</table>
		</div>
	<s:include value="../footer.jsp"></s:include>
</body>
</html>