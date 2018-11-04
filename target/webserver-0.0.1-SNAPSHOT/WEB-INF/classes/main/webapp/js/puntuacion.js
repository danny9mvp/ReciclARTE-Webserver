/**
 * 
 */
var nickname;
var txtId = $('#txtId');
$('#tablaDatosUsuario').hide();
$('#tablaRegistroPuntos').hide();
$('#containerFormAP').hide();
$('#formBuscarUsuario').submit(function (e){	
	e.preventDefault();	
	var tablaDatosUsuario= $('#tablaDatosUsuario tbody');
	var tablaRegistroPuntos = $('#tablaRegistroUsuario tbody');
	tablaDatosUsuario.empty();
	tablaRegistroPuntos.empty();
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: 'rest/usuarios/'+txtId.val(),
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
	var materialSeleccionado = selectMaterial.find(':selected').val();
	var peso = spinnerPeso.val();
	var json = JSON.stringify({
		"material":materialSeleccionado,
		"peso":peso,
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
		beforeSend: function(){
			$.toast({
				heading: 'Información',
				text: 'Enviando...',
				icon: 'info',
				loaderBg: '#9EC600',
				position: 'top-right',
				hideAfter: 5000
			});
		},
		done: function(){
			$.toast({
				heading: 'Información',
				text: 'Esperando respuesta...',
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