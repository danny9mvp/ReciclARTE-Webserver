/**
 * 
 */
var nickname;
var txtId = $('#txtId');
var googleSheetId = $('#txtGoogleSheetId');
$('#tablaDatosUsuario').hide();
$('#tablaRegistroEvaluacion').hide();
$('#tablaRegistroPuntos').hide();
$('#containerFormAP').hide();
$('#containerFormResEv').hide();
var tablaDatosUsuario= $('#tablaDatosUsuario tbody');
var tablaRegistroPuntos = $('#tablaRegistroPuntos tbody');
$('#formBuscarUsuario').submit(function (e){	
	e.preventDefault();		
	tablaDatosUsuario.empty();
	tablaRegistroPuntos.empty();
	$('#tablaRegistroEvaluacion tbody').empty();
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: 'rest/usuarios/getUsuario/'+txtId.val(),
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
				$('#containerFormResEv').show();
				tablaDatosUsuario.append('<tr>'
						+'<td>'+this.nombres+'</td>'+'<td>'+this.apellidos+'</td>'
						+'<td>'+this.nickname+'</td>'+'<td>'+this.barrio+'</td>'
						+'<td>'+this.puntos+'</td>'
						+'</tr>');
			});			
			$.ajax({
				type:'GET',
				dataType: 'json',
				url: 'rest/usuarios/registroReciclaje/'+txtId.val(),				
				success: function(data){					
					$.each(data, function(key, value){						
						$.each(value, function(i, obj){							
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
			$('#containerFormResEv').hide();
			$('#tablaRegistroEvaluacion').empty();
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
$('#formResultadoEvaluacion').submit(function(e){
	e.preventDefault();
	$('#tablaRegistroEvaluacion tbody').empty();
	var json = JSON.stringify({
		"identificacion": $('#txtId').val(),
		"nick": nickname,
		"googleSheet": $('#txtGoogleSheetId').val()
	});
	console.log(json);
	$.ajax({
		type: 'POST',
		url: 'rest/usuarios/evaluacionPuntuacion',
		dataType: 'json',
		contentType: 'application/json',
		data: json,
		success: function(data){
			console.log(data);
			$('#tablaRegistroEvaluacion').show();
			$(data).each(function(){
			$('#tablaRegistroEvaluacion tbody').append(
					'<tr>'+'<td>'+this.indice+'</td>'+'<td>'+this.fecha+'</td>'+'<td>'+this.puntuacion+'</td>'+'</tr>');
			});
		},
		error: function(jqXHR, status, error){
			$.toast({
				heading: 'Error',
				text: "El usuario seleccionado aún no ha realizado la prueba.",
				icon: 'error',				
				position: 'top-right',
				hideAfter: 5000
			});
		}
	});
});