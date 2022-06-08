package DAO;
import Entity.Eventos;
import java.util.List;

public interface EventosDAO {
    void adicionarEventos(Eventos e);
    void remover (int e);
    List<Eventos> PesquisarEvento(String tipo);
}
