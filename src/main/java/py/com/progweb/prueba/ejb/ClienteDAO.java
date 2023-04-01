package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ClienteDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    public void agregar(Cliente cliente){
        this.em.persist(cliente);
    }

    public List<Cliente> listar(){
        return this.em.createQuery("select * from Cliente c").getResultList();
    }
    public List<Cliente> seleccionar(String nombre){
        return this.em.createQuery("select c from Cliente c where nombre like :param")
                .setParameter("param", "%"+nombre+"%").getResultList();
    }

    public Cliente getById(Long id){
        return this.em.find(Cliente.class, id);
    }
}
