package com.ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.entities.Barrio;
import com.entities.Usuario;
import com.entities.UsuarioPK;
import com.util.jsonpojos.RequestLogin;
import com.util.jsonpojos.RequestSignIn;

@Path("/sesion")
public class SesionService {
	@POST
	@Path("/logIn")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logIn(RequestLogin requestLogin) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Connection con = em.unwrap(Connection.class);
			CallableStatement callLogin = con.prepareCall("{call logIn(?,?,?,?,?,?)}");
			callLogin.setString(1, requestLogin.getUsuario());
			callLogin.setString(2, requestLogin.getPass());
			callLogin.registerOutParameter(3, Types.VARCHAR);
			callLogin.registerOutParameter(4, Types.VARCHAR);
			callLogin.registerOutParameter(5, Types.VARCHAR);
			callLogin.registerOutParameter(6, Types.REAL);			
			callLogin.execute();			
				String nombres = callLogin.getString(3);
				String apellidos = callLogin.getString(4);
				String nick = callLogin.getString(5);
				float puntos = callLogin.getFloat(6);
				JSONObject json = new JSONObject();
				json.put("nombres", nombres);
				json.put("apellidos", apellidos);
				json.put("nick", nick);
				json.put("puntos", puntos);
				callLogin.close();					
				return Response.ok(json.toString()).build();
					
		}catch(Exception ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}
	@POST
	@Path("/signIn")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response signIn(RequestSignIn requestSignIn) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU");
		EntityManager em = emf.createEntityManager();
		UsuarioPK pk = new UsuarioPK(requestSignIn.getIdentificacion(),requestSignIn.getNick());
		Usuario usuario = new Usuario(pk, requestSignIn.getNombres(), requestSignIn.getApellidos(), requestSignIn.getEmail()
				, requestSignIn.getClave(), requestSignIn.getActivo());
		usuario.setUsuaBarrio(em.find(Barrio.class, requestSignIn.getBarrioId()));
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		return Response.ok("Se ha completado su registro satisfactoriamente.").build();
	}
}
