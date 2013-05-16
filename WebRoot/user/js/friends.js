$(function(){
			
			$(".friends-nav li").each(function(i){
				$(this).click(function(){
					$(this).addClass("friends-nav-li-curr").siblings().removeClass("friends-nav-li-curr");
					$(".friends_div>div:eq("+i+")").show().siblings().hide();
				});
			});
			
			//搜索框
			$(".input-text").focus(function(){
				if($(this).val() == this.defaultValue){						  
					$(this).val("").css({"color":"#333","background-color":"#fff"});
				}
			});

			$(".input-text").blur(function(){
				if($(this).val() == ""){
					$(this).val(this.defaultValue).css({"color":"#aaa"});
				}
			});
			
});