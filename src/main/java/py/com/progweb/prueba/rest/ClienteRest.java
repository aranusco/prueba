package py.com.progweb.prueba.rest;

import org.jboss.remoting.Client;
import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.Punto;

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
    @PUT
    @Path(("/actualizar/{id}"))
    public Response actualizar(Cliente cliente){
        cliente.setId(cliente.getId());
        clienteDAO.actualizar(cliente);
        return Response.ok().build();
    }

    @DELETE
    @Path("/eliminar/{id}")
    public Response eliminar(@PathParam("id") Long id){
        clienteDAO.eliminar(id);
        return Response.ok(id).build();
    }

}
