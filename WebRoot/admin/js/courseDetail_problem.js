$(function(){
	
	var course_id = $(".common_tab_tab").attr("id");
	
	getChapterOption(course_id);
	getKnowOption(course_id);
	getProblemList(1);
	
	//挷定筛选条件
	$(".tab_select_input").change(function(){
		getProblemList(0);
	});
	
	//翻页
	$(".page_a").die().live("click",function(){
		var page =$(this).attr("id"); //-1：下一页，-2：上一页
		var curr_page = 0;
		curr_page = parseInt($(".page_curr_a").attr("id"));
		if(page == "+1"){
			page = curr_page + 1;
		}else if(page =="-1"){
			page = curr_page - 1;
		}
		getProblemList(page);
	});
	
	//刷新
	$(".tab_function_refresh_btn").click(function(){
		getProblemList();
	});
	
	//打开添加题目
	$(".tab_function_add_btn").click(function(){
		$(".common_dialog_box").show();
	});
	
	//保存题目
	$("#add_problem_btn").die().live("click",function(){
		
		var sub_type = $(".common_dialog_menu_li_curr").attr("id");		//题型
		
		var chapter = $("#problem_chapter_input").val();
		var content = $("#problem_content_input").val();
		var tip = $("#problem_tip_input").val();	//解析
		var hard = $("#problem_hard_input").val();
		var types = $("#problem_type_input").val();
		
		var s_key = $("#problem_select_key_input").val();	//选择题答案
		var p_key = $(".problem_program_key_input").val();	//编程题答案
		
		var option = new Array();	//选择题选项
		$(".problem_option_input").each(function(i){
			option[i] = $(this).val();
		});
		
		var value = 0;
		var option_weight = new Array();	//选择题选项权重
		$(".problem_select_option_weight_input").each(function(i){
			option_weight[i] = $(this).val();
			value = value + parseFloat($(this).val());
		});
		option_weight[3] = (1-value).toFixed(2);
		
		var key = new Array();	//填空题答案
		$(".problem_key_input").each(function(i){
			key[i] = $(this).val();
		});
		
		var know = new Array();	//知识点
		$(".common_dialog_point_box li").each(function(i){
			know[i] = $(this).attr("id");
		});
		
		var c_weight = new Array();	//知识点权重
		$(".point_weight").each(function(i){
			c_weight[i] = $(this).html();
		});
		
		mainToUser = $("#problem_mainToUser_input").val();
		mainToTest = $("#problem_mainToTest_input").val();
		other = $("#problem_other_input").val();
		keyToTest = $(".problem_keyToTest_input").val();
		
		$.ajax({   
			url:"admin/addProblem",    
			type:"POST",    
			data:{"chapter_id":chapter,"content":content,"tip":tip,"hard":hard,"types":types,"key":String(key),"know":String(know),
					"s_key":s_key,"p_key":p_key,"option":String(option),"sub_type":sub_type,"option_weight":String(option_weight),"c_weight":String(c_weight)
					,"mainToUser":mainToUser,"mainToTest":mainToTest,"other":other,"keyToTest":keyToTest},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				var text = "<span style='color:#1caede;'>保存成功！</span>";
				var is_close = true;
				if($(".auto_close:checked").size() == 0){
					is_close = false;
				}
				setDailogTip(text,is_close);
				
				var page = 0;
				if($(".page_curr_a").size() > 0){
					page = parseInt($(".page_curr_a").attr("id"));
				}
				getProblemList(page);
			}
		});	
	});
	
	//设置选择题选项的权重
	$(".problem_select_option_weight_input").die().live("change",function(){
		var value = 0;
		$(".problem_select_option_weight_input").each(function(){
			value = value + parseFloat($(this).val());
		});
		var rest = (1-value).toFixed(2);
		if(rest < 0){
			$(".last_weight").html(rest).css("color","#ff5500");
		}else{
			$(".last_weight").html(rest).css("color","#666");
		}
		
	});
	
	//打开关闭知识点框
	$(".common_dialog_point_add_open,.problem_min_option_close").die().live("click",function(){
		$(".problem_min_setweight").slideUp(function(){
			$(".problem_min_option").slideToggle();
		});
	});
	
	//打开关闭设置权重框
	$(".common_dialog_point_set,.problem_min_setweight_close,.point_weight").die().live("click",function(){
		$(".problem_min_option").slideUp(function(){
			$(".problem_min_setweight").slideToggle();
		});
		setConceptWeightList();
	});
	
	//更新知识点权重
	$(".setweight_input").die().live("change",function(){
		var index = $(this).index(".setweight_input");
		$(".common_dialog_point_box li:eq("+index+")").find(".point_weight").html($(this).val());
		setLastConceptWeight();
	});
	
	//添加知识点
	$("#problem_knowledge_ul li").die().live("click",function(){
		var id = $(this).attr("id");
		var name = $(this).find(".know_name").html();
		var weight = $(this).find(".know_name").attr("id"); //知识点的重要性
		var count = $(".common_dialog_point_box li").size();
		var exit = $(".common_dialog_point_box li[id='"+id+"']").size();
		if(count <11){ //不重复且不多于10个
			if(exit < 1){
				$(".common_dialog_point_box").append('<li id="'+id+'"><a class="point_weight" href="javascript:;" title="设置权重">1.0</a>'+
						'<span class="point_name" id="'+weight+'">'+name+'</span><span class="point_close" title="移除">×</span></li>');
				setDefaultWeight();
			}else{
				var text = "提醒：知识点重复了！";
				setDailogTip(text,false);
			};
		}else{
			var text = "提醒：最多只可添加10个知识考点！";
			setDailogTip(text,false);
		};
	});
	
	//移除知识点
	$(".point_close").die().live("click",function(){
		$(this).parent().remove();
		setConceptWeightList(); //更新到权重设置列表
	});
	
	//添加题目的类型
	$(".common_dialog_menu li").die().live("click",function(){
		var type = $(this).attr("id");
		$(this).addClass("common_dialog_menu_li_curr").siblings().removeClass("common_dialog_menu_li_curr");
		setQuetionType(type);
	});
	
	
	
	
	/**
	 * ========================== 
	 * 		funciton 
	 * ==========================
	 */
	
	
	/**
	 * 获得章节列表
	 */
	function getChapterOption(course_id){
		$.ajax({   
				url:"admin/getChapterOption",    
				type:"POST",    
				data:{"course_id":course_id},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				success:function(data){ 
					$("#problem_chapter_input").html("<option value='0'> ---- 请选择 ----</option>" + data.html);
					$("#tab_select_chapter_input").html("<option value='0'> ---- 全部 ----</option>" + data.html);
				}
			});	
	}
	
	/**
	 * 获得知识点列表
	 */
	function getKnowOption(course_id){
		$.ajax({   
				url:"admin/getConceptOption",    
				type:"POST",    
				data:{"course_id":course_id},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				success:function(data){ 
					$("#problem_knowledge_ul").html( data.html);
				}
			});	
	}
	
	/**
	 * 获取题目列表
	 */
	function getProblemList(page){
		var chapter = $("#tab_select_chapter_input").val();
		if(chapter == null){ //当章节还未载入
			chapter = 0;
		}
		var type = $("#tab_type").val();
		var sub_type = $("#tab_sub_type").val();
		$.ajax({   
			url:"admin/getProblemList",    
			type:"POST",    
			data:{"chapter_id":chapter,"course_id":course_id,"type":type,"sub_type":sub_type,"page":page},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			beforeSend:function(){
				$(".pleasewait").show();
			},
			complete:function(){
				$(".pleasewait").hide();
			},
			success:function(data){ 
				if(data.html == ""){
					$(".common_tab_ajax").html("<tr><td height='40'>暂无记录！</td></tr>");
				}else{
					$(".common_tab_ajax").html(data.html);
					$(".tab_page_box").html(data.pageHTML);
				};
			}
		});	
	};
	
	/**
	 * 设置添加新题目的类型
	 */
	function setQuetionType(type){
		if(type == "1"){    //选择题
			$(".common_dialog_tab").css("margin-top","20px");
			$("#question_option_tr").html(' <td height="40" align="right" valign="top">选项：</td>' +
				    '<td>' +
				    	'<input type="text" name="textfield" class="common_dialog_input problem_option_input" />' +
				    	'<span class="select_option_span">A.</span>' +
				    	'<input type="text" name="textfield" class="common_dialog_input problem_option_input" />' +
				    	'<span class="select_option_span">B.</span>' +
						'<input type="text" name="textfield" class="common_dialog_input problem_option_input" />' +
						'<span class="select_option_span">C.</span>' +
						'<input type="text" name="textfield" class="common_dialog_input problem_option_input" />' +
						'<span class="select_option_span">D.</span>' +
				    '</td>');
			
			$("#question_key_td").html('<select class="tab_select_input" id="problem_select_key_input">' +
					'<option value="0"> - 请选择 - </option>' +
					'<option value="A">A</option>' +
					'<option value="B">B</option>' +
					'<option value="C">C</option>' +
					'<option value="D">D</option>' +
					'</select>');
			$("#mainToTest_tr").html('');
			$("#keyToTest_tr").html('');
			$("#mainToUser_tr").html('');
			$("#other_tr").html('');
			
		}else if(type == "2"){    //填空题
			$(".common_dialog_tab").css("margin-top","50px");
			$("#question_option_tr").html("");
			$("#question_key_td").html('<input type="text" name="textfield" class="common_dialog_input problem_key_input" />、' +
					'<input type="text" name="textfield" class="common_dialog_input problem_key_input" />、' +
					'<input type="text" name="textfield" class="common_dialog_input problem_key_input" />、' +
					'<input type="text" name="textfield" class="common_dialog_input problem_key_input" />');
			$("#mainToTest_tr").html('');
			$("#keyToTest_tr").html('');
			$("#mainToUser_tr").html('');
			$("#other_tr").html('');
		}else if(type == "3"){   //编程题
			$(".common_dialog_tab").css("margin-top","10px");
			$("#question_option_tr").html("");
			$("#question_key_td").html('<input type="text" name="textfield" class="common_dialog_input problem_program_key_input" /><span class="common_dialog_note">(程序运行最终输出结果)</span>');
			$("#mainToTest_tr").html('<td height="60" valign="top" align="right">测试函数：</td><td><textarea class="common_dialog_textarea" id="problem_mainToTest_input"></textarea></td>'	);
			$("#keyToTest_tr").html(' <td height="40" align="right">测试答案：</td><td id="question_key_td"><input name="textfield" class="common_dialog_input problem_keyToTest_input" type="text"><span class="common_dialog_note">(测试程序最终输出结果)</span></td> ');
			$("#mainToUser_tr").html('<td height="60" valign="top" align="right">主函数：</td> <td><textarea class="common_dialog_textarea" id="problem_mainToUser_input"></textarea></td>');
			$("#other_tr").html('<td height="60" valign="top" align="right">辅助函数：</td><td><textarea class="common_dialog_textarea" id="problem_other_input"></textarea></td>');
		}
		
	};
	
	/**
	 * 计算最后一个知识点权值
	 */
	function setLastConceptWeight(){
		var value = 0;
		$(".setweight_input").each(function(){
			value = value + parseFloat($(this).val());
		});
		var rest = (1-value).toFixed(2);
		if(rest < 0){
			$(".set_weight_rest").html(rest).css("color","#ff5500");
			$(".common_dialog_point_box li:last").find(".point_weight").html(rest).css("color","#ff5500");
		}else{
			$(".set_weight_rest").html(rest).css("color","#666");
			$(".common_dialog_point_box li:last").find(".point_weight").html(rest).css("color","#1caede");
		};
	};
	
	/**
	 * 设置知识点默认权重
	 * 基本算法是：某个知识点的默认权重是该知识点的重要性和各知识点重要性的比值，取一个小数
	 */
	function setDefaultWeight(){
		var weight_list = new Array();	//默认权重
		var sum  = 0;	//各知识点必要性的总和
		
		$(".point_name").each(function(){	//求和
			var a = parseFloat($(this).attr("id"));
			sum = sum + a;
		});
		
		$(".point_name").each(function(i){	//求比值
			var w = parseInt($(this).attr("id"));
			weight_list[i] = (w/sum).toFixed(1);
		});
		
		$(".point_weight").each(function(i){	//设置默认的权重
			$(this).html(weight_list[i]);
		});
		
		var value = 0;
		if(weight_list.length>1){	//当大于一个知识点时
			for(var i = 0; i<weight_list.length-1; i++){
				value = value + parseFloat(weight_list[i]);
			};
			var rest = (1-value).toFixed(1);
			$(".common_dialog_point_box li:last").find(".point_weight").html(rest+"0");
		}else{
			$(".common_dialog_point_box li:last").find(".point_weight").html("1.00");
		}
		
	}
	
	/**
	 * 设置权重列表
	 */
	function setConceptWeightList(){
		var html = "";
		var name ="";
		var weight = "";
		var str = "";
		$(".common_dialog_point_box li").each(function(i){
			name = $(this).find(".point_name").html();
			weight = $(this).find(".point_weight").html();
			id = $(this).attr("id");
			if(i == $(".common_dialog_point_box li").size()-1){ //当是最后一个时
				str = '<li>'+(i+1)+'.'+name+'<span class="set_weight_rest">0</span></li>';
			}else{
				str =  '<li>'+(i+1)+'.'+name+'<select class="tab_select_input setweight_input" id="problem_concept_weight_input">' +
				'<option value="'+weight+'">'+weight+'</option>' +
				'<option value="0">0</option>' +
				'<option value="0.1">0.1</option>' +
				'<option value="0.2">0.2</option>' +
				'<option value="0.25">0.25</option>' +
				'<option value="0.3">0.3</option>' +
				'<option value="0.4">0.4</option>' +
				'<option value="0.5">0.5</option>' +
				'<option value="1.0">1.0</option>' +
				'</select></li>';
			}
			html = html + str;
		});
		if(html == ""){
			$("#problem_setweight_ul").html("<p style='line-height:20px; color:#999; text-align:center;'>未添加知识点</p>");
		}else{
			$("#problem_setweight_ul").html(html);
		}
		
		setLastConceptWeight();
	}
	
});