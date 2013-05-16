<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri =  "/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学互社区-管理员中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>admin/css/index.css">
	<script src="<%=basePath%>/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>admin/js/index.js"></script>

  </head>
  
  <body>
  <div class="header">
  	<img src="<%=basePath%>admin/image/logo_admin.png" class="logo_img" width="239" height="30" />
  	<div class="header_function">
  		<div class="header_function_left">当前在线：学生2<span class="line_divide"></span>教师0<span class="line_divide"></span>管理员0</div>
  		<div class="header_function_right">系统管理员：1 <a href="javascript:;">安全退出</a></div>
  	</div>
  </div>
  <div class="main">
  	<div class="main_left">
  		<div class="left_title"><span>管理中心</span></div>
  		<div class="left_nav_title"><span>社区动态</span></div>
  		<ul>
  			<li><a href="javascript:;">学习动态</a></li>
  			<li><a href="javascript:;">所有话题</a></li>
  			<li><a href="javascript:;">已解决话题</a></li>
  			<li><a href="javascript:;">未解决话题</a></li>
  			<li><a href="javascript:;">精华话题</a></li>
  		</ul>
  		<div class="left_nav_title"><span>帐号管理</span></div>
  		<ul style="display:none">
  			<li><a href="javascript:;">学生帐号</a></li>
  			<li><a href="javascript:;">教师帐号</a></li>
  			<li><a href="javascript:;">管理员帐号</a></li>
  		</ul>
  		<div class="left_nav_title"><span>教学管理</span></div>
  		<ul style="display:none">
  			<li><a href="javascript:;">课程管理</a></li>
  			<li><a href="javascript:;"> 题库管理</a></li>
  		</ul>
  		<div class="left_nav_title"><span>大厅管理</span></div>
  		<ul style="display:none">
  			<li><a href="javascript:;"></a></li>
  		</ul>
  	</div>
  	
  	
  	<div class="main_right">
  		<ul class="right_title">
  			<li class="right_nav_li_1 right_nav_li right_nav_li_curr" id="adminDefault"><span class="right_nav_title">首页</span></li>
  			<li class="right_nav_li" id="stuAccount"><span class="right_nav_title">学生帐号</span><span class="right_nav_close">×</span></li>
  			<li class="right_nav_li" id="course"><span class="right_nav_title">课程管理</span><span class="right_nav_close">×</span></li>
  			<li class="right_nav_li" id="questionBank"><span class="right_nav_title">题库管理</span><span class="right_nav_close">×</span></li>
  			<li class="right_nav_li" id="courseDetail"><span class="right_nav_title">C语言..设计</span><span class="right_nav_close">×</span></li>
  		</ul>
  		<div class="main_frame">
  			<!--  ajax  -->
  		</div>
  	</div>
  	
  </div>
  <div class="footer">
CopyRight © 2012-2013 I-Touchable 网络学习平台 后台管理 Ver1.0
  </div>
  <div class="pleasewait"><img src="<%=basePath%>/user/image/loading_ico.gif" width="32" height="32" /> 请稍等…</div>
  </body>
</html>
