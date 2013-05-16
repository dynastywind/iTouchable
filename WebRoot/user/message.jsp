<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	<link rel="stylesheet" type="text/css" href="<%=basePath%>user/css/message.css">
	<script src="<%=basePath%>user/js/message.js"></script> 
	<script src="<%=basePath%>user/js/message-chat.js"></script>
  
<ul class="message-nav">
	<li id="0" class="message-nav-li-curr">最新消息</li>
	<li id="1">好友留言</li>
	<li id="2">好友请求</li>
	<li id="3">邀请信息</li>
	<li id="4">系统消息</li>
</ul>
<div class="message">
	<div class="location_box">消息-好友消息-<span class="message_name">示例1</span>的聊天记录</div>
	<div class="message-page-box message-page-box-top">
	</div>
	<div class="message-1">
		
	</div>
	<div class="message-page-box message-page-box-bottom">
	</div>
</div>
