/**
 * 
 */

$(document).ready(function(){
	$('.nBtn, .table .eBtn').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
		if(text == 'Modifier'){
		$.get(href,function(epreuve,status){
			$('.myForm #id').val(epreuve.id);
			$('.myForm #lib').val(epreuve.lib);
		});
		$('.myForm #exampleModal').modal();		
		}else{
			$('.myForm #id').val('');
			$('.myForm #lib').val('');
			$('.myForm #exampleModal').modal();	
		}
	});
	
	$('.table .dBtn').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#exampleModal1 #delRef').attr('href',href);
		$('#exampleModal1').modal();
	});
	
});