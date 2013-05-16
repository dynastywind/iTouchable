$(function(){
	
	getCourseList(0);
	
	//加载更多（下一页）
	$(".hall_load_more").die().live("click",function(){
		var a_type  = $(this).attr("a_type");
		var user_id  = $(this).attr("user_id");
		var topic_type = $(this).attr("topic_type");
		page = parseInt($(this).attr("page"))+1;
		getActivityList(user_id,society_id,a_type,topic_type,page);//载入大厅动态
	});
	
	
	function getCourseList(page){
		$.ajax({   
				url:"admin/getCourseList",    
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