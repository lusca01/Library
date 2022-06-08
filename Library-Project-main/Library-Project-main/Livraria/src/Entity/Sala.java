package Entity;

public class Sala {
    private long idSala;
    private String nomeSala;
    private String tamanhoSala;
    private String tipoSala;

    public long getIdSala() {
        return idSala;
    }

    public void setIdSala(long idSala) {
        this.idSala = idSala;
    }

    public String getNomeSala() {
        return nomeSala;
    }

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }

    public String getTamanhoSala() {
        return tamanhoSala;
    }

    public void setTamanhoSala(String tamanhoSala) {
        this.tamanhoSala = tamanhoSala;
    }

    public String getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }
}