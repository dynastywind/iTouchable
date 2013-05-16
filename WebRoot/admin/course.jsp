<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath%>admin/js/course.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>admin/css/course.css">

<div class="common_tab_box">
	<div class="common_tab_title">课程列表</div>
  				<table width="1130" border="0" cellspacing="1" cellpadding="0" align="center" class="common_tab_tab">
  				<tr class="tab_bottom_tr">
    					<th height="30"  colspan="13">
    						<div class="tab_find_box">
    						查找课程：<input type="text" value="关键词" class="tab_find_inupt">
    						</div>
    						<div class="input_btn tab_find_btn">查找</div>
    						<div class="tab_funcion_box"><div class="input_btn tab_function_btn">删除所选</div><div class="input_btn tab_function_btn">添加课程</div>
    						<div class="input_btn tab_function_btn">刷新列表</div></div>
    					</th>
  					</tr>
  					<tr class="tab_top_tr">
   						 <th height="30" width="20" class="tab_td_1">&nbsp;</th>
    					 <th width="30"><input type="checkbox" name="checkbox" value="checkbox" /></th>
    					<th width="70">课程编号</th>
    					<th width="200">名称</th>
    					<th width="70">课程章节</th>
    					<th width="70">知识点</th>
    					<th width="70">题目总量</th>
    					<th width="70">授课教师</th>
    					<th width="70">选课学生</th>
    					<th width="70">学习大厅</th>
    					<th width="70">学习动态</th>
    					<th width="130">创建时间</th>
    					<th>操作</th>
  					</tr>
  					<tr>
  					<td height="30"  colspan="13">
  					<table width="1129" border="0" cellspacing="1" cellpadding="0" align="center" class="common_tab_ajax">
  					<!-- ajax  -->
  					</table>
  					</td>
  					</tr>
  					<tr class="tab_bottom_tr">
    					<th height="30"  colspan="13">
    						<div class="tab_page_box">
    						</div>
    					</th>
  					</tr>
					</table>
</div>  				
