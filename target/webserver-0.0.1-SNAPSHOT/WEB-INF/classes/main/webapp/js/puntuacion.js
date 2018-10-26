/**
 * 
 */
$(document).ready(function (){
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: 'rest/service',
		success: function(data){
			var tabla= $('#tabla tbody');
			$.each(data, function(index, element){
				tabla.append('<tr><td>'+element.nombre+'</td></tr>'+'<tr><td>'+element.barrio+'</td></tr>'
						+'<tr><td>'+element.puntos+'</td></tr>')
			})
			$.toast({
				heading: 'Éxito',
				text: '¡Tabla rellenada!',
				icon: 'success',
				position: 'top-right'
			});
			console.log('response:'+data);
			$('#txtMsjWs').text(data);
		},
		error: function(jqXHR, status, error){
			$.toast({
				heading: 'Error',
				text: 'Ha ocurrido el siguiente error:'+error,
				icon: 'error',
				position: 'top-right'
			});
		}
	});
});	