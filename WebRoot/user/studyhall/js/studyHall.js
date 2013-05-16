$(function(){
	
	var society_id = $(".hall-left-notice").attr("id");
	
	getTopNotice(society_id);//载入公告
	
	getMemberList(society_id);//载入成员
	
	$(".hall-movement-container").load("user/hall/getActivity");
	getActivityList(0,society_id,"a",0,1);//载入大厅动态
	
	//$(".hall-movement-container").load("user/hall/adminHall?society_id="+society_id);
	
	//选项卡
	$(".hall-right-nav ul li").die().live("click",function(){
		$(this).addClass("hall-nav-li-curr").siblings().removeClass("hall-nav-li-curr");
	});
	
	//大厅动态
	$(".hall_activity_nav").die().live("click",function(){
		$(".hall-movement-container").load("user/hall/getActivity");
		getActivityList(0,society_id,"a",0,1);
		$(".hall_load_more").show(); //显示”加载更多“
	});
	

	//打开评论
	$(".movement_comment_open").die().live("click",function(){
		$(this).parents(".movement-list").next().slideToggle();
	});
	
	//关闭评论
	$(".movement_comment_close").die().live("click",function(){
		$(this).parent().slideUp();
	});
	
	//提交新问答
	$("#submit-topic").click(function(){
		var text = $("#new_text").val();
		var society_id =$(".hall-left-notice").attr("id");
		$.ajax({   
			url:"user/hall/addTopicOnHall",    
			type:"POST",    
			data:{"topic_text":text,"society_id":society_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				$("#new_text").val("");
				$(".hall-movement-container").load("user/hall/getActivity");
				getActivityList(0,society_id,"a",0,1);//载入大厅动态
			}
		});
	});
	
	//查看单个成员动态
	$(".hall-left-member>div").die().live("click",function(){
		$(this).addClass("hall-member-curr").siblings().removeClass("hall-member-curr");
		var user_id = parseInt($(this).attr("user_id"));
		var name = $(this).html();
		if(user_id>0){
			if($(".hall_nav_person[attr='"+user_id+"']").length > 0){
			}else{
				$(".hall_nav_person").remove();
				$(".hall-right-nav ul").append("<li class='hall-nav-li hall_nav_person' user_id='"+user_id+"'><span class='hall_nav_name'>"+name+"</span><span class='hall_nav_close'>×</span></li>");
			}
			$(".hall_nav_person").click();
		}
	});
	
	$(".hall_nav_person").die().live("click",function(){
		var user_id = parseInt($(this).attr("user_id"));
		$(".hall-movement-container").load("user/hall/getActivity");
		$(".hall_load_more").show(); //显示”加载更多“
		getActivityList(user_id,society_id,"a",0,1);
	});
	
	//查看全部成员
	$(".hall-left-member>div:first").click(function(){
		$(".hall_activity_nav").click();
	});
	
	//关闭单个成员动态
	$(".hall_nav_close").die().live("click",function(){
		$(this).parent().remove();	
		$(".hall-left-member>div:first").addClass("hall-member-curr").siblings().removeClass("hall-member-curr");//设置左侧成员列表
		$(".hall-nav-li:first").click();
	});
	
	//显示关闭成员
	$(".hall_nav_person").live("mouseover",function(){
		$(this).find(".hall_nav_close").show();
		if(!$(this).hasClass("hall-nav-li-curr")){ //当有边框时
			$(this).find(".hall_nav_name").css("margin-left","-1px");
		}
	}).live("mouseout",function(){
		$(this).find(".hall_nav_close").hide();
		if(!$(this).hasClass("hall-nav-li-curr")){ //当有边框时
			$(this).find(".hall_nav_name").css("margin-left","0px");
		}
	});
	
	//评论框
	$(".comment-input").die("focus").live("focus",function(){
		if($(this).val() == "参与讨论"){
			$(this).val("");
		};
	}).die("blur").live("blur",function(){
		if($(this).val() == ""){
			$(this).val("参与讨论");
		};
	});
	
	
	//提交评论
	$(".comment-back-btn").die().live("click",function(){
		var obj = $(this);
		at_id = obj.attr("at_id");
		comment_text = $(this).prev(".comment-input").val();
		if(at_id!=""){
			comment_text = comment_text.substring(comment_text.indexOf("：")+1);
		}
		activity_id = $(this).attr("id");
		$.ajax({   
			url:"user/addComment",    
			type:"POST",    
			data:{"comment_text":comment_text,"activity_id":activity_id,"at_id":at_id},   
			dataType:"json",  
			beforeSend:function(){
				$(".pleasewait").show();
			},
			complete:function(){
				$(".pleasewait").hide();
			},
			error:function(){alert("ajax error");},
			success:function(data){ 
				$(".comment-input").val("");
				obj.attr("at_id","0");//初始化at_id
				loadComment(activity_id);
			}
		});
		
	});
	
	
	// 打开管理页面或大厅资料
	$(".hall_admin_btn").click(function(){
		var id = $(this).attr("id");
		//var t_id = $(this).attr("t_id");
		if(id=="0"){
			typename = "大厅资料";
		}else if(id=="1"){
			typename = "大厅管理";
		}
		if($(".hall_nav_admin[id='"+id+"']").length < 1){
			$(".hall-right-nav ul").append("<li class='hall-nav-li hall_nav_admin' id='"+id+"'><span id='"+id+"' class='hall_admin_name'>"+typename+"</span><span class='hall_admin_close'>×</span></li>");
		}
		$(".hall_admin_name[id='"+id+"']").click();
	});
	
	$(".hall_admin_name").die().live("click",function(){
		var id = $(this).attr("id");
		if(id=="0"){
			url = "hallInfo?society_id="+society_id;
		}else if(id=="1"){
			url = "adminHall?society_id="+society_id;
		}
		$(".hall-movement-container").load("user/hall/"+url);
	});
	
	//显示关闭管理
	$(".hall_nav_admin").live("mouseover",function(){
		$(this).find(".hall_admin_close").show();
		if(!$(this).hasClass("hall-nav-li-curr")){ //当有边框时
			$(this).find(".hall_admin_name").css("margin-left","-1px");
		}
	}).live("mouseout",function(){
		$(this).find(".hall_admin_close").hide();
		if(!$(this).hasClass("hall-nav-li-curr")){ //当有边框时
			$(this).find(".hall_admin_name").css("margin-left","0px");
		}
	});
	
	//关闭管理页面
	$(".hall_admin_close").live("click",function(){
		$(this).parent().remove();	
		$(".hall_activity_nav").click();
	});
	
	 /*=========================================
	  * function
	  =========================================*/
	
	
	/**
	 * 按话题载入评论
	 */
	function loadComment(activity_id){
		$.ajax({   
			url:"user/hall/getComment",    
			type:"POST",    
			data:{"activity_id":activity_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				$(".movement_comment_html[activity_id='"+activity_id+"']").html(data.returnCommentList);
			}
		});
	}
	 
});

/**
 * 获得前3条公告
 */
function getTopNotice(society_id){
	 $.ajax({   
			url:"user/hall/getTopNotice",    
			type:"POST",    
			data:{"society_id":society_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				if(data.notice_HTML == ""){
					$(".hall-left-notice").html("<p style='color:#888; font-size:13px; margin:0px; text-align:center;'>暂无</p>");
				}else{
					$(".hall-left-notice").html(data.notice_HTML);
				}
			}
		});
}

/**
 * 载入成员列表
 */
function getMemberList(society_id){
	 $.ajax({   
			url:"user/hall/getMemberList",    
			type:"POST",    
			data:{"society_id":society_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				$(".hall-left-member").append(data.member_HTML);
				$(".hall_member_count").html(data.member_count);
			}
		});
}
