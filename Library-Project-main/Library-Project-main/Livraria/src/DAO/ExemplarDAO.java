package DAO;
import Entity.Exemplar;
import java.util.List;

public interface ExemplarDAO {
    void adicionarExemplar(Exemplar e);
    void remover (int id);
    List<Exemplar> PesquisarExemplar(String titulo);
}
