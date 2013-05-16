<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri =  "/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/>
    <title>学生管理中心-E一天</title>
    <meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/user/css/studentCenter.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/user/css/chat.css"/>
	<script src="<%=basePath%>/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/user/js/function.js"></script>
	<script type="text/javascript" src="<%=basePath%>/user/js/warning.js"></script>
	<script type="text/javascript" src="<%=basePath%>/user/js/tip.js"></script>
	<script type="text/javascript" src="<%=basePath%>/user/js/chat_list_dialog.js"></script>
	<script type="text/javascript" src="<%=basePath%>/user//js/studentCenter.js"></script>
	<script type="text/javascript" src="<%=basePath%>/user//js/chat.js"></script>
	<script src="<%=basePath%>user/js/dialog.js"></script>
	
	
<!--[if lt IE 10 ]>
<script src="<%=basePath%>js/PIE.js"></script>
<![endif] -->
<script type="text/javascript">
		$(function(){
			
			$(function() {
   				 if (window.PIE) {
      				  $('.rounded').each(function() {
         			   PIE.attach(this);
       			 		});
  					  }
			});
		});
		
	</script>
<style>
#nav { behavior: url(<%=basePath%>js/pie.htc);}
</style>
</head>

<body>
  <div class="head_div">
  	<div class="head_center_div"><div class="head_title_div"><span>学互社区</span>大学生学习互动平台</div>
  	<div class="head_function_div">
  <span>刘柏众</span>
  <div class="head_home_menu">
  		<a href="user/studentCenter.jsp">个人中心</a>
  		<a href="javascript:;">我的主页</a>
  		<a href="user/logout"   class="head_logout_a">安全退出</a>
  	</div>
  	</div>
  	
  	
  
  
  </div>
  </div>
 
  <div class="bann_contain_div">
  	<div class="bann_div">
		<img src="<%=basePath%>/image/logo-new.png" class="logo-img" width="300" height="90" />
	  <div class="add-box">
			<textarea name="new_text" id="new_text" class="add-textarea"></textarea>
			<img src="<%=basePath%>user/image/new-ask.png"  id="submit-topic"   width="119" class="ask-img" height="28" />	
			<img src="<%=basePath%>user/image/new-soso.png"  class="soso-img" width="84" height="28" />
	</div>
	</div>
  </div>  
	
	<!-- body_div -->
	<div class="body_div" id="nav">
		<!-- body-top -->
		<div class="body-top">
			<div class="top-left"><a href="javascript:;"><img src="<%=basePath%>/user/userfile/10103278/img/examle.jpg" alt="" width="70" height="70" border="0" class="info-img"/></a>
			<div class="info-name"><s:property value="student.name" /></div>
			<div class="info-xh"><s:property value="student.id_number" /></div>
			<div class="info-rand">13级</div>
			<div class="info-school"><s:property value="student.school" /> <s:property value="student.classes" /></div>
			</div>
			<div class="top-center">
				
			</div>
			<div class="top-right">
				
			</div>
		 </div>
	 <!-- end body-top -->
		 <!-- left -->
	 <div class="body_left_div">
	<div class="left_dh_div">
      <div  id="left_title0" class="dh_title"><img src="<%=basePath%>image/dh_title_xh.png" width="73" height="22" /></div>
       <ul class="center_nav_ul">
	  <li class="left_curr_li"><a id="getTopicList" href="javascript:;">社区动态</a></li>
	  <li><a id="myTopic" href="javascript:;">我的问答</a></li>
	  <li><a id="getFriendsList"  href="javascript:;">好友</a></li>
	  <li><a id="message" href="javascript:;" style=" float:left; margin-left:0px;">消息</a><div class="message_new_div">2</div></li>
	  <li><a id="setting"  href="javascript:;" >设置</a></li>
	  </ul>
	  <div class="dh_title"><img src="<%=basePath%>image/dh_title_zx.png" width="73" height="22" /></div>
	  <ul class="center_nav_ul">
	  <li><a id="courseCenter" href="javascript:;" >课程中心</a></li>
	  <li><a id="examCenter" href="javascript:;" >考试练习</a></li>
	  </ul>
	   <div class="dh_title"><img src="<%=basePath%>image/dh_title_dt.png" width="73" height="22" /></div>
	  <ul class="center_nav_ul">
	  		<li><a href="javascript:;" id="getSociety" >学习大厅</a></li>
	  </ul>
	  <div class="center_studyhall_contain">
	  <!-- ajax  -->
      </div>
	 </div>
	 </div>
	 <!-- end left -->
	<!-- right -->
	 <div class="center-body_right_div">
	 	<div  id="frame_main" ></div>
	 </div>
  	</div>
	<!-- end right -->
	
	<!-- end body_div -->
	
	 <!-- foot -->
	 <div class="foot_div">
	  	<div class="foot_center_div">
			<div class="foot1"><a href="#">关于学互</a>｜<a href="#">联系建议</a>｜<a href="#">帮助中心</a></div>
			<div class="foot2"><span class="en-span">CopyRight &copy; 2012 I-Touchable</span> 网络学习平台</div>
			<div class="gz">关注我们 <img src="<%=basePath%>image/gz_renren.png" width="30" height="30" border="0" /><img src="<%=basePath%>image/gz_qq.png" width="30" height="30" /><img src="<%=basePath%>image/gz_sina.png" width="30" height="30" /></div>
		</div>
 	 </div>
 	 
  	<!-- end foot -->
  
  <!-- fix -->
  
  <div class="_Friends_contain">
  	<div class="_Friends_nav">
  		<div class="_Friends_title" title="展开列表"> <img src="<%=basePath%>user/image/litter_person.png" width="10" height="12" /> 最近联系</div>
		<div id="_Friends_ajax_box">
			<div class="_Friends_list_loading"></div>
		<!-- 好友列表 -->
		</div>
		<!-- /_Friends_ajax_box -->
		<div class="_Friends_bottom" title="折叠所有"></div>	
	</div>
	
	
	<ul class="_Chat_name_nav">
	<!-- 载入聊天人姓名  -->
	</ul>
	
	<div class="_Message_contain">
     <!-- 载入聊天框 -->
	</div>
	
</div>
  
 <!-- end fix -->
 <div class="pleasewait"><img src="<%=basePath%>/user/image/loading_ico.gif" width="32" height="32" /> 请稍等…</div>
</body>
</html>
	
	
