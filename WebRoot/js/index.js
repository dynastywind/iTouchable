$(function(){
	
	//用户登录
	$("#submit-login").click(function(){
		var id = $("#id").val();
		var password = $("#password").val();
		if(id=="" || password==""){
			alert("不能为空！");
		}else{
			$.ajax({   
				url:"user/userLogin",    
				type:"POST",    
				data:{"id":id,"password":password},   
				dataType:"json",  
				error:function(){alert("ajax error");},
				success:function(data){ 
					switch(data.pass){
						case 0:
							alert("帐号或密码错误！"); break;
						case 1:
							window.location.href="user/studentCenter"; break;
						case 2:
							alert("帐号不存在！") ;
					}
				}
			});
		}
		
	});
	
});