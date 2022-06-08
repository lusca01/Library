package DAO;

import Entity.Sala;

import java.util.List;

public interface SalaDAO {
    void adicionarSala(Sala s);
    void remover (long id);
    List<Sala> PesquisarSala(String nome);
}
