(function($){
	
	path = "http://localhost:8080/zone/user/";
	
	$.add_group = function(params){
		
		if($('._Container').length){
			// 当已经有框时不显示:
			return false;
		}
		
		var buttonHTML = '';
		$.each(params.buttons,function(src,obj){
			// 生成button:
			buttonHTML += '<img src="'+path+src+'" width="70" height="25" border="0" class="_button"  />';
			if(!obj.action){
				obj.action = function(){};
			};
		});		
		//生成html
		var markup = [
			'<div class="_Mask"></div><div class="_Container" >',
			'<div class="_Box"><div class="_Box-min">',
			'<div class="_HeadTitle">&nbsp;&nbsp;',params.title,'<span class="_close">×</span></div>',
			'<div class="_Msg"><span>',params.message,'</span><input name="group_name" style="margin:30px; width:200px; height:22px; background-image:url('+path+'image/input_bg.gif); background-repeat:repeat-x; background-color:#fff; border:1px solid #ddd;" type="text" id="group_name" /></div>',
			'<div class="_Buttons">',
			buttonHTML,
			'</div></div></div></div>'
		].join('');
		
		$(markup).hide().appendTo('body').show();
		
		$("._Box").slideDown(400);
		
		var buttons = $('._Buttons ._button'),
			i = 0;
		var closes = $("._close");

		$.each(params.buttons,function(name,obj){
			buttons.eq(i++).click(function(){
				//调用事件
				obj.action();
				$.add_group.hide();
				return false;
			});
		});
		closes.click(function(){
			$.add_group.hide();
		});
	};

	$.add_group.hide = function(){
		$('._Container').remove();
		$('._Mask').remove();
	};
	
})(jQuery);

