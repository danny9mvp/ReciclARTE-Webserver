package com.ws;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.persistence.config.TargetServer;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.eclipse.persistence.config.PersistenceUnitProperties.*;


import com.entities.EvaluacionRealizada;
import com.entities.GoogleSheets;
import com.entities.MaterialRecolectado;
import com.entities.PuntoRecoleccion;
import com.entities.Puntuacion;
import com.entities.RankingPuntos;
import com.entities.TipoMaterial;
import com.entities.Usuario;
import com.entities.UsuarioPK;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.googleapis.GoogleSheetsAPI;
import com.util.jsonpojos.RequestEvaluacionRealizada;
import com.util.jsonpojos.RequestPuntaje;

@Path("/usuarios")
public class UsuarioService {
	private Map propiedadesConexion;
	@PostConstruct
	public void init() {		
		URI JDBC_URI;
		try {
			propiedadesConexion=new HashMap<>();
			JDBC_URI = new URI(System.getenv("DATABASE_URL"));
			propiedadesConexion.put(TRANSACTION_TYPE, PersistenceUnitTransactionType.RESOURCE_LOCAL.name());
			propiedadesConexion.put(JDBC_DRIVER, "org.postgresql.Driver");
			propiedadesConexion.put(JDBC_URL, "jdbc:postgresql://"+JDBC_URI.getHost()+":"+JDBC_URI.getPort()+JDBC_URI.getPath());
			propiedadesConexion.put(JDBC_USER, JDBC_URI.getUserInfo().split(":")[0]);
			propiedadesConexion.put(JDBC_PASSWORD, JDBC_URI.getUserInfo().split(":")[1]);
			propiedadesConexion.put(CONNECTION_POOL_INITIAL, "1");
			propiedadesConexion.put(CONNECTION_POOL_MIN, "64");
			propiedadesConexion.put(CONNECTION_POOL_MAX, "64");
			propiedadesConexion.put(CACHE_STATEMENTS, "true");
			propiedadesConexion.put(LOGGING_LEVEL, "FINE");
			propiedadesConexion.put(LOGGING_TIMESTAMP, "false");
			propiedadesConexion.put(LOGGING_THREAD, "false");
			propiedadesConexion.put(LOGGING_SESSION, "false");
			  // Ensure that no server-platform is configured
			propiedadesConexion.put(TARGET_SERVER, TargetServer.DEFAULT);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@GET
	@Path("/getUsuario/{identificacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(@PathParam("identificacion") String identificacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU", propiedadesConexion);
		EntityManager em = emf.createEntityManager();
		Usuario us=null;				
		try {
			TypedQuery<Usuario> usuarioIdentificacion = em.createQuery("SELECT u FROM Usuario u WHERE u.usuarioPK.usuaIdentificacion = :usuaIdentificacion",
					Usuario.class);
			usuarioIdentificacion.setParameter("usuaIdentificacion", identificacion);
			us = usuarioIdentificacion.getSingleResult();
			em.getTransaction().begin();
			Connection con = em.unwrap(Connection.class);
			CallableStatement callObtenerPuntaje=con.prepareCall("{? = call obtenerPuntaje(?,?)}");
			callObtenerPuntaje.registerOutParameter(1, Types.REAL);
			callObtenerPuntaje.setString(2, us.getUsuarioPK().getUsuaIdentificacion());
			callObtenerPuntaje.setString(3, us.getUsuarioPK().getUsuaNick());
			callObtenerPuntaje.execute();
			float puntos = callObtenerPuntaje.getFloat(1);
			callObtenerPuntaje.close();			
			JSONObject json = new JSONObject();
			json.put("nombres", us.getUsuaNombres());
			json.put("apellidos", us.getUsuaApellidos());
			json.put("barrio",us.getUsuaBarrio().getBarrNombre());
			json.put("nickname",us.getUsuarioPK().getUsuaNick());
			json.put("puntos", puntos);				
			return Response.ok(json.toString()).build();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}
	@POST
	@Path("/registrarPuntos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registrarPuntos(RequestPuntaje requestPuntaje) {
		try {
			//INICIO REGISTRAR NUEVA PUNTUACIÓN
			float puntaje = requestPuntaje.getPeso()/10;
			Date fecha = requestPuntaje.getFecha();			
			String identificacion = requestPuntaje.getIdentificacion();
			String nick = requestPuntaje.getNickname();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU", propiedadesConexion);
			EntityManager em = emf.createEntityManager();
			//TRANSACCIÓN
			em.getTransaction().begin();
				Puntuacion nuevoPuntaje = new Puntuacion(null,puntaje,fecha);
				nuevoPuntaje.setUsuario(em.find(Usuario.class, new UsuarioPK(identificacion,nick)));
				//CREADO REGISTRO DE PUNTOS
				em.persist(nuevoPuntaje);
				int puntoRecoleccionId=Integer.parseInt(requestPuntaje.getPuntoRecoleccion());
				String material = requestPuntaje.getMaterial();
				MaterialRecolectado nuevoMaterialRecolectado = new MaterialRecolectado(null);
				nuevoMaterialRecolectado.setMatrecPuntorecoleccion(em.find(PuntoRecoleccion.class, puntoRecoleccionId));
				nuevoMaterialRecolectado.setMatrecTipomaterial(em.find(TipoMaterial.class, Integer.parseInt(material)));
				nuevoMaterialRecolectado.setMatrecPuntuacion(nuevoPuntaje);
				//ASOCIADO REGISTRO DE PUNTOS AL MATERIAL QUE SE RECOLECTÓ
				em.persist(nuevoMaterialRecolectado);
			em.getTransaction().commit();
			return Response.ok("Puntaje actualizado satisfactoriamente.").build();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}
	@GET
	@Path("/ranking")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RankingPuntos> getRanking() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU", propiedadesConexion);
		EntityManager em = emf.createEntityManager();
		List<RankingPuntos> ranking = em.createNamedQuery("RankingPuntos.findAll").getResultList();
		return ranking;
	}
	@GET
	@Path("/registroReciclaje/{identificacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegistroReciclaje(@PathParam("identificacion") String identificacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU", propiedadesConexion);
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Connection con = em.unwrap(Connection.class);
			PreparedStatement callObtenerRegistroPuntos = con.prepareStatement("SELECT * FROM registroReciclaje(?)");			
			callObtenerRegistroPuntos.setString(1, identificacion);
			callObtenerRegistroPuntos.executeQuery();
			ResultSet rs = callObtenerRegistroPuntos.getResultSet();			
			JSONArray jsonArray = new JSONArray();
			JSONObject json=null;
			JSONObject jsonFinal = new JSONObject();			
			while(rs.next()) {
				int nColumnas = rs.getMetaData().getColumnCount();
				json=new JSONObject();
				for(int i=0;i< nColumnas;i++) {					
					json.put(rs.getMetaData().getColumnLabel(i+1), rs.getObject(i+1));
					
				}
				jsonArray.put(json);
			}
			jsonFinal.put("resultado", jsonArray);
			callObtenerRegistroPuntos.close();
			return Response.ok(jsonFinal.toString()).build();
		}catch(Exception ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}		
	}
	
