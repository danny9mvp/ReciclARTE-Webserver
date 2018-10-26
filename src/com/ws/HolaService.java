package com.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.entities.Usuario;

@Path("/service")
public class HolaService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario() {
		return new Usuario("Daniel","Ciudad Quito",199);				
	}
}
