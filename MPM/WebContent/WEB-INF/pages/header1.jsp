<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<style type="text/css">
#long-tab ul li{width: 145px;}
</style>
<head>
</head>
<body>

        <div class="gheader-wp">
        <div class="gheader-wp-b">
        <div class="gheader">
            <a class="gheader-logo"  href="/" title="重点人员监管信息"></a>
            <ul class="gheader-nav">
            	<li><a class="m_ment-title" href="../mainpage/main-page.action">首页</a></li>
                <li id="fMenu" class="m_menu">
                	<a class="m_menu-title" href="#">轨迹管理<b><s></s></b></a>
                	<div>
                		<ul>
                			<!-- <li><a href="<s:url action="../stat/doctor-rounds-stat.action" />"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />实时轨迹管理</a></li> -->
                			<li><a href="<s:url action="../path/real-time-path.action" />"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />实时轨迹管理</a></li>
               				<li><a href="<s:url action="../path/specific-path.action" />"><img src="<s:url value='/styles/image/nurse.png'/>" width="20" height="20" />特定轨迹跟踪</a></li>
               				<li><a href="<s:url action="../path/sickroom-equip.action" />"><img src="<s:url value='/styles/image/patient.png'/>" width="20" height="20" />病房设备联动</a></li>
                		</ul>
                	</div>
                </li>
                
                
                				
				<li id="aMenu" class="m_menu">
                	<a class="m_menu-title"   href="#">统计分析<b><s></s></b></a>
               		<div>
                   		<ul>
               				<li><a href="<s:url action="../stat/nurse-rounds-stat.action" />"><img src="<s:url value='/styles/image/nurse.png'/>" width="20" height="20" />护士工作统计</a></li>
               				<li><a href="<s:url action="../stat/doctor-rounds-stat.action" />"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />医生查房统计</a></li>
               				<li><a href="<s:url action="../alert/patient-leave-alert.action" />"><img src="<s:url value='/styles/image/patient.png'/>" width="20" height="20" />告警统计分析</a></li>
                   		</ul>
               		</div>
                </li>
				
                <li id="mMenu" class="m_menu">
                	<a class="m_menu-title"   href="#">RFID阅读设备管理<b><s></s></b></a>
                		<div>
                    		<ul>
                    			<li><a href="#">可视化管理</a></li>
                				<li><a href="<s:url action="../stat/doctor-rounds-stat.action" />">设备状态查询</a></li>
                				<li><a href="#">设备控制</a></li>
                    		</ul>
                		</div>
                </li>
                <li id="bMenu" class="m_menu">
                	<a class="m_menu-title"   href="#">RFID阅读中间件<b><s></s></b></a>
                		<div>
                    		<ul>
                    			<li><a href="#"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />高并发读取</a></li>
                				<li><a href="<s:url action="../stat/card-manager-stat.action" />"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />底层日志</a></li>
                				<li><a href="<s:url action="../stat/card-manager-stat.action" />"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />事件映射</a></li>
                    		</ul>
                		</div>
                </li>
                <li id="eMenu" class="m_menu">
                	<a class="m_menu-title"   href="#">病房终端设备管理<b><s></s></b></a>
                		<div>
                    		<ul>
                    			<li><a href="#"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />可视化管理</a></li>
                    			<li><a href="#"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />设备查询</a></li>
                    			<li><a href="#"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />设备控制</a></li>
                    		</ul>
                		</div>
                </li>
                 <li id="dMenu" class="m_menu">
                	<a class="m_menu-title"   href="#">决策支持<b><s></s></b></a>
                		<div style="width: 165px;" id="long-tab">
                    		<ul>
                				<li><a href="<s:url action="../stat/patientSingleMain.action" />"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />病人护理行为分析</a></li>
                				<li><a href="<s:url action="../stat/nursingEfficiencyStat.action" />"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />护士护理行为分析</a></li>
                				<li><a href="<s:url action="../stat/nursingStrengthStat.action" />"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />护理强度分析</a></li>
                				<li><a href="<s:url action="../stat/dataMiningStat.action" />"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />临床决策支持</a></li>                    		
                    		</ul>
                		</div>
                </li>
                <li id="cMenu" class="m_menu">
                	<a class="m_menu-title"  href="#" >配置管理<b><s></s></b></a>
                	<div>
                		<ul>
                			<li><a href="#">标签管理</a></li>
                			<li><a href="#">地图管理</a></li>
                			<li><a href="#">用户权限管理</a></li>
                			<li><a href="<s:url action="../stat/var-set-stat.action" />">参数配置</a></li>
                			<li><a href="<s:url action="../stat/user-right-stat.action" />">用户权限管理</a></li>
                			<li><a href="<s:url action="../stat/change-pswd-stat.action" />">修改密码</a></li>
                			<li><a href="<s:url action="../stat/add-user-stat.action" />">添加用户</a></li>
                		</ul>
                	</div>
                </li>
            </ul>

            <div class="gheader-i">
              <p> 欢迎&nbsp;<%=session.getAttribute("userName") %>&nbsp;医生
				&nbsp;|&nbsp;&nbsp;&nbsp;<a href="../logout.action" class="gheader-i-signup">退出</a>
              &nbsp;&nbsp;&nbsp;</p>
              <!-- 
              <p>选择病区:
	<select name="some_name" id="some_name" style="width:90px;">
	  <option value="option1">病区1</option>
  </select> 
</p>-->
          </div>
        </div>
        </div>
        </div>
        
        </body>
        </html>