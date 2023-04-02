package py.com.progweb.prueba.model;

import javax.persistence.*;

@Entity
@Table(name = "reglas_asignacion_puntos")
public class ReglasAsignacionPuntos {
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator="reglaSec")
    @SequenceGenerator(name = "reglaSec",sequenceName = "reglas_sec",allocationSize = 0)
    @Column(name = "id_reglas")
    private Long id;

    @Basic(optional = false)
    @Column(name = "limite_inferior", nullable = false)
    private Integer limiteInferior;

    @Basic(optional = false)
    @Column(name = "limite_superior", nullable = false)
    private Integer limiteSuperior;

    @Basic(optional = false)
    @Column(name = "monto_equivalencia_punto", nullable = false)
    private Integer equivalencia;

    public ReglasAsignacionPuntos() {
    }

    public ReglasAsignacionPuntos(Integer limiteInferior, Integer limiteSuperior, Integer equivalencia) {
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.equivalencia = equivalencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Integer limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Integer getLimiteSuperior() {
        return limiteSuperior;
    }

    public void getLimiteSuperior(Integer limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public Integer getEquivalencia() {
        return equivalencia;
    }

    public void getEquivalencia(Integer equivalencia) {
        this.equivalencia = equivalencia;
    }

    @Override
    public String toString() {
        return "ReglasAsignacionPuntos{" +
                "id=" + id +
                ", limiteInferior='" + limiteInferior + '\'' +
                ", limiteSuperior=" + limiteSuperior + '\'' +
                ", equivalencia='" + equivalencia +
                '}';
    }
}
