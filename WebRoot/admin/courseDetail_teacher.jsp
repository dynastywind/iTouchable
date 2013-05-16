<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath%>admin/js/courseDetail_teacher.js"></script>

  				<table width="1130" border="0" cellspacing="1" cellpadding="0" align="center" class="common_tab_tab" id="1">
  				<tr class="tab_bottom_tr">
    					<th height="30"  colspan="12">
    						<div class="tab_find_box">
    						查找记录：<input type="text" value="关键词" class="tab_find_inupt">
    						</div>
    						<div class="input_btn tab_find_btn">查找</div>
    						<div class="tab_select_box">
    						</div>
    						<div class="tab_funcion_box"><div class="input_btn tab_function_btn">删除所选</div><div class="input_btn tab_function_btn tab_function_add_btn" >添加记录</div>
    						<div class="input_btn tab_function_btn tab_function_refresh_btn">刷新列表</div></div>
    					</th>
  					</tr>
  					<tr class="tab_top_tr">
   						 <th height="30" width="20" class="tab_td_1">&nbsp;</th>
    					 <th width="30"><input type="checkbox" name="checkbox" value="checkbox" /></th>
    					<th width="70">工号</th>
    					<th width="120">姓名</th>
    					<th width="70">职称</th>
    					<th width="150">学校</th>
    					<th width="150">院系</th>
    					<th width="140">最近登录</th>
    					<th width="70">活跃度</th>
    					<th width="70">当前状态</th>
    					<th width="130">添加时间</th>
    					<th>操作</th>
  					</tr>
  					<tr>
  					<td height="30"  colspan="12">
  					<table width="1129" border="0" cellspacing="1" cellpadding="0" align="center" class="common_tab_ajax">
  					<!-- ajax  -->
  					</table>
  					</td>
  					</tr>
  					<tr class="tab_bottom_tr">
    					<th height="30"  colspan="12">
    						<div class="tab_page_box">
    						<!-- page ajax  -->
    						</div>
    					</th>
  					</tr>
					</table>

					
