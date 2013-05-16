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
    <script type="text/javascript" src="<%=basePath%>js/admin.js"></script>
  </head>
  <body>
  <table width="1000" border="1" align="center">
  <tr>
    <td height="40" align="center"><h2>I-Touchable 智能学习平台-管理登录</h2></td>
  </tr>
  <tr>
    <td height="400" align="center"><table width="168" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="168">
     帐号：<input name="id" type="text" value="1" id="id" />
     密码：<input name="password" type="password" value="1" id="password" />
            <input type="submit" name="Submit" id="submit-login"   value="提交" />
    </td>
  </tr>
</table></td>
  </tr>
</table>
  </body>
</html>