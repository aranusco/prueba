package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.DetalleDAO;
import py.com.progweb.prueba.model.Detalle;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/detalle")
@Consumes("application/json")
@Produces("application/json")
public class DetalleRest {

    Logger log;
    @Inject
    DetalleDAO detalleDAO;

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id){
        return Response.ok(detalleDAO.getById(id)).build();
    }
    @GET
    @Path("/listar")
    public Response listarCabera(){
        return Response.ok(detalleDAO.listar()).build();
    }
    @POST
    public Response agregar(Detalle detalle){
        detalleDAO.agregar(detalle);
        return Response.ok(detalle).build();
    }
    @PUT
    @Path(("/actualizar"))
    public Response actualizar(Detalle detalle){
        detalleDAO.actualizar(detalle);
        return Response.ok().build();
    }

    @DELETE
    @Path("/eliminar/{id}")
    public Response eliminar(@PathParam("id") Long id){
        detalleDAO.eliminar(id);
        return Response.ok(id).build();
    }

}
