(function($){
	
	path = "http://localhost:8080/zone/user/";
	
	$.dialog = function(params){
		
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
			'<div class="_Msg">'+params.msg_html+'</div>',
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
				$.dialog.hide();
				return false;
			});
		});
		closes.click(function(){
			$.dialog.hide();
		});
	};

	$.dialog.hide = function(){
		$('._Container').remove();
		$('._Mask').remove();
	};
	
})(jQuery);

