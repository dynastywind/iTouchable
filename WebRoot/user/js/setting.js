$(function(){
	
	//选项卡
	$(".setting_nav li").click(function(){
		var i = $(this).attr("id");
		$(this).addClass("setting_li_curr").siblings().removeClass("setting_li_curr");
		$(".setting_panel:eq("+i+")").show().siblings(".setting_panel").hide();
	});
	
	 getPersonInfo();
	 
	 //保存用户资料
	 $(".setting_save_btn").live("click",function(){
		 updatePersonInfo();
	 });
	 
	 //验证旧密码
	 $("#password_old").blur(function(){
		 if($(this).val() != ""){
			 checkOldPassword();
		 }else{
			 $(".password_tip").html("×不能为空！");
		 }
	 });
	 
	 //初始化
	 $("#password_old").focus(function(){
		 $(".password_tip").html("");
		$(this).css("border","1px solid #ddd"); 
	 });
	 
	 //保存新密码
	 $(".setting_password_btn").click(function(){
		 updatePassword();
	 });
	 
	 
	/*=========================================
	 * function
	  =========================================*/
	 
	 /**
	  * 载入用户资料
	  */
	function getPersonInfo(){
		$.ajax({   
			url:"user/getPersonInfo",    
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
				$("#person_info_box").html(data.return_HTML);
			}
		});	 
	}
	
	/**
	 * 更新用户资料
	 */
	function updatePersonInfo(){
		var user_name = $("#user_name").val();
		var user_gender = $("#user_gender:checked").val();
		var user_birth = "";
		var user_school = $("#user_school").val();
		var user_grade = $("#user_grade").val();
		var user_college = $("#user_college").val();
		var user_class = $("#user_class").val();
		var user_city = $("#user_city").val();
		var user_email = $("#user_email").val();
		var user_qq = $("#user_qq").val();
		var user_tel = $("#user_tel").val();
		
		if($("#user_year").val() != "0" &&  $("#user_month").val() != "0" || $("#user_day").val() != "0"){
			user_birth = $("#user_year").val() + "-" + $("#user_month").val() + "-" + $("#user_day").val() ;
		}
		
		$.ajax({   
			url:"user/updatePersonInfo",    
			type:"POST",    
			data:{"user.name":user_name,"user.gender":user_gender,"user.birth":user_birth,"user.school":user_school,"user.grade":user_grade,"user.college":user_college,
				"user.classes":user_class,"user.city":user_city,"user.email":user_email,"user.tel":user_tel,"user.qq":user_qq},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			beforeSend:function(){
				$(".pleasewait").show();
			},
			complete:function(){
				$(".pleasewait").hide();
			},
			success:function(data){ 
				$.tip({'text':'保存成功!'});
			}
		});	
		
	}
	
	/**
	 * 验证旧密码
	 */
	function checkOldPassword(){
		var password = $("#password_old").val();
		$.ajax({   
			url:"user/checkPassword",    
			type:"POST",    
			data:{"password":password},   
			dataType:"json",  
			error:function(){alert("ajax error");},
			success:function(data){ 
				if(data.pass=="1"){
					$(".password_tip").html("√");//提示信息
					$("#password_old").css("border","1px solid #ddd");
				}else{
					$("#password_old").css("border","1px solid #ff6600");
					$(".password_tip").html("×密码有误！");
				}
			}
		});	
	}
	
	/**
	 * 更新密码
	 */
	function  updatePassword(){
		var old_pass = $("#password_old").val();
		var password = $("#password_new_2").val();
		if(password != "" && $("#password_new_1").val() == password){
			$.ajax({   
				url:"user/updateUserPassword",    
				type:"POST",    
				data:{"password":password,"old_pass":old_pass},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				success:function(data){ 
					if(data.pass=="1"){
						$.tip({'text':'保存成功!'});
					}else{
						$.tip({'text':'保存失败!'});
					}
				}
			});	
		}else{
			$.tip({'text':'输入不能为空！'});
		}
		
	}
	
});