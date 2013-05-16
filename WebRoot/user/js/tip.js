(function($){
	$.tip = function(params){
		if($('._TipDiv').length){
			// 当已经有框时不显示:
			return false;
		}
		
		//生成html
		var markup = [
			'<div class="_TipDiv" style=" width:300px; text-align:center; font-size:14px; color:#fff; font-weight:bold; display:none; position:fixed; left:',$.tip.pageWidth()/2-150,'px; top:',$.tip.pageHeight(),'px; height:40px; line-height:40px; background-color:#333;">',params.text,'</div>'
		].join('');
		$(markup).hide().appendTo('body').show().animate({top:"-=40px"},500);
		setTimeout(function(){$.tip.hide();},1000); 
	};
	
	$.tip.hide = function(){
		 $("._TipDiv").animate({top:"+=40px"},500);
		 setTimeout(function(){$("._TipDiv").remove();},1500); 
	};
	
	$.tip.pageHeight = function(){ 
		if($.browser.msie){ 
			return document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight; 
		}else{ 
			return self.innerHeight; 
		} 
	}; 
	
	$.tip.pageWidth = function(){ 
		if($.browser.msie){ 
			return document.compatMode == "CSS1Compat"? document.documentElement.clientWidth : document.body.clientWidth; 
		}else{ 
			return self.innerWidth; 
		} 
	};
})(jQuery);