	@POST
	@Path("/evaluacionPuntuacion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response evaluacionPuntuacion(RequestEvaluacionRealizada reqEvaluacionRealizada) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU", propiedadesConexion);
		EntityManager em = emf.createEntityManager();
		TypedQuery<Usuario> buscarCorreoUsuario = em.createQuery("SELECT u FROM Usuario u WHERE u.usuarioPK.usuaIdentificacion = :usuaIdentificacion",
				Usuario.class);
		buscarCorreoUsuario.setParameter("usuaIdentificacion", reqEvaluacionRealizada.getIdentificacion());
		Usuario us = buscarCorreoUsuario.getSingleResult();
		String correo = us.getUsuaEmail();
		try {
		GoogleSheetsAPI googleSheets = GoogleSheetsAPI.getSingleton();		
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = reqEvaluacionRealizada.getGoogleSheet();        
        final String range = "A:C";
        com.google.api.services.sheets.v4.Sheets service = 
        		new com.google.api.services.sheets.v4.Sheets.Builder(HTTP_TRANSPORT, googleSheets.JSON_FACTORY, googleSheets.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(googleSheets.APPLICATION_NAME)
                .build();
        com.google.api.services.sheets.v4.model.ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            return Response.status(404).entity("El usuario no ha realizado esta prueba.").build();
        } else {
        	JSONObject json = new JSONObject();
        	int i=1;
            for (List row : values) {            	
                // Print columns A and E, which correspond to indices 0 and 4.
            	if(!row.get(0).equals("Marca temporal") && !row.get(1).equals("Dirección de correo electrónico") && !row.get(2).equals("Puntuación")
            			&& row.get(1).equals(correo)) {
            		json.put("indice",i).put("fecha",row.get(0)).put("puntuacion", row.get(2));
            		i++;
            	}
            }
            Date fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(json.getString("fecha"));
            String[] splitter = json.getString("puntuacion").split("/");
            Float puntuacion = Float.parseFloat(splitter[0].trim());           
            boolean existe = (boolean) em.createNativeQuery("SELECT EXISTS (SELECT EVAL_puntuacion FROM EVALUACIONREALIZADA,PUNTUACION WHERE EVAL_googlesheet = ?" + 
            		" AND EVAL_puntuacion=PUNT_id AND PUNT_usuarioidentificacion= ?)")
            		.setParameter(1, reqEvaluacionRealizada.getGoogleSheet())
            		.setParameter(2, reqEvaluacionRealizada.getIdentificacion())
            		.getSingleResult();            
            if(!existe){
            	em.getTransaction().begin();
					//CREADO REGISTRO DE PUNTOS
					Puntuacion nuevoPuntaje = new Puntuacion(null,puntuacion,fecha);
					nuevoPuntaje.setUsuario(em.find(Usuario.class, new UsuarioPK(reqEvaluacionRealizada.getIdentificacion(),
							reqEvaluacionRealizada.getNick())));				
					em.persist(nuevoPuntaje);				
					EvaluacionRealizada nuevaEvaluacionRealizada = new EvaluacionRealizada(null);				
					nuevaEvaluacionRealizada.setEvalPuntuacion(nuevoPuntaje);
					nuevaEvaluacionRealizada.setEvalGooglesheet(em.find(GoogleSheets.class, reqEvaluacionRealizada.getGoogleSheet()));					
					//ASOCIADO REGISTRO DE PUNTOS A LA EVALUACIÓN
					em.persist(nuevaEvaluacionRealizada);				
				em.getTransaction().commit();
            }
            return Response.ok(json.toString()).build();
        }        
		}catch(Exception ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}
}
