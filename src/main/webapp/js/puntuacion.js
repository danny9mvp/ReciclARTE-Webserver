/**
 * 
 */
var nickname;
var txtId = $('#txtId');
$('#tablaDatosUsuario').hide();
$('#tablaRegistroPuntos').hide();
$('#containerFormAP').hide();
var tablaDatosUsuario= $('#tablaDatosUsuario tbody');
var tablaRegistroPuntos = $('#tablaRegistroPuntos tbody');
$('#formBuscarUsuario').submit(function (e){	
	e.preventDefault();		
	tablaDatosUsuario.empty();
	tablaRegistroPuntos.empty();
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: 'rest/usuarios/'+txtId.val(),
		done: function(){
			$.toast({
				heading: 'Información',
				text: 'Enviando...',
				icon: 'info',
				loaderBg: '#9EC600',
				position: 'top-right',
				hideAfter: 5000
			});
		},
		success: function(data){			
			nickname=data.nickname;			
			$(data).each(function(){
				$('#tablaDatosUsuario').show();
				$('#tablaRegistroPuntos').show();
				$('#containerFormAP').show();
				tablaDatosUsuario.append('<tr>'
						+'<td>'+this.nombres+'</td>'+'<td>'+this.apellidos+'</td>'
						+'<td>'+this.nickname+'</td>'+'<td>'+this.barrio+'</td>'
						+'<td>'+this.puntos+'</td>'
						+'</tr>');
			});			
			$.ajax({
				type:'GET',
				dataType: 'json',
				url: 'rest/usuarios/obtenerRegistroPuntos/'+txtId.val(),				
				success: function(data){
					$.each(data, function(key, value){						
						$.each(value, function(i, obj){
							console.log(obj);
							tablaRegistroPuntos.append('<tr>'
									+'<td>'+obj.id+'</td>'+'<td>'+obj.material+'</td>'
									+'<td>'+obj.peso.toFixed(2)+'</td>'+'<td>'+obj.puntuacion+'</td>'
									+'<td>'+obj.fecha+'</td>'+'<td>'+obj.puntorecoleccion+'</td>'									
									+'</tr>');
						});
					});
				},
				error: function(jqXHR, status, error){
					$.toast({
						heading: 'Error',
						text: 'Estado:'+status,
						icon: 'error',
						position: 'top-right',
						hideAfter: 5000
					});
				}
			});
			$.toast({
				heading: 'Éxito',
				text: '¡Usuario encontrado!',
				icon: 'success',
				position: 'top-right',
				hideAfter: 5000
			});						
			console.log('response:'+data);			
		},
		error: function(jqXHR, status, error){
			$('#tablaDatosUsuario').hide();
			$('#tablaRegistroPuntos').hide();
			$('#containerFormAP').hide();
			$.toast({
				heading: 'Error',
				text: 'Estado:'+status,
				icon: 'error',
				position: 'top-right',
				hideAfter: 5000
			});
		}
	});	
});
(function ($) {
    $.fn.serializeFormJSON = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);
$('#formActualizarPuntuacion').submit(function (e){
	e.preventDefault();
	var selectMaterial = $('#selectMaterial');
	var spinnerPeso = $('#spinnerPeso');
	var selectPuntoRec = $('#selectPuntoRec');
	var materialSeleccionado = selectMaterial.find(':selected').val();
	var peso = spinnerPeso.val();
	var puntoRecoleccion = selectPuntoRec.find(':selected').val();
	var json = JSON.stringify({
		"material":materialSeleccionado,
		"peso":peso,
		"puntoRecoleccion":puntoRecoleccion,
		"fecha":Date.now(),
		"identificacion":txtId.val(),
		"nickname":nickname
	});
	console.log(json);
	$.ajax({
		type: 'POST',
		url: 'rest/usuarios/registrarPuntos',
		contentType: 'application/json',
		dataType: 'text',
		data: json,
		done: function(){
			$.toast({
				heading: 'Información',
				text: 'Enviando...',
				icon: 'info',
				loaderBg: '#9EC600',
				position: 'top-right',
				hideAfter: 5000
			});
		},
		success: function(data){
			$.toast({
				heading: 'Éxito',
				text: data,
				icon: 'success',				
				position: 'top-right',
				hideAfter: 5000
			});
			$('#tablaDatosUsuario').each(function(){				
				var celda=$(this).find('td').eq(4);
				var nuevoPuntaje= parseFloat(celda.text()) + parseFloat(peso)/10;
				console.log(peso);
				celda.text(nuevoPuntaje);
			});			
				tablaRegistroPuntos.append('<tr>'
						+'<td>'+selectMaterial.find(':selected').text()+'</td>'+'<td>'+peso+'</td>'						
						+'<td>'+parseFloat(peso)/10+'</td>'
						+'</tr>');			
		},
		error: function(jqXHR, status, error){
			$.toast({
				heading: 'Error',
				text: 'Error: '+error,
				icon: 'error',				
				position: 'top-right',
				hideAfter: 5000
			});
		}
	});
});