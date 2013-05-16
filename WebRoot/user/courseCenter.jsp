<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>user/css/courseCenter.css"/>
<script src="<%=basePath%>user/js/courseCenter.js"></script>

<ul class="course_nav">
	<li class="course_li_curr">课程中心</li>
	<li>我的课程</li>
</ul>
<div>

</div>