package py.com.progweb.prueba.rest;


import py.com.progweb.prueba.ejb.ReglasAsignacionPuntosDAO;
import py.com.progweb.prueba.model.ReglasAsignacionPuntos;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/ReglasAsignacionPuntos")
@Consumes("application/json")
@Produces("application/json")

public class ReglasAsignacionPuntosRest {

    @Inject
    ReglasAsignacionPuntosDAO reglasDAO;

    @GET
    public Response getReglaPorId(@QueryParam("id_reglas") Integer id_reglas){
        return Response.ok(reglasDAO.seleccionar(id_reglas)).build();
    }

    @GET
    @Path("/reglasAsignacionPuntos")
    public Response listarReglasAsignacionPuntos(){
        return Response.ok(reglasDAO.listar()).build();
    }

    @POST
    @Path("/")
    public Response agregar(ReglasAsignacionPuntos regla){
        reglasDAO.agregar(regla);
        return Response.ok(regla).build();
    }

    @PUT
    @Path(("/actualizar/{id}"))
    public Response actualizar(ReglasAsignacionPuntos reglas){
        reglasDAO.actualizar(reglas);
        return Response.ok().build();
    }
}
