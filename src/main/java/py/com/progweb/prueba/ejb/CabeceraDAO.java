package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.dto.UtilizarPuntosDTO;
import py.com.progweb.prueba.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
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

    public Object utilizarPuntos(UtilizarPuntosDTO request) {
        Cliente cliente = this.em.createQuery("select c from Cliente c "+
                "where c.id = :id", Cliente.class).setParameter("id", request.getIdCliente()).getSingleResult();
        System.out.println("CLIENTE: " + cliente);
        Punto punto = this.em.createQuery("select p from Punto p " +
                "where p.id = :id", Punto.class).setParameter("id", request.getIdConcepto()).getSingleResult();
        System.out.println("PUNTO: " + punto);
        List<BolsaPuntos> bolsaPuntosList = this.em.createQuery("select bp from BolsaPuntos bp " +
                "where bp.cliente.id = :id " + "order by bp.vencimientoPuntos.fechaInicio asc", BolsaPuntos.class)
                .setParameter("id", request.getIdCliente()).getResultList();
        System.out.println("BOLSA PUNTOS: " + bolsaPuntosList);
        int puntosRev = 0;
        int puntosNec = punto.getPuntos_requeridos();
        for (BolsaPuntos bolsaPuntos: bolsaPuntosList) {
            System.out.println("BOLSA PUNTO: " + bolsaPuntos);
            if (puntosRev < puntosNec){
                System.out.println("si");
                puntosRev += bolsaPuntos.getSaldoPuntos();
            } else {
                break;
            }
        }
        System.out.println("PUNTOS REV: "+puntosRev);
        if (puntosRev >= puntosNec){
            int cantidadRestante = puntosNec;
            Calendar fecha = Calendar.getInstance();

            Cabecera cabecera = new Cabecera();
            cabecera.setCliente(cliente);
            cabecera.setPuntajeUtilizado(puntosNec);
            cabecera.setFecha(fecha.getTime());
            cabecera.setConceptoUsoPunto(punto.getDescripcion_concepto());

            this.em.persist(cabecera);

            for (BolsaPuntos bolsaPuntos: bolsaPuntosList) {
                if (bolsaPuntos.getSaldoPuntos() > 0){
                    if (cantidadRestante > bolsaPuntos.getSaldoPuntos()){
                        Detalle detalle = new Detalle();
                        detalle.setCabecera(cabecera);
                        detalle.setBolsaPuntosUtilizada(bolsaPuntos);
                        detalle.setPuntajeUtilizado(bolsaPuntos.getSaldoPuntos());

                        this.em.persist(detalle);
                        cantidadRestante = cantidadRestante - bolsaPuntos.getSaldoPuntos();
                        bolsaPuntos.setPuntajeUtilizado(bolsaPuntos.getPuntajeUtilizado() + bolsaPuntos.getSaldoPuntos());
                        bolsaPuntos.setSaldoPuntos(0);
                        this.em.persist(bolsaPuntos);
                    } else {
                        Detalle detalle = new Detalle();
                        detalle.setCabecera(cabecera);
                        detalle.setBolsaPuntosUtilizada(bolsaPuntos);
                        detalle.setPuntajeUtilizado(cantidadRestante);

                        this.em.persist(detalle);
                        bolsaPuntos.setSaldoPuntos(bolsaPuntos.getSaldoPuntos() - cantidadRestante);
                        bolsaPuntos.setPuntajeUtilizado(bolsaPuntos.getPuntajeUtilizado() + cantidadRestante);
                        this.em.persist(bolsaPuntos);

                        cantidadRestante = 0;
                    }
                }
            }

            Email email = new Email();
            String encabezado = "Uso de puntos";
            String mensaje = "Estimado " + cliente.getNombre() + " " + cliente.getApellido() + ". Se ha registrado una transacción" +
                    " a su nombre, en concepto de uso de puntos. \n" +
                    "Cantidad de puntos utilizados: " + puntosNec + ". Con concepto de: " + punto.getDescripcion_concepto();
            email.sendEmail(cliente.getEmail(), encabezado, mensaje);
            return true;
        } else {
            return false;
        }
    }
}
