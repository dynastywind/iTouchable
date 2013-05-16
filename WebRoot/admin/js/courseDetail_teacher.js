$(function(){

	var course_id = $(".common_tab_tab").attr("id");
	
	//初始化列表
	getTeacherList(course_id,1);
	
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
		getTeacherList(1,page);
	});
	
	//刷新列表
	$(".tab_function_refresh_btn").click(function(){
		var page = 0;
		if($(".page_curr_a").size() > 0){
			page = parseInt($(".page_curr_a").attr("id"));
		}
		getTeacherList(course_id,page);
	});
	
	
	
	/**
	 * 获得章节列表
	 */
	function getTeacherList(id,page){
		$.ajax({   
				url:"admin/getCourseTeacher",    
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