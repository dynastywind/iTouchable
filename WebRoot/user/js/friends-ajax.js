$(function(){
	
	//载入分组列表
	loadGroupList();
	
	 loadAllFriends();
	 
	 //按分组载入好友
	 $(".freinds_ul li").live("click",function(){
		 var group = $(this).attr("id");
		 $.ajax({   
				url:"user/getFriendsListByGroup",    
				type:"POST",    
				data:{"group_id":group},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				beforeSend:function(){
					$(".pleasewait").show();
				},
				complete:function(){
					$(".pleasewait").hide();
				},
				success:function(data){ 
					if(data.returnList == ""){
						$("#friends-list-box").html("<p style='color:#888; font-size:14px; text-align:center;'>暂时还没有好友，赶紧去添加吧！</p>");
						$(".freinds_page_div").html("");
					}else{
						$("#friends-list-box").html(data.returnList);
						$(".freinds_page_div").html(data.pageList);
					}
				}
			});
		 $(".left_title_div span").html($(this).html()).attr("id",group);//设置标题
		 
	 });
	 
	 
	 //上一页
	 $(".pre-page").live("click",function(){
		 var curr_page = parseInt($(".page_curr_a").html());
		 $.ajax({   
				url:"user/getFriendsListByGroup",    
				type:"POST",    
				data:{"page":curr_page-1,"group_id":$(".group_title_name").attr("id")},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				beforeSend:function(){
					$(".pleasewait").show();
				},
				complete:function(){
					$(".pleasewait").hide();
				},
				success:function(data){ 
					$("#friends-list-box").html(data.returnList);
					$(".freinds_page_div").html(data.pageList);
				}
			});
	 });
	 
	 //翻页
	 $(".page_a").live("click",function(){
		 curr_page = parseInt($(".page_curr_a").html());
			page = $(this).attr("id");
			if(page == "-1"){
				page = curr_page - 1;//上一页
			}else if(page=="+1"){
				page = curr_page +1;//下一页
			}
			
		 $.ajax({   
				url:"user/getFriendsListByGroup",    
				type:"POST",    
				data:{"page":page,"group_id":$(".group_title_name").attr("id")},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				beforeSend:function(){
					$(".pleasewait").show();
				},
				complete:function(){
					$(".pleasewait").hide();
				},
				success:function(data){ 
					$("#friends-list-box").html(data.returnList);
					$(".freinds_page_div").html(data.pageList);
				}
			});
	 });
	 
	 //跳转页
	 $(".page-num").live("click",function(){
		 page = parseInt($(this).html());
		 $.ajax({   
				url:"user/getFriendsListByGroup",    
				type:"POST",    
				data:{"page":page,"group_id":group_id},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				beforeSend:function(){
					$(".pleasewait").show();
				},
				complete:function(){
					$(".pleasewait").hide();
				},
				success:function(data){ 
					$("#friends-list-box").html(data.returnList);
					$(".freinds_page_div").html(data.pageList);
				}
			});
	 });
	 
	 
	 
	//编辑分组下拉框
		$(".group_edit_ico").live("click",function(){
			$(this).next().slideToggle();
		});
		$(".edit_div").live("mouseleave",function(){
			$(this).slideUp();
		});
		
		//移动好友下拉框 
		$(".freinds_move").live("click",function(){
			friends_id = $(this).attr("friends_id");
			$(".freinds_move_div[friends_id="+friends_id+"]").slideToggle();
		});
	
		$(".friends-tab-1").live("mouseleave",function(){
				$(".freinds_move_div").slideUp();
		});
		
		$(".freinds_move_div a").live("click",function(i){
			friends_id = $(this).parent().attr("friends_id");
			group_id = $(this).attr("id");
			$.ajax({   
				url:"user/updateFriendsToGroup",    
				type:"POST",    
				data:{"group_id":group_id,"friends_id":friends_id},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				success:function(data){ 
					$("#friends-list-box li[id="+friends_id+"]").remove();//移除好友 
					refreshGroupList();//刷新分组列表,分组好友数量
					getGroupAndFriendsList();
					$.tip({'text': '移动成功！'});
				}
			});
			$(".freinds_move_div").fadeOut();
		});
	
					
		//删除好友弹出框 
		$(".freinds_delete").live("click",function(){
			friends_id = $(this).attr("friends_id");
			$.warning({
				'title'		: '删除确认',
				'ico'		: 'image/warning.png',
				'message'	: '您确定要删除该好友么？',
				'buttons'	: {
						'image/cancel_btn.gif'	: {
						'class'	: '',
						'action': function(){}
								},
						'image/ok_black_btn.gif'	: {
						'class'	: '',
						'action': function(){
							$.ajax({   
								url:"user/delFriend",    
								type:"POST",    
								data:{"friends_id":friends_id},   
								dataType:"json",  
								error:function(){alert("ajax error");},
								success:function(data){ 
									$("#friends-list-box li[id="+friends_id+"]").remove();//移除好友 
									refreshGroupList();//刷新分组列表,分组好友数量
									getGroupAndFriendsList();
									$.tip({'text': '删除成功！'});
								}
							});
							}
						}
					}
			});
		});
		
		//添加分组弹出框
		$(".add-group-btn").click(function(){
			$.add_group({
				'title'		: '添加分组',
				'message'	: '新增分组：',
				'buttons'	: {
						'image/cancel_btn.gif'	: {
						'class'	: '',
						'action': function(){}
								},
						'image/ok_black_btn.gif'	: {
						'class'	: '',
						'action': function(){
							group_name = $("#group_name").val();//新分组名称
							$.ajax({   
								url:"user/addGroup",    
								type:"POST",    
								data:{"group_name":group_name},   
								dataType:"json",  
								error:function(){alert("ajax error");},
								success:function(data){ 
									refreshGroupList();//刷新分组列表
									getGroupAndFriendsList();
									$.tip({'text': '添加成功！'});
								}
							});
							}
						}
					}
			});
			$("#group_name").focus();
		});
		
		//删除分组弹出框
		$(".group_delete").live("click",function(){
			//$("#dialog_iframe").attr("value","freinds_group_delete").click();
			group_id = $(this).attr("id");
			$.warning({
				'title'		: '删除确认',
				'ico'		: 'image/warning.png',
				'message'	: '分组中的好友将移至[未分组]，确定删除么？',
				'buttons'	: {
						'image/cancel_btn.gif'	: {
						'class'	: '',
						'action': function(){}
								},
						'image/ok_black_btn.gif'	: {
						'class'	: '',
						'action': function(){
								$.ajax({   
								url:"user/delGroup",    
								type:"POST",    
								data:{"group_id":group_id},   
								dataType:"json",  
								error:function(){alert("ajax error");},
								success:function(data){ 
									$(this).parent().hide();
									refreshGroupList();//刷新分组列表
									getGroupAndFriendsList();
									$.tip({'text': '删除成功！'});
								}
								});
							}
						}
					}
			});
		});
		
		//重命名分组弹出框
		$(".group_rename").live("click",function(){
			group_id = $(this).attr("id");
			$.add_group({
				'title'		: '重命名分组',
				'message'	: '新的组名：',
				'buttons'	: {
						'image/cancel_btn.gif'	: {
						'class'	: '',
						'action': function(){}
								},
						'image/ok_black_btn.gif'	: {
						'class'	: '',
						'action': function(){
							group_name = $("#group_name").val();
							$.ajax({   
								url:"user/updateGroupName",    
								type:"POST",    
								data:{"group_id":group_id,"group_name":group_name},   
								dataType:"json",  
								error:function(){alert("ajax error");},
								success:function(data){ 
									$(this).parent().hide();
									refreshGroupList();//刷新分组列表
									getGroupAndFriendsList();
									$.tip({'text': '重命名成功！'});
								}
							});
							}
						}
					}
			});
		});
		
		
		
	 /*=========================================
	  * function
	  =========================================*/
	 
	 /**
	  * 载入好友分组列表
	  */
	 function loadGroupList(){
			 $.ajax({   
					url:"user/getFriendsGroupList",    
					type:"POST",    
					data:{},   
					dataType:"json",  
					error:function(){alert("ajax error");},
					success:function(data){ 
						
						$(".freinds_ul").html(data.returnList);
					}
			});
	 }
	 
	/**
	 * 载入所有好友
	 */
		function loadAllFriends(){
			$.ajax({   
				url:"user/getFriendsListByGroup",    
				type:"POST",    
				data:{},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				beforeSend:function(){
					$(".pleasewait").show();
				},
				complete:function(){
					$(".pleasewait").hide();
				},
				success:function(data){ 
					if(data.returnList == ""){
						$("#friends-list-box").html("<p style='color:#888; font-size:14px; text-align:center;'>暂时还没有好友，赶紧去添加吧！</p>");
					}else{
						$("#friends-list-box").html(data.returnList);
						$(".freinds_page_div").html(data.pageList);
					}
					$(".left_title_div span").html("所有好友("+data.allFriendsCount+")");//设置标题
				}
			});
		}
	
		
		 /**
		  * 载入好友分组列表
		  */
		 function refreshGroupList(){
				 $.ajax({   
						url:"user/getFriendsGroupList",    
						type:"POST",    
						data:{},   
						dataType:"json",  
						error:function(){alert("ajax error");},
						success:function(data){ 
							$(".freinds_ul").html(data.returnList);
						}
				});
		 }
		 
});