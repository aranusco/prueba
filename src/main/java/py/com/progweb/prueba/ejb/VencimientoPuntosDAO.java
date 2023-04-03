package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.dto.ActualizarVencimientoDTO;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateless
public class VencimientoPuntosDAO {

    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    public List<VencimientoPuntos> listar(){
        Query q = this.em.createQuery("select vp from VencimientoPuntos vp");
        return (List<VencimientoPuntos>) q.getResultList();
    }
    public void agregar(VencimientoPuntos vencPuntos){
        this.em.persist(vencPuntos);
    }
    public VencimientoPuntos getById(Long id){
        return this.em.find(VencimientoPuntos.class, id);
    }
    public void actualizar(VencimientoPuntos request){
        VencimientoPuntos vencimientoToUpdate = getById(request.getId());
        vencimientoToUpdate.setFechaInicio(request.getFechaInicio());
        vencimientoToUpdate.setFechaFin(request.getFechaFin());
    }

    public void eliminar(Long id){
        VencimientoPuntos v = this.em.find(VencimientoPuntos.class, id);
        if (v != null){
            this.em.remove(v);
        }
    }
    public boolean actualizarVencimiento(ActualizarVencimientoDTO request) throws ParseException {
        Date fecha = new SimpleDateFormat("dd-mm-yyyy").parse(request.getVencimiento());

        BolsaPuntos bolsaPuntos = this.em.createQuery("from BolsaPuntos b " +
                "where b.id = :id", BolsaPuntos.class)
                .setParameter("id", request.getId()).getSingleResult();

        VencimientoPuntos vencimientoPuntos = bolsaPuntos.getVencimientoPuntos();

        vencimientoPuntos.setFechaFin(fecha);

        Long diferencia = Math.abs(fecha.getTime() - vencimientoPuntos.getFechaInicio().getTime());
        int cantDias = Math.toIntExact( TimeUnit.DAYS.convert(diferencia, TimeUnit.MILLISECONDS));
        vencimientoPuntos.setDuracion(cantDias);

        this.em.persist(vencimientoPuntos);
        return true;
    }
}
