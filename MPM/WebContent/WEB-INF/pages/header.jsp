<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<body>

        <div class="gheader-wp">
        <div class="gheader-wp-b">
        <div class="gheader">
            <a class="gheader-logo"  href="/" title="医疗行为管理系统"></a>
            
            <ul class="gheader-nav">
                <li><a href="../mainpage/main-page.action">行为监控</a></li>
                
                
                
                				
				<li id="aMenu" class="m_menu">
                	<a class="m_menu-title" >告警纪录<b><s></s></b></a>
                		<div>
                    		<ul>
                				<li><a href="<s:url action='../alert/nurse-leave-alert.action' />"><img src="<s:url value='/styles/image/nurse.png'/>" width="20" height="20" />护士离开病区告警</a></li>
                				<li><a href="<s:url action='../alert/patient-leave-alert.action' />"><img src="<s:url value='/styles/image/patient.png'/>" width="20" height="20" />病人离开病区告警</a></li>
                    		</ul>
                		</div>
                </li>
				
                <li id="mMenu" class="m_menu">
                	<a class="m_menu-title" >统计分析<b><s></s></b></a>
                		<div>
                    		<ul>
                				<li><a href="<s:url action="../stat/doctor-rounds-stat.action" />"><img src="<s:url value='/styles/image/doctor.png'/>" width="20" height="20" />医生查房统计</a></li>
                				<li><a href="<s:url action="../stat/nurse-rounds-stat.action" />"><img src="<s:url value='/styles/image/nurse.png'/>" width="20" height="20" />护士查房统计</a></li>
                				<li><a href="<s:url action="../stat/nurse-patient-stat.action" />"><img src="<s:url value='/styles/image/nurse.png'/>" width="20" height="20" />护士病人接触统计</a></li>
                				<li><a href="<s:url action="../stat/nurse-leave-stat.action" />"><img src="<s:url value='/styles/image/nurse.png'/>" width="20" height="20" />护士离开病区统计</a></li>
                				<li><a href="<s:url action="../stat/patient-rounded-stat.action" />"><img src="<s:url value='/styles/image/patient.png'/>" width="20" height="20" />病人查房统计</a></li>
                    		</ul>
                		</div>
                </li>
                <li>
                	<a href="" >卡片管理<b><s></s></b></a>
                	<div>
                		<ul>
                			<li><a>医护人员卡片</a></li>
                			<li><a>病人卡片</a></li>
                		</ul>
                	</div>
                </li>
                <li>
                	<a href="" >系统设置<b><s></s></b></a>
                	<div>
                		<ul>
                			<li><a>参数配置</a></li>
                			<li><a>用户管理</a></li>
                		</ul>
                	</div>
                </li>
            </ul>

            <div class="gheader-i">
              <p> 欢迎&nbsp;张华&nbsp;医生
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