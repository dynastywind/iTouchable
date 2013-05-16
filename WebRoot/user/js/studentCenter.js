$(function(){
	
	//头部下拉框
	$(".head_function_div").hover(function(){
		$(".head_home_menu").slideDown();
	},function(){
		$(".head_home_menu").slideUp();
	});
	
	//提交新问答
	$("#submit-topic").click(function(){
		text = $("#new_text").val();
		$.ajax({   
			url:"user/addTopic",    
			type:"POST",    
			data:{"topic_text":text},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				$("#new_text").val("");
				$("#frame_main").load("user/getTopicList");//刷新全部话题
			}
		});
	});
	
	//左侧导航
	$(".center_nav_ul a").click(function(){
		url = $(this).attr("id");
		$(".left_dh_div li").removeClass("left_curr_li");
		$(this).parent().addClass("left_curr_li");
		$.ajax({   
			url:"getSession",    
			type:"POST",    
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){
				if(data.session ==""){ //判断session是否失效
					window.location.href = "/zone";
				}else{
					$("#frame_main").load("user/"+url);
				}
			}
		});
		
	});
	
	//学习大厅导航
	$(".center_studyhall_open").live("click",function(){
		$(this).parent().next().slideToggle();
	});
	
	//显示创建按钮
	$(".center_studyhall_nav").live("mouseover",function(){
		$(this).find(".center_studyhall_add_btn").show();
	}).live("mouseout",function(){
		$(this).find(".center_studyhall_add_btn").hide();
	});
	
	//打开大厅新窗口
	$(".center_studyhall_nav_ul a").live("click",function(){
		var id = $(this).attr("society_id");
		window.open("/zone/user/hall/studyHall?c="+id) ;
	});
	
	//打开创建大厅页面
	$(".center_studyhall_add_btn").click(function(){
		$(".left_dh_div li").removeClass("left_curr_li");
		$("#frame_main").load("user/getSociety");
	});
	
	//载入学习大厅列表
	getSocietyList();
	
	//载入框架页面
	//$("#frame_main").load("user/getTopicList");
	$("#frame_main").load("user/examCenter");
	
	
	/*========================================
	 * function
	======================================== */
	
	
});

