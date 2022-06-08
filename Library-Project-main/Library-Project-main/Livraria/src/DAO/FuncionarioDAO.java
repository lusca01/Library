package DAO;
import Entity.Funcionario;
import java.util.List;

public interface FuncionarioDAO {
    void adicionarFuncionario(Funcionario f);
    void remover (int id);
    List<Funcionario> PesquisarFuncionario (String nome);
}
