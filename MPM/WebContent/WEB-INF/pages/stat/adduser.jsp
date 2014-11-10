<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统－系统设置－用户管理-添加用户</title>

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
		var cardNo = $('#gonghao').val();
		var password = $('#passwd').val();
		var utype = $('#utype').val();
		var str = "";

		if (cardNo == "") {
			str = "请输入工号";
		}
		if (password == "") {
			str = "请输入密码";
		}
		if (utype == "") {
			str = "请设置用户类型";
		}

		if (str != "") {
			alert(str);
			return;
		}
		alert("添加成功！");
		document.adduserForm.submit();
	}
</script>
</head>
<body>

	<div class="container">

		<s:include value="../header1.jsp"></s:include>


		<div class="wrap">
			<div class="wrap">
				<div class="side-title">
					<h2>· 您现在所在的位置：&nbsp;&nbsp;用户管理&nbsp;&nbsp;→&nbsp;&nbsp;添加用户</h2>
				</div>

				<ul class="titles">
				</ul>
				<form action="../system/add-user.action" method="post"
					name="adduserForm" id="adduserForm">
					<div class="STYLE1" id="newrfid">
						添加新用户
						<div id="alert" align="left"
							style="color: #FF0000; margin-top: 80px; margin-left: 30px;"></div>
						<table bordercolor="#000000" border=0 cellpadding="0"
							cellspacing="0" style="margin-top: 100px; margin-left: 90px;">
							<tr>
								<td>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;帐号：</td>
								<td><input type="text" id="account" name="account" /></td>
							</tr>
							<tr>
								<td height="19"></td>
								<td>
									<area />
								</td>
							</tr>
							<tr>
								<td height="13">&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
									&nbsp;&nbsp;密码：</td>
								<td><input type="password" id="passwd" name="passwd" /></td>
							</tr>
							<tr>
								<td height="19"></td>
								<td>
									<area />
								</td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;工号：</td>
								<td><input type="text" id="staffNum" name="staffNum" /></td>
							</tr>
							<tr>
								<td height="19"></td>
								<td>
									<area />
								</td>
							</tr>
							<tr>
								<td height="34">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									用户类型：</td>
								<td><SELECT id="utype" class="u11" name="utype">
										<OPTION value="管理员">管理员</OPTION>
										<OPTION value="普通用户">普通用户</OPTION>
								</SELECT></td>
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
	</div>
</body>
</html>
