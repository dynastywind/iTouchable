<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/index.css" type="text/css" rel="stylesheet" />

  </head>
  
  <body>
  <h3>登录</h3>
<hr />
<a href="index.jsp">返回首页</a>
<form id="form1" name="form1" method="post" action="StuLoginAction.do">
      用户名：<input name="id" type="text" id="id" />
     密码：<input name="password" type="password" id="password" />
            <input type="submit" name="Submit" value="提交" />
    </form>
	<s:if test="fieldErrors.loginError[0]!=null">
<table border="0" align="center" cellpadding="0" cellspacing="0" class="tip_tab">
  <tr>
    <td height="30" align="center"><s:property value="fieldErrors.loginError[0]"/></td>
  </tr>
</table> 
</s:if>
<s:if test="#parameters.y!=null">
<table border="0" align="center" cellpadding="0" cellspacing="0" class="tip_tab">
  <tr>
    <td height="30" align="center">对不起，请先登录！</td>
  </tr>
</table> 
</s:if>
  </body>
</html>
