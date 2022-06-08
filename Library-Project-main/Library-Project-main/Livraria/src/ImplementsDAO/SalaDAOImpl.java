package ImplementsDAO;
import DAO.SalaDAO;
import Entity.Sala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalaDAOImpl implements SalaDAO {
    private static final String DBURL = "jdbc:mariadb://localhost:3306/livraria";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";

    public SalaDAOImpl(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void adicionarSala(Sala s) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "INSERT INTO salas (idSala, nomeSala, tamanhoSala, tipoSala)" +
                    " VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, s.getIdSala());
            stmt.setString(2, s.getNomeSala());
            stmt.setString(3, s.getTamanhoSala());
            stmt.setString(4, s.getTipoSala());
            stmt.executeUpdate();

            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void remover(long id) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "DELETE FROM salas WHERE idSala = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Sala> PesquisarSala(String nome) {
        List<Sala> procurar = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM salas WHERE nomeSala like '%" + nome + "%'";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Sala sal = new Sala();
                sal.setIdSala( rs.getLong("idSala"));
                sal.setNomeSala( rs.getString("nomeSala"));
                sal.setTamanhoSala( rs.getString("tamanhoSala"));
                sal.setTipoSala( rs.getString("tipoSala"));
                procurar.add(sal);
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return procurar;
    }
}
