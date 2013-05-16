$(function(){
	
	//寻找好友
	$(".friend-soso-btn").click(function(){
		keyword = $("#keyword").val();
		if( keyword == document.getElementById("keyword").defaultValue){
			$(".friends-result-box").show();
			$(".friends-result-list-box").html("<p style='color:#888; font-size:14px; text-align:center;height:50px; line-height:50px;'>请输入搜索的关键词！</p>");
			$("#keyword").focus();
		}else{
			searchForFriends(keyword,0);
		}
	});
	
	//翻页
	$(".friends-result-page-a").live("click",function(){
		curr_page = parseInt($(".friends-result-page_curr_a").html());
		page = $(this).attr("id");
		if(page == "-1"){
			page = curr_page - 1;//上一页
		}else if(page=="+1"){
			page = curr_page +1;//下一页
		}
		searchForFriends(keyword,page);
	});
	
	
	
	
	//申请好友
	$(".add-friends-btn").live("click",function(){
		var obj = $(this);
		friends_id = parseInt(obj.attr("id"));
		user_name = $(".friends-result-list[id='"+friends_id+"']").find(".friends-result-name").html();
		$.ajax({   
			url:"user/getFriendsGroupListOption",    
			type:"POST",    
			data:{},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				$.dialog({
					'title'		: '好友申请',
					'msg_html'	: '<div class="add-friends-dialog-1">申请添加 '+user_name+' 为好友，选择分组：<select name="add_friends_group" id="add_friends_group">'+
						'<option value="">未分组</option>'+data.returnList+'</select></div><div class="add-friends-dialog-1">留言：<textarea id="add_friends_msg"></textarea></div>',
					'buttons'	: {
							'image/cancel_btn.gif'	: {
							'class'	: '',
							'action': function(){}
									},
							'image/ok_black_btn.gif'	: {
							'class'	: '',
							'action': function(){
									group_id = $("#add_friends_group").val();
									text = $("#add_friends_msg").val();
									$.ajax({   
										url:"user/addFriends",    
										type:"POST",    
										data:{"friends_id":friends_id,"group_id":group_id,"msg_text":text},   
										dataType:"json",  
										error:function(){alert("ajax error");},
										success:function(data){ 
											obj.removeClass().addClass("wait_friends_deal").html("申请已发出");
											$.tip({'text': '好友申请已发出，等待对方回应！'});
										}
									});
								}
							}
						}
				});
			}
		});
	});
	
	
		
	 /*=========================================
	  * function
	  =========================================*/
	 function searchForFriends(keyword,page){
		 $.ajax({   
				url:"user/searchFriends",    
				type:"POST",    
				data:{"keyword":keyword,"page":page},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				beforeSend:function(){
					$(".pleasewait").show();
				},
				complete:function(){
					$(".pleasewait").hide();
				},
				success:function(data){ 
					$(".friends-result-box").show();
					if(data.result_html == ""){
						$(".friends-result-list-box").html("<p style='color:#888; font-size:14px; text-align:center;height:50px; line-height:50px;'>对不起，没有相应的结果！</p>");
						$(".friends-result-page").html("");//设置页码
					}else{
						$(".friends-result-list-box").html(data.result_html);//设置结果列表
						$(".friends-result-page").html(data.pageList);//设置页码
					}
				}
			});
	 }
	
});