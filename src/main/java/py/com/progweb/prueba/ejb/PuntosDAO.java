package py.com.progweb.prueba.ejb;

import org.jboss.logging.Param;
import py.com.progweb.prueba.model.Persona;
import py.com.progweb.prueba.model.Punto;
import javax.persistence.EntityManager;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PuntosDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    public void agregar(Punto punto){
        this.em.persist(punto);
    }

    public List<Punto> listar(){
        Query q =this.em.createQuery("select c from Punto c");
        return (List<Punto>)q.getResultList();
    }

    public Punto getById(Integer id){
        return this.em.find(Punto.class,id);
    }
    public void actualizar(Punto punto) {
        Punto puntosToUpdate = getById(punto.getId());
        puntosToUpdate.setDescripcion_concepto(punto.getDescripcion_concepto());
        puntosToUpdate.setPuntos_requeridos(punto.getPuntos_requeridos());
    }

}
