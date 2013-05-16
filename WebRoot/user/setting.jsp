<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	<link rel="stylesheet" type="text/css" href="<%=basePath%>user/css/setting.css"/>
	<script src="<%=basePath%>user/js/setting.js"></script>
  
<ul class="setting_nav">
	<li id='0' class="setting_li_curr">个人资料</li>
	<li id='1' >密码设置</li>
	<li id='2' >头像设置</li>
	<li id='3' >隐私权限</li>
</ul>

<div class='setting_panel' id='person_info_box'>
<!--  个人资料  -->
</div>

<div class='setting_panel'  style='display:none;'>
<table border='0' cellspacing='0' cellpadding='0' class='setting_tab' >
<tr>
	<th colspan='2'>修改密码</th>
</tr>
<tr>
	<td colspan='2'>&nbsp;</td>
</tr>
<tr>
	<td align='right' width='250'>请输入旧密码：</td>
	<td><input type='password' id='password_old' class='setting_input_password'/><span class='password_tip'></span></td>
</tr>
<tr>
	<td align='right' width='250'>新密码：</td>
	<td><input type='password' id='password_new_1' class='setting_input_password'/><span class='password_new_tip'></span></td>
</tr>
<tr>
	<td align='right' width='250'>新密码确认：</td>
	<td><input type='password' id='password_new_2' class='setting_input_password'/><span class='password_repeat_tip'></span></td>
</tr>
</table>
<div class='setting_password_btn'>确认修改</div>
</div>


<div class='setting_panel' style='display:none;'>
<table border='0' cellspacing='0' cellpadding='0' class='setting_tab' >
<tr>
	<th colspan='2'>头像设置</th>
</tr>
<tr>
	<td colspan='2'>&nbsp;</td>
</tr>
</table>
<div class='setting_password_btn'>确认修改</div>
</div>


<div class='setting_panel' style='display:none;'>
<table border='0' cellspacing='0' cellpadding='0' class='setting_tab' >
<tr>
	<th colspan='2'>隐私权限</th>
</tr>
<tr>
	<td colspan='2'>&nbsp;</td>
</tr>
</table>
<div class='setting_password_btn'>保存修改</div>
</div>