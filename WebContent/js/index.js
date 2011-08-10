$(document).ready(function(){
	$(".urls_mais_acessadas tr:odd").addClass("odd");
	
	$(".link").mouseover(function(){
		$(this).css("font-weight", "bold");
	});
	
	$(".link").mouseout(function(){
		$(this).css("font-weight", "normal");
	});
	
});