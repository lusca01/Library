package Control;
import Entity.Clientes;
import ImplementsDAO.ClientesDAOImpl;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class ClientesControl {
    public IntegerProperty idCliente = new SimpleIntegerProperty(0);
    public StringProperty nomeCliente = new SimpleStringProperty("");
    public StringProperty docRg = new SimpleStringProperty("");
    public StringProperty email = new SimpleStringProperty("");
    public StringProperty telefone = new SimpleStringProperty("");
    public ObjectProperty dataNascimento = new SimpleObjectProperty(LocalDate.now());
    public ObjectProperty dataAfiliacao = new SimpleObjectProperty(LocalDate.now());

    private ObservableList<Clientes> listaView = FXCollections.observableArrayList();
    private ClientesDAOImpl clientesDAO = new ClientesDAOImpl();

    public Clientes getEntity(){
        Clientes c = new Clientes();
        c.setIdClientes(idCliente.get());
        c.setNomeCliente(nomeCliente.get());
        c.setEmail(email.get());
        c.setTelefone(telefone.get());
        c.setDataNascimento((LocalDate) dataNascimento.get());
        c.setDataAfiliacao((LocalDate) dataAfiliacao.get());
        return c;
    }

    public void setEntity(Clientes c){
        idCliente.set(c.getIdClientes());
        docRg.set(c.getDocRG());
        email.set(c.getEmail());
        telefone.set(c.getTelefone());
        nomeCliente.set(c.getNomeCliente());
        dataNascimento.set(c.getDataNascimento());
        dataAfiliacao.set(c.getDataAfiliacao());

    }

    public void salvar(){
        Clientes c = getEntity();
        clientesDAO.adicionarClientes(c);
        setEntity(new Clientes());
        atualizarListaView();
    }

    public void pesquisarClientes(){
        listaView.clear();
        List<Clientes> procurar = clientesDAO.PesquisarClientes(nomeCliente.get());
        listaView.addAll(procurar);
    }

    public void remover (int id){
        clientesDAO.remover(id);
        atualizarListaView();
    }

    public void atualizarListaView(){
        listaView.clear();
        listaView.addAll(clientesDAO.PesquisarClientes(""));
    }

    public ObservableList<Clientes> getListaView(){
        return listaView;
    }
}
