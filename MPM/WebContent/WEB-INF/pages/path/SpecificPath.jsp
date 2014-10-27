<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.text.* , java.sql.* " %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统－轨迹管理－特定轨迹管理</title>

<link rel="stylesheet" type="text/css" href="<s:url value='/styles/h.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/index.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/smoothness/jquery-ui-1.8.16.custom.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/jquery_table_jui.css'/>"  >

<script type="text/javascript" src="<s:url value='/javascript/jquery-1.7.min.js'/>"></script>
<script src="<s:url value='/javascript/h.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery-ui-1.8.16.custom.min.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery-ui-timepicker-addon.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery.dataTables.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/highcharts.js'/>" type="text/javascript"></script>

<script src="<s:url value='/javascript/jquery-ui-timepicker-zh-CN.js'/>" type="text/javascript" ></script>

<script type="text/javascript">
$(function(event){
	
});


$(function(){
	G.req(["gPopMenu"],function(j){j("#aMenu");});
	G.req(["gPopMenu"],function(j){j("#bMenu");});
	G.req(["gPopMenu"],function(j){j("#cMenu");});
	G.req(["gPopMenu"],function(j){j("#mMenu");});
	G.req(["gPopMenu"],function(j){j("#dMenu");});
	G.req(["gPopMenu"],function(j){j("#eMenu");});
	G.req(["gPopMenu"],function(j){j("#fMenu");});

	$( "#beginStatDate" ).datetimepicker({dateFormat:'yy-mm-dd'});
	$( "#endStatDate" ).datetimepicker({dateFormat:'yy-mm-dd'});
	$( "input:submit", ".wrap" ).button();
	$('#example').dataTable({
		"bJQueryUI": true,
		"sPaginationType": "full_numbers"
	});
	
	$( "#dialog" ).dialog({ 
		autoOpen: false, 
		width: 'auto'
	});
	
});

/****************************************************/
//此处是做图表操作
/****************************************************/

function detailClick(){
	$( "#dialog" ).dialog("open");
}




function showMess(x, y, time, user){

	if($('#tooltip')){
		$('#tooltip').remove();
	}
    
	var content = "";
    content = user+" "+time+" 巡查过该病房";
      
    var tooltip = "<div id='tooltip'><p style='text-align:center;font-size:15px;'>"+content+"</p>";		            
    $('body').append(tooltip);
    $('#tooltip').css({"opacity":"0.8","top":(y+35)+"px","left":(x+10)+"px"}).show('fast');		

    setTimeout(function(){
   	 $('#tooltip').remove();                     
   	},5000); 
} 


function getEventPosition(ev){

	var x, y;

	if (ev.layerX || ev.layerX == 0) {

	x = ev.layerX;

	y = ev.layerY;

	} else if (ev.offsetX || ev.offsetX == 0) { // Opera

	x = ev.offsetX;

	y = ev.offsetY;

	}

	return {x: x, y: y};

}



function drawPath(id){

	//定义canvas
	var canvas = document.getElementById(id);
	   if (canvas == null)
	       return false;
   var context = canvas.getContext("2d");

   //表示该医生巡查过该病房
   var image = new Image();
   image.src = '<s:url value="/styles/images/roleIcon/doctor.png"/>';

   //画出轨迹线路
   context.lineWidth = "5";
   context.strokeStyle = "rgb(250,0,0)";
   context.fillStyle = "rgb(250,0,0)"
   var count = 0;
   

	$("#path_t tr").each(function(){
		var place = $(this).attr("place");
		var x = $(this).attr("x");
		var y = $(this).attr("y");
		if(y == '380'){
		  drawLine_down(context, x, y);
		  count ++;
		}
	})
	if(count > 0){
		drawLine_corner(context);
	}
	
   $("#path_t tr").each(function(){
		var place = $(this).attr("place");
		var x = $(this).attr("x");
		var y = $(this).attr("y");
		if(y == '110'){
			drawLine_up(context,x,y);
		}
	})
	
    	context.stroke();
   
   //画医生位置
   image.onload = function () {
	   $("#path_t tr").each(function(){
			var place = $(this).attr("place");
			var x = $(this).attr("x");
			var y = $(this).attr("y");
		    context.drawImage(image,x,y,30,30);
		})
   }


	//给canvas增加事件
   canvas.addEventListener('click', function(e){

       p = getEventPosition(e);

		//根据x,y坐标位置，判断在哪个病房
	 $("#path_t tr").each(function(){
		var x = $(this).attr("x");
		var y = $(this).attr("y");
		var time = $(this).attr("time");
		var user = $(this).attr("user");
		var place = $(this).attr("place");

		var px = p.x;
		var py = p.y;

		///alert(px < parseInt(x)+20);
		if( parseInt(px)> parseInt(x)-20 ){
			if( parseInt(px) < parseInt(x)+20 ){
				if( parseInt(py) > parseInt(y)-20  ){
					if( parseInt(py) < parseInt(y)+20 ){
						  // alert(p.x + " " + p.y);
						  // alert(x + " "+y);
		       			showMess(p.x, p.y, time, user);
					}
				}
			}
		}
	    
	})
	
		
       if(canvas.isPointInPath(p.x, p.y)){

       //点击了矩形

       }

       }, false);
	
}

