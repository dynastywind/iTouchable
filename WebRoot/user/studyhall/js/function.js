 /*=========================================
	 * function
 =========================================*/

/**
	 * 按类型载入动态 
	 */
	function getActivityList(user_id,society_id,type,topic_type,page){
		$.ajax({   
				url:"user/hall/getActivityListOnSociety",    
				type:"POST",    
				data:{"user_id":user_id,"society_id":society_id,"type":type,"topic_type":topic_type,"page":page},   
				dataType:"json",  
				beforeSend:function(){
					$(".more_img").show();
					$(".pleasewait").show();
				},
				complete:function(){
					$(".more_img").hide();
					$(".pleasewait").hide();
				},
				error:function(){alert("ajax error");},
				success:function(data){ 
					if(page < 2){
						tip = "暂无记录！";
						$(".hall_person_container").html("");
						if(data.movement_HTML == ""){
							$(".hall_person_container").html("<div class='already-all'>"+tip+"<div>");
							$(".hall_load_more").hide();
						}else{
							$(".hall_person_container").html(data.movement_HTML);
						}	
					}else{
						tip = "已加载完全部！";
						if(data.movement_HTML == ""){
							$(".hall_person_container").append("<div class='already-all'>"+tip+"<div>");
							$(".hall_load_more").hide();
						}else{
							$(".hall_person_container").append(data.movement_HTML);
						}	
					}
					$(".hall_load_more,.refresh-topic").attr("page",data.page);//设置当前页码
					$(".hall_load_more,.refresh-topic").attr("a_type",data.type);//设置当前类型
					$(".hall_load_more,.refresh-topic").attr("topic_type",data.topic_type);//设置当前话题类型
					$(".hall_load_more,.refresh-topic").attr("user_id",data.user_id);//设置当单个成员id
				}
			});
	}