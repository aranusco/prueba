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
    @Path("/nombre/{nombre}")
    public Response getClientePorNombre(@PathParam("nombre") String nombre){
        return Response.ok(clienteDAO.seleccionar(nombre)).build();
    }
    @GET
    @Path("/apellido/{apellido}")
    public Response getClientePorApellido(@PathParam("apellido") String apellido){
        return Response.ok(clienteDAO.seleccionarApellido(apellido)).build();
    }
    @GET
    @Path("/cumple/{fecha}")
    public Response getClienteByFecha(@PathParam("fecha") String fecha){
        return Response.ok(clienteDAO.seleccionarByFecha(fecha)).build();
    }
    @GET
    @Path("/listar")
    public Response listarClientes(){
        return Response.ok(clienteDAO.listar()).build();
    }
    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id){
        return Response.ok(clienteDAO.getById(id)).build();
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
