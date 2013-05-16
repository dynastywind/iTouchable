$(function(){
	
	//选项卡
	$(".common_right_nav li").click(function(){
		var url = $(this).attr("id");
		$(this).addClass("common_right_nav_li_curr").siblings().removeClass("common_right_nav_li_curr");
		$(".course_frame").load("admin/" + url);
	});
	
	//载入默认页面
	$(".course_frame").load("admin/courseDetailProblem");
});