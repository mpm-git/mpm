<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无锡市第四人民医院医疗行为管理系统</title>
<style>
body{background: url(styles/image/bg1.gif) repeat-x;}
.login_panel{background-image: url(styles/image/formbg.png); width:458px; height:310px; margin:200px auto; overflow:hidden}
.login_ul{ list-style:none; margin:40px 20px;padding:0}
.login_ul li{margin:10px}
.login_ul li span{ display:inline-block; width:100px; text-align:right; font: normal 16px/38px '微软雅黑';padding:0 10px 0 0;color:#5ca6b1}
.text{background: url(styles/image/1.png)  no-repeat; width: 260px; color: rgb(174, 174, 174); font-family: '微软雅黑','宋体','黑体'; border:none; height: 31px;padding: 9px 12px;}
.heading{background-image: url(styles/image/heading.png); width:442px; height:67px;margin:10px 7px 7px }
</style>
<script type="text/javascript"
	src="<s:url value='/javascript/jquery-1.7.min.js'/>"></script>
<script type="text/javascript">
		$(function(){
			$("#account").focus();
		});
		function login() {
			var accountValue = $('#account').val();
			var passwordValue = $('#password').val();
			if (accountValue == "用户名" || accountValue == "") {
				alert("请输入用户名");
				return;
			}
			if (passwordValue == "密码" || accountValue == "") {
				alert("请输入密码");
				return;
			}

			$('#loginForm').submit();
		}	
</script>
<title>医疗行为管理系统</title>
</head>

<body>
<div class="login_panel">
	<p class="heading"></p>
    <ul class="login_ul">
    <form action="login.action" method="post" name="Login" id="loginForm">
    <li><span>用户名:</span><input type="text" class="text" maxlength="30" id="account"   name="account" /></li>
    <li><span>密码:</span><input type="password" class="text" maxlength="30" id="password"   name="password" /></li>
    <li><span></span><a href="javascript:login()"><img src="styles/image/btn.png"></a></li>
    </form>
    </ul>
</div>

</body>
</html>
