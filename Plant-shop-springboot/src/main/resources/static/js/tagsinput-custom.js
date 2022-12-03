/**
 * 
 */

$(document).ready(function(){	
		
	$(".btn-Size").click(function() {	
		var val = $(this).text();		
		$("#Sizes").tagsinput('add', val);
	});
	$(".btn-brand").click(function() {
		var val = $(this).text();
		$("#brands").tagsinput('add', val);
	});
	$(".btn-category").click(function() {
		var val = $(this).text();
		$("#categories").tagsinput('add', val);
	});
	
});