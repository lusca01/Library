package DAO;
import Entity.Parcerias;
import java.util.List;

public interface ParceriasDAO {
    void adicionarParcerias(Parcerias p);
    void remover (int id);
    List<Parcerias> PesquisarParcerias(String nome);
}