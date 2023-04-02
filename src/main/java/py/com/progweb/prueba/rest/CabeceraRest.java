package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.CabeceraDAO;
import py.com.progweb.prueba.model.Cabecera;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/cabecera")
@Consumes("application/json")
@Produces("application/json")
public class CabeceraRest {

    Logger log;
    @Inject
    CabeceraDAO cabeceraDAO;

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id){
        return Response.ok(cabeceraDAO.getById(id)).build();
    }
    @GET
    @Path("/listar")
    public Response listarCabera(){
        return Response.ok(cabeceraDAO.listar()).build();
    }
    @POST
    public Response agregar(Cabecera cabecera){
        cabeceraDAO.agregar(cabecera);
        return Response.ok(cabecera).build();
    }
    @PUT
    @Path(("/actualizar"))
    public Response actualizar(Cabecera cabecera){
        cabeceraDAO.actualizar(cabecera);
        return Response.ok().build();
    }

    @DELETE
    @Path("/eliminar/{id}")
    public Response eliminar(@PathParam("id") Long id){
        cabeceraDAO.eliminar(id);
        return Response.ok(id).build();
    }

}
