/*========================
 * 公用 function
 ========================*/

/*
* 设置边框
*/
function setBorderLine(){
	left = $(".body_left_div").height();
	right = $(".center-body_right_div").height();
	if(left>right){
		$(".body_left_div").css("border-right","1px solid #ddd");
		$(".center-body_right_div").css("border-left","0px");
	}
}

/**
 * 载入联系人列表
 */
function getGroupAndFriendsList(){
	$.ajax({   
		url:"user/getGroupAndFriends",    
		type:"POST",    
		data:{},   
		dataType:"json",  
		error:function(){alert("ajax error");},
		success:function(data){ 
			$("#_Friends_ajax_box").html(data.returnList);
		}
	});
}


/**
 * 载入学习大厅列表
 */
function getSocietyList(){
	$.ajax({   
		url:"user/getSoceityList",    
		type:"POST",    
		data:{},   
		dataType:"json",  
		error:function(){alert("ajax error");},
		success:function(data){ 
			$(".center_studyhall_contain").html(data.society_HTML);
		}
	});
}