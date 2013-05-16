<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>user/studyhall/css/activity.css">
<script type="text/javascript" src="<%=basePath%>/user/studyhall/js/info.js"></script>

<ul class='hall_info_nav'>
	<li id="0"  class='hall_info_nav_li hall_info_nav_curr'>大厅资料</li>
	<li id="1"  class='hall_info_nav_li'>公告</li>
	<li id="2"  class='hall_info_nav_li'>大厅成员</li>
</ul>
<div class="hall_info_container">
<div>
	<s:property value="info_HTML" escape="false"/>
</div>
<div style="display:none;">
	<s:property value="notice_HTML" escape="false"/>
</div>
<div style="display:none;">
	<s:property value="member_HTML" escape="false"/>
</div>

</div>
