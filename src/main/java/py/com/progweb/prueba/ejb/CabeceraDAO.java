package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cabecera;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Stateless
public class CabeceraDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    public void agregar(Cabecera cabecera){
        this.em.persist(cabecera);
    }

    public List<Cabecera> listar(){
        Query q = this.em.createQuery("select c from Cabecera c");
        return (List<Cabecera>) q.getResultList();
    }
    public Cabecera getById(Long id){
        return this.em.find(Cabecera.class, id);
    }

    public void actualizar(Cabecera cabecera) {
        Cabecera cabeceraToUpdate = getById(cabecera.getId());
        cabeceraToUpdate.setCliente(cabecera.getCliente());
        cabeceraToUpdate.setFecha(cabecera.getFecha());
        cabeceraToUpdate.setPuntajeUtilizado(cabecera.getPuntajeUtilizado());
        cabeceraToUpdate.setConceptoUsoPunto(cabecera.getConceptoUsoPunto());
    }

    public void eliminar(Long id){
        Cabecera c = this.em.find(Cabecera.class, id);
        if (c != null){
            this.em.remove(c);
        }
    }
}
