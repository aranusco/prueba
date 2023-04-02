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
    @POST
    public Response agregar(VencimientoPuntos request){
        vencimientoPuntosDAO.agregar(request);
        return Response.ok(request).build();
    }
}
