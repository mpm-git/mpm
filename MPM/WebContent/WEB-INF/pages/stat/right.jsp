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
var start;
var total;
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
	
	init_feature_table();
/* 	$('#right_example').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers"
	}); */
});

function init_feature_table(){
$('#right_example').dataTable({
			"bPaginate": true,//翻页功能
			"bSort": false,
			"bJQueryUI": true,
	        "sPaginationType": "full_numbers",
	        "bRetrieve": true,
	        "bDestroy":true,
	        "bStateSave": true,
	        "sAjaxSource" :"get_all_users_features.action?"+new Date().getTime(),
	         "aoColumnDefs": [
					{
					 sDefaultContent: '',
					 aTargets: [ '_all' ]
					  }
					], 
					/* "aaSorting": [[ 4, "desc" ]], */
	/* 	"aaData":[
					['陈云', '18880001', '管理员', '删除 修改', '这里专门用来写备注的哟'],
					['刘翠翠', '18880002', '普通员工', '删除 修改', '这里专门用来写备注的哟'],
					['王番', '18880003', '普通员工', '删除 修改', '这里专门用来写备注的哟'],
					['邹海鑫', '18880004', '普通员工', '删除 修改', '这里专门用来写备注的哟'],
					['邹海鑫2', '18880004', '普通员工', '删除 修改', '这里专门用来写备注的哟'],
					['邹海鑫3', '18880004', '普通员工', '删除 修改', '这里专门用来写备注的哟'],
					['邹海鑫4', '18880004', '普通员工', '删除 修改', '这里专门用来写备注的哟'],
					['邹海鑫5', '18880004', '普通员工', '删除 修改', '这里专门用来写备注的哟'],
					['邹海鑫6', '18880004', '普通员工', '删除 修改', '这里专门用来写备注的哟'],
					['邹海鑫7', '18880004', '普通员工', '删除 修改', '这里专门用来写备注的哟'],
					['邹海鑫8', '18880004', '普通员工', '删除 修改', '这里专门用来写备注的哟'],
					['邹海鑫9', '18880004', '普通员工', '删除 修改', '这里专门用来写备注的哟'],
					['邹海鑫12', '18880004', '普通员工', '删除 修改', '这里专门用来写备注的哟'],
		          ], */
		"aoColumns": [
		              { "aaData": "name" },
		              { "aaData": "staffNum" },
		              { "aaData": "userType" },
		              { "aaData": "operate","sClass": "center" },
		              { "aaData": "descr" },
		/* { "sTitle": "name","sClass": "center" },
		{ "sTitle": "staffNum","sClass": "center" },
		{ "sTitle": "userType","sClass": "center" },
		{ "sTitle": "operate","sClass": "center" },
		{ "sTitle": "descr","sClass": "center" } */
		],
		"oLanguage" : {
			"sProcessing" : "正在加载中......",
			"sLengthMenu" : "每页显示 _MENU_ 条记录",
			"sZeroRecords" : "没有数据！",
			"sEmptyTable" : "表中无数据存在！",
			"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
			"sInfoEmpty" : "显示0到0条记录",
			"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
			//"sSearch" : "搜索",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "末页"
			}
		}
		});
}

function delete_feature(id){
	$.ajax({    
        url : '../stat/delete_feature.action',    
        type : 'post',    
        data : {
        	'id': id
        	},    
        dataType : 'json',    
        success : function(data) {
        	/* console.info(data.message=='delete_success');
        	if(data.message=='delete_success'){
        		init_feature_table();
        	} */
        	if (data.message =='delete_success') 
        	{ 
	        	alert('删除成功!'); 
	        	start = $("#right_example").dataTable().fnSettings()._iDisplayStart; 
	        	total = $("#right_example").dataTable().fnSettings().fnRecordsDisplay(); 
	        	window.location.reload(); 
	        	if((total-start)==1){ 
		        	if (start > 0) { 
		        	$("#right_example").dataTable().fnPageChange( 'previous', true ); 
		        	} 
	        	} 
        	} 
        	else 
        	{ 
        		alert('删除发生错误，请联系管理员!'); 
        	} 
        	
        }
    });  
}

//跳转到修改页面
function redirectJsp(id){
	alert(1111);
	$.ajax({    
        url : '../stat/role-list.action',    
        type : 'post',    
        data : {
        	'id': id
        	},    
        dataType : 'json',    
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
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="right_example">
       <thead>
            <tr>
              <th width="20%">姓名</th>
              <th width="20%">工号</th>
              <th width="20%">用户类型</th>
              <th width="20%">操作</th>
              <th width="20%">备注</th>
            </tr>
          </thead>
 <!--           <tbody>
            <tr class="gradeA">
              <td>王晓霞</td>
              <td>0123</td>
              <td class="center">管理员</td>
              <td></td>
              <td></td>
            </tr>
            <tr class="gradeA">
               <td>王晓霞</td>
              <td>0123</td>
              <td class="center">管理员</td>
              <td></td>
              <td></td>
            </tr>
            <tr class="gradeA">
               <td>王晓霞</td>
              <td>0123</td>
              <td class="center">管理员</td>
              <td></td>
              <td></td>
            </tr>
            <tr class="gradeA">
               <td>王晓霞</td>
              <td>0123</td>
              <td class="center">普通用户</td>
              <td>删除   编辑</td>
              <td></td>
            </tr>
            <tr class="gradeA">
              <td>王晓霞</td>
              <td>0123</td>
              <td class="center">普通用户</td>
              <td>删除   编辑</td>
              <td></td>
            </tr>
            <tr class="gradeA">
              <td>王晓霞</td>
              <td>0123</td>
              <td class="center">普通用户</td>
              <td>删除   编辑</td>
              <td></td>
            </tr>
            <tr class="gradeA">
              <td>王晓霞</td>
              <td>0123</td>
              <td class="center">普通用户</td>
              <td>删除   编辑</td>
              <td></td>
            </tr> 
            </tbody>-->
          </table>
    </div>
  </div>
  
  <s:include value="../footer.jsp"></s:include>
 </div>

</body>
</html>
