<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>在线学习</title>
    <link href="css/index.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/index.js"></script>
  </head>
  <body>
  <table width="1000" border="1" align="center">
  <tr>
    <td height="40" align="center"><h2>I-Touchable 智能学习平台-首页</h2></td>
  </tr>
  <tr>
    <td height="40">&nbsp;&nbsp;<a href="index.html">首页</a>
小组 问答&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="login.jsp">登录</a> <a href="regist.jsp">注册</a></td>
  </tr>
  <tr>
    <td height="300" align="center"><table width="168" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="168">
      用户名：<input name="id" type="text" value="10103278" id="id" />
     密码：<input name="password" type="password" value="1" id="password" />
            <input type="submit" name="Submit" id="submit-login"   value="提交" />
    </td>
  </tr>
</table></td>
  </tr>
  <tr>
    <td height="40" align="center"><a href="#">联系我们</a> <a href="#">关于我们</a> <a href="admin.jsp">管理登录</a></td>
  </tr>
</table>
  </body>
</html>