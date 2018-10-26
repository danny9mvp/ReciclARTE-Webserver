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
			$(data).each(function(){
				tabla.append('<tr>'+'<td>'+this.nombre+'</td>'+'<td>'+this.barrio+'</td>'+'<td>'+this.puntos+'</td></tr>')
			});
			$.toast({
				heading: 'Éxito',
				text: '¡Tabla rellenada!',
				icon: 'success',
				position: 'top-right',
				hideAfter: 5000
			});
			console.log('response:'+data);			
		},
		error: function(jqXHR, status, error){
			$.toast({
				heading: 'Error',
				text: 'Ha ocurrido el siguiente error:'+error,
				icon: 'error',
				position: 'top-right',
				hideAfter: 5000
			});
		}
	});	
});