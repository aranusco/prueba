package py.com.progweb.prueba.model;

import javax.persistence.*;

@Entity
@Table(name = "Puntos")
public class Punto {
    @Id
    @Basic(optional = false)
    @Column(name = "id_puntos")
    @GeneratedValue(generator="puntosSec")
    @SequenceGenerator(name="puntosSec",sequenceName="puntos_sec",allocationSize=0)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion_concepto",length = 100)
    private String descripcion_concepto;
    @Basic(optional = false)
    @Column(name = "puntos_requeridos")
    private Integer puntos_requeridos;


    public Punto(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion_concepto() {
        return descripcion_concepto;
    }

    public void setDescripcion_concepto(String descripcion_concepto) {
        this.descripcion_concepto = descripcion_concepto;
    }

    public Integer getPuntos_requeridos() {
        return puntos_requeridos;
    }

    public void setPuntos_requeridos(Integer puntos_requeridos) {
        this.puntos_requeridos = puntos_requeridos;
    }
}