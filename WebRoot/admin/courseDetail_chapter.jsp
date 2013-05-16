<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath%>admin/js/courseDetail_chapter.js"></script>

  				<table width="1130" border="0" cellspacing="1" cellpadding="0" align="center" class="common_tab_tab" id="1">
  				<tr class="tab_bottom_tr">
    					<th height="30"  colspan="11">
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
    					<th width="80">章节</th>
    					<th width="200">名称</th>
    					<th width="80">知识点</th>
    					<th width="180">练习题目</th>
    					<th width="80">测试题目</th>
    					<th width="80">章节练习</th>
    					<th width="80">掌握度</th>
    					<th width="130">添加时间</th>
    					<th>操作</th>
  					</tr>
  					<tr>
  					<td height="30"  colspan="11">
  					<table width="1129" border="0" cellspacing="1" cellpadding="0" align="center" class="common_tab_ajax">
  					<!-- ajax  -->
  					</table>
  					</td>
  					</tr>
  					<tr class="tab_bottom_tr">
    					<th height="30"  colspan="11">
    						<div class="tab_page_box">
    						<!-- page ajax  -->
    						</div>
    					</th>
  					</tr>
					</table>

					
<div class="common_dialog_box_min">
	<div class="common_dialog_title_min">
		<div class="common_dialog_title_left_min">添加章节 [C/C++程序设计]</div>
		<div class="common_dialog_close_min">×</div>
	</div>
	<div class="common_dialog_main_min">
		<table width="316" border="0" cellspacing="0" cellpadding="0" align="center" class="common_dialog_tab">
  <tr>
    <td height="70" width="75" align="center">章节名称：</td>
    <td>
    <input type="text" id="chapter_name_input" class="common_dialog_input common_dialog_input_big" />
    	</td>
  </tr>
  <tr>
    <td colspan="2" align="right"><div class="input_btn" id="chapter_add_btn">确 认</div></td>
  </tr>
</table>
	</div>
	<div class="common_dialog_bottom_min">
		<div class="common_dialog_tip_min"></div>
		<div class="common_dialog_autoclose"><input type="checkbox" class="auto_close" id="auto_close" value="1" checked="checked" /><label for="auto_close">自动关闭</label></div>
	</div>
</div>