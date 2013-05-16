 /*=========================================
      * 
	  * ajax 实现在线聊天
	  *
	  =========================================*/
$(function(){
		
	$("._Chat_name_open").click();
	
	//发送消息
	$("._Message_send_img").live("click",function(){
		var obj = $(this);
		friends_id = obj.attr("user_id");//好友id
		chat_text = obj.parent().find("#_Message_text").val();
		if(chat_text == ""){
			$("._Message_tip").slideDown();
			setTimeout(function(){
				$("._Message_tip").slideUp();
			},800);
		}else{
			 $.ajax({   
					url:"user/addChat",    
					type:"POST",    
					data:{"friends_id":friends_id,"chat_text":chat_text},   
					dataType:"json",  
					beforeSend:function(){
						obj.addClass("_Message_send_loading");
					},
					complete:function(){
						obj.removeClass("_Message_send_loading");
					},
					error:function(){alert("ajax error!");},
					success:function(data){ 
						obj.parent().find("#_Message_text").val("");
						getChat(friends_id,0);
						obj.parent().find("#_Message_text").focus();
					}
				});
		}
		
	});
	
	//打开姓名选项卡和聊天框
	$("#_Friends_ajax_box li").live("click",function(){
		if($("._Chat_name_li").length < 6){ //最多只能打开6个聊天框
			user_id = $(this).attr("user_id");
			if($("._Chat_name_li[user_id='"+user_id+"']").size()==0){//当该好友聊天框未打开时，添加新聊天框
				src = $(this).find("img").attr("src");
				name = $(this).find("span").html();
				$("._Chat_name_nav").append('<li class="_Chat_name_li" user_id="' + user_id + '"><span class="_Chat_name_open"><img src="' + src + '" class="_Chat_head_img" alt="" height="20" width="20"> ' + name + '</span><span class="_Chat_name_close">×</span></li>');//姓名选项卡
				$("._Message_contain").append('<div class="_Message_box_div">'+
						  '<div class="_Message_div">'+
							'<div class="_Message_show_div" user_id="' + user_id + '">'+
							'</div>'+
						'<div class="_Message_input_div">'+
						  '<textarea name="_Message_text" id="_Message_text"></textarea><div class="_Message_tip">消息不能为空！</div>'+
							  '<div class="_Message_send_img" user_id="' + user_id + '"></div><a href="#">聊天记录</a> <a href="javascript:void(0);" class="_Message_close_all_a" style="float:left; margin-left:5px;">×关闭全部</a> </div>	'+	
					  '</div>'+
					  '<div class="_Message_close_div" title="隐藏当前窗口"></div>'+
					'</div>');
				
				$("._Chat_name_open:last").click();//显示
				getChat(user_id,0);
			}else{
				$("._Chat_name_li[user_id='"+user_id+"']").find("._Chat_name_open").click();
			}
		}else{
			alert("对不起，聊天框的数量已达到上限！");
		}
		
	});
	
	
	 /*=========================================
	  * function
	  =========================================*/
	
	/**
	 * 载入聊天记录
	 */
	function getChat(friends_id,page){
		$.ajax({   
			url:"user/getChatListOnChat",    
			type:"POST",    
			data:{"friends_id":friends_id,"page":page},   
			dataType:"json",  
			beforeSend:function(){
			},
			complete:function(){
			},
			error:function(){alert("ajax error!");},
			success:function(data){ 
				$("._Message_show_div[user_id='"+friends_id+"']").html(data.chat_HTML);
				$("._Message_show_div[user_id='"+friends_id+"']").scrollTop(900);
			}
		});
	}
	
	
	
});