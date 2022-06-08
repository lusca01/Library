package Entity;
import java.time.LocalDate;

public class Parcerias {
    private int idParceria;
    private String tipoParceria;
    private String nomeParceria;
    private LocalDate dataParceria;
    private String fornece;

    public int getIdParceria() {
        return idParceria;
    }

    public void setIdParceria(int idParceria) {
        this.idParceria = idParceria;
    }

    public String getTipoParceria() {
        return tipoParceria;
    }

    public void setTipoParceria(String tipoParceria) {
        this.tipoParceria = tipoParceria;
    }

    public String getNomeParceria() {
        return nomeParceria;
    }

    public void setNomeParceria(String nomeParceria) {
        this.nomeParceria = nomeParceria;
    }

    public LocalDate getDataParceria() {
        return dataParceria;
    }

    public void setDataParceria(LocalDate dataParceria) {
        this.dataParceria = dataParceria;
    }

    public String getFornece() {
        return fornece;
    }

    public void setFornece(String fornece) {
        this.fornece = fornece;
    }
}
