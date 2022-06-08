package Entity;
import java.time.LocalDate;

public class Clientes {

    private int idCliente;
    private String nomeCliente;
    private String docRg;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDate dataAfiliacao;

    public int getIdClientes() {
        return idCliente;
    }

    public void setIdClientes(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDocRG() {
        return docRg;
    }

    public void setDocRG(String docRg) {
        this.docRg = docRg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailCliente) {
        this.email = emailCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String foneCliente) {
        this.telefone = foneCliente;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataAfiliacao() {
        return dataAfiliacao;
    }

    public void setDataAfiliacao(LocalDate dataAfiliacao) {
        this.dataAfiliacao = dataAfiliacao;
    }
}
