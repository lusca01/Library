package Entity;
import java.time.LocalDate;

public class Exemplar {
    private int Id;
    private String Titulo;
    private String Autor;
    private int nPaginas;
    private LocalDate AnoPublic;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public int getnPaginas() {
        return nPaginas;
    }

    public void setnPaginas(int nPaginas) {
        this.nPaginas = nPaginas;
    }

    public LocalDate getAnoPublic() {
        return AnoPublic;
    }

    public void setAnoPublic(LocalDate AnoPublic) {
        this.AnoPublic = AnoPublic;
    }
}
