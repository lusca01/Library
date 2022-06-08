package Control;
import Entity.Funcionario;
import ImplementsDAO.FuncionarioDAOImpl;
import DAO.FuncionarioDAO;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class FuncionarioControl {
    public IntegerProperty idFuncionario = new SimpleIntegerProperty(0);
    public StringProperty nomeFuncionario = new SimpleStringProperty("");
    public StringProperty funcaoFuncionario = new SimpleStringProperty("");
    public ObjectProperty dataAdmissao = new SimpleObjectProperty();
    public DoubleProperty salarioFuncionario = new SimpleDoubleProperty(0);

    private ObservableList<Funcionario> listaView = FXCollections.observableArrayList();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();

    public Funcionario getEntity(){
        Funcionario f = new Funcionario();
        f.setIdFuncionario(idFuncionario.get());
        f.setNomeFuncionario(nomeFuncionario.get());
        f.setFuncaoFuncionario(funcaoFuncionario.get());
        f.setDataAdmissao((LocalDate)dataAdmissao.get() );
        f.setSalarioFuncionario(salarioFuncionario.get());
        return f;
    }

    public void setEntity(Funcionario f){
        idFuncionario.set(f.getIdFuncionario());
        nomeFuncionario.set(f.getNomeFuncionario());
        funcaoFuncionario.set(f.getFuncaoFuncionario());
        dataAdmissao.set(f.getDataAdmissao());
        salarioFuncionario.set(f.getSalarioFuncionario());
    }

    public void salvar(){
        Funcionario f = getEntity();
        funcionarioDAO.adicionarFuncionario(f);
        setEntity(new Funcionario());
        atualizarListaView();
    }

    public void pesquisarFuncionario(){
        listaView.clear();
        List<Funcionario> procurar = funcionarioDAO.PesquisarFuncionario(nomeFuncionario.get());
        listaView.addAll(procurar);
    }

    public void remover (int id){
        funcionarioDAO.remover(id);
        atualizarListaView();
    }

    public void atualizarListaView(){
        listaView.clear();
        listaView.addAll(funcionarioDAO.PesquisarFuncionario(""));
    }

    public ObservableList<Funcionario> getListaView(){
        return listaView;
    }

}
