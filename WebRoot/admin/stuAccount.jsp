<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri =  "/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script src="<%=basePath%>admin/js/stuAccount.js"></script>

<div class="common_tab_box">
	<div class="common_tab_title">学生帐号</div>
  				<table width="1130" border="0" cellspacing="1" cellpadding="0" align="center" class="common_tab_tab">
  				<tr class="tab_bottom_tr">
    					<th height="30"  colspan="12">
    						<div class="tab_find_box">
    						查找学生：<input type="text" value="关键词" class="tab_find_inupt">
    						</div>
    						<div class="input_btn tab_find_btn">查找</div>
    						<div class="tab_funcion_box"><div class="input_btn tab_function_btn">删除所选</div><div class="input_btn tab_function_btn">添加学生</div>
    						<div class="input_btn tab_function_btn">刷新列表</div></div>
    					</th>
  					</tr>
  					<tr class="tab_top_tr">
   						 <th height="30" width="20" class="tab_td_1">&nbsp;</th>
    					 <th width="30"><input type="checkbox" name="checkbox" value="checkbox" /></th>
    					<th width="100">姓名</th>
    					<th width="70">学号</th>
    					<th width="120">学校</th>
    					<th width="120">学院</th>
    					<th width="70">年级</th>
    					<th width="140">最近登录</th>
    					<th width="70">积分</th>
    					<th width="100">当前状态</th>
    					<th width="130">添加时间</th>
    					<th>操作</th>
  					</tr>
  					<tr class="tab_bottom_tr">
    					<th height="30"  colspan="12">
    						<table width="1129" border="0" cellspacing="1" cellpadding="0" align="center" class="common_tab_ajax">
  								<!-- ajax  -->
  							</table>
    					</th>
  					</tr>
  					<tr class="tab_bottom_tr">
    					<th height="30"  colspan="12">
    						<div class="tab_page_box">
    						</div>
    					</th>
  					</tr>
					</table>
</div>  				
