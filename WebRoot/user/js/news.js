$(function(){
	
	//初始载入话题
	loadTopic("a",0,0);
	
	//选项卡
	$(".new_dh_div ul li").click(function(){
		$(this).parents(".new_dh_div").find("li").removeClass("new_curr_li");
		$(this).addClass("new_curr_li");
	});
	
	//社区动态
	$(".new_dh_ul li").click(function(){
		var a_type = $(this).attr("a_type");
		var topic_type = $(this).attr("topic_type");
		$(".new_content_div").html("");
		loadTopic(a_type,topic_type,0);
	});
	
	//刷新当前话题列表
	$(".refresh-topic").click(function(){
		var a_type = $(this).attr("a_type");
		var topic_type = $(this).attr("topic_type");
		$(".new_content_div").html("");
		loadTopic(a_type,topic_type,0);
	});
	
	//加载更多（下一页）
	$(".load-more").click(function(){
		var topic_type = $(this).attr("topic_type");
		var a_type = $(this).attr("a_type");
		var page = $(this).attr("page");
		loadNextTopic(a_type,topic_type,0,page);
	});
	
	//回应at
	$(".new-comment-back").die().live("click",function(){
		var activity_id = $(this).attr("activity_id");
		var user_at_id = $(this).attr("at_id");
		var user_at_name = $(this).attr("at_name");
		$("textarea[activity_id='"+activity_id+"']").focus().val("回应"+user_at_name+"：" );
		$(".comment-back-btn[id='"+activity_id+"']").attr("at_id",user_at_id);
	});
	
	//激活评论框
	$(".comment-input").die("focus").live("focus",function(){
		$(this).val("");
		$(this).addClass("comment-input-curr").prev(".add-comment-img").show().next().next(".comment-back-btn").show();
	});
	
	//提交评论
	$(".comment-back-btn").die().live("click",function(){
		var obj = $(this);
		var at_id = obj.attr("at_id");
		var comment_text = $(this).prev(".comment-input").val();
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
				$("#new_text").val("");
				obj.attr("at_id","0");//初始化at_id
				loadComment(activity_id);
			}
		});
		
	});
	
	//冻结评论框
	$(".comment-input").die("blur").live("blur",function(){
		var obj = $(this);
		setTimeout(function(){
			obj.val("参与讨论").removeClass("comment-input-curr").prev(".add-comment-img").hide().next().next(".comment-back-btn").hide();
		},500);
	});
	
	//展开评论
	$(".comment-more").live("click",function(){
		$(this).next().find(".news_comment_list_div").show();
		$(this).hide();
		$(this).next().next().show();
	});
	
	$(".news_comment_hide").live("click",function(){
		$(this).hide();
		obj1 = $(this).prev();
		obj2 = obj1.find(".news_comment_list_div");
		if(obj2.size()>3){
			obj2.slice(0,obj2.size()-3).hide();//只显示最后3条评论
			obj1.prev().show();
		}	
	});
	
	
	//删除话题
	$(".news_admin_del").live("click",function(){
		var obj = $(this);
		var activity_id = obj.attr("id");
		$.ajax({   
			url:"user/delTopic",    
			type:"POST",    
			data:{"activity_id":activity_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				obj.parents(".news_list_div").remove();
				$.tip({'text': '删除成功！'});
			}
		});
	});
	
	
	//标记是否已解决
	$(".news_admin_mark").live("click",function(){
		var obj = $(this);
		var activity_id = obj.attr("id");
		var is_f = obj.attr("is_f");
		if(is_f == "0"){
			finish = "false";
		}else{
			finish = "true";
		}
		$.ajax({   
			url:"user/updateTopicFinish",    
			type:"POST",    
			data:{"id":activity_id,"finish":finish},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				if(is_f == "0"){
					obj.html("标记为未解决");
					obj.attr("is_f","1");
				}else{
					obj.html("标记为已解决");
					obj.attr("is_f","0");
				}
				
				$.tip({'text': '标记成功！'});
			}
		});
	});
	
	/*---------------------------------------------------------------*/
	/**
	 * function
	 */
	/*---------------------------------------------------------------*/
	
	
	/**
	 * 按类型载入话题列表
	 */
	function loadTopic(a_type,topic_type,friends_id){
		$(".new_content_div").html("");
		$(".load-more").hide();
		$.ajax({   
			url:"user/getActivityList",    
			type:"POST",    
			data:{"type":a_type,"topic_type":topic_type,"friends_id":friends_id},   
			dataType:"json",  
			beforeSend:function(){
				$(".loading").show();
			},
			complete:function(){
				$(".loading").hide();
			},
			error:function(){alert("ajax error");},
			success:function(data){ 
				if(data.returnList==""){
					$(".new_content_div").html("<p style='color:#666; text-align:center; line-height:50px; font-size:14px;'>暂无记录！</p>");
				}else{
					$(".new_content_div").html(data.returnList);
					foldComment();
					$(".load-more").show();//设置当前页码
				}
				$(".load-more,.refresh-topic").attr("page",data.page);//设置当前页码
				$(".load-more,.refresh-topic").attr("a_type",data.type);//设置当前类型
				$(".load-more,.refresh-topic").attr("topic_type",data.topic_type);//设置当前话题类型
			}
		});
		setBorderLine();//设置边框
	}
	
	/**
	 * 按类型载入下一页
	 * types:话题的类型
	 * page:当前页码
	 */
	function loadNextTopic(a_type,topic_type,friends_id,page){
		$.ajax({   
			url:"user/getActivityList",    
			type:"POST",    
			data:{"type":a_type,"topic_type":topic_type,"friends_id":friends_id,"page":parseInt(page)+1},   
			dataType:"json",  
			beforeSend:function(){
				$(".more-img").show();
			},
			complete:function(){
				$(".more-img").hide();
			},
			error:function(){alert("ajax error");},
			success:function(data){ 
				if(data.returnList==""){
					$(".new_content_div").append("<div class='already-all'>已加载全部！<div>");
					$(".load-more").hide();
				}else{
					$(".new_content_div").append(data.returnList);
					foldComment();
				}
				$(".load-more,.refresh-topic").attr("page",data.page);//设置当前页码
				$(".load-more,.refresh-topic").attr("a_type",data.type);//设置当前类型
				$(".load-more,.refresh-topic").attr("topic_type",data.topic_type);//设置当前话题类型
			}
		});
		setBorderLine();//设置边框
	}
	
	/**
	 * 按话题载入评论
	 */
	function loadComment(activity_id){
		$.ajax({   
			url:"user/getCommentByTopic",    
			type:"POST",    
			data:{"activity_id":activity_id},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
					$("#topic-comment-"+activity_id).html(data.returnCommentList);
					foldComment();
			}
		});
		setBorderLine();//设置边框
	}
	
	/**
	 * 折叠评论（评论数大于3）
	 */
	function foldComment(){
		obj1 = $(".news_comment_contain_div");
		obj1.each(function(i){
			obj2 = $(".news_comment_contain_div:eq("+i+")>div");
			if(obj2.size()>3){
				obj2.slice(0,obj2.size()-3).hide();//只显示最后3条评论
				$(".news_comment_contain_div:eq("+i+")").prev().show();
			}
		});	
		
	}
	
});