<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统-告警纪录－告警纪录－护士离开病区告警</title>

<link rel="stylesheet" type="text/css" href="<s:url value='/styles/h.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/index.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/smoothness/jquery-ui-1.8.16.custom.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/jquery_table_jui.css'/>"  >

<script type="text/javascript" src="<s:url value='/javascript/jquery-1.7.min.js'/>"></script>
<script src="<s:url value='/javascript/h.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery-ui-1.8.16.custom.min.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery.dataTables.js'/>" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">

$(function(){
	G.req(["gPopMenu"],function(j){j("#aMenu");});
	G.req(["gPopMenu"],function(j){j("#bMenu");});
	G.req(["gPopMenu"],function(j){j("#cMenu");});
	G.req(["gPopMenu"],function(j){j("#mMenu");});

	$( "#beginStatDate" ).datepicker();
	$( "#endStatDate" ).datepicker();
	$( "input:submit", ".wrap" ).button();
	$('#example').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers"
	});
	
});

</script>

</head>
<body>


<div class="container">

<s:include value="../header1.jsp"></s:include>

              
  <div class="wrap">
    <div class="wrap">
      <div class="side-title">
        <h2>· 您现在所在的位置：&nbsp;&nbsp;告警纪录&nbsp;&nbsp;→&nbsp;&nbsp;护士离开病区告警</h2>
      </div>
      
      <ul class="titles">
        <li>
          <form>
            选择病区:
            <select name="some_name" id="some_name" style="width:90px;">
              <option value="option1">病区1</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            起始时间:
            <input type="text" id="beginStatDate" name="beginStatDate" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            截止时间:
            <input type="text" id="endStatDate" name="endStatDate" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" value="查询"/>
          </form>
          <p class="titles-b"></p>
        </li>
      </ul>
      

      <div style="padding-top: 20px;">
        <table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
          <thead>
            <tr>
              <th width="20%">病区</th>
              <th width="20%">护士</th>
              <th width="20%">离开时间</th>
              <th width="20%">回来时间</th>
              <th width="20%">总计</th>
            </tr>
          </thead>
          <tbody>
            <tr class="gradeA">
              <td>病区1</td>
              <td>王小霞</td>
              <td>2011-12-25 13:26:23</td>
              <td>2011-12-25 14:26:23</td>
              <td class="center">1小时</td>
            </tr>
            <tr class="gradeA">
              <td>病区1</td>
              <td>王小霞</td>
              <td>2011-12-25 15:03:43</td>
              <td>2011-12-25 14:23:43</td>
              <td class="center">1小时20分</td>
            </tr>
            <tr class="gradeA">
              <td>病区1</td>
              <td>夏雪</td>
              <td>2011-12-25 13:26:23</td>
              <td>2011-12-25 14:26:23</td>
              <td class="center">1小时</td>
            </tr>
            <tr class="gradeA">
              <td>病区1</td>
              <td>夏雪</td>
              <td>2011-12-25 15:03:43</td>
              <td>2011-12-25 14:23:43</td>
              <td class="center">1小时20分</td>
            </tr>
            <tr class="gradeA">
              <td>病区1</td>
              <td>林丽</td>
              <td>2011-12-25 13:26:23</td>
              <td>2011-12-25 14:26:23</td>
              <td class="center">1小时</td>
            </tr>
            <tr class="gradeA">
              <td>病区1</td>
              <td>林丽</td>
              <td>2011-12-25 15:03:43</td>
              <td>2011-12-25 14:23:43</td>
              <td class="center">1小时20分</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  
  <s:include value="../footer.jsp"></s:include>
 </div>

</body>
</html>
