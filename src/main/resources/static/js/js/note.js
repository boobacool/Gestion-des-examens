$(document).ready(function() {

	$('#idniv').change(function(event) {
		event.preventDefault();
		comboChangeMatiere1();		
    });

});



function comboChangeMatiere1() {
		
	var ida = $("#ida").val();
	var idant = $("#idant").val();
	var idfil = $("#idfil").val();
	var idniv = $("#idniv").val();
	var idexa = $("#idexa").val();
	
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/noteita/exaepreuve/listeMatieres/?ida="+ida+"&idant="+idant+"&idfil="+idfil+"&idniv="+idniv+"&idexa="+idexa,
		dataType : 'json',
		success : function(data) {
			var html = '<option value="">====Matières====</option>';
			var len = data.length;
			for (var i = 0; i < len; i++) {
				
				html += '<option value="' + data[i].id + '">' + data[i].epreuve.lib
						+ '</option>';
			}
			html += '</option>';
			$('#idep').html(html);
			
		},
		error : function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	});
}