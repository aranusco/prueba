package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.ReglasAsignacionPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class ReglasAsignacionPuntosDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    public void agregar(ReglasAsignacionPuntos regla){
        this.em.persist(regla);
    }

    public List<ReglasAsignacionPuntos> seleccionar(Integer id_cliente){
        return this.em.createQuery("select r from reglas_asignacion_puntos c where id_reglas like :param")
                .setParameter("param", id_cliente).getResultList();
    }

    public List<ReglasAsignacionPuntos> listar(){
        return this.em.createQuery("select r from ReglasAsignacionPuntos r").getResultList();
    }

    public ReglasAsignacionPuntos getById(Long id){
        return this.em.find(ReglasAsignacionPuntos.class, id);
    }

    public void actualizar(ReglasAsignacionPuntos regla){
        this.em.merge(regla);
    }

    public void eliminar(ReglasAsignacionPuntos regla){
        this.em.remove(this.em.contains(regla) ? regla : this.em.merge(regla));
    }
}