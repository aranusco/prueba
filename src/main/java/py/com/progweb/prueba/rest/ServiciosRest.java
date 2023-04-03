package py.com.progweb.prueba.rest;

import org.hornetq.utils.json.JSONObject;
import py.com.progweb.prueba.dto.ActualizarVencimientoDTO;
import py.com.progweb.prueba.dto.CargaPuntosDTO;
import py.com.progweb.prueba.dto.UtilizarPuntosDTO;
import py.com.progweb.prueba.ejb.BolsaPuntosDAO;
import py.com.progweb.prueba.ejb.CabeceraDAO;
import py.com.progweb.prueba.ejb.ReglasAsignacionPuntosDAO;
import py.com.progweb.prueba.ejb.VencimientoPuntosDAO;
import py.com.progweb.prueba.model.Cabecera;
import py.com.progweb.prueba.model.Cliente;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.text.ParseException;

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
    @POST
    @Path("/carga-puntos")
    public Response cargarPuntos(CargaPuntosDTO request){
        bolsaPuntosDAO.agregar(request);
        return Response.ok(request).build();
    }
    @POST
    @Path("/consulta-puntos")
    public Response consultaPuntos(CargaPuntosDTO request){
        JSONObject json = new JSONObject();
        return Response.ok(reglasDAO.consultaPuntos(request)).build();
    }
    @PUT
    @Path("/actualizar-fecha-vencimiento")
    public Response actualizarFechaVencimiento(ActualizarVencimientoDTO request) throws ParseException {
        boolean actualizado = false;
        actualizado = vencimientoPuntosDAO.actualizarVencimiento(request);
        if (!actualizado){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }
    @POST
    @Path("/utilizar-puntos")
    public Response utilizarPuntos(UtilizarPuntosDTO request){
        return Response.ok(cabeceraDAO.utilizarPuntos(request)).build();
    }
}
