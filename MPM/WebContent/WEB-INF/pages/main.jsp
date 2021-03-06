<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link rel="stylesheet" type="text/css" href="<s:url value='/styles/h.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/index.css'/>" />
<link rel="stylesheet" type="text/css" href="<s:url value='/styles/smoothness/jquery-ui-1.8.16.custom.css'/>" />


<script type="text/javascript" src="<s:url value='/javascript/jquery-1.7.min.js'/>"></script>
<script src="<s:url value='/javascript/h.js'/>" type="text/javascript" charset="utf-8"></script>
<script src="<s:url value='/javascript/jquery-ui-1.8.16.custom.min.js'/>" type="text/javascript" charset="utf-8"></script>




<script type="text/javascript">
		//存放当前页面上显示的div
		var currentDynamicDivs = new Array();
		
		
		
		function removeDiv(rfid){ 
			var obj = document.getElementById(rfid);
			document.getElementById("people").removeChild(obj);
		}
		
		function createDiv(rfid, role, name, left, top)
		{	
			var tempDiv = "<div id='" + rfid + 
				"' style='position: absolute; left: " + left +
				"px; top: " + top +
				"px;font-size:12;width:40px;height:30px;'>";
			
			<%--if (role == '0'){
				tempDiv += "<img src='<s:url value='/styles/images/roleIcon/doctor.png'/>'/>" + name;
			}else if (role == '1'){
				tempDiv += "<img src='<s:url value='/styles/images/roleIcon/nurse.png'/>' />" + name;
			}else if (role == '2'){
				tempDiv += "<img src='<s:url value='/styles/images/roleIcon/patient.png'/>' />" + name;
			}--%>
				
			tempDiv += "</div>";
			$("#people").append(tempDiv); 
		}
		
		
		function queryPosition(){
			$.ajax({
				  type:"get",
				  url: "./area-monitor.action" + "?x=" + new Date().getTime(),
				  cache: false,
				  success: function(responseText){
						var str = responseText;
						//删除currentDynamicDivs中没有被处理的div，也就是后台不能获取该rfid的位置
				 		for (var j=0; j<currentDynamicDivs.length; j++){
				 			removeDiv(currentDynamicDivs[j]);
				 		}
				 		currentDynamicDivs.length = 0;
				 		
				 		var array = str.split("&");
				 		
				 		//$("#test").html(array.length);
				 		//return;
						var strs = new Array(); 
						strs = array[0].split(","); //字符分割  
						var size = parseInt(strs[0]);
						for (var i = 0; i < size; ++i ){  
							
							var rfid = strs[ i * 5 + 1 ];
							var role = strs[ i * 5 + 2 ];
							var name = strs[ i * 5 + 3 ];
							var targetLeft = parseFloat( strs[ i * 5 + 4 ] );
							var targetTop = parseFloat( strs[ i * 5 + 5 ] );
							
							//$("#test").append(name +"," + targetLeft+","+targetTop + "    ");
							
					 		//创建div
					 		createDiv(rfid, role, name, targetLeft, targetTop);
					 		
					 		currentDynamicDivs.push(rfid);
						} 
						
						strs = array[1].split(","); //字符分割   
						var size = parseInt(strs[0]);
						var resl = "<ul class='titles'><li><h3>姓名</h3><h3>角色</h3><h3>当天次数</h3><p class='titles-b'></p></li></ul>";
						
						for (var i = 0; i < size; ++i ){  
							
							var name = strs[ i * 2 + 1 ];
							var numreal = strs[ i * 2 + 2 ];
							
							resl += "<ul class='titles'><li><h3>" + name +"</h3><h3><img src='<s:url value='/styles/image/nurse.png'/>'/></h3><h3>" + numreal+"</h3><p class='titles-b'></p></li></ul>";
						} 
						
						$("#tabs-1").html(resl);
						
						
						strs = array[2].split(","); //字符分割   
						var size = parseInt(strs[0]);
						var resl = "<ul class='titles'><li><h3>姓名</h3><h3>角色</h3><h3>本周次数</h3><p class='titles-b'></p></li></ul>";
						
						for (var i = 0; i < size; ++i ){  
							
							var name = strs[ i * 2 + 1 ];
							var numreal = strs[ i * 2 + 2 ];
							
							resl += "<ul class='titles'><li><h3>" + name +"</h3><h3><img src='<s:url value='/styles/image/doctor.png'/>'/></h3><h3>" + numreal+"</h3><p class='titles-b'></p></li></ul>";
						} 
						
						$("#tabs-2").html(resl);
				  }
			});
		}
		
		
		
		
		$(function(){
			///G.req=function(c,i){c=G.isArray(c)?c:c.split(" ")}
			G.req(["gPopMenu"],function(j){j("#aMenu");});
			G.req(["gPopMenu"],function(j){j("#bMenu");});
			G.req(["gPopMenu"],function(j){j("#cMenu");});
			G.req(["gPopMenu"],function(j){j("#dMenu");});
			G.req(["gPopMenu"],function(j){j("#eMenu");});
			G.req(["gPopMenu"],function(j){j("#fMenu");});
			G.req(["gPopMenu"],function(j){j("#mMenu");});
			$( "#tabs" ).tabs({
				event: "mouseover",
			});
			
			queryPosition();
			///setInterval("queryPosition()",1000);

			var event;
			/**
			$("body").mouseover(function(event){
				var text = event.pageX + "," + event.pageY;
				$("#XY").val(text);
			});
			*/

	});
	


    function showMess(x, y){
         
    	 var content = "";
	     content = "医生A 9点半 巡查过某病房";
	       
	     var tooltip = "<div id='tooltip'><p style='text-align:center;font-size:15px;'>"+content+"</p>";		            
	     $('body').append(tooltip);
	     $('#tooltip').css({"opacity":"0.8","top":(y+20)+"px","left":(x+10)+"px"}).show('fast');		

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
		
	function draw8(id) {
	    var canvas = document.getElementById(id);
	    if (canvas == null)
	        return false;
	    var context = canvas.getContext("2d");
	    //画出轨迹线路
	    context.lineWidth = "5";
	    context.strokeStyle = "rgb(250,0,0)";
	    context.fillStyle = "rgb(250,0,0)"
	    //病房16
	    context.lineTo(400+30, 110+30);
	    //病房15
	    context.lineTo(400+30, 110+50+30);
	    context.lineTo(470+30, 110+50+30);
	    context.lineTo(470+30, 110+30);
	    //病房14
	    context.lineTo(470+30, 110+50+30);
	    context.lineTo(470+70+30, 110+50+30);
	    context.lineTo(470+70+30, 110+30);

	    context.lineTo(400+70*2+30, 110+50+30);
	    context.lineTo(400+70*5+30, 110+50+30);
	    context.lineTo(400+70*5+30, 110+30);

	    context.lineTo(400+70*5+30, 110+50+30);
	    context.lineTo(400+70*5+30, 380);

	    context.lineTo(400+70*5+30, 380-50);
	    context.lineTo(400+70*3+30, 380-50);
	    context.lineTo(400+70*3+30, 380);
	    
	    //context.moveTo(200, 50);
	    //context.lineTo(100,50);
	    context.stroke();

	    //画出当前医生的位置
	    var image = new Image();
	    image.src = '<s:url value="/styles/images/roleIcon/doctor.png"/>';
	    image.id = 'img1';
	    image.onload = function () {
	    	document.body.appendChild(image);
		    
            //drawImage(image,x,y,w,h)
            context.drawImage(image, 400, 110, 30, 30);  //病房16
            context.drawImage(image, 400+70, 110, 30, 30);  //病房15
            context.drawImage(image, 400+70*2, 110, 30, 30);  //病房14

            context.drawImage(image, 400+70*5, 110, 30, 30);  //病房14

            context.drawImage(image, 400+70*5, 380, 30, 30);  //病房1
            context.drawImage(image, 400+70*3, 380, 30, 30);  //病房3
        }

	    
		//给canvas增加事件
	    canvas.addEventListener('click', function(e){

	        p = getEventPosition(e);

			//根据x,y坐标位置，判断在哪个病房
	        showMess(p.x, p.y);
			
	        if(canvas.isPointInPath(p.x, p.y)){

	        //点击了矩形

	        }

	        }, false);
	   
	}

	
	function draw12(id) {
        
        var image = new Image();
        image.src = '<s:url value="/styles/images/roleIcon/doctor.png"/>';
        
        var canvas = document.getElementById(id);
        if (canvas == null)
            return false;
        var context = canvas.getContext("2d");
        //context.fillStyle = "#EEEEFF";
        //context.fillRect(0, 0, 400, 300);
        image.onload = function () {
            //drawImage(image,x,y,w,h)
            context.drawImage(image, 50, 50, 30, 30);
        }
    }

	window.onload = function () {

	///	draw8("canvas8");
		

	}

	
</script>
<style type="text/css">
    body div#tooltip {position:absolute;z-index:1000;width: auto;max-width:500px;background:#EDF5FA none repeat scroll 0% 50%;border:3px solid #CEF3FF;text-align:left;padding:5px;min-height:1em;-moz-border-radius:5px;font-family:Verdana,Arial,Helvetica,Georgia,Sans-Serif;}
	body div#tooltip p { margin:0;padding:0;color:#027AC6;font:12px Verdana,Arial,sans-serif; }
	body div#tooltip p em { display:block;margin-top:3px;color:#f60;font-style:normal;font-weight:bold; }
	body div#tooltip p em span { font-weight:bold;color:#669900; } 
	dl.window dt{padding-top:5px;}
	dl.window{height:71px;}

 #canvas8 {
     behavior: url(<s:url value='/javascript/ie-css3.htc'/>);
 }	
	
