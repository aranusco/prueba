package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.VencimientoPuntosDAO;
import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/vencimiento-puntos")
@Consumes("application/json")
@Produces("application/json")
public class VencimientoPuntosRest {
    @Inject
    VencimientoPuntosDAO vencimientoPuntosDAO;

    @GET
    public Response listar(){
        return Response.ok(vencimientoPuntosDAO.listar()).build();
    }
    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id){
        return Response.ok(vencimientoPuntosDAO.getById(id)).build();
    }
    @POST
    public Response agregar(VencimientoPuntos request){
        vencimientoPuntosDAO.agregar(request);
        return Response.ok(request).build();
    }
    @PUT
    @Path("/actualizar")
    public Response actualizar(VencimientoPuntos request){
        vencimientoPuntosDAO.actualizar(request);
        return Response.ok().build();
    }
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id){
        vencimientoPuntosDAO.eliminar(id);
        return Response.ok().build();
    }

}
