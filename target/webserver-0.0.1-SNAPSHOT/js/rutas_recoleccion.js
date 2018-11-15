$('#puntosRecoleccionTab').click(function(){
	var vectorRutaAseo = new ol.layer.Vector({
        source: new ol.layer.VectorSource({
          url: './rutas/Ruta_aseo.gpx',
          format: new ol.format.GPX()
        }),
        style: function(feature) {
          return style[feature.getGeometry().getType()];
        }
     });
	var map = new ol.Map({
		target: 'map2',
		layers: [new ol.layer.Tile({
			source: new ol.source.OSM()
		}), vectorRutaAseo],
		view: new ol.View({
			center : ol.proj.fromLonLat([ -74.1181, 4.7209 ]),
			zoom : 14
		})
	})
})