</style>
<title></title>
</head>
<body>

<div id="test"></div>
		<div class="container">
		<s:include value="./header1.jsp"></s:include>
 
<div class="wrap">
    <div class="main" style="border:none">
        <div id="people" class="focus">
			 <!-- <header><h1>画线test(lineTo moveTo)</h1></header> --><canvas id="canvas8" width="820" height="540"></canvas>
		</div>
    </div>

	<div id="name" class="side">		
		<div class="side-title">
		    <h2>当天查房情况</h2>
		</div>
		
		<div id="tabs" style="margin-top:5px;">
			<ul>
				<li><a href="#tabs-1">护士</a></li>
				<li><a href="#tabs-2">医生</a></li>
			</ul>
			<div id="tabs-1" style="overflow-y:scroll;height:200px;">
				<ul class="titles">
				<li>
				        <h3>姓名</h3>
				        <h3>角色</h3>
						<h3>当天次数</h3>
				    <p class="titles-b">
				    </p>
				</li>
				</ul>
				
				<s:iterator value="#request.dailyWardRoundNumbers" id="d">
					<ul class="titles">
					<li>
					        <h3><s:property value="#d.name" /></h3>
					        <h3><img src="<s:url value='/styles/image/nurse.png'/>"/></h3>
							<h3><s:property value="#d.numreal" /></h3>
							<h3></h3>
					    <p class="titles-b">
					    </p>
					</li>
					</ul>
				</s:iterator>
			</div>
			<div id="tabs-2" style="overflow-y:scroll;height:200px;">
				<ul class="titles">
				<li>
				        <h3>姓名</h3>
				        <h3>角色</h3>
						<h3>本周次数</h3>
				    <p class="titles-b">
				    </p>
				</li>
				</ul>
				<!-- 
					Modify by zhuwei at 2014/05/14 16:33
					<s:iterator value="#request.weeklyWardRoundNumbers" id="d">
					</s:iterator>
				 -->
				<s:iterator value="#request.weeklyWardRoundNumbers" var="d">
					<ul class="titles">
					<li>
					        <h3><s:property value="#d.name" /></h3>
					        <h3><img src="<s:url value='/styles/image/doctor.png'/>"/></h3>
							<h3><s:property value="#d.numreal" /></h3>
					    <p class="titles-b">
					    </p>
					</li>
					</ul>
				</s:iterator>
			</div>
		</div>
		
		
		<div class="side-title">
		    <h2>护士离开病区异常情况告警</h2>&nbsp;&nbsp;<a href="<s:url action='../alert/nurse-leave-alert.action' />">(详细)</a>
		</div>
		<ul class="titles">
		<li>
		        <h3>姓名</h3>
				<h3>离开时间</h3>
				<h3>回来时间</h3>
				<h3>总计</h3>
		    <p class="titles-b">
		    </p>
		</li>
		</ul>
		
		<ul class="titles">
		<li>
		        <h3>张敏</h3>
				<h3>14：32</h3>
				<h3>16：32</h3>
				<h3>2小时</h3>
		    <p class="titles-b">
		    </p>
		</li>
		</ul>
		
		<ul class="titles">
		<li>
		        <h3>夏雪</h3>
				<h3>9：32</h3>
				<h3>11：32</h3>
				<h3>2小时</h3>
		    <p class="titles-b">
		    </p>
		</li>
		</ul>
		
		<ul class="titles">
		<li>
		        <h3>林丽</h3>
				<h3>10：32</h3>
				<h3>11：32</h3>
				<h3>1小时</h3>
		    <p class="titles-b">
		    </p>
		</li>
		</ul>
		
				
		
		<div class="side-title">
		    <h2>病人离开病区异常情况告警</h2>&nbsp;&nbsp;<a href="<s:url action='../alert/patient-leave-alert.action' />">(详细)</a>
		</div>
		<ul class="titles">
		<li>
		        <h3>姓名</h3>
				<h3>离开时间</h3>
				<h3>回来时间</h3>
				<h3>总计</h3>
		    <p class="titles-b">
		    </p>
		</li>
		</ul>
		
		<ul class="titles">
		<li>
		        <h3>胡大海</h3>
				<h3>14：32</h3>
				<h3>16：32</h3>
				<h3>2小时</h3>
		    <p class="titles-b">
		    </p>
		</li>
		</ul>
		
		<ul class="titles">
		<li>
		        <h3>张磊</h3>
				<h3>9：32</h3>
				<h3>11：32</h3>
				<h3>2小时</h3>
		    <p class="titles-b">
		    </p>
		</li>
		</ul>
		
		<ul class="titles">
		<li>
		        <h3>谢江山</h3>
				<h3>10：42</h3>
				<h3>11：32</h3>
				<h3>1小时</h3>
		    <p class="titles-b">
		    </p>
		</li>
		</ul>
				
		<ul class="titles">
		<li>
		        <h3>刘涛</h3>
				<h3>9：32</h3>
				<h3>11：32</h3>
				<h3>2小时</h3>
		    <p class="titles-b">
		    </p>
		</li>
		</ul>
		
			
	</div>

</div>

<s:include value="./footer.jsp"></s:include>

</div>

</body>
</html>