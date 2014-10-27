<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript"
	src="<s:url value='/javascript/jquery-1.7.min.js'/>"></script>
<title>Insert title here</title>

<script>



function btnClick(state){
	document.getElementById("state").value = state;
}

function roomClick(roomno){
	document.getElementById("roomno").value = roomno;
	var state = document.getElementById("state").value;
	if(state != 0){
		$('#testForm').submit(); 
	}
}

</script>

</head>
<body>

<form action="test.action" method="post" name="testForm" id="testForm">



张华 
护士	
<input value="屋内" type="button" onclick="javascript:btnClick(11)"/>&nbsp;&nbsp;
<input value="屋外" type="button" onclick="javascript:btnClick(12)"/>&nbsp;&nbsp;
<input value="消失" type="button" onclick="javascript:btnClick(13)"/>&nbsp;
<input value="查房" type="button" onclick="javascript:btnClick(14)"/>

<br><br><br>

张艳艳 医生
<input value="屋内" type="button" onclick="javascript:btnClick(21)"/>&nbsp;&nbsp;
<input value="屋外" type="button" onclick="javascript:btnClick(22)"/>&nbsp;&nbsp;
<input value="消失" type="button" onclick="javascript:btnClick(23)"/>&nbsp;
<input value="查房" type="button" onclick="javascript:btnClick(24)"/>

<br><br><br>

李闯 病人
<input value="屋内" type="button" onclick="javascript:btnClick(31)"/>&nbsp;&nbsp;
<input value="屋外" type="button" onclick="javascript:btnClick(32)"/>&nbsp;&nbsp;
<input value="消失" type="button" onclick="javascript:btnClick(33)"/>

<br><br><br>


<input value="201" type="button" name="room" onclick="javascript:roomClick(1)"/> 
&nbsp;&nbsp;
<input value="202" type="button" name="room" onclick="javascript:roomClick(2)"/>
<br>
<input type="text" id="state" name="state" value="0"/>
<input type="text" id="roomno" name="roomno"/>

</form>

</body>
</html>