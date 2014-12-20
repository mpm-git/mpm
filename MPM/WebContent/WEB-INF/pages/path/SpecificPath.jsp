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
<script type="text/javascript" src="<s:url value='/html5/findPath/js/ex/load.js'/>"></script>
<script type="text/javascript">
//loadJsCssFileRelToLoadJs("../main.js","js");
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
	load_r_person_array(function(){

			var liandong2 = new cz_select(r_person_array);
			//设置第一个选择框
			liandong2.firstSelectChange("--", "area1");
			//设置子选择框
			liandong2.subSelectChange("area1", "person_type");
			liandong2.subSelectChange("person_type", "person_name");
		});

	});
var global_view;
var global_view_comp_tag=false;
var _element;

	window.onload = function() {
		$("#query").attr("display","none");
		var data="M21.022,16.349c-0.611-1.104-1.359-1.998-2.109-2.623c-0.875,0.641-1.941,1.031-3.104,1.031c-1.164,0-2.231-0.391-3.105-1.031c-0.75,0.625-1.498,1.519-2.111,2.623c-1.422,2.563-1.579,5.192-0.351,5.874c0.55,0.307,1.127,0.078,1.723-0.496c-0.105,0.582-0.167,1.213-0.167,1.873c0,2.932,1.139,5.307,2.543,5.307c0.846,0,1.265-0.865,1.466-2.189c0.201,1.324,0.62,2.189,1.464,2.189c1.406,0,2.545-2.375,2.545-5.307c0-0.66-0.061-1.291-0.168-1.873c0.598,0.574,1.174,0.803,1.725,0.496C22.603,21.541,22.444,18.912,21.022,16.349zM15.808,13.757c2.363,0,4.279-1.916,4.279-4.279s-1.916-4.279-4.279-4.279c-2.363,0-4.28,1.916-4.28,4.279S13.445,13.757,15.808,13.757zM18.731,4.974c1.235,0.455,0.492-0.725,0.492-1.531s0.762-1.792-0.492-1.391c-1.316,0.422-2.383,0.654-2.383,1.461S17.415,4.489,18.731,4.974zM15.816,4.4c0.782,0,0.345-0.396,0.345-0.884c0-0.488,0.438-0.883-0.345-0.883s-0.374,0.396-0.374,0.883C15.442,4.005,15.034,4.4,15.816,4.4zM12.884,4.974c1.316-0.484,2.383-0.654,2.383-1.461S14.2,2.474,12.884,2.052c-1.254-0.402-0.492,0.584-0.492,1.391S11.648,5.428,12.884,4.974z";
		var data1="M21.021,16.349c-0.611-1.104-1.359-1.998-2.109-2.623c-0.875,0.641-1.941,1.031-3.103,1.031c-1.164,0-2.231-0.391-3.105-1.031c-0.75,0.625-1.498,1.519-2.111,2.623c-1.422,2.563-1.578,5.192-0.35,5.874c0.55,0.307,1.127,0.078,1.723-0.496c-0.105,0.582-0.166,1.213-0.166,1.873c0,2.932,1.139,5.307,2.543,5.307c0.846,0,1.265-0.865,1.466-2.189c0.201,1.324,0.62,2.189,1.463,2.189c1.406,0,2.545-2.375,2.545-5.307c0-0.66-0.061-1.291-0.168-1.873c0.598,0.574,1.174,0.803,1.725,0.496C22.602,21.541,22.443,18.912,21.021,16.349zM15.808,13.757c2.362,0,4.278-1.916,4.278-4.279s-1.916-4.279-4.278-4.279c-2.363,0-4.28,1.916-4.28,4.279S13.445,13.757,15.808,13.757z";
		if (!Raphael.svg) {
			alert("浏览器不支持");
//			window.location = './notsupported.html';
		}
		global_view=new View({
			isShowGrid:true,
			div:"people",
			cols:82,
			rows:54,
			background:{type:"image",data:__root+"html5/findPath/img/8B.png"},
			isShowBlockedGrid:false,
			blockedGrid:[[20,0],[0,19],[75,31],[5,31],[6,29],[6,30],[6,31],[6,32],[6,33],[7,29],[7,33],[8,29],[8,33],[9,29],[9,33],[10,26],[10,27],[10,28],[10,29],[10,33],[11,26],[11,33],[12,26],[12,33],[12,34],[12,35],[12,36],[12,37],[12,38],[12,39],[12,40],[12,41],[12,42],[12,43],[12,44],[13,26],[13,33],[13,34],[13,35],[13,36],[13,37],[13,38],[13,39],[13,40],[13,41],[13,42],[13,43],[13,44],[14,26],[14,44],[15,17],[15,18],[15,19],[15,20],[15,21],[15,22],[15,23],[15,24],[15,25],[15,26],[15,44],[16,17],[16,33],[16,44],[17,17],[17,33],[17,44],[18,17],[18,20],[18,21],[18,22],[18,23],[18,24],[18,25],[18,26],[18,27],[18,28],[18,29],[18,30],[18,33],[18,44],[19,17],[19,20],[19,30],[19,33],[19,34],[19,35],[19,36],[19,37],[19,38],[19,39],[19,40],[19,41],[19,42],[19,43],[19,44],[20,17],[20,20],[20,30],[20,33],[20,44],[21,17],[21,20],[21,30],[21,33],[21,44],[22,17],[22,20],[22,30],[22,33],[22,44],[23,17],[23,20],[23,30],[23,44],[24,17],[24,20],[24,30],[24,44],[25,17],[25,20],[25,30],[25,33],[25,34],[25,35],[25,36],[25,37],[25,38],[25,39],[25,40],[25,41],[25,42],[25,43],[25,44],[26,9],[26,10],[26,11],[26,12],[26,13],[26,14],[26,15],[26,16],[26,17],[26,20],[26,30],[26,33],[26,34],[26,35],[26,36],[26,37],[26,38],[26,39],[26,40],[26,41],[26,42],[26,43],[26,44],[27,9],[27,20],[27,30],[27,44],[28,9],[28,17],[28,20],[28,30],[28,44],[29,9],[29,17],[29,20],[29,30],[29,33],[29,44],[30,9],[30,17],[30,20],[30,30],[30,33],[30,44],[31,9],[31,17],[31,20],[31,30],[31,33],[31,44],[32,9],[32,10],[32,11],[32,12],[32,13],[32,14],[32,15],[32,16],[32,17],[32,20],[32,30],[32,33],[32,34],[32,35],[32,36],[32,37],[32,38],[32,39],[32,40],[32,41],[32,42],[32,43],[32,44],[33,9],[33,17],[33,20],[33,30],[33,33],[33,44],[34,9],[34,17],[34,20],[34,30],[34,33],[34,44],[35,9],[35,17],[35,20],[35,30],[35,33],[35,44],[36,9],[36,17],[36,20],[36,30],[36,44],[37,9],[37,20],[37,30],[37,44],[38,9],[38,20],[38,30],[38,33],[38,34],[38,35],[38,36],[38,37],[38,38],[38,39],[38,40],[38,41],[38,42],[38,43],[38,44],[39,9],[39,10],[39,11],[39,12],[39,13],[39,14],[39,15],[39,16],[39,17],[39,20],[39,30],[39,33],[39,34],[39,35],[39,36],[39,37],[39,38],[39,39],[39,40],[39,41],[39,42],[39,43],[39,44],[40,9],[40,20],[40,30],[40,44],[41,9],[41,20],[41,30],[41,44],[42,9],[42,17],[42,20],[42,30],[42,33],[42,44],[43,9],[43,17],[43,20],[43,30],[43,33],[43,44],[44,9],[44,17],[44,20],[44,30],[44,33],[44,44],[45,9],[45,17],[45,20],[45,30],[45,33],[45,34],[45,35],[45,36],[45,37],[45,38],[45,39],[45,40],[45,41],[45,42],[45,43],[45,44],[46,9],[46,10],[46,11],[46,12],[46,13],[46,14],[46,15],[46,16],[46,17],[46,20],[46,30],[46,33],[46,44],[47,9],[47,17],[47,20],[47,30],[47,33],[47,44],[48,9],[48,17],[48,20],[48,30],[48,33],[48,44],[49,9],[49,17],[49,20],[49,30],[49,44],[50,9],[50,20],[50,30],[50,44],[51,9],[51,20],[51,30],[51,33],[51,34],[51,35],[51,36],[51,37],[51,38],[51,39],[51,40],[51,41],[51,42],[51,43],[51,44],[52,9],[52,10],[52,11],[52,12],[52,13],[52,14],[52,15],[52,16],[52,17],[52,20],[52,30],[52,33],[52,34],[52,35],[52,36],[52,37],[52,38],[52,39],[52,40],[52,41],[52,42],[52,43],[52,44],[53,9],[53,20],[53,30],[53,44],[54,9],[54,20],[54,30],[54,44],[55,9],[55,17],[55,20],[55,30],[55,33],[55,44],[56,9],[56,17],[56,20],[56,30],[56,33],[56,44],[57,9],[57,17],[57,20],[57,30],[57,33],[57,44],[58,9],[58,10],[58,11],[58,12],[58,13],[58,14],[58,15],[58,16],[58,17],[58,20],[58,30],[58,33],[58,44],[59,9],[59,17],[59,20],[59,30],[59,33],[59,34],[59,35],[59,36],[59,37],[59,38],[59,39],[59,40],[59,41],[59,42],[59,43],[59,44],[60,9],[60,17],[60,20],[60,30],[60,33],[60,44],[61,9],[61,17],[61,20],[61,30],[61,33],[61,44],[62,9],[62,17],[62,20],[62,30],[62,33],[62,44],[63,9],[63,20],[63,30],[63,44],[64,9],[64,20],[64,30],[64,44],[65,9],[65,10],[65,11],[65,12],[65,13],[65,14],[65,15],[65,16],[65,17],[65,20],[65,30],[65,33],[65,34],[65,35],[65,36],[65,37],[65,38],[65,39],[65,40],[65,41],[65,42],[65,43],[65,44],[66,9],[66,20],[66,30],[66,44],[67,9],[67,20],[67,30],[67,44],[68,9],[68,17],[68,20],[68,30],[68,33],[68,44],[69,9],[69,17],[69,20],[69,30],[69,33],[69,44],[70,9],[70,17],[70,20],[70,30],[70,33],[70,44],[71,9],[71,17],[71,20],[71,30],[71,33],[71,34],[71,35],[71,36],[71,37],[71,38],[71,39],[71,40],[71,41],[71,42],[71,43],[71,44],[72,9],[72,10],[72,11],[72,12],[72,13],[72,14],[72,15],[72,16],[72,17],[72,20],[72,30],[72,33],[72,34],[72,35],[72,36],[72,37],[72,38],[72,39],[72,40],[72,41],[72,42],[72,43],[72,44],[73,9],[73,17],[73,20],[73,21],[73,22],[73,23],[73,24],[73,25],[73,26],[73,27],[73,28],[73,29],[73,30],[73,33],[73,44],[74,9],[74,17],[74,33],[74,44],[75,9],[75,17],[75,33],[75,44],[76,9],[76,44],[77,9],[77,44],[78,9],[78,10],[78,11],[78,12],[78,13],[78,14],[78,15],[78,16],[78,17],[78,18],[78,19],[78,20],[78,21],[78,22],[78,23],[78,24],[78,25],[78,26],[78,27],[78,28],[78,29],[78,30],[78,31],[78,32],[78,33],[78,34],[78,35],[78,36],[78,37],[78,38],[78,39],[78,40],[78,41],[78,42],[78,43],[78,44]],
			call_initGridComp:function(){
				global_view_comp_tag=true;
				
			}
		});
		$("#query").click(function(){
			if(global_view_comp_tag)
			{
				var beginStatDate=$("#beginStatDate").val();
				var endStatDate=$("#endStatDate").val();
				var empNo = $("#person_name").val();
				var person=r_person_data.getElementByAttr({empNO:empNo})[0];
				var image;
				if (person.type == "医生")
					image = __root + 'styles/image/doctor.png';
				else
					image = __root + 'styles/image/nurse.png';
				if(empNo==null||empNo=="")
				{
					alert("请选择要查看的用户");
				}
				else
				{
					//global_view.removeAllChildren();
					$.ajax({ 
						url: r_person_history_location, 
						data:{empNO:person.empNO,start_time:beginStatDate,end_time:endStatDate},
						success: function(data){
// 							if(e.param.text.time!=data.nTime)
// 							{
// 								console.info(121);
// 								e.moveTo({x:data.nX,y:data.nY},{
// 									speed:5,
// 									along:true,//moveTo无效
// 									from:1,//moveTo无效
// 									to:0,//moveTo无效
// 									callBack:function(){
// 									}
// 								});
// 							}
// 							e.param.text.time=data.nTime;
						var res;
						if(data&&data.total>0)
						{
							
							res=data.result;
							if(_element)
							{
								_element.loginOut();
								delete(_element);
								_element=null;
							}	
// 							else
// 							{
								_element=new CZ_Element(
								{
										src:{type:'image',data:image},//{type:"image",data:""}
										l:3.2,
										w:3.2,
										text:{
												style:{
														fill: "#f00",
														font: "16px Georgia",
														'font-weight':800
													},
													person:person,
													render:function(text){
														return text.person.name;
													}
												},
											pos:{x:res[0].nX,y:res[0].nY},
											showMovePath:true,
											center:true
								});
								_element.loginIn(global_view);
								_moveTo(_element,res,1);
// 							}
								
								//_element.moveTo({x:res[0].nX,y:res[0].nY},{
// 								_element.moveTo({x:19,y:19},{
// 									speed:20,
// 										along:true,//moveTo无效
// 										from:1,//moveTo无效
// 										to:0,//moveTo无效
// 										callBack:function(){
// 											console.info("callback");
// 											//return function (){_moveTo(__element,_res,_i);};
// 										}
// 									});
								//else
						}
						//console.info(data);
					}});
				}
			}
		});
		
		//draw8("canvas8");

	}
	function _moveTo(_element,res,ii)
	{
		if(!ii)
			ii=0;
		if(ii<res.length)
		_element.moveTo({x:res[ii].nX,y:res[ii].nY},{
			speed:20,
				along:true,//moveTo无效
				from:1,//moveTo无效
				to:0,//moveTo无效
				callBack:function(__element,_res,_i){
					return function (){_moveTo(__element,_res,_i);};
				}(_element,res,ii+1)
			});

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
            <select name="area1" id="area1" style="width:90px;">
              <option value="option1">病区1</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            选择类别:
            <select name="person_type" id="person_type" style="width:90px;">
<!--               <option value="3027">杨杰</option> -->
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            选择:
            <select name="person_name" id="person_name" style="width:90px;">
<!--               <option value="3027">杨杰</option> -->
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            起始时间:
            <input type="text" id="beginStatDate" name="beginStatDate" value="${startTimeStr}" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            截止时间:
            <input type="text" id="endStatDate" name="endStatDate" value="${endTimeStr}" />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input id='query' type="button" value="查询"/>
          </form>
          <p class="titles-b"></p>
        </li>
      </ul>

      
      <div class="main" style="border:none">
       <div id="people">
<!-- 			 <header><h1>画线test(lineTo moveTo)</h1></header><canvas id="canvas8" width="820" height="540"></canvas> -->
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
