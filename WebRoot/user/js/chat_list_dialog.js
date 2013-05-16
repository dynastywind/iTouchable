
//好友列表和聊天框
//studentCenter.jsp

$(function(){
	
	//载入好友列表
	//$("._Friends_list_loading").hide();
	getGroupAndFriendsList();
	
	//打开/折叠好友列表
	$("._Friends_title,._Friends_bottom").live("click",function(){
		$("#_Friends_ajax_box").slideToggle(function(){
			if($(this).is(":hidden")){
				$("._Friends_bottom").addClass("_Friends_bottom_up");
			}else{
				$("._Friends_bottom").removeClass("_Friends_bottom_up");
			}
		});
	});
	
	//打开/折叠好友分组
	$("._Friends_group_title").live("click",function(){
		$(this).next().slideToggle().siblings("._Friends_group_friends").slideUp();
	});
	
	//打开聊天框
	$("._Chat_name_open").live("click",function(){
		$(this).parent().addClass("_Chat_name_li_curr").siblings().removeClass("_Chat_name_li_curr");
		index = $(this).index("._Chat_name_open");
		$("._Message_box_div:eq("+index+")").show().siblings().hide();
		$("._Message_contain").css("margin-right",(index*122)+"px");
	});
	
	//显示关闭聊天按钮
	$("._Chat_name_li").live("mouseover",function(){
										$(this).find("._Chat_name_close").show();
										}).live("mouseout",function(){
											$(this).find("._Chat_name_close").hide();
										});
	
	//关闭聊天框
	$("._Chat_name_close").live("click",function(){
		index = $(this).index("._Chat_name_close");
		$("._Chat_name_li:eq("+index+")").remove();//姓名选项卡
		$("._Message_box_div:eq("+index+")").remove();//聊天框
		i = $("._Chat_name_li_curr").index("._Chat_name_li");
		if(i>-1){//当最后一个聊天框移除后，不移动
			$("._Message_contain").css("margin-right",(i*122)+"px");//移动当前聊天框的位置
		}
	});
	
	
	
	//隐藏当前聊天框
	$("._Message_close_div").live("click",function(){
		$("._Message_box_div").hide();
		$("._Chat_name_li_curr").removeClass("_Chat_name_li_curr");
	});
	
	//关闭全部聊天框
	$("._Message_close_all_a").live("click",function(){
		$("._Message_contain").empty();
		$("._Chat_name_nav").empty();
	});

	
	
	 /*=========================================
	  * function
	  =========================================*/
	
	
	
});