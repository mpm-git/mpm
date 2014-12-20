<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医疗行为管理系统－系统设置-健康普及管理</title>

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
<script type="text/javascript" charset="utf-8"
	src="/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<s:url value='/upload/ajaxfileupload.js'/>"> </script>
	

<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
	var start;
	var total;
	$(function() {
		G.req([ "gPopMenu" ], function(j) {
			j("#aMenu");
		});
		G.req([ "gPopMenu" ], function(j) {
			j("#bMenu");
		});
		G.req([ "gPopMenu" ], function(j) {
			j("#cMenu");
		});
		G.req([ "gPopMenu" ], function(j) {
			j("#mMenu");
		});
		G.req([ "gPopMenu" ], function(j) {
			j("#dMenu");
		});
		G.req([ "gPopMenu" ], function(j) {
			j("#eMenu");
		});
		G.req([ "gPopMenu" ], function(j) {
			j("#fMenu");
		});

		$("#beginStatDate").datepicker();
		$("#endStatDate").datepicker();
		$("input:submit", ".wrap").button();
		

		
		$.ajax({    
	        url : '../stat/findtypes.action',    
	        type : 'post',    
	        dataType : 'json', 
	        async: false, 
	        success : function(data) {
	        	var types=data.types;
				if(types!=null & types!=''){
					var arr=new Array();
					arr=types.split(',');
					for(var i=0;i<arr.length;i++){
						$('#pageType').append("<option value='"+arr[i]+"'>"+arr[i]+"</option>");
					}
				}
	        	
	        }
	    });
		var id=<%= request.getParameter("id")%>;
		if(id!=null){
			$.ajax({    
		        url : '../stat/find_health_content.action',    
		        type : 'post',    
		        dataType : 'json', 
		        async: false, 
		        data:{
		        	'id':id
		        },
		        success : function(data) {
		        	if(data.message=='success'){
		        		var healthContent=data.healthContent1;
		        		$('#title').val(healthContent.title);
		        		$("#pageType option[value='"+healthContent.type+"']").attr("selected","selected");
		        		$('#personInfo_form1').hide();
		        		$('#text').val(healthContent.imagePath);
		        		$('#upload_image').attr("src",healthContent.imagePath);
// 		        		$("#text").show();
		        		//UE.getEditor('editor').setContent(healthContent.jspContent);
		        		var ueditor=UE.getEditor('editor');
		        		ueditor.addListener("ready", function () {
		        			 // editor准备好之后才可以使用
		        			 ueditor.setContent(healthContent.jspContent);

		        		});
		        		$('#button_save').bind('click',function(){
		        			updateHealthPage(id);
		        		});
		        		
		        	}
		        }
		    });
		}else{
			$('#button_save').bind('click',function(){
				getAllHtml();
    		});
		}
		/* $('#pageType').change(function(){
			var type=$('#pageType').val();
			if(type==1){
				$('#typeTr').show();
			}
		}); */
	});
	
	function ajaxFileUpload() {
		$("#personInfo_form1").ajaxStart(function() {
			$(this).show();
		}).ajaxComplete(function() {
			$(this).hide();
		});

		$.ajaxFileUpload({
					url : "/ueditor/jsp/controller.jsp?action=uploadimage",
					secureuri : false,
					fileElementId : 'file',
					dataType : 'json',
					success : function(data) {
// 						$("#text").show();
						$("#text").val("/ueditor"+data.url);
						$("#upload_image").attr("src","/ueditor"+data.url);
					}
		
				});
		return false;
	}
	
	function updateHealthPage(id){
		var html = UE.getEditor('editor').getContent();
		var title=$('#title').val();
		var type=$('#pageType').val();
		var imagePath=$('#text').val()
		if(title==''){
			alert("标题不能为空");
			return;
		}
		if(imagePath==''){
			alert("主题图片不能为空");
			return;
		}
		if(html==''){
			alert("内容不能为空");
			return;
		}
		
		$.ajax({
			url : 'save_html_content.action',
			type : 'post',
			data : {
				'id':id,
				'htmlContent' : html,
				'title':title,
				'type':type,
				'imagePath':imagePath
			},
			dataType : 'json',
			success : function(data) {
				console.info(data.message);
				if (data.message == 'success') {
					alert('保存成功');
					window.location="../stat/health-popularization.action";
				}
				if (data.message == 'error') {
					alert('保存失败');
				}

			}
		});
	}
