$(function(){
	
	//选项卡
	$(".hall_admin_nav li").die().live("click",function(){
		$(this).addClass("hall_admin_nav_curr").siblings().removeClass("hall_admin_nav_curr");
		var index = $(this).attr("id");
		$(".hall_admin_container>div:eq("+index+")").show().siblings().hide();
	});
	
	// 打开添加公告框
	$(".amdin_add_notice_btn").click(function(){
		$(".admin_notice_add_box").slideToggle();
	});
	
	//关闭添加公告框
	$(".admin_notice_add_close").click(function(){
		$(".admin_notice_add_box").slideUp();
	});
	
	//控制公告数字
	$("#admin_notice_textarea").keyup(function(){
		$(this).val($.trim($(this).val()));
		var v = $(this).val();
		var h = 140- v.length;
		if(h<1){
			$(this).val(v.substr(0,140));
			$(".admin_notice_last_words").html(0);
		}else{
			$(".admin_notice_last_words").html(h);
		}
	});
	
	
	//表格变色
	$("#ajax_member tr").die("mouseover").live("mouseover",function(){
		$(this).find("td").css("background-color","#f8f8f8");
	}).die("mouseout").live("mouseout",function(){
		$(this).find("td").css("background-color","#fff");
	});
	
	
	/*=========================================
	  * 好友管理
	  =========================================*/
	
	//打开邀请好友
	$(".amdin_add_member_btn").click(function(){
		$(".admin_member_add_box").slideToggle();
	});
	//关闭邀请好友
	$(".admin_member_add_close").click(function(){
		$(".admin_member_add_box").slideUp();
	});
	
	//折叠好友
	$(".society_friends_group_title span").live("click",function(){
		$(this).parent().next().slideToggle().siblings(".society_friends_ul").slideUp();
	});
	
	//按组勾选好友
	$(".society_friends_group_check").live("change",function(){
		var obj = $(this).parent().next().find("input:enabled");
		var box_obj = $(this).parents(".society_friends_box").prev().find("ul");
		var count_allow = parseInt(box_obj.parent().find(".society_friends_count_allow").html());
		var count = box_obj.find("li").length;
		if(count == count_allow){ 
			$(this).attr("checked",false);
			$.tip({'text':"对不起，最多可添加"+count_allow+"人！"});
		}
		if($(this).attr("checked")){
			obj.attr("checked",true);
		}else{
			obj.attr("checked",false);
		}
		obj.each(function(){   //勾选或移除
			addUser($(this));
		});
	});
	
	//单个勾选好友
	$(".society_friends_ul input[type='checkbox']").die("change").live("change",function(){
		addUser($(this));
	});
	
	//移除好友
	$(".admin_member_selected_box span").live("click",function(){
		var user_id = $(this).parent().attr("user_id");
		$(this).parent().remove(); //移除已选框中的
		var obj = $(".admin_member_friends_box").find("input[value='"+user_id+"']");//取消勾选
		obj.attr("checked",false);
		addUser(obj);
	});
	
	//移除所有
	$(".society_friends_remove_all_btn").click(function(){
		 $(".admin_member_friends_box").find("input").attr("checked",false);
		 $(".admin_member_selected_box").html("");
	});
	
	
	
	 /*=========================================
	  * function
	  =========================================*/
	/*
	 * 勾选好友
	 * obj:input类型
	 */
	function addUser(obj){
		var user_id = obj.parent().attr("user_id");
		var user_name=obj.prev().html();
		var box_obj = $(".admin_member_selected_box");
		if(obj.attr("checked")){
			var count_allow = parseInt($(".society_friends_count_allow").html());
			var count = box_obj.find("li").length;
			if(count<count_allow){ //当小于最大允许值时
				if(box_obj.find("li[user_id='"+user_id+"']").size() == 0){ //当不存在时
					box_obj.append("<li user_id='"+user_id+"'>"+user_name+"<span>×</span></li>");//添加
				}
			}else{
				obj.attr("checked",false);
				obj.parent().parent().prev().find("input").attr("checked",false);
				$.tip({'text':"对不起，最多可添加"+count_allow+"人！"});
			}
		}else{
			obj.parent().parent().prev().find("input").attr("checked",false);//取消分组勾选
			box_obj.find("li[user_id='"+user_id+"']").remove();//移除
		}
		countUser();
	}
	
	
	/*
	 * 计算已添加的好友数量
	 * obj:box类型
	 */
	function countUser(){
		$(".society_friends_count").html($(".admin_member_selected_box li").length);
	}
});


