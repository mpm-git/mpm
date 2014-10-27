<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统－卡片管理-开卡</title>

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
		var gonghao = $('#gonghao').val();
		var str = "";
	
		/* if (gonghao == "") {
			str = "请输入工号";
		}
		if (str != "") {
			alert(str);
			return;
		} */
		
		
		
		
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
					<h2>· 您现在所在的位置：&nbsp;&nbsp;卡片管理&nbsp;&nbsp;→&nbsp;&nbsp;开卡</h2>
				</div>

				<ul class="titles">
				</ul>
				<form action="../system/add-rfid.action" method="post"
					name="newrfid" id="newrfid">
					<div class="STYLE1" id="newrfid">
						医护人员开卡
						<table bordercolor="#000000" border=0 cellpadding="0"
							cellspacing="0" style="margin-top: 40px; margin-left: 90px;">
							<tr>
								<td>请输入&nbsp;&nbsp;&nbsp;&nbsp;工号：</td>
								<td><input type="text" id="gonghao" name="gonghao"/></td>
							</tr>
							<tr>
								<td height="28"></td>
								<td>
									<area />
								</td>
							</tr>
							<tr>
								<td height="33">&nbsp;&nbsp;&nbsp;&nbsp;姓名：</td>
								<td>
									<div id="name">李宇轩</div>
								</td>
							</tr>
							<tr>
								<td height="30">&nbsp;&nbsp;&nbsp;&nbsp;性别：</td>
								<td>
									<div id="sex">男</div>
								</td>
							</tr>
							<tr>
								<td height="34">&nbsp;&nbsp;&nbsp;&nbsp;科室：</td>
								<td>
									<div id="dept">内科</div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;职务：</td>
								<td>
									<div id="role">主治医师</div>
								</td>
							</tr>
							<tr>
								<td height="28"></td>
								<td>
									<area />
								</td>
							</tr>
							<tr>
								<td height="37">请刷卡：</td>
								<td>
									<area />
								</td>
							</tr>
							<tr>
								<td colspan="2"><area />
									<div id="rfid" align="center">E200-1066-6613-84F-2222-2222</div></td>
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
