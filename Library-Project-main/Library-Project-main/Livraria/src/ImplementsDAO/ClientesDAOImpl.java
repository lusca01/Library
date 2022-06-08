package ImplementsDAO;
import DAO.ClientesDAO;
import Entity.Clientes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAOImpl implements ClientesDAO {
    private static final String DBURL = "jdbc:mariadb://localhost:3306/livraria";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";

    public ClientesDAOImpl(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void adicionarClientes(Clientes c) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "INSERT INTO Clientes (idCliente, nomeCliente, docRg, dataNascimento, dataAfiliacao, telefone, email)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getIdClientes());
            stmt.setString(2, c.getNomeCliente());
            stmt.setString(3, c.getDocRG());
            stmt.setDate(4, java.sql.Date.valueOf(c.getDataNascimento()));
            stmt.setDate(5, java.sql.Date.valueOf(c.getDataNascimento()));
            stmt.setString(6, c.getTelefone());
            stmt.setString(7, c.getEmail());
            stmt.executeUpdate();
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void remover(int id) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "DELETE FROM clientes WHERE idCliente = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Clientes> PesquisarClientes(String nome) {
        List<Clientes> procurar = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT idCliente, nomeCliente, docRg, telefone, email, dataNascimento, dataAfiliacao FROM clientes WHERE nomeCliente like '%" + nome + "%'";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Clientes cl = new Clientes();
                cl.setIdClientes( rs.getInt("idCliente"));
                cl.setNomeCliente( rs.getString("nomeCliente") );
                cl.setDocRG( rs.getString("docRg"));
                cl.setTelefone( rs.getString("telefone"));
                cl.setEmail( rs.getString("email"));
                cl.setDataNascimento( rs.getDate("dataNascimento").toLocalDate() );
                cl.setDataAfiliacao( rs.getDate("dataAfiliacao").toLocalDate() );
                procurar.add(cl);
            }
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return procurar;
    }
}
