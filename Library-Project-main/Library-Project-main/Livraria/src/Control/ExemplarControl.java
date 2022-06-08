package Control;
import Entity.Exemplar;
import ImplementsDAO.ExemplarDAOImpl;
import DAO.ExemplarDAO;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class ExemplarControl {
    public IntegerProperty id = new SimpleIntegerProperty(0);
    public StringProperty titulo = new SimpleStringProperty("");
    public  StringProperty autor = new SimpleStringProperty("");
    public  IntegerProperty nPaginas = new SimpleIntegerProperty(0);
    public ObjectProperty anopub = new SimpleObjectProperty();

    private ObservableList<Exemplar> listaview = FXCollections.observableArrayList();
    private ExemplarDAO exDAO = new ExemplarDAOImpl();

    public Exemplar getEntity(){
        Exemplar e = new Exemplar();
        e.setId(id.get());
        e.setTitulo(titulo.get());
        e.setAutor(autor.get());
        e.setnPaginas(nPaginas.get());
        e.setAnoPublic((LocalDate) anopub.get());
        return e;
    }

    public void setEntity(Exemplar e) {
        id.set(e.getId());
        titulo.set(e.getTitulo());
        autor.set(e.getTitulo());
        nPaginas.set(e.getnPaginas());
        anopub.set(e.getAnoPublic());
    }

    public void remover(int id){
        exDAO.remover(id);
        atualizaListaView();
    }

    public void salvar(){
        Exemplar exemplar = getEntity();
        exDAO.adicionarExemplar(exemplar);
        setEntity( new Exemplar());
        atualizaListaView();
    }

    public void pesquisarExemplar(){
        listaview.clear();
        List<Exemplar> procurar = exDAO.PesquisarExemplar(titulo.get());
        listaview.addAll(procurar);
    }

    private void atualizaListaView() {
        listaview.clear();
        listaview.addAll(exDAO.PesquisarExemplar(""));
    }

    public ObservableList<Exemplar> getListaView(){
        return listaview;
    }
}
