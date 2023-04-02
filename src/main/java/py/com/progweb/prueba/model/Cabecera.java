package py.com.progweb.prueba.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cabecera")
public class Cabecera {

    @Id
    @Basic(optional = false)
    @Column(name = "id_cabecera")
    @GeneratedValue(generator="cabeceraSec")
    @SequenceGenerator(name = "cabeceraSec",sequenceName = "cabecera_sec",allocationSize = 0)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Basic(optional = false)
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;

    @Basic(optional = false)
    @Column(name = "fecha",length = 50)
    private Date fecha;


    @Basic(optional = false)
    @Column(name = "concepto_uso_punto",length = 50)
    private String  conceptoUsoPunto;

    public Cabecera() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getConceptoUsoPunto() {
        return conceptoUsoPunto;
    }

    public void setConceptoUsoPunto(String conceptoUsoPunto) {
        this.conceptoUsoPunto = conceptoUsoPunto;
    }



}
