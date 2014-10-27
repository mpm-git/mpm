<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统－系统设置-修改密码</title>

<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/h.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/index.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/smoothness/jquery-ui-1.8.16.custom.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/jquery_table_jui.css'/>">

<script type="text/javascript"
	src="<s:url value='/javascript/jquery-1.7.min.js'/>"></script>
<script src="<s:url value='/javascript/h.js'/>" type="text/javascript"
	charset="utf-8"></script>
<script
	src="<s:url value='/javascript/jquery-ui-1.8.16.custom.min.js'/>"
	type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery.dataTables.js'/>"
	type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/highcharts.js'/>"
	type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		G.req(["gPopMenu"],function(j){j("#aMenu");});
		G.req(["gPopMenu"],function(j){j("#bMenu");});
		G.req(["gPopMenu"],function(j){j("#cMenu");});
		G.req(["gPopMenu"],function(j){j("#mMenu");});
		G.req(["gPopMenu"],function(j){j("#dMenu");});
		G.req(["gPopMenu"],function(j){j("#eMenu");});
		G.req(["gPopMenu"],function(j){j("#fMenu");});

		$("#beginStatDate").datepicker();
		$("#endStatDate").datepicker();
		$("input:submit", ".wrap").button();
		$('#example').dataTable({
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers"
		});

	});
</script>
<script type="text/javascript">
	function alert1() {
		var oldpswd = $('#oldpswd').val();
		var newpswd = $('#newpswd').val();
		var renewpswd = $('#renewpswd').val();
		var str = "";
	
		if (oldpswd == "") {
			str = "请输入旧密码";
		}
		if (newpswd == "") {
			str = "请输入新密码";
		}
		if (renewpswd == "") {
			str = "请输入确认密码";
		}
	
		if (str != "") {
			alert(str);
			return;
		}
		alert("添加成功！");
		document.changepswd.submit();
}
</script>
</head>
<body>


	<div class="container">

		<s:include value="../header1.jsp"></s:include>


		<div class="wrap">
			<div class="wrap">
				<div class="side-title">
					<h2>· 您现在所在的位置：&nbsp;&nbsp;系统设置&nbsp;&nbsp;→&nbsp;&nbsp;修改密码</h2>
				</div>

				<ul class="titles">
					<li>
						<!-- <p class="titles-b"></p> -->
					</li>
				</ul>
				<form action="../system/change-pswd.action" method="post"
					name="changepswd" id="changepswd">
					<div class="changepswd" id="newrfid">
						<div style="margin-left: 100px; margin-top: 25px;">修改登录密码</div>

						<table bordercolor="#000000" border=0 cellpadding="0"
							cellspacing="0" style="margin-top: 70px; margin-left: 40px;">
							<tr>
								<td>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;旧密码：
								</td>
								<td><input type="password" id="oldpswd" name="oldpswd"/></td>
							</tr>
							<tr>
								<td height="13"></td>
								<td>
									<area />
								</td>
							</tr>
							<tr>
								<td height="13">&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
									&nbsp;&nbsp;新密码：</td>
								<td><input type="password" id="newpswd" name="newpswd"/></td>
							</tr>
							<tr>
								<td height="13">&nbsp;&nbsp;&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td height="34">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认密码：</td>
								<td><input name="renewpswd" type="password" id="renewpswd"></td>
							</tr>
						</table>
						<div class="btnsave">
							<a href="javascript:alert1()"><img
								 />保   存</a>
						</div>
					</div>
				</form>
			</div>

			<s:include value="../footer.jsp"></s:include>
		</div>
</body>
</html>
