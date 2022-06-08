package Control;

import Entity.Locacao;
import ImplementsDAO.LocacaoDAOImpl;
import DAO.LocacaoDAO;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class LocacaoControl {
    public IntegerProperty idLocacao = new SimpleIntegerProperty(0);
    public StringProperty salaLocacao = new SimpleStringProperty("");
    public ObjectProperty dataLocacao = new SimpleObjectProperty(LocalDate.now());
    public StringProperty horaLocacao = new SimpleStringProperty("");
    public  StringProperty responsavelLocacao = new SimpleStringProperty("");

    private ObservableList<Locacao> listaView = FXCollections.observableArrayList();
    private LocacaoDAO locacaoDAO = new LocacaoDAOImpl();

    public Locacao getEntity(){
        Locacao l = new Locacao();
        l.setIdLocacao(idLocacao.get());
        l.setSalaLocacao(salaLocacao.get());
        l.setDataLocacao((LocalDate)dataLocacao.get());
        l.setHoraLocacao(horaLocacao.get());
        l.setResponsavelLocacao(responsavelLocacao.get());
        return l;
    }

    public void setEntity(Locacao l){
        idLocacao.set(l.getIdLocacao());
        salaLocacao.set(l.getSalaLocacao());
        dataLocacao.set(l.getDataLocacao());
        horaLocacao.set(l.getHoraLocacao());
        responsavelLocacao.set(l.getResponsavelLocacao());
    }

    public void salvar(){
        Locacao l = getEntity();
        locacaoDAO.adicionarLocacao(l);
        setEntity(new Locacao());
        atualizarListaView();
    }

    public void pesquisarLocacao(){
        listaView.clear();
        List<Locacao> procurar = locacaoDAO.PesquisarLocacao(salaLocacao.get());
        listaView.addAll(procurar);
    }

    public void remover (int id){
        locacaoDAO.remover(id);
        atualizarListaView();
    }

    public void atualizarListaView(){
        listaView.clear();
        listaView.addAll(locacaoDAO.PesquisarLocacao(""));
    }

    public ObservableList<Locacao> getListaView(){
        return listaView;
    }
}
