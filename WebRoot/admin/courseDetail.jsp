<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath%>admin/js/courseDetail.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>admin/css/courseDetail.css">

<div class="common_tab_box">
	<div class="common_tab_title">[课程管理] C语言程序设计</div>
	<ul class="common_right_nav">
		<li id="courseDetailInfo">课程信息</li>
		<li id="courseDetailChapter">课程章节</li>
		<li id="courseDetailConcept">知识点</li>
		<li id="courseDetailProblem" class="common_right_nav_li_curr">课程题库</li>
		<li id="courseDetailTeacher">授课教师</li>
		<li id="courseDetailStudent">选课学生</li>
		<li id="courseDetailHall">学习大厅</li>
		<li id="courseDetailActivity">学习动态</li>
	</ul>
<div  class="course_frame">
<!--  ajax  -->
</div>
</div>  				
