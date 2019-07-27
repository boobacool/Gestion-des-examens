$(document).ready(function() {
	
	
	$('#idtp').change(function(event) {
		event.preventDefault();
		comboChangePersonnel();		
    });

});



function comboChangePersonnel() {
		
	var ida= $("#ida").val();
	var idtp= $("#idtp").val();
	
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "http://localhost:9000/grh-grpita/contrat/contId/?ida="+ida+"&idtp="+idtp,
		dataType : 'json',
		success : function(data) {
			var html = '<option value="">====Personnels====</option>';
			var len = data.length;
			for (var i = 0; i < len; i++) {
				
				html += '<option value="' + data[i].id + '">' + data[i].nom+" "+data[i].prenom+" "+data[i].contact
						+ '</option>';
			}
			html += '</option>';
			$('#personnel').html(html);
			
		},
		error : function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	});
}