<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri =  "/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="common_tab_box">
	<div class="common_tab_title">课程题库</div>
  				<table width="1130" border="0" cellspacing="1" cellpadding="0" align="center" class="common_tab_tab">
  				<tr class="tab_bottom_tr">
    					<th height="30"  colspan="9">
    						<div class="tab_find_box">
    						查找题目：<input type="text" value="关键词" class="tab_find_inupt">
    						</div>
    						<div class="input_btn tab_find_btn">查找</div>
    						<div class="tab_funcion_box"><div class="input_btn tab_function_btn">删除所选</div><div class="input_btn tab_function_btn">添加课程</div>
    						<div class="input_btn tab_function_btn">刷新列表</div></div>
    					</th>
  					</tr>
  					<tr class="tab_top_tr">
   						 <th height="30" width="20" class="tab_td_1">&nbsp;</th>
    					 <th width="30"><input type="checkbox" name="checkbox" value="checkbox" /></th>
    					<th width="100">章节</th>
    					<th width="70">难度</th>
    					<th width="120">类型</th>
    					<th width="300">题目</th>
    					<th width="70">正确率</th>
    					<th width="200">知识点</th>
    					<th>操作</th>
  					</tr>
  					<tr>
    					<td height="30" class="tab_td_1">1</td>
    					<td><input type="checkbox" name="checkbox" value="checkbox" /></td>
    					<td>&nbsp;</td>
    					<td>&nbsp;</td>
    					<td>&nbsp;</td>
    					<td>&nbsp;</td>
    					<td>&nbsp;</td>
    					<td>&nbsp;</td>
    					<td>&nbsp;</td>
  					</tr>
  					<tr class="tab_bottom_tr">
    					<th height="30"  colspan="9">
    						<div class="tab_page_box">
    							<a href="javascript:;">首页 </a>
    							<a href="javascript:;">上一页 </a>
    							<a href="javascript:;">1</a>
    							<a href="javascript:;">2</a>
    							<a href="javascript:;">3</a>
    							<a href="javascript:;">下一页</a>
    							<a href="javascript:;">末页</a>
    						</div>
    					</th>
  					</tr>
					</table>
</div>  				
