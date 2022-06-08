package DAO;

import Entity.Clientes;
import java.util.List;

public interface ClientesDAO {

    void adicionarClientes(Clientes c);
    void remover (int c);
    List<Clientes> PesquisarClientes(String tipo);
}
