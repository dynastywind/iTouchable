$(function(){
	
	//导航
	$(".left_nav_title").click(function(){
		$(this).next().slideToggle().siblings("ul").slideUp();
	});
	
	//右侧选项卡
	$(".right_nav_title").die().live("click",function(){
		var obj = $(this).parent();
		var url = obj.attr("id");
		obj.addClass("right_nav_li_curr").siblings().removeClass("right_nav_li_curr");
		$(".main_frame").load("admin/" + url);
	});
	
	//右侧显示关闭
	$(".right_title li").die("mouseover").live("mouseover",function(){
		$(this).find(".right_nav_close").show();
	}).die("mouseout").live("mouseout",function(){
		$(this).find(".right_nav_close").hide();
	});
	
	//关闭弹出框
	$(".common_dialog_close").die().live("click",function(){
		$(this).parents(".common_dialog_box").hide();
		$(".mask").hide();
	});
	//关闭弹出框（小）
	$(".common_dialog_close_min").die().live("click",function(){
		$(this).parents(".common_dialog_box_min").hide();
	});
	
	
	//表格变色
	$(".common_tab_ajax tr").live("mouseover",function(){
		$(this).find("td").css("background-color","#f8f8f8");
	}).live("mouseout",function(){
		$(this).find("td").css("background-color","#fff");
	});
	
	//载入默认页面
	$(".main_frame").load("admin/courseDetail");
	
});

/**
 * 设置提示
 */
function setDailogTip(text,is_close){
	$(".common_dialog_tip").show().html(text);
	setTimeout(function(){
		$(".common_dialog_tip").fadeOut(400,function(){
			if(is_close){
				$(".common_dialog_box").hide();
			}
		});  //淡出
	},1000);
	$(".common_dialog_main").find("input").each(function(){
		$(this).val(this.defaultValue);
	});
	$(".common_dialog_main").find("select").each(function(){
		$(this).val(this.defaultValue);
	});
	$(".common_dialog_main").find("textarea").each(function(){
		$(this).val(this.defaultValue);
	});
}

/**
 * 设置提示 min
 */
function setDailogTipMin(text,is_close){
	$(".common_dialog_tip_min").show().html(text);
	setTimeout(function(){
		$(".common_dialog_tip_min").fadeOut(400,function(){
			if(is_close){
				$(".common_dialog_box_min").hide();
			}
		});  //淡出
	},1000);
	$(".common_dialog_main_min").find("input").each(function(){
		$(this).val(this.defaultValue);
	});
	$(".common_dialog_main_min").find("select").each(function(){
		$(this).val(this.defaultValue);
	});
}
