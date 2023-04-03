package py.com.progweb.prueba.rest;

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

   /* @GET
    @Path("uso-puntos/descripcion")
    public Response puntosPorFechaUso(@QueryParam("param") String descripcionConcepto) {
        try {
            List<Detalle> listaUsos = consultasDao.puntosPorFechaUsot(descripcionConcepto);
            if (listaUsos == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(listaUsos).build();
        } catch (Exception e)  {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error ocurred while listing point uses: " + e.getMessage()).build();
        }
    }*/


}