</script>
 <script type="text/javascript" language="javascript">    
        function FileUpload_onselect()
        {
        	ajaxFileUpload();
        }
    </script>
</head>
<body>


	<div class="container">

		<s:include value="../header1.jsp"></s:include>


		<div class="wrap">
			<div class="wrap">
				<div class="side-title">
					<h2>· 您现在所在的位置：&nbsp;&nbsp;系统设置&nbsp;&nbsp;→&nbsp;&nbsp;健康普及管理</h2>
				</div>
				<ul class="titles">
					<li>
						<!-- <p class="titles-b"></p> -->
					</li>
				</ul>
				<table bordercolor="#000000" border=0 cellpadding="0"
					cellspacing="0" style="margin-top: 20px;">
					<tr>
						<td  class="odd">&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;标题：</td>
						<td><input type="text" id="title" name="title" /></td>
					</tr>
					<tr>
						<td height="19"></td>
						<td>
							<area />
						</td>
					</tr>
					<tr>
						<td  class="odd">&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;类型：</td>
						<td><SELECT id="pageType" style="width: 131px;"
							name="type">
						</SELECT></td>
					</tr>
					<tr>
						<td height="19"></td>
						<td>
							<area />
						</td>
					</tr>
					<tr>
						<td class="odd">&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;主题图片：</td>
						<td>
							<form id="personInfo_form1" method="post" enctype="multipart/form-data"> 
								<!-- <input id="health_file" type="file" value="选择文件" name="upfile"><input type="button" value="上传" id="uploadImage" onclick="ajaxFileUpload();"> -->
<!-- 								<input type="button" value="选择" onclick="file.click()" class="btn_mouseout"/> -->
								 <input id="file" type="file"  name="upfile" contentEditable="false" accept="image/*" style="width:448px; height: 22px;display:none" onclick="return FileUpload_onclick()" onchange="return FileUpload_onselect()"/>
							</form>
							<input style="display: none;" id="text" type="text" style="display:none" />
								<img id="upload_image" src="<s:url value='/styles/image/add.png'/>" onclick="file.click()" /> 
						</td>
					</tr>
				</table>
				<!-- <form id="personInfo_form1" method="post" enctype="multipart/form-data"> 
			 		&nbsp;&nbsp;&nbsp;&nbsp;上传路径：<input id="file" type="file" value="选择文件" name="upfile">
					<input type="button" value="上传" id="uploadImage" onclick="ajaxFileUpload();">
				</form>
					<input id="text" readonly type="text"/>  -->
				<div style="padding-top: 20px">
					<div>
						<script id="editor" type="text/plain"
							style="width:1024px;height:500px;"></script>
					</div>

					<div id="btns">
						<div>
							<button id="button_save" onclick="">保存页面</button>
							<button onclick="setContent()">清空内容</button>
					        <!--  <button onclick="setContent(true)">追加内容</button>
							<button onclick="getContent()">获得内容</button>
					        <button onclick="getContentTxt()">获得纯文本</button>
					        <button onclick="getPlainTxt()">获得带格式的纯文本</button>
					        <button onclick="hasContent()">判断是否有内容</button>
					        <button onclick="setFocus()">使编辑器获得焦点</button>
					        <button onmousedown="isFocus(event)">编辑器是否获得焦点</button>
					        <button onmousedown="setblur(event)" >编辑器失去焦点</button>
					
					    </div>
					    <div>
					        <button onclick="getText()">获得当前选中的文本</button>
					        <button onclick="insertHtml()">插入给定的内容</button>
					        <button id="enable" onclick="setEnabled()">可以编辑</button>
					        <button onclick="setDisabled()">不可编辑</button>
					        <button onclick=" UE.getEditor('editor').setHide()">隐藏编辑器</button>
					        <button onclick=" UE.getEditor('editor').setShow()">显示编辑器</button>
					        <button onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高</button>
					    </div>
					
					    <div>
					        <button onclick="getLocalData()" >获取草稿箱内容</button>
					        <button onclick="clearLocalData()" >清空草稿箱</button>
					    </div>-->

						</div>
						<!-- 
					<div>
					    <button onclick="createEditor()">
					    创建编辑器</button>
					    <button onclick="deleteEditor()">
					    删除编辑器</button>
					</div>
					 -->
					</div>

				</div>
			</div>

			<s:include value="../footer.jsp"></s:include>
		</div>
