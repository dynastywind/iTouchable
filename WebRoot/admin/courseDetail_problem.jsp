<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath%>admin/js/courseDetail_problem.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>admin/css/courseDetail_problem.css">

  				<table width="1130" border="0" cellspacing="1" cellpadding="0" align="center" class="common_tab_tab" id="1">
  				<tr class="tab_bottom_tr">
    					<th height="30"  colspan="12">
    						<div class="tab_find_box">
    						查找题目：<input type="text" value="关键词" class="tab_find_inupt">
    						</div>
    						<div class="input_btn tab_find_btn">查找</div>
    						<div class="tab_select_box">
    						题型：<select class="tab_select_input"  id="tab_sub_type">
    								<option value="0"> -全部-</option>
    								<option value="1">选择题</option>
    								<option value="2">填空题</option>
    								<option value="3">编程题</option>
    						</select>
    						类型：<select class="tab_select_input" id="tab_type">
    								<option value="0"> -全部-</option>
    								<option value="1">练习题</option>
    								<option value="2">测试题</option>
    						</select>
    						 课程章节：
    						 <select class="tab_select_input" id="tab_select_chapter_input">
    						 <!--  ajax  -->
    						</select>
    						</div>
    						<div class="tab_funcion_box"><div class="input_btn tab_function_btn">删除所选</div><div class="input_btn tab_function_btn tab_function_add_btn">添加题目</div>
    						<div class="input_btn tab_function_btn tab_function_refresh_btn">刷新列表</div></div>
    					</th>
  					</tr>
  					<tr class="tab_top_tr">
   						 <th height="30" width="20" class="tab_td_1">&nbsp;</th>
    					 <th width="30"><input type="checkbox" name="checkbox" value="checkbox" /></th>
    					<th width="200">课程章节</th>
    					<th width="70">题型</th>
    					<th width="200">题目</th>
    					<th width="40">难度</th>
    					<th width="70">知识点</th>
    					<th width="70">类型</th>
    					<th width="70">已做次数</th>
    					<th width="50">正确率</th>
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
    						<!--  ajax  -->
    						</div>
    					</th>
  					</tr>
					</table>
					
					
<div class="common_dialog_box">
	<div class="common_dialog_title">
		<div class="common_dialog_title_left">添加题目 [C/C++程序设计]</div>
		<div class="common_dialog_close" title="关闭">×</div>
	</div>
	<ul class="common_dialog_menu">
		<li id="1"  class="common_dialog_menu_li_1 common_dialog_menu_li_curr">选择题</li>
		<li id="2" >填空题</li>
		<li id="3" >编程题</li>
	</ul>
	<div class="common_dialog_main">
	
	
	
<table width="750" border="0" cellspacing="0" cellpadding="0" align="center" class="common_dialog_tab">
  <tr>
    <td height="30" width="75" align="right">章节：</td>
    <td>
    <select class="tab_select_input" id="problem_chapter_input">
    <!--  ajax  -->
    </select></td>
  </tr>
  <tr>
    <td height="60" align="right" valign="top">题目：</td>
    <td><textarea class="common_dialog_textarea" id="problem_content_input"></textarea></td>
  </tr>
   <tr id="mainToUser_tr">
    
  </tr>
  <tr id="other_tr">

  </tr>
 
