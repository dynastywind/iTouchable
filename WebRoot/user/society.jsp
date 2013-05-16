<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	<link rel="stylesheet" type="text/css" href="<%=basePath%>user/css/society.css"/>
	<script src="<%=basePath%>user/js/society.js"></script>
  
<ul class="society_add_nav">
	<li class="society_add_li_curr">学习大厅</li>
	<li>创建大厅</li>
</ul>

<div class="society">
	<div class="society_1">
		<div class="society_search_box">
			<input name="keyword_society" type="text" id="keyword_society" value="关键词" /><div class="society_search_btn">搜索</div>
		</div>
	</div>
	
	
	<div class="society_2" >
   		<div class="society_form_1"><div class="society_form_left">大厅类型：</div>
   			<div class="society_form_right"><select name="society_type" id="society_type">
   				<option value="0">请选择</option>
   				<option value="1">自然班</option>
   				<option value="2">教学班</option>
   				<option value="3">学习小组</option>
   			</select></div>
   		</div>
   		<div class="society_form_2"><div class="society_form_left">名称：</div><div class="society_form_right"><input type="text" name="society_name" id="society_name" /></div></div>
   		<div class="society_form_2"><div class="society_form_left">学习课程：</div><div class="society_form_right"><select name="society_course" id="society_course">
   				<option value="0">请选择</option>
   				<option value="1">C语言</option>
   			</select></div></div>
   		<div class="society_form_2"><div class="society_form_left">指导老师：</div><div class="society_form_right"><select name="society_teacher" id="society_teacher">
   				<option value="0">请选择</option>
   				<option value="1">李军老师</option>
   				<option value="2">张小明老师</option>
   				<option value="0">不需要</option>
   			</select></div></div>
   		<div class="society_form_3"><div class="society_form_left">简单描述：</div>
   		<div class="society_form_right"><textarea name="society_info" id="society_info"></textarea></div></div>
   		<div class="society_form_5"><div class="society_form_left">邀请成员：</div>
   		<div class="society_form_right">
   		<div class="society_friends_selected_box" >
   				<ul class="society_friends_name_selected" id="society_friends_member">
   				</ul>
   				<div class="society_friends_funciton">可添加 <span class="society_friends_count_allow" >100</span> 个，已添加 <span class="society_friends_count">0</span> 个。<a href="javascript:;" class="society_friends_remove_all_btn">移除所有</a><a href="javascript:;" class="society_friends_add_btn">添加</a></div>
   			</div>
   				<div class="society_friends_box" id="society_friends_box_member">
   					<div class="society_friends_title">好友列表<span class="society_friends_close">×</span></div>
   					<!-- ajax -->
   				</div>
   		</div>
   		</div>
   		<div class="society_form_6">
   			<div class="society_add_btn">确认创建</div>
   		</div>
	</div>
</div>