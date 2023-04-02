package py.com.progweb.prueba.model;

import javax.persistence.*;

@Entity
@Table(name = "detalle")
public class Detalle {

    @Id
    @Basic(optional = false)
    @Column(name = "id_detalle")
    @GeneratedValue(generator="detalleSec")
    @SequenceGenerator(name = "detalleSec",sequenceName = "detalle_sec",allocationSize = 0)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cabecera")
    private Cabecera cabecera;

    @Basic(optional = false)
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bolsa_puntos_utilizada")
    private BolsaPuntos bolsaPuntosUtilizada;

    public Detalle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(Cabecera cabecera) {
        this.cabecera = cabecera;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public BolsaPuntos getBolsaPuntosUtilizada() {
        return bolsaPuntosUtilizada;
    }

    public void setBolsaPuntosUtilizada(BolsaPuntos bolsaPuntosUtilizada) {
        this.bolsaPuntosUtilizada = bolsaPuntosUtilizada;
    }
}
