var recordWrap = 
			'<div>' + 
			'<div><a id="lastPage">上一页</a> <a id="nextPage">下一页</a>  页数<a id="page">1</a>/<a id="pageSum">1</a>.</div>' +
			'<div>每页显示<a id="recordNum">20</a>条.</div>' +
			'<div>总记录<a id="recordSum">0</a>条.</div>' +
			'<div id="loading" class="container" style="display:none;">加载中......</div>' +
			'</div>';
var scrollTime;
$(function($) {
	$.fn.extend({
		recordMenu : function(options){
			$(this).html(recordWrap);
            var defaults = {
                
            };
            var options =  $.extend(defaults, options);
            onContent.onInitLoad();
            
            var delayReturnData = null;
            $("#lastPage").click(function(){
            	var $page = parseInt($("#page").html());
            	if($page > 1){
            		$("#page").html($page - 1);
            		handleColor();
                	clearTimeout(delayReturnData);
        			delayReturnData = setTimeout(function(){onContent.onInitLoad();},100);
            	}
            });
            $("#nextPage").click(function(){
            	var $page = parseInt($("#page").html());
            	var $pageSum = parseInt($("#pageSum").html());
            	if($page < $pageSum){
            		$("#page").html($page + 1);
            		handleColor();
                	clearTimeout(delayReturnData);
        			delayReturnData = setTimeout(function(){onContent.onInitLoad();},100);
            	}
            });
        	$("#page").mousewheel(function(event, delta, deltaX, deltaY) {
        		var pageSumValue = parseInt($("#pageSum").html());
        		var pageValue = parseInt($(this).html());
        		if(delta < 0){
        			if(pageValue > 1){
            			$(this).html(pageValue - 1);
            			handleColor();
            			clearTimeout(delayReturnData);
            			delayReturnData = setTimeout(function(){onContent.onInitLoad();},1000);
        			}
        		}else{
        			if(pageValue < pageSumValue){
            			$(this).html(pageValue + 1);
            			handleColor();
            			clearTimeout(delayReturnData);
            			delayReturnData = setTimeout(function(){onContent.onInitLoad();},1000);
        			}
        		}
        		event.preventDefault();
        	});
        	$("#recordNum").mousewheel(function(event, delta, deltaX, deltaY) {
        		var recordNumValue = parseInt($(this).html());
        		if(delta < 0){
        			if(recordNumValue > 10){
        				$(this).html(recordNumValue - 10);
        				$("#page").html(1);
        				handleColor();
        				clearTimeout(delayReturnData);
            			delayReturnData = setTimeout(function(){onContent.onInitLoad();},1000);
        			}
        		}else{
        			if(recordNumValue < 50){
        				$(this).html(recordNumValue + 10);
        				$("#page").html(1);
        				handleColor();
        				clearTimeout(delayReturnData);
            			delayReturnData = setTimeout(function(){onContent.onInitLoad();},1000);
        			}
        		}
        		event.preventDefault();
        	});
        	$(window).bind("scroll",function() {
        		clearTimeout(scrollTime);
        		if(!onContent.isLoad && (parseInt($("#page").html()) < parseInt($("#pageSum").html()))){
        			if ($(document).scrollTop() + $(window).height() > $(document).height() - 300) {
    		    		scrollTime = setTimeout(function(){
    		    			$("#page").html(parseInt($("#page").html())+1);
    		    			onContent.onReload();
    		    		},100);
        			}
        		}
        	});
		}
	});
});
function handleColor(){
	var $page = parseInt($("#page").html());
	var $pageSum = parseInt($("#pageSum").html());
	var $lastPage = $("#lastPage");
	var $nextPage = $("#nextPage");
	if($page == 1 && $pageSum == 1){
		$lastPage.css({"color":"grey","cursor" : "text"});
		$nextPage.css({"color":"grey","cursor" : "text"});
	}else if($page == 1){
		$lastPage.css({"color":"grey","cursor" : "text"});
		$nextPage.css({"color":"#0088CC","cursor" : "pointer"});
	}else if($page == $pageSum){
		$lastPage.css({"color":"#0088CC","cursor" : "pointer"});
		$nextPage.css({"color":"grey","cursor" : "text"});
	}
}