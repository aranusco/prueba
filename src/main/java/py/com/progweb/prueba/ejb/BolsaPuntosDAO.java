package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class BolsaPuntosDAO {
    @PersistenceContext(unitName="pruebaPU")
    private EntityManager em;

    public void agregar(BolsaPuntos bolsaPuntos){
        this.em.persist(bolsaPuntos);
    }
    public List<BolsaPuntos> listar(){
        Query q = this.em.createQuery("select bp from BolsaPuntos bp");
        return (List<BolsaPuntos>) q.getResultList();
    }

    public BolsaPuntos getById(Long id){
        return this.em.find(BolsaPuntos.class, id);
    }
    public void actualizar(BolsaPuntos bolsaPuntos){
        BolsaPuntos bolsaPuntosToUpdate = getById(bolsaPuntos.getId());
        bolsaPuntosToUpdate.setCliente(bolsaPuntos.getCliente());
        bolsaPuntosToUpdate.setSaldoPuntos(bolsaPuntos.getSaldoPuntos());
        bolsaPuntosToUpdate.setMontoOperacion(bolsaPuntos.getMontoOperacion());
        bolsaPuntosToUpdate.setPuntajeUtilizado(bolsaPuntos.getPuntajeUtilizado());
        bolsaPuntosToUpdate.setPuntajeAsignado(bolsaPuntos.getPuntajeAsignado());
    }
    public void eliminar(Long id){
        BolsaPuntos c = this.em.find(BolsaPuntos.class, id);
        if (c != null){
            this.em.remove(c);
        }
    }
}
