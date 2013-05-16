 /*=========================================
      * 
	  *
	  =========================================*/
$(function(){
	
	//选项卡
	$(".society_add_nav li").each(function(i){
		$(this).click(function(){
			$(this).addClass("society_add_li_curr").siblings().removeClass("society_add_li_curr");
			$(".society>div:eq("+i+")").show().siblings().hide();
		});
	});
	
	//搜索框
	$("#keyword_society").focus(function(){
		if($(this).val() == this.defaultValue){						  
			$(this).val("").css({"color":"#333","background-color":"#fff"});
		}
	});

	$("#keyword_society").blur(function(){
		if($(this).val() == ""){
			$(this).val(this.defaultValue).css({"color":"#aaa"});
		}
	});
	
	//载入好友列表
	 $.ajax({   
			url:"user/getFriendsListOnAddSociety",    
			type:"POST",    
			data:{},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				$(".society_friends_box").append(data.returnList);
			}
		});
	
	//打开好友
	$(".society_friends_add_btn").click(function(){
		$(this).parents(".society_friends_selected_box").next().slideToggle();
	});
	$(".society_friends_name_selected").click(function(){
		$(this).parent().next().slideDown();
	});
	$(".society_friends_close").click(function(){
		$(this).parents(".society_friends_box").slideUp();
	});
	
	//折叠好友
	$(".society_friends_group_title span").die().live("click",function(){
		$(this).parent().next().slideToggle().siblings(".society_friends_ul").slideUp();
	});
	
	//按组勾选好友
	$(".society_friends_group_check").live("change",function(){
		var obj = $(this).parent().next().find("input");
		var box_obj = $(this).parents(".society_friends_box").prev().find("ul");
		var count_allow = parseInt(box_obj.parent().find(".society_friends_count_allow").html());
		var count = box_obj.find("li").length;
		if(count == count_allow){ 
			$(this).attr("checked",false);
			$.tip({'text':"对不起，最多可添加"+count_allow+"人！"});
		}
		if($(this).attr("checked")){
			obj.attr("checked",true);
		}else{
			obj.attr("checked",false);
		}
		obj.each(function(){   //勾选或移除
			addUser($(this));
		});
	});
	
	//单个勾选好友
	$(".society_friends_ul input[type='checkbox']").live("change",function(){
		addUser($(this));
	});
	
	//移除好友
	$(".society_friends_name_selected span").live("click",function(){
		var user_id = $(this).parent().attr("user_id");
		$(this).parent().remove();
		var obj = $(".society_friends_box").find("input[value='"+user_id+"']");
		obj.attr("checked",false);
		addUser(obj);
	});
	
	//移除所有
	$(".society_friends_remove_all_btn").click(function(){
		 var obj = $(this).parents(".society_friends_selected_box").next().find("input");
		 obj.attr("checked",false);
		 obj.each(function(){
			 addUser($(this));
		 });
	});
	
	
	//提交表单
	$(".society_add_btn").click(function(){
		var society_type = $("#society_type").val();
		var society_name = $("#society_name").val();
		var society_info = $("#society_info").val();
		var society_teacher = $("#society_teacher").val();
		var society_course = $("#society_course").val();
		var member = new Array();//成员
		$("#society_friends_member li").each(function(i){
			member[i] = $(this).attr("user_id");
		});
		 $.ajax({   
				url:"user/addSociety",    
				type:"POST",    
				data:{"society_type":society_type,"society_course":society_course,"society_name":society_name,"society_info":society_info,"society_teacher":society_teacher,"society_member":String(member)},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				success:function(data){ 
					$("#society_name").val(""); //初始化
					$("#society_info").val("");
					var obj = $(".society_friends_box input");
					 obj.attr("checked",false);
					 obj.each(function(){ //移除
						 addUser($(this));
					 });
					 $(".society_friends_box").slideUp();
					 $.tip({'text':'创建成功！'});
				}
			});
		
	});
	
	
	 /*=========================================
	  * function
	  =========================================*/
	
	
	/*
	 * 勾选好友
	 * obj:input类型
	 */
	function addUser(obj){
		var user_id = obj.parent().attr("user_id");
		var user_name=obj.prev().html();
		var box_obj = obj.parents(".society_friends_box").prev().find("ul");
		if(obj.attr("checked")){
			var count_allow = parseInt(box_obj.parent().find(".society_friends_count_allow").html());
			var count = box_obj.find("li").length;
			if(count<count_allow){ //当小于最大允许值时
				obj.parent().addClass("society_friends_selected");
				if(box_obj.find("li[user_id='"+user_id+"']").size() == 0){ //当不存在时
					box_obj.append("<li user_id='"+user_id+"'>"+user_name+"<span>×</span></li>");//添加
				}
			}else{
				obj.attr("checked",false);
				obj.parent().removeClass("society_friends_selected");
				obj.parent().parent().prev().find("input").attr("checked",false);
				$.tip({'text':"对不起，最多可添加"+count_allow+"人！"});
			}
		}else{
			obj.parent().removeClass("society_friends_selected");
			obj.parent().parent().prev().find("input").attr("checked",false);
			box_obj.find("li[user_id='"+user_id+"']").remove();//移除
		}
		countUser(box_obj); //计算已添加的好友
	}
	
	
	/*
	 * 计算已添加的好友数量
	 * obj:box类型
	 */
	function countUser(obj){
		obj.parent().find(".society_friends_count").html(obj.find("li").length);
	}
});