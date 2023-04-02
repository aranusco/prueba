package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.dto.CargaPuntosDTO;
import py.com.progweb.prueba.ejb.BolsaPuntosDAO;
import py.com.progweb.prueba.ejb.CabeceraDAO;
import py.com.progweb.prueba.ejb.ReglasAsignacionPuntosDAO;
import py.com.progweb.prueba.ejb.VencimientoPuntosDAO;
import py.com.progweb.prueba.model.Cabecera;
import py.com.progweb.prueba.model.Cliente;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/servicios")
@Consumes("application/json")
@Produces("application/json")
public class ServiciosRest {
    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;
    @Inject
    CabeceraDAO cabeceraDAO;
    @Inject
    ReglasAsignacionPuntosDAO reglasDAO;
    @Inject
    VencimientoPuntosDAO vencimientoPuntosDAO;
    @Inject
    Cliente cliente;

    @POST
    @Path("/carga-puntos")
    public Response cargarPuntos(CargaPuntosDTO request){
        bolsaPuntosDAO.agregar(request);
        return Response.ok(request).build();
    }
}
