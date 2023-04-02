package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.dto.CargaPuntosDTO;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Cliente;
import py.com.progweb.prueba.model.ReglasAsignacionPuntos;
import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Stateless
public class BolsaPuntosDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    public void agregar(CargaPuntosDTO request){
        int cantPuntos;
        ReglasAsignacionPuntos registro = this.em.createQuery("select r from ReglasAsignacionPuntos r" +
                " where r.limiteInferior <= :monto and r.limiteSuperior >= :monto", ReglasAsignacionPuntos.class)
                .setParameter("monto", request.getMonto()).getSingleResult();

        cantPuntos = Math.floorDiv(request.getMonto(), registro.getEquivalencia());

        Cliente cliente = this.em.createQuery("from Cliente c where c.id = :id", Cliente.class)
                .setParameter("id",request.getIdCliente()).getSingleResult();

        VencimientoPuntos vencimientoPuntos = new VencimientoPuntos();
        Calendar calendar = Calendar.getInstance();

        vencimientoPuntos.setFechaInicio(calendar.getTime());
        calendar.add(Calendar.DATE, 30);
        vencimientoPuntos.setFechaFin(calendar.getTime());
        vencimientoPuntos.setDuracion(30);

        BolsaPuntos bolsaPuntos = new BolsaPuntos();
        bolsaPuntos.setCliente(cliente);
        bolsaPuntos.setVencimientoPuntos(vencimientoPuntos);
        bolsaPuntos.setPuntajeAsignado(cantPuntos);
        bolsaPuntos.setMontoOperacion(request.getMonto());

        this.em.persist(vencimientoPuntos);
        this.em.persist(bolsaPuntos);
    }
    public List<BolsaPuntos> listar(){
        Query q = this.em.createQuery("select bp from BolsaPuntos bp");
        return (List<BolsaPuntos>) q.getResultList();
    }

    public BolsaPuntos getById(Long id){
        return this.em.find(BolsaPuntos.class, id);
    }
    public void actualizar(BolsaPuntos bolsaPuntos){
        BolsaPuntos bolsaPuntosToUpdate = getById(bolsaPuntos.getId());
        bolsaPuntosToUpdate.setCliente(bolsaPuntos.getCliente());
        bolsaPuntosToUpdate.setSaldoPuntos(bolsaPuntos.getSaldoPuntos());
        bolsaPuntosToUpdate.setMontoOperacion(bolsaPuntos.getMontoOperacion());
        bolsaPuntosToUpdate.setPuntajeUtilizado(bolsaPuntos.getPuntajeUtilizado());
        bolsaPuntosToUpdate.setPuntajeAsignado(bolsaPuntos.getPuntajeAsignado());
    }
    public void eliminar(Long id){
        BolsaPuntos c = this.em.find(BolsaPuntos.class, id);
        if (c != null){
            this.em.remove(c);
        }
    }
    public List<BolsaPuntos> listarPorCliente(Long id){
        Query query= this.em.createQuery("SELECT c " +
                        "FROM BolsaPuntos c " +
                        "WHERE cliente.id = :id", BolsaPuntos.class)
                .setParameter("id", id);
        return (List<BolsaPuntos>) query.getResultList();
    }
}
