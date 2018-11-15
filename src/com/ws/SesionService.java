package com.ws;

import static org.eclipse.persistence.config.PersistenceUnitProperties.CACHE_STATEMENTS;
import static org.eclipse.persistence.config.PersistenceUnitProperties.CONNECTION_POOL_INITIAL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.CONNECTION_POOL_MAX;
import static org.eclipse.persistence.config.PersistenceUnitProperties.CONNECTION_POOL_MIN;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_DRIVER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_PASSWORD;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_URL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_USER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.LOGGING_LEVEL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.LOGGING_SESSION;
import static org.eclipse.persistence.config.PersistenceUnitProperties.LOGGING_THREAD;
import static org.eclipse.persistence.config.PersistenceUnitProperties.LOGGING_TIMESTAMP;
import static org.eclipse.persistence.config.PersistenceUnitProperties.TARGET_SERVER;
import static org.eclipse.persistence.config.PersistenceUnitProperties.TRANSACTION_TYPE;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.persistence.config.TargetServer;
import org.json.JSONObject;

import com.entities.Barrio;
import com.entities.Usuario;
import com.entities.UsuarioPK;
import com.util.jsonpojos.RequestLogin;
import com.util.jsonpojos.RequestSignIn;

@Path("/sesion")
public class SesionService {
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
	@POST
	@Path("/logIn")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logIn(RequestLogin requestLogin) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU", propiedadesConexion);
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU", propiedadesConexion);
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
