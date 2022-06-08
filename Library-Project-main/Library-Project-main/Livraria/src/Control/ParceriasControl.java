package Control;
import Entity.Parcerias;
import ImplementsDAO.ParceriasDAOImpl;
import DAO.ParceriasDAO;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class ParceriasControl {
    public IntegerProperty idParceria = new SimpleIntegerProperty(0);
    public StringProperty tipoParceria = new SimpleStringProperty("");
    public StringProperty nomeParceria = new SimpleStringProperty("");
    public ObjectProperty dataParceria = new SimpleObjectProperty(LocalDate.now());
    public StringProperty fornece = new SimpleStringProperty("");

    private ObservableList<Parcerias> listaView = FXCollections.observableArrayList();
    private ParceriasDAO parceriasDAO = new ParceriasDAOImpl();

    public Parcerias getEntity(){
        Parcerias p = new Parcerias();
        p.setIdParceria(idParceria.get());
        p.setTipoParceria(tipoParceria.get());
        p.setNomeParceria(nomeParceria.get());
        p.setDataParceria((LocalDate) dataParceria.get());
        p.setFornece(fornece.get());
        return p;
    }

    public void setEntity(Parcerias p){
        idParceria.set(p.getIdParceria());
        tipoParceria.set(p.getTipoParceria());
        nomeParceria.set(p.getNomeParceria());
        dataParceria.set(p.getDataParceria());
        fornece.set(p.getFornece());
    }

    public void salvar(){
        Parcerias p = getEntity();
        parceriasDAO.adicionarParcerias(p);
        setEntity(new Parcerias());
        atualizarListaView();
    }

    public void pesquisarParcerias(){
        listaView.clear();
        List<Parcerias> procurar = parceriasDAO.PesquisarParcerias(nomeParceria.get());
        listaView.addAll(procurar);
    }

    public void remover(int id){
        parceriasDAO.remover(id);
        atualizarListaView();
    }

    public void atualizarListaView(){
        listaView.clear();
        listaView.addAll(parceriasDAO.PesquisarParcerias(""));
    }
    public ObservableList<Parcerias> getListaView(){
        return listaView;
    }
}
