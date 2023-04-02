package py.com.progweb.prueba.rest;


import py.com.progweb.prueba.ejb.ReglasAsignacionPuntosDAO;
import py.com.progweb.prueba.model.ReglasAsignacionPuntos;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/reglas")
@Consumes("application/json")
@Produces("application/json")

public class ReglasAsignacionPuntosRest {

    @Inject
    ReglasAsignacionPuntosDAO reglasDAO;

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id){
        return Response.ok(reglasDAO.getById(id)).build();
    }

    @GET
    @Path("/listar")
    public Response listarReglasAsignacionPuntos(){
        return Response.ok(reglasDAO.listar()).build();
    }

    @POST
    public Response agregar(ReglasAsignacionPuntos regla){
        reglasDAO.agregar(regla);
        return Response.ok(regla).build();
    }

    @PUT
    @Path(("/actualizar"))
    public Response actualizar(ReglasAsignacionPuntos reglas){
        reglasDAO.actualizar(reglas);
        return Response.ok().build();
    }

    @DELETE
    @Path("/eliminar/{id}")
    public Response eliminar(@PathParam("id") Long id){
        reglasDAO.eliminar(id);
        return Response.ok(id).build();
    }
}
