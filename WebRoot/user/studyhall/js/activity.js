$(function(){
	
	var society_id = $(".hall-left-notice").attr("id");
	var user_id = $(".hall_nav_person").attr("user_id");
	
	//选项卡
	$(".hall_person_nav_li").die().live("click",function(){
		$(this).addClass("hall_person_nav_curr").siblings().removeClass("hall_person_nav_curr");
		var topic_type = $(this).attr("topic_type");
		var a_type = $(this).attr("a_type");
		if($(".hall-nav-li-curr[user_id]").length>0){ //当前为单个成员
			getActivityList(user_id,society_id,a_type,topic_type,1);
		}else{
			getActivityList(0,society_id,a_type,topic_type,1);
		}
		$(".hall_load_more").show(); //显示”加载更多“
	});
	
	//刷新当前话题列表
	$(".refresh-topic").die().live("click",function(){
		var a_type = $(this).attr("a_type");
		var topic_type = $(this).attr("topic_type");
		var user_id = $(this).attr("user_id");
		getActivityList(user_id,society_id,a_type,topic_type,1);
		$(".hall_load_more").show(); //显示”加载更多“
	});
	
	//加载更多（下一页）
	$(".hall_load_more").die().live("click",function(){
		var a_type  = $(this).attr("a_type");
		var user_id  = $(this).attr("user_id");
		var topic_type = $(this).attr("topic_type");
		page = parseInt($(this).attr("page"))+1;
		getActivityList(user_id,society_id,a_type,topic_type,page);//载入大厅动态
	});
	
	 /*=========================================
	  * function
	  =========================================*/
	
});


