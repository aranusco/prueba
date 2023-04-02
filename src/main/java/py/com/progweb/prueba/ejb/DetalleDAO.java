package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Detalle;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Stateless
public class DetalleDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    public void agregar(Detalle detalle){
        this.em.persist(detalle);
    }

    public List<Detalle> listar(){
        Query q = this.em.createQuery("select c from Detalle c");
        return (List<Detalle>) q.getResultList();
    }
    public Detalle getById(Long id){
        return this.em.find(Detalle.class, id);
    }

    public void actualizar(Detalle detalle) {
        Detalle detalleToUpdate = getById(detalle.getId());
        detalleToUpdate.setCabecera(detalle.getCabecera());
        detalleToUpdate.setPuntajeUtilizado(detalle.getPuntajeUtilizado());
        detalleToUpdate.setBolsaPuntosUtilizada(detalle.getBolsaPuntosUtilizada());
    }

    public void eliminar(Long id){
        Detalle c = this.em.find(Detalle.class, id);
        if (c != null){
            this.em.remove(c);
        }
    }
}
