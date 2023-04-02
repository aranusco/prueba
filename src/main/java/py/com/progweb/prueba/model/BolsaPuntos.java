package py.com.progweb.prueba.model;

import javax.persistence.*;

@Entity
@Table(name = "bolsa_puntos")
public class BolsaPuntos {
    @Id
    @GeneratedValue(generator = "bolsaPuntosSec")
    @SequenceGenerator(name = "bolsaPuntosSec", sequenceName = "bolsa_sec", allocationSize = 0)
    @Column(name = "id_bolsa")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vencimiento_puntos")
    private VencimientoPuntos vencimientoPuntos;
    @Basic(optional = false)
    @Column(name = "puntaje_asignado")
    private int puntajeAsignado;
    @Basic(optional = false)
    @Column(name = "puntaje_utilizado")
    private int puntajeUtilizado;
    @Basic(optional = false)
    @Column(name = "saldo_puntos")
    private int saldoPuntos;
    @Basic(optional = false)
    @Column(name = "monto_operacion")
    private int montoOperacion;

    public BolsaPuntos() {
    }

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

    public VencimientoPuntos getVencimientoPuntos() {
        return vencimientoPuntos;
    }

    public void setVencimientoPuntos(VencimientoPuntos vencimientoPuntos) {
        this.vencimientoPuntos = vencimientoPuntos;
    }

    public int getPuntajeAsignado() {
        return puntajeAsignado;
    }

    public void setPuntajeAsignado(int puntajeAsignado) {
        this.puntajeAsignado = puntajeAsignado;
    }

    public int getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(int puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public int getSaldoPuntos() {
        return saldoPuntos;
    }

    public void setSaldoPuntos(int saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    public int getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(int montoOperacion) {
        this.montoOperacion = montoOperacion;
    }
}
