package Entity;
import java.time.LocalDate;

public class Locacao {
    private int IdLocacao;
    private String salaLocacao;
    private LocalDate dataLocacao;
    private String horaLocacao;
    private String responsavelLocacao;

    public int getIdLocacao() {
        return IdLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        IdLocacao = idLocacao;
    }

    public String getSalaLocacao() {
        return salaLocacao;
    }

    public void setSalaLocacao(String salaLocacao) {
        this.salaLocacao = salaLocacao;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public String getHoraLocacao() {
        return horaLocacao;
    }

    public void setHoraLocacao(String horaLocacao) {
        this.horaLocacao = horaLocacao;
    }

    public String getResponsavelLocacao() {
        return responsavelLocacao;
    }

    public void setResponsavelLocacao(String responsavelLocacao) {
        this.responsavelLocacao = responsavelLocacao;
    }
}