//下面的病房画线
function drawLine_down(context, x, y){

	context.lineTo(x,y-50);
	context.lineTo(x,y);
	context.lineTo(x,y-50);
	
}

//上面的病房画线
function drawLine_up(context,x,y){


	context.lineTo(parseInt(x)+30,parseInt(y)+80);
	context.lineTo(parseInt(x)+30,parseInt(y)+30);
	context.lineTo(parseInt(x)+30,parseInt(y)+30);
	context.lineTo(parseInt(x)+30,parseInt(y)+80);
	
}

//拐角
function drawLine_corner(context){

	context.lineTo(750, 330);
	context.lineTo(750, 190);
	
}






window.onload = function () {

	//draw8("canvas8");
	drawPath("canvas8");

}


</script>
<style type="text/css">
    body div#tooltip {position:absolute;z-index:1000;width: auto;max-width:500px;background:#EDF5FA none repeat scroll 0% 50%;border:3px solid #CEF3FF;text-align:left;padding:5px;min-height:1em;-moz-border-radius:5px;font-family:Verdana,Arial,Helvetica,Georgia,Sans-Serif;}
	body div#tooltip p { margin:0;padding:0;color:#027AC6;font:12px Verdana,Arial,sans-serif; }
	body div#tooltip p em { display:block;margin-top:3px;color:#f60;font-style:normal;font-weight:bold; }
	body div#tooltip p em span { font-weight:bold;color:#669900; } 
	dl.window dt{padding-top:5px;}
	dl.window{height:71px;}
	
</style>

</head>
<body>


<div class="container">
<s:include value="../header1.jsp"></s:include>

              
  <div class="wrap">
    <div class="wrap">
      <div class="side-title">
        <h2>· 您现在所在的位置：&nbsp;&nbsp;轨迹分析&nbsp;&nbsp;→&nbsp;&nbsp;特定轨迹管理</h2>
      </div>
      
      <ul class="titles">
        <li>
          <form method="post">
            选择病区:
            <select name="area" id="area" style="width:90px;">
              <option value="option1">病区1</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            选择医生:
            <select name="doctor" id="doctor" style="width:90px;">
              <option value="3027">杨杰</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            起始时间:
            <input type="text" id="beginStatDate" name="beginStatDate" value="${startTimeStr}" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            截止时间:
            <input type="text" id="endStatDate" name="endStatDate" value="${endTimeStr}" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" value="查询"/>
          </form>
          <p class="titles-b"></p>
        </li>
      </ul>

      
      <div class="main" style="border:none">
       <div id="people" class="focus">
			 <!-- <header><h1>画线test(lineTo moveTo)</h1></header> --><canvas id="canvas8" width="820" height="540"></canvas>
		</div>
		
		<!-- 隐藏 -->
	<table id="path_t" style="display: none">
      <s:iterator value="#request.allPath" var="path" >
         <tr time="<s:property value="timedesc"/>" x="<s:property value="x"/>" y="<s:property value="y"/>" place="<s:property value="ACTIONPLACE_CODE"/>" user="<s:property value="USER_DESC"/>">
         <td ><s:property value="timedesc"/></td>
         <td ><s:property value="x"/></td>
         <td ><s:property value="y"/></td>
         <td ><s:property value="ACTIONPLACE_CODE"/></td>
         </tr>
      </s:iterator>
      </table>
		
		
      
     </div>
      
      <div id="chartDiv" style="padding-top: 20px; width: 800px; height: 300px; margin: 0 auto; margin-bottom: 20px;">
      	
      </div>
     
      <!-- 
      <div id="dialog" title="查房详细信息">
		<table border="1">
			<thead>
				<tr>
					<td>房间号</td>
					<td>开始查房时间</td>
					<td>结束查房时间</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>201</td>
					<td>2011-12-23 18:26:23</td>
					<td>2011-12-23 18:40:28</td>
				</tr>
				<tr>
					<td>201</td>
					<td>2011-12-24 6:26:23</td>
					<td>2011-12-23 6:40:28</td>
				</tr>
				<tr>
					<td>201</td>
					<td>2011-12-25 13:26:23</td>
					<td>2011-12-25 13:40:28</td>
				</tr>
				<tr>
					<td>201</td>
					<td>2011-12-26 14:26:23</td>
					<td>2011-12-26 14:40:28</td>
				</tr>
			
			</tbody>
		</table>
	  </div> -->
      
    </div>
  </div>
  
  <s:include value="../footer.jsp"></s:include>
 </div>

</body>
</html>
