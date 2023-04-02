package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.PuntosDAO;
import py.com.progweb.prueba.model.Punto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/puntos")
@Consumes("application/json")
@Produces("application/json")
public class PuntosRest {
    @Inject
    PuntosDAO PuntosDAO;

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        return Response.ok(PuntosDAO.getById(id)).build();
    }
    @GET
    @Path("/lista")
    public Response listarPuntos(){
        return Response.ok(PuntosDAO.listar()).build();
    }
    @POST
    public Response agregar(Punto punto){
        PuntosDAO.agregar(punto);
        return Response.ok(punto).build();
    }
    @PUT
    @Path(("/{id}"))
    public Response actualizar(@PathParam("id") Integer id, Punto punto){
        punto.setId(id);
        PuntosDAO.actualizar(punto);
        return Response.ok().build();
    }


}
