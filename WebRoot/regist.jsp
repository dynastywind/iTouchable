<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h3>用户注册</h3>
<hr />
<form name="form1" method="post" action="StuRegistAction.do">
  <table width="500" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="30" align="right">学号：</td>
      <td><input name="stu.id" type="text" id="stu.id"></td>
    </tr>
    <tr>
      <td width="196" height="30" align="right">姓名：</td>
      <td width="304"><input name="stu.name" type="text" id="stu.name"></td>
    </tr>
    <tr>
      <td height="30" align="right">密码：</td>
      <td><input name="stu.password" type="text" id="stu.password"></td>
    </tr>
    <tr>
      <td height="30" align="right">密码确认：</td>
      <td><input name="password2" type="password" id="password2"></td>
    </tr>
    <tr>
      <td height="40" colspan="2" align="center"><input type="image" name="imageField" src="image/regist.png"></td>
    </tr>
  </table>
</form>
<a href="index.jsp">返回首页</a>
  </body>
  </body>
</html>
