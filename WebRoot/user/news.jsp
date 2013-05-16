<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/user/css/news.css"/>
<script src="<%=basePath%>user/js/news.js"></script>

  <div class="news-body_div">
          <div class="new_dh_div">
          	<ul class="new_dh_ul">
          		<li class="refresh_all new_curr_li" topic_type="0" a_type="a">全部</li>
          		<li topic_type="0" a_type="m">学习动态</li>
          		<li topic_type="0" a_type="t">问答动态</li>
          		<li topic_type="1" a_type="t">待解决</li>
          		<li topic_type="2" a_type="t">已解决</li>
          		<li style="width:100px;" topic_type="3" a_type="t">精华讨论<img src="<%=basePath%>user/image/hot.png"  width="29" height="14"/></li>
          	</ul>
          	<ul>
          		<li style=" width:80px; float:right;">我参与的<div class="new_ico">12</div></li>
          		<li style=" width:80px; float:right;">我的动态</li>
          	</ul>
          </div>
		  <div class="refresh_div"><a href="javascript:;" title="刷新" class="refresh-topic" topic_type="0" a_type="a"><img src="<%=basePath%>/user/image/refresh.png" border="0" width="23" height="20" class="refresh_img" /></a></div>
			<div class="loading"><img src="<%=basePath%>/user/image/loading_ico.gif" width="32" height="32" /> 正在努力加载中！</div>	
		  <div class="new_content_div">
			  
		  </div>
		  <div class="load-more" topic_type="0" a_type="a" page="1"><img src="<%=basePath%>/user/image/loading_ico.gif" width="32" height="32" class="more-img" /> 加载更多…</div>
		
  </div>
