$(function(){
	
	//选项卡
	$(".hall_info_nav li").die().live("click",function(){
		$(this).addClass("hall_info_nav_curr").siblings().removeClass("hall_info_nav_curr");
		var index = $(this).attr("id");
		$(".hall_info_container>div:eq("+index+")").show().siblings().hide();
	});
	
	//表格变色
	$(".info_member_tab tr").die("mouseover").live("mouseover",function(){
		$(this).find("td").css("background-color","#f8f8f8");
	}).die("mouseout").live("mouseout",function(){
		$(this).find("td").css("background-color","#fff");
	});
	
	 /*=========================================
	  * function
	  =========================================*/
	
});


