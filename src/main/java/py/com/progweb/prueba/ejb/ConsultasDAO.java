package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Detalle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class ConsultasDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    public List<BolsaPuntos> listarPorCliente(Long id){
        Query query= this.em.createQuery("SELECT c " +
                        "FROM BolsaPuntos c " +
                        "WHERE cliente.id = :id", BolsaPuntos.class)
                .setParameter("id", id);
        return (List<BolsaPuntos>) query.getResultList();
    }

    public List<Detalle> listarPorFechaUso(String fecha) {
        return (List<Detalle>) em.createQuery("Select u from Detalle u " +
                "where to_char(u.cabecera.fecha, 'yyyy-mm-dd') = :fecha", Detalle.class).setParameter("fecha", fecha).getResultList();
    }

    public List<Detalle> puntosPorDecripcion(String descripcionConcepto) {
        return em.createQuery("from Detalle u " +
                "where u.cabecera.conceptoUsoPunto = :descripcionConcepto", Detalle.class).setParameter("descripcionConcepto", descripcionConcepto).getResultList();
    }

    public List<Detalle> puntosPorIdCliente(Long id) {
        return em.createQuery("from Detalle u " +
                "where u.cabecera.cliente.id = :id", Detalle.class).setParameter("id", id).getResultList();
    }




    public List<BolsaPuntos> listarPorRango(Integer rangoInferior, Integer rangoSuperior) {
        return this.em.createQuery("select bp from BolsaPuntos bp " +
                "where bp.puntajeAsignado >= :min and puntajeAsignado <= :max", BolsaPuntos.class)
                .setParameter("min", rangoInferior).setParameter("max", rangoSuperior)
                .getResultList();
    }


}
