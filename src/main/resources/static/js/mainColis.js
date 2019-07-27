$(document).ready(function(){
	
	$(function() {
		$("#datedepot").datepicker();
		$("#dateretrait").datepicker();
		$("#dateprevu").datepicker();
	});
	
   $('.aBtn, .table .eBtn').on('click',function(event){
	   
	   event.preventDefault();
	   var href=$(this).attr('href');
	   var text=$(this).text();
	   if(text=='Modifier'){
	   $.get(href,function(colis,status){
		   $('.myForm #id').val(colis.id);
		   $('.myForm #datedepot').val(colis.datedepot);
		   $('.myForm #dateprevu').val(colis.dateprevu);
		   $('.myForm #dateretrait').val(colis.dateretrait);
		   $('.myForm #etat').val(colis.etat);
		   $('.myForm #montant').val(colis.montant);
		   $('.myForm #cltexp').val(colis.cltexp);
		   $('.myForm #cltdest').val(colis.cltdest);
		   $('.myForm #villeexp').val(colis.villeexp);
		   $('.myForm #villedest').val(colis.villedest);
		   $('.myForm #typeColis').val(colis.typeColis);
	   })
	 $('.myForm #exampleModal').modal();
	   }else if(text=='Insertion'){
		   $('.myForm #id').val('');
		   $('.myForm #datedepot').val('');
		   $('.myForm #dateprevu').val('');
		   $('.myForm #dateretrait').val('');
		   $('.myForm #etat').val('');
		   $('.myForm #montant').val('');
		   $('.myForm #cltdest').val('0');
		   $('.myForm #villeexp').val('0');
		   $('.myForm #villedest').val('0');
		   $('.myForm #typeColis').val('0');
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