<tr id="question_option_tr">
   <!--  选择题选项 -->
    <td height="40" align="right" valign="top">选项：</td>
				    <td>
				    	<input type="text" name="textfield" class="common_dialog_input problem_option_input" />
						<div class="problem_option_weight_div">权重：<select class="tab_select_input problem_select_option_weight_input">
							<option value="0">0</option>
							<option value="0.1">0.1</option>
							<option value="0.2">0.2</option>
							<option value="0.25" selected="selected">0.25</option>
							<option value="0.3">0.3</option>
							<option value="0.4">0.4</option>
							<option value="0.5">0.5</option>
						</select></div>
				    	<span class="select_option_span">A.</span>
				    	<input type="text" name="textfield" class="common_dialog_input problem_option_input" /><div class="problem_option_weight_div">权重：<select class="tab_select_input problem_select_option_weight_input">
							<option value="0">0</option>
							<option value="0.1">0.1</option>
							<option value="0.2">0.2</option>
							<option value="0.25" selected="selected">0.25</option>
							<option value="0.3">0.3</option>
							<option value="0.4">0.4</option>
							<option value="0.5">0.5</option>
						</select></div>
				    	<span class="select_option_span">B.</span>
						<input type="text" name="textfield" class="common_dialog_input problem_option_input" /><div class="problem_option_weight_div">权重：<select class="tab_select_input problem_select_option_weight_input" >
							<option value="0">0</option>
							<option value="0.1">0.1</option>
							<option value="0.2">0.2</option>
							<option value="0.25" selected="selected">0.25</option>
							<option value="0.3">0.3</option>
							<option value="0.4">0.4</option>
							<option value="0.5">0.5</option>
						</select></div>
						<span class="select_option_span">C.</span>
						<input type="text" name="textfield" class="common_dialog_input problem_option_input" /><div class="problem_option_weight_div">权重：<span class="last_weight">0.25</span></div>
						<span class="select_option_span">D.</span>
				    </td>
 </tr>
  
  <tr>
    <td height="40" align="right">答案：</td>
    <td id="question_key_td">
     答案
     <select class="tab_select_input" id="problem_select_key_input">
					<option value="0"> - 请选择 - </option>
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="C">C</option>
					<option value="D">D</option>
					</select>
    </td>
  </tr>
  <tr>
    <td height="30" align="right" valign="top">解析：</td>
    <td><textarea class="common_dialog_textarea" id="problem_tip_input"></textarea></td>
  </tr>
  <tr id="mainToTest_tr">
    
  </tr>
  <tr id="keyToTest_tr">
   
   </tr>
  <tr>
    <td height="40" align="right">类型：</td>
    <td><select class="tab_select_input" id="problem_type_input">
    	<option value="0"> - 请选择 - </option>
    	<option value="1">练习题</option>
    	<option value="2">测试题</option>
    </select><span class="common_dialog_block_span">&nbsp;</span>难度：<select class="tab_select_input" id="problem_hard_input">
    	<option value="0"> - 请选择 - </option>
    	<option value="1">1</option>
    	<option value="2">2</option>
    	<option value="3">3</option>
    	<option value="4">4</option>
    	<option value="5">5</option>
    </select><span class="common_dialog_note">(数值越大，难度最高)</span></td>
  </tr>
  <tr>
    <td height="50" align="right" valign="top">知识点：</td>
    <td valign="top">
		<ul class="common_dialog_point_box">
		<!-- seleted --> 
		</ul>
		<a class="common_dialog_point_set" href="javascript:;">修改权重</a><a class="common_dialog_point_add_open" href="javascript:;">添加</a><span style="float:left;" class="common_dialog_note">(最多可添加10个)</span>
	</td>
  </tr>
  <tr>
    <td colspan="2" align="center"><div class="input_btn" id="add_problem_btn" style="margin-top:-20px;">保 存</div></td>
  </tr>
</table> 
	</div>
	
<div class="problem_min_box problem_min_option" >
<div class="problem_min_title">
	<div class="problem_min_title_left">课程知识点</div>
	<div class="problem_min_close problem_min_option_close" title="关闭">×</div>
</div>
	<ul id="problem_knowledge_ul">
	<!--  ajax  -->
	</ul>	
</div>

<div class="problem_min_box problem_min_setweight">
<div class="problem_min_title">
	<div class="problem_min_title_left">设置知识点权重</div>
	<div class="problem_min_close problem_min_setweight_close" title="关闭">×</div>
</div>
	<ul id="problem_setweight_ul">
		<li>1.指针<select class="tab_select_input setweight_input" id="problem_hard_input">
    	<option value="0">0</option>
    	<option value="0.1">0.1</option>
    	<option value="0.2">0.2</option>
    	<option value="0.25">0.25</option>
    	<option value="0.3">0.3</option>
    	<option value="0.4">0.4</option>
    	<option value="0.5">0.5</option>
    	<option value="1.0">1.0</option>
    </select></li>
		<li>2.for循环 <span class="set_weight_rest">0.35</span></li>
	</ul>	
</div>


	<div class="common_dialog_bottom">
		<div class="common_dialog_tip"></div>
		<div class="common_dialog_autoclose"><input type="checkbox" class="auto_close" id="auto_close" value="1" checked="checked" /><label for="auto_close">自动关闭</label></div>
	</div>
</div>