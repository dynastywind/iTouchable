<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>user/css/examCenter.css"/>
<script src="<%=basePath%>user/js/examCenter.js"></script>

<ul class="exam_nav">
	<li class="exam_li_curr">考试练习</li>
</ul>
<ul class="exam_course_nav">
	<li class="exam_course_nav_curr"><span class="exam_course_name">C语言</span><span class="exam_course_pointer"></span></li>
</ul>
<div class="exam_box">
	<div class="exam">
		<ul class="exam_type">
			<li class="exam_type_curr">智能测试</li>
			<li>章节练习</li>
			<li>模拟考试</li>
		</ul>
		<div class="exam_list_box">
			<div class="exam_list">
				<div class="exam_list_1"><a href="javascript:;" class="exam_list_title">第一章 C语言</a></div>
				<div class="exam_list_2"></div>
			</div>
			<div class="exam_list">
				<div class="exam_list_1"><a href="javascript:;" class="exam_list_title">第二章 C语言</a></div>
				<div class="exam_list_2"></div>
			</div>
			<div class="exam_list">
				<div class="exam_list_1"><a href="javascript:;" class="exam_list_title">第三章 C语言</a></div>
				<div class="exam_list_2"></div>
			</div>
		</div>
	</div>
</div>