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
var id = <%=request.getAttribute("userId")%> ;
$(function(){
	G.req(["gPopMenu"],function(j){j("#aMenu");});
	G.req(["gPopMenu"],function(j){j("#bMenu");});
	G.req(["gPopMenu"],function(j){j("#cMenu");});
	G.req(["gPopMenu"],function(j){j("#mMenu");});
	G.req(["gPopMenu"],function(j){j("#dMenu");});
	G.req(["gPopMenu"],function(j){j("#eMenu");});
	G.req(["gPopMenu"],function(j){j("#fMenu");});

	$( "#beginStatDate" ).datepicker();
	$( "#endStatDate" ).datepicker();
	$( "input:submit", ".wrap" ).button();

	
	 
	 $.ajax({    
	     url : '../stat/get_user_by_id.action',    
	     type : 'post',    
	     data : {
	     	'id': id
	     	},    
	     dataType : 'json',
	     success:function(data){
	    	 var user=data.user;
	    	 $('#rightUpdate_name').html(user.name);
	    	 $('#rightUpdate_staffNum').html(user.staffNum);
	    	 $("#rightUpdate_select_role").val(user.userRole); 
	     }
 	});  
	 
	
/* 	$('#right_example').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers"
	}); */
});
	 function updataRole(){
		var userRole= $('#rightUpdate_select_role').val();
		 $.ajax({    
		     url : '../stat/update_user_role.action',    
		     type : 'post',    
		     data : {
		    	 'id':id,
		     	'userRole':userRole
		     	},    
		     dataType : 'json',
		     success:function(data){
		    	 if(data.message='success'){
		    		 alert('修改成功');
		    		 window.location="../stat/user-right-stat.action";
		    	 }else{ 
		    		 alert('修改失败');
		    	 }
		     }
	 	});  
	 }


</script>
</head>
<body>


<div class="container">

<s:include value="../header1.jsp"></s:include>

              
  <div class="wrap">
    <div class="wrap">
      <div class="side-title">
        <h2>· 您现在所在的位置：&nbsp;&nbsp;系统设置&nbsp;&nbsp;→&nbsp;&nbsp;用户权限管理</h2>
      </div>
      
      <ul class="titles">
        <li>
          <!-- <p class="titles-b"></p> -->
        </li>
      </ul>
      <div style="padding-top: 20px">
  <%--       <table bordercolor="#000000" border=0 cellpadding="0"
							cellspacing="0" style="margin-top: 100px; margin-left: 90px;">
				<tr>
					<td>名字:</td>
					<td id="rightUpdate_name"></td>
				</tr>
				<tr>
					<td>工号:</td>
					<td id="rightUpdate_staffNum"></td>
				</tr>
				<tr>
					<td>请选择角色:</td>
					<td>
						<select id="rightUpdate_select_role">
							<option value="管理员">管理员</option>   
							<option value="普通员工">普通员工</option>   
						</select>
					</td>
				</tr>
							
			</table> --%>
					<div class="STYLE1" id="newrfid">
						修改用户角色
						<div id="alert" align="left"
							style="color: #FF0000; margin-top: 80px; margin-left: 30px;"></div>
						<table bordercolor="#000000" border=0 cellpadding="0"
							cellspacing="0" style="margin-top: 100px; margin-left: 90px;">
							<tr>
								<td>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;名字：</td>
								<td id="rightUpdate_name"></td>
							</tr>
							<tr>
								<td height="19"></td>
								<td>
									<area />
								</td>
							</tr>
							<tr>
								<td height="13">&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
									&nbsp;&nbsp;工号：</td>
								<td id="rightUpdate_staffNum"></td>
							</tr>
							<tr>
								<td height="19"></td>
								<td>
									<area />
								</td>
							</tr>
							<tr>
								<td height="34">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									请选择用户类型：</td>
								<td><SELECT id="rightUpdate_select_role" class="u11">
										<OPTION value="管理员">管理员</OPTION>
										<OPTION value="普通用户">普通用户</OPTION>
								</SELECT></td>
							</tr>
						</table>
						<div class="btnsave">
							<a href="javascript:void(0)" onclick="updataRole()"><img
								 />修    改</a>
						</div>
					</div>
    </div>
  </div>
  
  <s:include value="../footer.jsp"></s:include>
 </div>

</body>
</html>
