<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>user/studyhall/css/activity.css">
<script type="text/javascript" src="<%=basePath%>/user/studyhall/js/admin.js"></script>
<script type="text/javascript" src="<%=basePath%>/user/studyhall/js/admin_ajax.js"></script>

<ul class='hall_admin_nav'>
	<li id="0" class='hall_admin_nav_li hall_admin_nav_curr'>资料设置</li>
	<li id="1"  class='hall_admin_nav_li'>公告</li>
	<li id="2"  class='hall_admin_nav_li'>大厅成员</li>
</ul>
<div class="hall_admin_container">

<div   style="display:none;">
<div class="admin_info_box">
<!-- ajax 大厅资料 -->
</div>
<div class="admin_save_btn">保存修改</div>
</div>


<div style="display:none;">
<div class="admin_notice_function">共有<span class="admin_notice_all_count">0</span>条公告  <a href="javascript:;" class="amdin_add_notice_btn">添加公告</a></div>

<div class="admin_notice_add_box">
<a href="javascript:;" class="admin_notice_add_close">×</a>
	<textarea name="textarea" id="admin_notice_textarea"></textarea>
	<div class="admin_notice_save_btn">保  存</div>
	<div class="admin_notice_count">还可输入<span class="admin_notice_last_words">140</span>字</div>
</div>
	<div class="admin_notice_box">
	<!-- ajax  公告列表  -->
	</div>

</div>


<div>
<div class="admin_member_function">共有<span class="admin_member_all_count"></span>名成员，其中管理员0名  <a href="javascript:;" class="amdin_add_member_btn">邀请成员</a></div>
<div class="admin_member_add_box">
<a href="javascript:;" class="admin_member_add_close">×</a>
	<div class="admin_member_add_left">
		<div class="admin_member_add_title">邀请成员(可添加<span class="society_friends_count_allow">20</span>名，已选<span class="society_friends_count">2</span>名)<a href="javascript:;" class="society_friends_remove_all_btn">取消所有</a></div>
		<ul class="admin_member_selected_box">
		</ul>
		<div class="admin_member_add_btn">发出邀请</div>
	</div>
	
	<div class="admin_member_friends_box">
		<div class="society_friends_title">好友列表</div>
		<!-- ajax  好友列表  -->
   	</div>
</div>

<table width="620" border="0" cellspacing="1" cellpadding="0" class="info_member_tab">
  <tr>
    <th scope="col" width="50" align="center"><input type="checkbox" name="checkbox" value="checkbox" />全选</th>
    <th scope="col" width="70" align="center">大厅活跃度</th>
    <th scope="col" width="80" align="center">姓名</th>
    <th scope="col" width="50" align="center">性别</th>
    <th scope="col" width="150" align="center">学院</th>
    <th scope="col" width="80" align="center">年级</th>
    <th scope="col" align="center">&nbsp;</th>
  </tr>
  
  <tr>
  <td colspan="7" >
  <table width="620" border="0" cellspacing="1" cellpadding="0"  id="ajax_member">
  	<!-- ajax  成员列表  -->
  </table>
  </td>
  </tr>
  
  <tr>
    <th colspan="7"  align="left"><a href="javascript:;" class="member_ban_select_btn">禁言所选</a><a href="javascript:;" class="member_del_select_btn">移除所选</a></th>
  </tr>
</table>
</div>



</div>

