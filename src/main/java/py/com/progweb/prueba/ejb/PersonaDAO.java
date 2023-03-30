package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Persona;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PersonaDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    public void agregar(Persona entidad) {
        this.em.persist(entidad);
    }

    public List<Persona> todos(){
        return this.em.createQuery("select p from Persona p").getResultList();
    }

    public List<Persona> lista(String nombreLike){
        return this.em.createQuery("select p from Persona p where nombre like :param")
                .setParameter("param","%"+nombreLike+"%").getResultList();
    }
    public Persona getById(Integer id){
        return this.em.find(Persona.class,id);
    }
}