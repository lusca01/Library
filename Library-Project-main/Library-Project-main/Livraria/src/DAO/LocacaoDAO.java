package DAO;
import Entity.Locacao;
import java.util.List;

public interface LocacaoDAO {
    void adicionarLocacao(Locacao l);
    void remover (int id);
    List<Locacao> PesquisarLocacao (String sala);
}
