package com.ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

import com.entities.Barrio;
import com.entities.Material;
import com.entities.MaterialRecolectado;
import com.entities.PuntoRecoleccion;
import com.entities.Puntuacion;
import com.entities.RankingPuntos;
import com.entities.TipoMaterial;
import com.entities.Usuario;
import com.entities.UsuarioPK;
import com.util.jsonpojos.RequestLogin;
import com.util.jsonpojos.RequestPuntaje;
import com.util.jsonpojos.RequestSignIn;

@Path("/usuarios")
public class UsuarioService {
	@GET
	@Path("/{identificacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(@PathParam("identificacion") String identificacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU");
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
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU");
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU");
		EntityManager em = emf.createEntityManager();
		List<RankingPuntos> ranking = em.createNamedQuery("RankingPuntos.findAll").getResultList();
		return ranking;
	}
	@GET
	@Path("/obtenerRegistroPuntos/{identificacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegistroPuntos(@PathParam("identificacion") String identificacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Connection con = em.unwrap(Connection.class);
			PreparedStatement callObtenerRegistroPuntos = con.prepareStatement("SELECT * FROM obtenerRegistroPuntos(?)");			
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
}
