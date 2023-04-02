package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Stateless
public class ClienteDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    public void agregar(Cliente cliente){
        this.em.persist(cliente);
    }

    public List<Cliente> listar(){
        Query q = this.em.createQuery("select c from Cliente c");
        return (List<Cliente>) q.getResultList();
    }
    public List seleccionar(String nombre){
        return this.em.createQuery("select c from Cliente c where nombre like :param")
                .setParameter("param", "%"+nombre+"%").getResultList();
    }

    public Cliente getById(Long id){
        return this.em.find(Cliente.class, id);
    }
    public void actualizar(Cliente cliente) {
        Cliente clienteToUpdate = getById(cliente.getId());
        clienteToUpdate.setNombre(cliente.getNombre());
        clienteToUpdate.setApellido(cliente.getApellido());
        clienteToUpdate.setDocumento(cliente.getDocumento());
        clienteToUpdate.setTipoDocumento(cliente.getTipoDocumento());
        clienteToUpdate.setNacionalidad(cliente.getNacionalidad());
        clienteToUpdate.setTelefono(cliente.getTelefono());
        clienteToUpdate.setFechaNacimiento(cliente.getFechaNacimiento());

    }

    public void eliminar(Long id){
        Cliente c = this.em.find(Cliente.class, id);
        if (c != null){
            this.em.remove(c);
        }
    }
}
