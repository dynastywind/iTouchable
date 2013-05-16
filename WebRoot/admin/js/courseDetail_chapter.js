$(function(){

	var course_id = $(".common_tab_tab").attr("id");
	
	//添加章节
	$(".tab_function_add_btn").click(function(){
		$(".common_dialog_box_min").show();
	});
	
	//初始化列表
	getChapterList(course_id,1);
	
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
		getChapterList(1,page);
	});
	
	//刷新列表
	$(".tab_function_refresh_btn").click(function(){
		var page = 0;
		if($(".page_curr_a").size() > 0){
			page = parseInt($(".page_curr_a").attr("id"));
		}
		getChapterList(course_id,page);
	});
	
	
	//提交新章节
	$("#chapter_add_btn").die().live("click",function(){
		var name = $("#chapter_name_input").val();
		if(name!=""){
			$.ajax({   
				url:"admin/addChapter",    
				type:"POST",    
				data:{"chapter_name":name},   
				dataType:"json",  
				beforeSend:function(){
					var text = "<span style='color:#1caede;'>保存中……</span>";
					setDailogTipMin(text,false);
				},
				complete:function(){
					
				},
				error:function(){alert("ajax error");},
				success:function(data){ 
					var text = "<span style='color:#1caede;'>保存成功！</span>";
					var is_close = true;
					if($(".auto_close:checked").size() == 0){
						is_close = false;
					}
					setDailogTipMin(text,is_close);
					$(".tab_function_refresh_btn").click();
				}
			});	
		}else{
			var text = "名称不能为空！";
			setDailogTipMin(text,false);
		}
	});
	
	
	/**
	 * 获得章节列表
	 */
	function getChapterList(id,page){
		$.ajax({   
				url:"admin/getChapterList",    
				type:"POST",    
				data:{"course_id":id,"page":page},   
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
	
	
});