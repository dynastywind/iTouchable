$(function(){
	
	//选项卡
	$(".message-nav li").click(function(){
		$(this).addClass("message-nav-li-curr").siblings().removeClass("message-nav-li-curr");
		getLatestMessage($(this).attr("id"));
	});
	
	//载入最新消息
	getLatestMessage(0);
	
	//处理好友请求
	$(".add-friends-pass-btn").live("click",function(){
		deal = $(this).attr("deal");
		message_id = parseInt($(this).attr("id"));
		user_name = $(".message-list[id='"+message_id+"']").find(".message-list-name").html();
		if(deal == "yes"){
			$.ajax({   
				url:"user/getFriendsGroupListOption",    
				type:"POST",    
				data:{},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				success:function(data){ 
					$.dialog({
						'title'		: '选择分组',
						'msg_html'	: '<div class="add-friends-dialog-1">同意添加 '+user_name+' 为好友，请选择分组：<select name="add_friends_group" id="add_friends_group">'+
							'<option value="">未分组</option>'+data.returnList+'</select></div>',
						'buttons'	: {
								'image/cancel_btn.gif'	: {
								'class'	: '',
								'action': function(){}
										},
								'image/ok_black_btn.gif'	: {
								'class'	: '',
								'action': function(){
									group_id = $("#add_friends_group").val();
									$.ajax({   
										url:"user/dealAddfriends",    
										type:"POST",    
										data:{"deal":deal,"group_id":group_id,"message_id":message_id},   
										dataType:"json",  
										error:function(){alert("ajax error");},
										success:function(data){ 
											types = $(".message-nav-li-curr").attr("id");
											getLatestMessage(types);
											getGroupAndFriendsList();
											$.tip({'text': '好友添加成功！'});
										}
									});
									}
								}
							}
					});
				}
			});
			
		}else{
			$.ajax({   
				url:"user/dealAddfriends",    
				type:"POST",    
				data:{"deal":deal,"group_id":"","message_id":message_id},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				success:function(data){ 
					types = $(".message-nav-li-curr").attr("id");
					getLatestMessage(types);
					$.tip({'text': '已拒绝 '+user_name+' 的好友请求！'});
				}
			});
		}
		
	});
	
	
	//处理大厅邀请
	$(".add-society-pass-btn").live("click",function(){
		deal = $(this).attr("deal");
		message_id = parseInt($(this).attr("id"));
		user_name = $(".message-list[id='"+message_id+"']").find(".message-list-name").html();
		$.ajax({   
			url:"user/dealHallInvite",    
			type:"POST",    
			data:{"deal":deal,"message_id":message_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				types = $(".message-nav-li-curr").attr("id");
				getLatestMessage(types);
				if(deal == "yes"){
					$.tip({'text': '已同意 '+user_name+' 的邀请！'});
				}else{
					$.tip({'text': '已拒绝 '+user_name+' 的邀请！'});
				}
			}
		});
		
	});
	
	 /*=========================================
	  * function
	  =========================================*/
	function getLatestMessage(types){
		if(types == "1"){
			title ="好友留言";
		}else if(types == "2"){
			title ="好友请求";
		}else if(types == "3"){
			title ="邀请信息";
		}else if(types == "4"){
			title ="系统消息";
		}else{
			title ="最新未读消息";
		}
		 $.ajax({   
				url:"user/getLatestMessage",    
				type:"POST",    
				data:{"types":types},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				beforeSend:function(){
					$(".pleasewait").show();
				},
				complete:function(){
					$(".pleasewait").hide();
				},
				success:function(data){ 
					if(data.latestMessageHTML == ""){
						$(".message-1").html("<p style='text-align:center; font-size:14px; color:#666; line-height:50px;'>暂无"+title+"！</p>");
						$(".message-page-box").hide().html("");
					}else{
						$(".message-1").html(data.latestMessageHTML);
						$(".message-page-box").show().html(data.pageList);
					}
					
				}
			});
	}
	
	
});