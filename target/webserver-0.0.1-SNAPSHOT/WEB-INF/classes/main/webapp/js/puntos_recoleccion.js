$('#puntosRecoleccionTab').click(function(){	
	var style = {
			  'Point': [new ol.style.Style({
			    image: new ol.style.Circle({
			      fill: new ol.style.Fill({
			        color: 'rgba(255,255,0,0.4)'
			      }),
			      radius: 5,
			      stroke: new ol.style.Stroke({
			        color: '#ff0',
			        width: 1
			      })
			    })
			  })],
			  'LineString': [new ol.style.Style({
			    stroke: new ol.style.Stroke({
			      color: '#f00',
			      width: 3
			    })
			  })],
			  'MultiLineString': [new ol.style.Style({
			    stroke: new ol.style.Stroke({
			      color: '#0f0',
			      width: 3
			    })
			  })]
			};

			var vectorRuta = new ol.layer.Vector({
			  source: new ol.source.Vector({
			    url: './rutas/Ruta_aseo.gpx',
			    format: new ol.format.GPX()
			  }),
			  style: function(feature, resolution) {
			    return style[feature.getGeometry().getType()];
			  }
			});
	var map = new ol.Map({		
			target : 'map',
			layers : [ new ol.layer.Tile({
				source : new ol.source.OSM()
			}), vectorRuta],
			view : new ol.View({
				center : ol.proj.fromLonLat([ -74.1181, 4.7209 ]),
				zoom : 14
			})	
	});
	//Añadiendo marcadores de puntos de recolección al mapa
	$.ajax({
		type: 'GET',
		url: 'rest/mapas/puntosRecoleccion',
		dataType: 'json',
		success: function(data){
			console.log(data);
			$.each(data, function(key, value){					
					var marcador = new ol.Feature({
						geometry: new ol.geom.Point(ol.proj.fromLonLat([value.longitud,value.latitud]))						
					});
					var vectorSource = new ol.source.Vector({
						features: [marcador]
					});
					var stylePuntoRecoleccion = new ol.style.Style({
						image: new ol.style.Icon({
							anchor: [0.5, 0.5],
					        size: [134, 129],	        
					        opacity: 1,
					        scale: 0.25,
					        src: './ico/punto_recoleccion.png'
						}),
						text: new ol.style.Text({
							font: 'bold 12px Verdana',
							fill : new ol.style.Fill({
						        color : '#00802b'
						    }),
							offsetY : 24,
							text: value.nombre
						})
					});
					var marcadorVectorLayer = new ol.layer.Vector({
						source: vectorSource,
						style: stylePuntoRecoleccion						
					});
					map.addLayer(marcadorVectorLayer);					
			});
		},
		error: function(jqXHR, status, error){
			console.log("Error:"+error+" "+status);
		}
	});
	//Añadiendo marcadores de jornadas de recoleccion al mapa
	$.ajax({
		type:'GET',
		url: 'rest/mapas/jornadasRecoleccion',
		dataType: 'json',
		success: function(data){
			console.log(data);
			$.each(data, function(key, value){
				var marcador = new ol.Feature({
					geometry: new ol.geom.Point(ol.proj.fromLonLat([value.longitud,value.latitud])),
				});
				var vectorSource = new ol.source.Vector({
					features: [marcador]
				});
				var stylePuntoRecoleccion = new ol.style.Style({
					image: new ol.style.Icon({
						anchor: [0.5, 0.5],
				        size: [134, 129],	        
				        opacity: 1,
				        scale: 0.25,
				        src: './ico/jornada_recoleccion.png'
					}),
					text: new ol.style.Text({
						font: 'bold 12px Verdana',
						fill : new ol.style.Fill({
					        color : '#002699'
					    }),
						offsetY : 24,
						text: value.descripcion
					})
				});
				var marcadorVectorLayer = new ol.layer.Vector({
					source: vectorSource,
					style: stylePuntoRecoleccion						
				});
				map.addLayer(marcadorVectorLayer);
			})
		},
		error: function(jqXHR, status, error){
			console.log("Error:"+error+" "+status);
		}
	});
	
});		    