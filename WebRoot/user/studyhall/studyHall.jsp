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
    
    <title>学习大厅-<s:property value="society.name" />-E一天</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>user/studyhall/css/studyHall.css">
	<script src="<%=basePath%>/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/user/studyhall/js/function.js"></script>
	<script type="text/javascript" src="<%=basePath%>/user/js/tip.js"></script>
	<script type="text/javascript" src="<%=basePath%>/user/studyhall/js/studyHall.js"></script>

  </head>
  
  <body>
  <div class="head_div">
  	<div class="head_center_div"><div class="head_title_div">学互网 智能网络学习互动平台</div><div class="head_function_div"><a href="user/studentCenter.jsp" style=" border-left:0px;">个人中心</a><a href="user/homepage/index.jsp">我的主页</a><a href="StuLogoutAction.do" style="border-right:0px;">安全退出</a></div></div>
</div>
  
  <div class="bann_contain_div">
  	<div class="bann_div">
		<img src="<%=basePath%>user/studyhall/image/logo-hall.png" class="logo-img" width="300" height="90" />
	  
	</div>
  </div>

<div class="hall-body">
	<div class="hall-left">
		<div class="hall-left-info">
			<img src="<%=basePath%>user/studyhall/image/examle.jpg" class="info-img" width="50" height="50" />
			<div class="hall-left-info-right">
				<div class="hall-left-info-right-1"><s:property value="society.society_name" /></div>
				<div class="hall-left-info-right-2">类别：<s:if test="society.society_type == 1">自然班</s:if>
				<s:elseif test="society.society_type == 2">教学班</s:elseif>
				<s:else>学习小组</s:else>
				</div>
			</div>
		</div>
		<div class="hall-left-title">公告(<span class="hall_notice_count">1</span>)<a href="javascript:;" class="hall_admin_btn" id="1">大厅管理</a><a href="javascript:;" class="hall_admin_btn" id="0">大厅资料</a></div>
		<div class="hall-left-notice" id="<s:property value="c" />">
			<!--  ajax  -->
		</div>
		<div class="hall_notice_all"><a href="javascript:;" class="hall_admin_btn" id="0" t_id="1">查看全部</a></div>
		<div class="hall-left-title">成员(<span class="hall_member_count"></span>)</div>
		<div class="hall-left-member">
			<div class="hall-member-list hall-member-curr" user_id="0">全部</div>
			<!--  ajax -->
		</div>
		<div class="hall_member_all"><a href="javascript:;" class="hall_admin_btn" id="0" t_id="2">查看全部</a></div>
	</div>
	<div class="hall-right">
		<div class="hall-right-nav">
			<ul>
				<li class="hall_activity_nav hall-nav-li hall-nav-li-curr">大厅动态</li>
				<li class="hall-nav-li">讨论板</li>
				<li class="hall-nav-li">成绩统计</li>
			</ul>
		</div>
		<div class="hall-movement-container">
		<!--  ajax  -->
		</div>
	</div>
</div>


<div class="foot_div">
	  	<div class="foot_center_div">
			<div class="foot1"><a href="#">关于学互</a>｜<a href="#">联系建议</a>｜<a href="#">帮助中心</a></div>
			<div class="foot2"><span class="en-span">CopyRight &copy; 2012 I-Touchable</span> 智能网络学习平台</div>
			<div class="gz">关注我们 <img src="image/gz_renren.png" width="30" height="30" border="0" /><img src="image/gz_qq.png" width="30" height="30" /><img src="image/gz_sina.png" width="30" height="30" /></div>
		</div>
</div>
<div class="fixed-box">
  <img src="<%=basePath%>user/studyhall/image/examle.jpg" class="add-new-img" width="35" height="35" />
  <textarea name="new_text" id="new_text" class="add-new-input"></textarea>
  <img src="<%=basePath%>user/studyhall/image/soso-btn.gif" width="87" height="44" class="soso-btn" />
   <img src="<%=basePath%>user/studyhall/image/ask-btn.gif" width="110" height="44" id="submit-topic" class="ask-btn" />
</div>
 <div class="pleasewait"><img src="<%=basePath%>/user/image/loading_ico.gif" width="32" height="32" /> 请稍等…</div>
  </body>
</html>
