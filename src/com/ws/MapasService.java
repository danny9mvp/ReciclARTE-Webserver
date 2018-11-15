package com.ws;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.persistence.config.TargetServer;
import static org.eclipse.persistence.config.PersistenceUnitProperties.*;

import com.entities.CoordenadasJornadasRecoleccion;
import com.entities.CoordenadasRutasRecoleccion;

@Path("/mapas")
public class MapasService {
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
	@Path("/puntosRecoleccion")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CoordenadasRutasRecoleccion> getPuntosRecoleccion() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU", propiedadesConexion);
		EntityManager em = emf.createEntityManager();
		List<CoordenadasRutasRecoleccion> coordenadasRutasRecoleccion = em.createNamedQuery("CoordenadasRutasRecoleccion.findAll").getResultList();
		return coordenadasRutasRecoleccion;
	}
	@GET
	@Path("/jornadasRecoleccion")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CoordenadasJornadasRecoleccion> getJornadasRecoleccion(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU", propiedadesConexion);
		EntityManager em = emf.createEntityManager();
		List<CoordenadasJornadasRecoleccion> coordenadasJornadasRecoleccion = em.createNamedQuery("CoordenadasJornadasRecoleccion.findAll").getResultList();
		return coordenadasJornadasRecoleccion;
	}
}
