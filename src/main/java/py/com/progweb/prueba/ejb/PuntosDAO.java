package py.com.progweb.prueba.ejb;

import org.jboss.logging.Param;
import py.com.progweb.prueba.model.Persona;
import py.com.progweb.prueba.model.Punto;

import javax.persistence.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

    public List<Punto> ListarDescripcion(String descripcion){
        TypedQuery query= (TypedQuery) em.createQuery("SELECT c " +
                        "FROM Punto c " +
                        "WHERE c.descripcion_concepto = :descripcion", Punto.class)
                .setParameter("descripcion", descripcion);
        return (List<Punto>)query.getResultList();

    }
    public void actualizar(Punto punto) {
        Punto puntosToUpdate = getById(punto.getId());
        puntosToUpdate.setDescripcion_concepto(punto.getDescripcion_concepto());
        puntosToUpdate.setPuntos_requeridos(punto.getPuntos_requeridos());
    }

    public void eliminar(Integer id){
        Punto c = this.em.find(Punto.class, id);
        if (c != null){
            this.em.remove(c);
        }
    }

}
