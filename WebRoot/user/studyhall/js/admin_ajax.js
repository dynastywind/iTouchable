$(function(){
	
	var society_id = $(".hall-left-notice").attr("id");
	var society_name = $(".hall-left-info-right-1").html();
	
	
	//载入成员列表
	getMemberList();
	
	//载入好友列表
	getFriendsList(society_id);
	
	//载入公告列表
	getNoticeList(society_id);
	
	//载入大厅资料
	getInfo(society_id);
	
	// 保存公告
	$(".admin_notice_save_btn").click(function(){
		var text = $("#admin_notice_textarea").val();
		if(text.length>0){
			$.ajax({   
				url:"user/hall/addNotice",    
				type:"POST",    
				data:{"society_id":society_id,"text":text},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				beforeSend:function(){
					$(".pleasewait").show();
				},
				complete:function(){
					$(".pleasewait").hide();
				},
				success:function(data){ 
					getNoticeList(society_id);//刷新公告列表
					getTopNotice(society_id);
					$("#admin_notice_textarea").val("");
				}
			});	 
		}else{
			$.tip({'text':'公告输入框为空！'});
			$("#admin_notice_textarea").focus();
		}
	});
	
	//置顶
	$(".admin_notice_top_btn").die().live("click",function(){
		var is_top = $(this).attr("is_top");
		var n_id = $(this).attr("n_id");
		$.ajax({   
			url:"user/hall/setTop",    
			type:"POST",    
			data:{"society_id":society_id,"is_top":is_top,"notice_id":n_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			beforeSend:function(){
				$(".pleasewait").show();
			},
			complete:function(){
				$(".pleasewait").hide();
			},
			success:function(data){ 
				getNoticeList(society_id);//刷新公告列表
				getTopNotice(society_id);
			}
		});	 
	});
	
	//删除公告
	$(".admin_notice_delete_btn").die().live("click",function(){
		var n_id = $(this).attr("n_id");
		$.ajax({   
			url:"user/hall/delNotice",    
			type:"POST",    
			data:{"notice_id":n_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			beforeSend:function(){
				$(".pleasewait").show();
			},
			complete:function(){
				$(".pleasewait").hide();
			},
			success:function(data){ 
				getNoticeList(society_id);//刷新公告列表
				getTopNotice(society_id);
				$.tip({'text':'删除成功！'});
			}
		});	 
	});
	
 /*=========================================
	  * 成员 管理
 =========================================*/
	
	// 保存邀请
	$(".admin_member_add_btn").click(function(){
		var member = new Array();//成员
		$(".admin_member_selected_box li").each(function(i){
			member[i] = $(this).attr("user_id");
		});
		if($(".admin_member_selected_box li").length>0){
			$.ajax({   
				url:"user/hall/addMember",    
				type:"POST",    
				data:{"society_id":society_id,"society_name":society_name,"society_member":String(member)},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				beforeSend:function(){
					$(".pleasewait").show();
				},
				complete:function(){
					$(".pleasewait").hide();
				},
				success:function(data){ 
					$(".admin_member_selected_box").html("");
					 $(".admin_member_friends_box").find("input").attr("checked",false);
					$.tip({'text':'申请已发出！'});
				}
			});
		}else{
			$.tip({'text':'未选择任何好友！'});
		}
	});
	
	//设置管理员
	$(".hall_admin_setadmin_btn").die("click").live("click",function(){
		var id = $(this).attr("id");
		var u_id = $(this).attr("u_id");
		var m_id = $(this).attr("m_id");
		var is_admin = false;
		if(id == "1"){
			is_admin = true;
		}
		$.ajax({   
			url:"user/hall/setAdmin",    
			type:"POST",    
			data:{"member_id":m_id,"to_u_id":u_id,"is_admin":is_admin,"society_name":society_name},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			beforeSend:function(){
				$(".pleasewait").show();
			},
			complete:function(){
				$(".pleasewait").hide();
			},
			success:function(data){ 
				getMemberList();   //刷新成员列表
				$.tip({'text':'授权成功！'});
			}
		});
	});
	
 /*=========================================
	  * function
 =========================================*/
	
	/**
	 * 载入成员列表
	 */
	function getMemberList(){
		$.ajax({   
			url:"user/hall/getMemberListOnAdmin",    
			type:"POST",    
			data:{"society_id":society_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			beforeSend:function(){
				$(".pleasewait").show();
			},
			complete:function(){
				$(".pleasewait").hide();
			},
			success:function(data){ 
				$("#ajax_member").html(data.return_HTML);
				$(".admin_member_all_count").html(data.return_count);
			}
		});	 
	}
	
	
	/**
	 * 载入好友列表
	 */
	function getFriendsList(society_id){
		$.ajax({   
			url:"user/hall/getFriendsListOnAdmin",    
			type:"POST",    
			data:{"society_id":society_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			beforeSend:function(){
				$(".pleasewait").show();
			},
			complete:function(){
				$(".pleasewait").hide();
			},
			success:function(data){ 
				$(".admin_member_friends_box").append(data.returnList);
			}
		});	 
	}
	
	/**
	 * 载入公告列表
	 */
	function getNoticeList(society_id){
		$.ajax({   
			url:"user/hall/getNoticeListOnAdmin",    
			type:"POST",    
			data:{"society_id":society_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			beforeSend:function(){
				$(".pleasewait").show();
			},
			complete:function(){
				$(".pleasewait").hide();
			},
			success:function(data){ 
				if(data.notice_HTML == ""){
					$(".admin_notice_box").html("<p style='color:#888; font-size:14px; text-align:center;'>暂无公告</p>");
					$(".admin_notice_all_count").html("0");
				}else{
					$(".admin_notice_box").html(data.notice_HTML);
					$(".admin_notice_all_count").html(data.return_count);
				}
			}
		});	 
	}
	
	/**
	 * 载入大厅资料
	 */
	function getInfo(society_id){
		$.ajax({   
			url:"user/hall/getInfoOnAdmin",    
			type:"POST",    
			data:{"society_id":society_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			beforeSend:function(){
				$(".pleasewait").show();
			},
			complete:function(){
				$(".pleasewait").hide();
			},
			success:function(data){ 
				$(".admin_info_box").html(data.returnList);
			}
		});	 
	}
	
});


