package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.VencimientoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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


}
