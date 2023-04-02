package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.BolsaPuntosDAO;
import py.com.progweb.prueba.model.BolsaPuntos;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/bolsa-puntos")
@Consumes("application/json")
@Produces("application/json")
public class BolsaPuntosRest {

    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id){
        return Response.ok(bolsaPuntosDAO.getById(id)).build();
    }
    @GET
    @Path("/listar")
    public Response listarBolsaPuntos(){
        return Response.ok(bolsaPuntosDAO.listar()).build();
    }
    @POST
    public Response agregar(BolsaPuntos request){
        bolsaPuntosDAO.agregar(request);
        return Response.ok(request).build();
    }
}
