$(document).ready(function() {
	
	$('#iden').change(function(event) {
		event.preventDefault();
       comboChange();		
    });
	
	

});

function comboChange() {
		
	var idt= $("#iden").val();
	
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "http://localhost:9000/grh-grpita/formateur/fteurId/?id="+idt,
		dataType : 'json',
		success : function(data) {
			var html = '<option value="">====Formateurs====</option>';
			var len = data.length;
			for (var i = 0; i < len; i++) {
				//console.log("test"+data.data[i].id);
				html += '<option value="' + data[i].id + '">' + data[i].nom+' '+data[i].prenom+' '+data[i].contact
						+ '</option>';
			}
			html += '</option>';
			$('#formateur').html(html);
			
		},
		error : function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}
	});
}

