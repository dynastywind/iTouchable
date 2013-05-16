<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	<link rel="stylesheet" type="text/css" href="<%=basePath%>user/css/friends.css"/>
	<script src="<%=basePath%>user/js/friends.js"></script>
	<script type="text/javascript" src="<%=basePath%>/user/js/add_group.js"></script>
	<script src="<%=basePath%>user/js/friends-ajax.js"></script>
	<script src="<%=basePath%>user/js/friends-search.js"></script>
	<script src="<%=basePath%>user/js/friends-find.js"></script>
   

<ul class="friends-nav">
	<li class="friends-nav-li-curr">好友管理</li>
	<li>寻找好友</li>
</ul>
<div class="friends_div">
<div class="friends-1">

    <div class="friends-find-box">
	<input name="keyword_find" type="text" class="input-text" id="keyword_find" value="姓名/学校/关键词" /><div class="friend-find-btn">查找</div>
	</div>

  <div class="friends_left_div">
  	<div class="freinds_list_div">
		<div class="left_title_div"><span class="group_title_name" id=""></span><div class="freinds_page_div"></div>
		</div>
		<ul id="friends-list-box"><!-- 加载好友列表  --></ul>
		<div class="freinds_list_bottom_div"><div class="freinds_page_div"></div></div>
	</div>
  </div>
  
	<div class="friends_right_div">
		
		<div class="comtact_div">
			<div class="right_title_div">好友列表<a href="javascript:;" class="add-group-btn">新分组</a></div> 
			<ul class="freinds_ul">
				
				
			</ul>
		<div class="comtact_bottom_div"></div>
		</div>
	</div>
</div>
<div class="friends-2">
	<div class="friends-soso-box">
	<input name="keyword" type="text" class="input-text" id="keyword" value="姓名/学校/关键词" /><div class="friend-soso-btn">搜索</div>
	</div>
	
	<div class="friends-result-box">
		<div class="friends-recommend-title">搜索结果</div>
		<div class="friends-result-page result-page-top"><!-- 分页 --></div>
		<div class="friends-result-list-box">
				<!-- 结果列表 -->
		</div>
		<div class="friends-result-page"><!-- 分页 --></div>
	</div>
	<div class="friends-recommend-box">
		<div class="friends-recommend-title">好友推荐</div>
	</div>
</div>
</div>
