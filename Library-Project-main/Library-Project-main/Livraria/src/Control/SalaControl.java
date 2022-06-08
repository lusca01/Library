package Control;

import DAO.SalaDAO;
import Entity.Sala;
import ImplementsDAO.SalaDAOImpl;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class SalaControl {
    public LongProperty idSala = new SimpleLongProperty(0);
    public StringProperty nomeSala = new SimpleStringProperty("");
    public StringProperty tamanhoSala = new SimpleStringProperty("");
    public StringProperty tipoSala = new SimpleStringProperty("");

    private ObservableList<Sala> listaView = FXCollections.observableArrayList();
    private SalaDAO salaDAO = new SalaDAOImpl();

    public Sala getEntity(){
        Sala s = new Sala();
        s.setIdSala(idSala.get());
        s.setNomeSala(nomeSala.get());
        s.setTamanhoSala(tamanhoSala.get());
        s.setTipoSala(tipoSala.get());
        return s;
    }

    public void setEntity(Sala s){
        idSala.set(s.getIdSala());
        nomeSala.set(s.getNomeSala());
        tamanhoSala.set(s.getTamanhoSala());
        tipoSala.set(s.getTipoSala());
    }

    public void salvar(){
        Sala s = getEntity();
        salaDAO.adicionarSala(s);
        setEntity(new Sala());
        atualizarListaView();
    }

    public void pesquisarSala(){
        listaView.clear();
        List<Sala> procurar = salaDAO.PesquisarSala(nomeSala.get());
        listaView.addAll(procurar);
    }

    public void remover(long id){
        salaDAO.remover(id);
        atualizarListaView();
    }

    public void atualizarListaView(){
        listaView.clear();
        listaView.addAll(salaDAO.PesquisarSala(""));
    }
    public ObservableList<Sala> getListaView(){
        return listaView;
    }
}
