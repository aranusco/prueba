package py.com.progweb.prueba.dto;

public class CargaPuntosDTO {
    private Long idCliente;
    private Integer monto;

    public CargaPuntosDTO() {
    }
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }
}
