$(document).ready(function(){
	
	$(function() {
		$("#datenais").datepicker();
	});
	
   $('.aBtn, .table .eBtn').on('click',function(event){
	   
	   event.preventDefault();
	   var href=$(this).attr('href');
	   var text=$(this).text();
	   if(text=='Modifier'){
	   $.get(href,function(client,status){
		   $('.myForm #id').val(client.id);
		   $('.myForm #nom').val(client.nom);
		   $('.myForm #prenoms').val(client.prenoms);
		   $('.myForm #datenais').val(client.datenais);
		   $('.myForm #contact').val(client.contact);
		   $('.myForm #email').val(client.email);
	   })
	 $('.myForm #exampleModal').modal();
	   }else if(text=='Insertion'){
		   $('.myForm #id').val('');
		   $('.myForm #nom').val('');
		   $('.myForm #prenoms').val('');
		   $('.myForm #datenais').val('');
		   $('.myForm #contact').val('');
		   $('.myForm #email').val('');
		   $('.myForm #exampleModal').modal();
	   }
   });
  
$('.table .sBtn').on('click',function(event){
	event.preventDefault();
	var href=$(this).attr('href');
	 $('#myModal #supp').attr('href',href);
	 $('#myModal').modal();
	 
   });	
});