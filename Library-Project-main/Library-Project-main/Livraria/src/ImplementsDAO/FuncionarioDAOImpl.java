package ImplementsDAO;
import Entity.Funcionario;
import DAO.FuncionarioDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAOImpl implements FuncionarioDAO {
    private static final String DBURL = "jdbc:mariadb://localhost:3306/livraria";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";

    public FuncionarioDAOImpl(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void adicionarFuncionario(Funcionario f) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "INSERT INTO funcionario (idFuncionario, nomeFuncionario, funcaoFuncionario, dataAdmissao, salarioFuncionario)" +
                    " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, f.getIdFuncionario());
            stmt.setString(2, f.getNomeFuncionario());
            stmt.setString(3, f.getFuncaoFuncionario());
            stmt.setDate(4, java.sql.Date.valueOf(f.getDataAdmissao()));
            stmt.setDouble(5, f.getSalarioFuncionario());
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
            String sql = "DELETE FROM funcionario WHERE idFuncionario = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Funcionario> PesquisarFuncionario(String nome) {
        List<Funcionario> procurar = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM funcionario WHERE nomeFuncionario like '%" + nome + "%'";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Funcionario fun = new Funcionario();
                fun.setIdFuncionario( rs.getInt("idFuncionario"));
                fun.setNomeFuncionario( rs.getString("nomeFuncionario"));
                fun.setFuncaoFuncionario( rs.getString("funcaoFuncionario"));
                fun.setDataAdmissao( rs.getDate("dataAdmissao").toLocalDate());
                fun.setSalarioFuncionario( rs.getDouble("salarioFuncionario"));
                procurar.add(fun);
            }
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return procurar;
    }
}