</body>
<script type="text/javascript">
	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');

	function isFocus(e) {
		alert(UE.getEditor('editor').isFocus());
		UE.dom.domUtils.preventDefault(e)
	}
	function setblur(e) {
		UE.getEditor('editor').blur();
		UE.dom.domUtils.preventDefault(e)
	}
	function insertHtml(content) {
		var value = prompt('插入html代码', '');
		UE.getEditor('editor').execCommand('insertHtml', content)
	}
	function createEditor() {
		enableBtn();
		UE.getEditor('editor');
	}
	function getAllHtml() {
		//var html= UE.getEditor('editor').getAllHtml();
		var html = UE.getEditor('editor').getContent();
		var title=$('#title').val();
		var type=$('#pageType').val();
		var imagePath=$('#text').val()
		if(title==''){
			alert("标题不能为空");
			return;
		}
		if(imagePath==''){
			alert("主题图片不能为空");
			return;
		}
		if(html==''){
			alert("内容不能为空");
			return;
		}
		$.ajax({
			url : 'save_html_content.action',
			type : 'post',
			data : {
				'htmlContent' : html,
				'title':title,
				'type':type,
				'imagePath':imagePath
			},
			dataType : 'json',
			success : function(data) {
				console.info(data.message);
				if (data.message == 'success') {
					alert('保存成功');
					window.location="../stat/health-popularization.action";
				}
				if (data.message == 'error') {
					alert('保存失败');
				}

			}
		});

	}
	function getContent() {
		var arr = [];
		arr.push("使用editor.getContent()方法可以获得编辑器的内容");
		arr.push("内容为：");
		arr.push(UE.getEditor('editor').getContent());
		alert(arr.join("\n"));
	}
	function getPlainTxt() {
		var arr = [];
		arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
		arr.push("内容为：");
		arr.push(UE.getEditor('editor').getPlainTxt());
		alert(arr.join('\n'))
	}
	function setContent() {
		var arr = [];
        arr.push( "使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容" );
        UE.getEditor('editor').setContent( '欢迎使用ueditor' );
        alert( arr.join( "\n" ) );
	}
	function setDisabled() {
		UE.getEditor('editor').setDisabled('fullscreen');
		disableBtn("enable");
	}

	function setEnabled() {
		UE.getEditor('editor').setEnabled();
		enableBtn();
	}

	function getText() {
		//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
		var range = UE.getEditor('editor').selection.getRange();
		range.select();
		var txt = UE.getEditor('editor').selection.getText();
		alert(txt)
	}

	function getContentTxt() {
		var arr = [];
		arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
		arr.push("编辑器的纯文本内容为：");
		arr.push(UE.getEditor('editor').getContentTxt());
		alert(arr.join("\n"));
	}
	function hasContent() {
		var arr = [];
		arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
		arr.push("判断结果为：");
		arr.push(UE.getEditor('editor').hasContents());
		alert(arr.join("\n"));
	}
	function setFocus() {
		UE.getEditor('editor').focus();
	}
	function deleteEditor() {
		disableBtn();
		UE.getEditor('editor').destroy();
	}
	function disableBtn(str) {
		var div = document.getElementById('btns');
		var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
		for ( var i = 0, btn; btn = btns[i++];) {
			if (btn.id == str) {
				UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
			} else {
				btn.setAttribute("disabled", "true");
			}
		}
	}
	function enableBtn() {
		var div = document.getElementById('btns');
		var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
		for ( var i = 0, btn; btn = btns[i++];) {
			UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
		}
	}

	function getLocalData() {
		alert(UE.getEditor('editor').execCommand("getlocaldata"));
	}

	function clearLocalData() {
		UE.getEditor('editor').execCommand("clearlocaldata");
		alert("已清空草稿箱")
	}
</script>
</html>
