package py.com.progweb.prueba.model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "vencimiento_puntos")
public class VencimientoPuntos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vencimientoSec")
    @SequenceGenerator(name = "vencimientoSec", sequenceName = "vencimiento_sec", allocationSize = 0)
    @Column(name = "id_vencimiento")
    private Long id;
    @Column(name = "fecha_inicio_validez", nullable = false)
    private Date fechaInicio;
    @Column(name = "fecha_fin_validez", nullable = false)
    private Date fechaFin;
    @Column(name = "dias_duracion_puntaje", nullable = false)
    private Integer duracion;
    public VencimientoPuntos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
}
