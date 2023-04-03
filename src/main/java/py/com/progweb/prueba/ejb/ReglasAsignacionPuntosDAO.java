package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.dto.CargaPuntosDTO;
import py.com.progweb.prueba.model.ReglasAsignacionPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class ReglasAsignacionPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public void agregar(ReglasAsignacionPuntos regla) {
        this.em.persist(regla);
    }

    public List<ReglasAsignacionPuntos> seleccionar(Integer id_cliente) {
        return this.em.createQuery("select r from reglas_asignacion_puntos c where id_reglas like :param")
                .setParameter("param", id_cliente).getResultList();
    }

    public List<ReglasAsignacionPuntos> listar() {
        return this.em.createQuery("select r from ReglasAsignacionPuntos r").getResultList();
    }

    public ReglasAsignacionPuntos getById(Long id) {
        return this.em.find(ReglasAsignacionPuntos.class, id);
    }

    public void actualizar(ReglasAsignacionPuntos regla) {
        this.em.merge(regla);
    }

    public void eliminar(Long id) {
        ReglasAsignacionPuntos c = this.em.find(ReglasAsignacionPuntos.class, id);
        if (c != null) {
            this.em.remove(c);
        }
    }
    public int consultaPuntos(CargaPuntosDTO request){
        ReglasAsignacionPuntos regla = this.em.createQuery("from ReglasAsignacionPuntos r " +
                "where r.limiteInferior <= :monto and r.limiteSuperior >= :monto", ReglasAsignacionPuntos.class)
                .setParameter("monto", request.getMonto()).getSingleResult();
        return Math.floorDiv(request.getMonto(), regla.getEquivalencia());
    }
}
