package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.model.Cliente;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/cliente")
@Consumes("application/json")
@Produces("application/json")
public class ClienteRest {
    @Inject
    ClienteDAO clienteDAO;

    @GET
    public Response getClientePorNombre(@QueryParam("nombre") String nombre){
        return Response.ok(clienteDAO.seleccionar(nombre)).build();
    }
    @GET
    @Path("/listar")
    public Response listarClientes(){
        return Response.ok(clienteDAO.listar()).build();
    }
    @POST
    public Response agregar(Cliente cliente){
        clienteDAO.agregar(cliente);
        return Response.ok(cliente).build();
    }

    @DELETE
    @Path("/eliminar/{id}")
    public Response eliminar(@PathParam("id") Long id){
        clienteDAO.eliminar(id);
        return Response.ok(id).build();
    }

}
