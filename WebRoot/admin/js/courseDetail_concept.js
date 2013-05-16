$(function(){
	
	var course_id = $(".common_tab_tab").attr("id");

	//添加章节
	$(".tab_function_add_btn").click(function(){
		$(".common_dialog_box_min").show();
		$.ajax({   
			url:"admin/getChapterOption",    
			type:"POST",    
			data:{"course_id":course_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				$("#knowledge_chapter_input").html("<option value='0'> ---- 请选择 ----</option>" + data.html);
			}
		});	
	});
	
	//初始化列表
	getKnowList(1,0,1);
	
	getChapterOption(1);
	
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
		getKnowList(1,0,page);
	});
	
	//刷新列表
	$(".tab_function_refresh_btn").click(function(){
		var page = 0;
		if($(".page_curr_a").size() > 0){
			page = parseInt($(".page_curr_a").attr("id"));
		}
		getKnowList(1,0,page);
	});
	
	
	//提交新章节
	$("#knowledge_add_btn").die().live("click",function(){
		var name = $("#knowledge_name_input").val();
		var course_id = $(".common_tab_tab").attr("id");
		var chapter_id = $("#knowledge_chapter_input").val();
		var weight = $("#knowledge_weight_input").val();
		if(name!="" && weight != 0 && chapter_id != 0){
			$.ajax({   
				url:"admin/addConcept",    
				type:"POST",    
				data:{"name":name,"course_id":course_id,"chapter_id":chapter_id,"weight":weight},   
				dataType:"json",  
				beforeSend:function(){
					var text = "<span style='color:#1caede;'>保存中……</span>";
					setDailogTipMin(text,false);
				},
				complete:function(){
					
				},
				error:function(){alert("ajax error");},
				success:function(data){ 
					var is_close = true;
					if($(".auto_close:checked").size() == 0){
						is_close = false;
					}
					setDailogTipMin(text,is_close);
					var text = "<span style='color:#1caede;'>保存成功！</span>";
					setDailogTipMin(text,is_close);
					
					var page = 0;
					if($(".page_curr_a").size() > 0){
						page = parseInt($(".page_curr_a").attr("id"));
					}
					getKnowList(1,0,page);
				}
			});	
		}else{
			var text = "信息不完整，保存失败！";
			setDailogTipMin(text,false);
		}
		
	});
	
	/**
	 * 获得知识点列表
	 */
	function getKnowList(course_id,chapter_id,page){
		$.ajax({   
				url:"admin/getConceptList",    
				type:"POST",    
				data:{"course_id":course_id,"chapter_id":chapter_id,"page":page},   
				dataType:"json",  
				beforeSend:function(){
					$(".pleasewait").show();
				},
				complete:function(){
					$(".pleasewait").hide();
				},
				error:function(){alert("ajax error");},
				success:function(data){ 
					if(data.html == ""){
						$(".common_tab_ajax").html("<tr><td height='40'>暂无记录！</td></tr>");
					}else{
						$(".common_tab_ajax").html(data.html);
						$(".tab_page_box").html(data.pageHTML);
					}
				}
			});	
	}
	
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
					$("#tab_select_chapter_input").html("<option value='0'> ---- 全部 ----</option>" + data.html);
				}
			});	
	}
	
	
});