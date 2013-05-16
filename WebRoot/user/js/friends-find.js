$(function(){
	
	//寻找好友
	$(".friend-find-btn").click(function(){
		keyword = $("#keyword_find").val();
		if( keyword == document.getElementById("keyword_find").defaultValue){
			$("#friends-list-box").html("<p style='color:#888; font-size:14px; text-align:center;'>请输入查找的关键词！</p>");
			$("#keyword_find").focus();
			$(".group_title_name").html("查找结果");//设置标题
			$(".freinds_page_div").html("");//设置页码
		}else{
			 $.ajax({   
					url:"user/findFriends",    
					type:"POST",    
					data:{"keyword":keyword},   
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
							$("#friends-list-box").html("<p style='color:#888; font-size:14px; text-align:center; '>对不起，没有相应的结果！</p>");
							$(".freinds_page_div").html("");//设置页码
						}else{
							$("#friends-list-box").html(data.result_html);//设置结果列表
							$(".freinds_page_div").html(data.pageList);//设置页码
						}
						$(".group_title_name").html("查找结果");//设置标题
					}
				});
		}
	});
	
	//翻页
	$(".find_page_a").live("click",function(){
		curr_page = parseInt($(".page_curr_a").html());
		page = $(this).attr("id");
		if(page == "-1"){
			page = curr_page - 1;//上一页
		}else if(page=="+1"){
			page = curr_page +1;//下一页
		}
		$.ajax({   
			url:"user/findFriends",    
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
					$("#friends-list-box").html("<p style='color:#888; font-size:14px; text-align:center; '>对不起，没有相应的结果！</p>");
					$(".freinds_page_div").html("");//设置页码
				}else{
					$("#friends-list-box").html(data.result_html);//设置结果列表
					$(".freinds_page_div").html(data.pageList);//设置页码
				}
			}
		});
	});
		
	 /*=========================================
	  * function
	  =========================================*/
	 
	
});