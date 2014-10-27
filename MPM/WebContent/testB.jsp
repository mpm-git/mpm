<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title> IE8支持HTML5+CSS3 </title>


<script src="<s:url value='/javascript/excanvas.js'/>" type="text/javascript" charset="utf-8"></script>

 
 <style type="text/css">
 body { 
     background: #444; 
     color: #FFF;
     font-family: Helvetica, Arial, sans-serif;
     text-align: center;
 }
 
 #cv {
     width: 600px; height: 400px;
     background: #000;
     border-radius: 20px;
     padding: 20px;
     margin: 20px auto;
     box-shadow: 0 0 40px #222;
     behavior: url(<s:url value='/javascript/ie-css3.htc'/>);
 }
 </style>
 
 <script type="text/javascript">
 function test() {
   var ctx = document.getElementById("cv").getContext("2d");
   /**
   ctx.fillStyle = "#aa0000";
   ctx.beginPath();
   ctx.arc(100, 100, 25, 0, Math.PI*2, true);
   ctx.closePath();
   ctx.fill();*/

   //画出轨迹线路
   ///ctx.lineWidth = "5";
   ctx.strokeStyle = "rgb(250,0,0)";
   ctx.fillStyle = "rgb(250,0,0)"
   //病房16
   ctx.lineTo(30, 30);
   //病房15
   ctx.lineTo(60, 60);
   ctx.stroke();
 }
 
 window.onload = test;
  </script>
 
</head>

 <body>
 <canvas id="cv"></canvas>


</body>
</html>