$(function(){
	
	//载入聊天记录
	$(".message_chat_right").live("click",function(){
		getChatList($(this).attr("message_id"),0);
	});
	
	//聊天记录 显示删除按钮
	$(".chat_history_right").live("mouseover",function(){
		$(this).find(".chat_history_delete").css("visibility","visible");
	}).live("mouseout",function(){
		$(this).find(".chat_history_delete").css("visibility","hidden");
	});
	
	//翻页
	$(".message_chat_page_a").live("click",function(){
		curr_page = parseInt($(".message_chat_page_curr_a").html());
		page = $(this).attr("id");
		if(page == "-1"){
			page = curr_page - 1;//上一页
		}else if(page=="+1"){
			page = curr_page +1;//下一页
		}
		getChatList(4,page);
	});
	
	//删除聊天信息
	$(".chat_history_delete").live("click",function(){
		var chat_id = $(this).attr("chat_id");
		var message_id=$(this).attr("message_id");
		$.ajax({   
			url:"user/delChatAction",    
			type:"POST",    
			data:{"message_id":message_id,"chat_id":chat_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			beforeSend:function(){
				$(".pleasewait").show();
			},
			complete:function(){
				$(".pleasewait").hide();
			},
			success:function(data){ 
				$(".chat_history_list[chat_id='"+chat_id+"']").remove();
				$.tip({'text': '删除成功！'});
			}
		});
		
	});
	
	 /*=========================================
	  * function
	  =========================================*/
	
	/**
	 * 分页载入聊天记录
	 */
	function getChatList(message_id,page){
		$.ajax({   
			url:"user/getChatListOnMessage",    
			type:"POST",    
			data:{"message_id":message_id,"page":page},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			beforeSend:function(){
				$(".pleasewait").show();
			},
			complete:function(){
				$(".pleasewait").hide();
			},
			success:function(data){ 
				if(data.chat_HTML == ""){
					$(".message-1").html("<p style='color:#888; font-size:14px; text-align:center;'>暂时无聊天记录！</p>");
					$(".message-page-box").html("");
				}else{
					$(".message-1").html(data.chat_HTML);
					$(".message-page-box").html(data.pageList);
				}
			}
		});
	}
	
});