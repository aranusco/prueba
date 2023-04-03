package py.com.progweb.prueba.dto;

public class ActualizarVencimientoDTO {
    private Long id;
    private String vencimiento;

    public ActualizarVencimientoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }
}
