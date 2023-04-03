package py.com.progweb.prueba.dto;

public class UtilizarPuntosDTO {
    private Long idCliente;
    private Integer idConcepto;

    public UtilizarPuntosDTO() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }
}
