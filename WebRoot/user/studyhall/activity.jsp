<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>user/studyhall/css/activity.css">
<script type="text/javascript" src="<%=basePath%>/user/studyhall/js/activity.js"></script>

<ul class='hall_person_nav'>
	<li  a_type="a"  topic_type="0" class='hall_person_nav_li hall_person_nav_curr'>全部</li>
	<li a_type="m"  topic_type="0" class='hall_person_nav_li'>学习动态</li>
	<li a_type="t"  topic_type="0" class='hall_person_nav_li'>问答讨论</li>
	<li a_type="t"  topic_type="1" class='hall_person_nav_li'>待解决</li>
	<li a_type="t"  topic_type="2" class='hall_person_nav_li'>已解决</li>
	<li a_type="t"  topic_type="3" class='hall_person_nav_li'>精华讨论</li>
</ul>
<div class="refresh_div"><a href="javascript:;" title="刷新" class="refresh-topic" topic_type="0" a_type="a"><img src="<%=basePath%>/user/image/refresh.png" border="0" width="23" height="20" class="refresh_img" /></a></div>
<div class="hall_person_container">

</div>
<div class="hall_load_more" topic_type="0" a_type="a" page="1" user_id="0"><img src="<%=basePath%>/user/image/loading_ico.gif" width="32" height="32" class="more_img" /> 加载更多…</div>