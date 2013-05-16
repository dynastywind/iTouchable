$(function(){

	//初始化列表
	getStudentList(1);
	
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
		getStudentList(page);
	});
	
	//刷新列表
	$(".tab_function_refresh_btn").click(function(){
		var page = 0;
		if($(".page_curr_a").size() > 0){
			page = parseInt($(".page_curr_a").attr("id"));
		}
		getStudentList(page);
	});
	
	
	
	/**
	 * 获得章节列表
	 */
	function getStudentList(page){
		$.ajax({   
				url:"admin/getStudentList",    
				type:"POST",    
				data:{"page":page},   
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