package py.com.progweb.prueba.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Basic(optional = false)
    @Column(name = "id_cliente")
    @GeneratedValue(generator="clienteSec")
    @SequenceGenerator(name = "clienteSec",sequenceName = "cliente_sec",allocationSize = 0)
    private Long id;
    @Basic(optional = false)
    @Column(name = "nombre",length = 50)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido",length = 50)
    private String apellido;
    @Basic(optional = false)
    @Column(name = "num_documento",length = 20)
    private String documento;
    @Basic(optional = false)
    @Column(name = "tipo_documento",length = 20)
    private String tipoDocumento;
    @Basic(optional = false)
    @Column(name = "nacionalidad",length = 50)
    private String nacionalidad;
    @Basic(optional = false)
    @Column(name = "email",length = 100)
    private String email;
    @Basic(optional = false)
    @Column(name = "telefono",length = 20)
    private String telefono;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento",length = 50)
    private String fechaNacimiento;

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
