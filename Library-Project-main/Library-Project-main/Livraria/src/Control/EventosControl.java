package Control;
import Entity.Eventos;
import ImplementsDAO.EventosDAOImpl;
import DAO.EventosDAO;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class EventosControl {
    public IntegerProperty idEvento = new SimpleIntegerProperty(0);
    public StringProperty tipoEvento = new SimpleStringProperty("");
    public ObjectProperty dataEvento = new SimpleObjectProperty(LocalDate.now());
    public StringProperty representante = new SimpleStringProperty("");
    public StringProperty participacao = new SimpleStringProperty("");
    public StringProperty publicoAlvo = new SimpleStringProperty("");

    private ObservableList<Eventos> listaView = FXCollections.observableArrayList();
    private EventosDAO eventosDAO = new EventosDAOImpl();

    public Eventos getEntity(){
        Eventos e = new Eventos();
        e.setIdEvento(idEvento.get());
        e.setTipoEvento(tipoEvento.get());
        e.setDataEvento((LocalDate) dataEvento.get());
        e.setRepresentante(representante.get());
        e.setParticipacao(participacao.get());
        e.setPublicoAlvo(publicoAlvo.get());
        return e;
    }

    public void setEntity(Eventos e){
        idEvento.set(e.getIdEvento());
        tipoEvento.set(e.getTipoEvento());
        dataEvento.set(e.getDataEvento());
        representante.set(e.getRepresentante());
        participacao.set(e.getParticipacao());
        publicoAlvo.set(e.getPublicoAlvo());
    }

    public void salvar(){
        Eventos e = getEntity();
        eventosDAO.adicionarEventos(e);
        setEntity(new Eventos());
        atualizarListaView();
    }

    public void pesquisarEventos(){
        listaView.clear();
        List<Eventos> procurar = eventosDAO.PesquisarEvento(tipoEvento.get());
        listaView.addAll(procurar);
    }

    public void remover (int id){
        eventosDAO.remover(id);
        atualizarListaView();
    }

    public void atualizarListaView(){
        listaView.clear();
        listaView.addAll(eventosDAO.PesquisarEvento(""));
    }

    public ObservableList<Eventos> getListaView(){
        return listaView;
    }
}
