package com.ws;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.entities.CoordenadasJornadasRecoleccion;
import com.entities.CoordenadasRutasRecoleccion;

@Path("/mapas")
public class MapasService {
	@GET
	@Path("/puntosRecoleccion")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CoordenadasRutasRecoleccion> getPuntosRecoleccion() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU");
		EntityManager em = emf.createEntityManager();
		List<CoordenadasRutasRecoleccion> coordenadasRutasRecoleccion = em.createNamedQuery("CoordenadasRutasRecoleccion.findAll").getResultList();
		return coordenadasRutasRecoleccion;
	}
	@GET
	@Path("/jornadasRecoleccion")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CoordenadasJornadasRecoleccion> getJornadasRecoleccion(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RECICLAJE_PU");
		EntityManager em = emf.createEntityManager();
		List<CoordenadasJornadasRecoleccion> coordenadasJornadasRecoleccion = em.createNamedQuery("CoordenadasJornadasRecoleccion.findAll").getResultList();
		return coordenadasJornadasRecoleccion;
	}
}
