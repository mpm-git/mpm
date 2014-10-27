<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统－系统设置-参数配置</title>

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
		var mintime = $('#mintime').val();
		var role = $('#role').val();
		var danwei = $('#danwei').val();
		var cishu = $('#cishu').val();
		var ip = $('#ip').val();
		var str = "";

		if (mintime == "") {
			str = "请输入查房最短时间,默认为5min";
		}
		if (role == "") {
			str = "请输入人员类型";
		}
		if (danwei == "") {
			str = "请输入单位";
		}
		if (cishu == "") {
			str = "请输入次数";
		}
		if (ip == "") {
			str = "请设置ip";
		}
		if (str != "") {
			alert(str);
			return;
		}
		alert("设置成功");
		
		
		
		document.setting.submit();

	}
</script>
</head>
<body>


	<div class="container">

		<s:include value="../header1.jsp"></s:include>


		<div class="wrap">
			<div class="wrap">
				<div class="side-title">
					<h2>· 您现在所在的位置：&nbsp;&nbsp;系统设置&nbsp;&nbsp;→&nbsp;&nbsp;参数设置</h2>
				</div>

				<ul class="titles">
					<li>
						<!-- <p class="titles-b"></p> -->
					</li>
				</ul>
				<form action="../system/setting.action" method="post"
					name="setting" id="setting">
					<div class="STYLE1" id="newrfid">
						参数配置
						<table bordercolor="#000000" border=0 cellpadding="0"
							cellspacing="0" style="margin-top: 40px; margin-left: 90px;">
							<tr>
								<td>医护人员查房最短时间：</td>
								<td><input type="text" id="mintime" name="mintime" />&nbsp;&nbsp;分钟</td>
							</tr>
							<tr>
								<td height="28"></td>
								<td>
									<area />
								</td>
							</tr>
							<tr>
								<td height="13">&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
									&nbsp;&nbsp;设置</td>
								<td>
									<div id="name"></div>
								</td>
							</tr>
							<tr>
								<td height="24">&nbsp;&nbsp;&nbsp;</td>
								<td><SELECT id=u328 class="u328" style="width: 100px;"
									name="role">
										<OPTION value="医生">医生</OPTION>
										<OPTION value="护士">护士</OPTION>
								</SELECT></td>
							</tr>
							<tr>
								<td height="34">&nbsp;&nbsp;&nbsp;</td>
								<td><SELECT id=u329 class="u329" style="width: 100px;"
									name="danwei">
										<OPTION value="0">每天</OPTION>
										<OPTION value="1">每周</OPTION>
								</SELECT></td>
							</tr>
							<tr>
								<td height="43">&nbsp;&nbsp;&nbsp;&nbsp;规定查房次数:</td>
								<td><INPUT id=u331 type=text value="" class="u331"
									name="cishu" style="width: 100px;">&nbsp;&nbsp;次</td>
							</tr>
								<td height="34">&nbsp;&nbsp;&nbsp;&nbsp;与临床路径接口IP:</td>
								<td><INPUT id=u331 type=text value="" class="u331"
									name="ip" style="width: 100px;"></td>
							<tr>
								
							</tr>
							<tr>
								<td height="28"></td>
								<td>
									<area />
								</td>
							</tr>
						</table>
						<div class="btnsave">
							<a href="javascript:alert1()"><img />保 存</a>
						</div>
				</form>
			</div>
		</div>

		<s:include value="../footer.jsp"></s:include>
	</div>
	</div>
</body>
</html>
