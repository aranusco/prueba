package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.ejb.ConsultasDAO;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Detalle;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("consultas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConsultasRest {

    @Inject
    ConsultasDAO consultasDao;


    @GET
    @Path("bolsa-puntos/cliente/{id}")
    public Response puntosPorCliente(@PathParam("id") Long id) {
        try {
            List<BolsaPuntos> listaUsos = consultasDao.listarPorCliente(id);
            if (listaUsos == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(listaUsos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error ocurred while listing point uses: " + e.getMessage()).build();
        }
    }
    @GET
    @Path("bolsa-puntos/rango")
    public Response puntosPorRango(@QueryParam("rangoInferior") Integer rangoInferior, @QueryParam("rangoSuperior") Integer rangoSuperior) {
        try {
            List<BolsaPuntos> listaPuntos = consultasDao.listarPorRango(rangoInferior, rangoSuperior);
            if (listaPuntos == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(listaPuntos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error ocurred while listing point uses: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("uso-puntos/fecha")
    public Response puntosPorFechaUso(@QueryParam("fecha") String fecha) {
        try {
            List<Detalle> listaUsos = consultasDao.listarPorFechaUso(fecha);
            if (listaUsos == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(listaUsos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error ocurred while listing point uses: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("uso-puntos/concepto")
    public Response puntosPorConcepto(@QueryParam("param") String descripcionConcepto) {
        try {
            List<Detalle> listaUsos = consultasDao.puntosPorDecripcion(descripcionConcepto);
            if (listaUsos == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(listaUsos).build();
        } catch (Exception e)  {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error ocurred while listing point uses: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("uso-puntos/cliente/{id}")
    public Response puntosPorIdCliente(@PathParam("id") Long id) {
        try {
            List<Detalle> listaUsos = consultasDao.puntosPorIdCliente(id);
            if (listaUsos == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(listaUsos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error ocurred while listing point uses: " + e.getMessage()).build();
        }
    }
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
        return Response.ok(clienteDAO.seleccionar(apellido)).build();
    }
    @GET
    @Path("/cumple/{fecha}")
    public Response getClienteByFecha(@PathParam("fecha") String fecha){
        return Response.ok(clienteDAO.seleccionarByFecha(fecha)).build();
    }

    @GET
    @Path("/expiracion/{dia}")
    public Response getClienteByFechaExpiracion(@PathParam("dia") Integer dia){
        return Response.ok(consultasDao.seleccionarByFechaExpiracion(dia)).build();
    }



}
