package ImplementsDAO;
import Entity.Locacao;
import DAO.LocacaoDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LocacaoDAOImpl implements LocacaoDAO {
    private static final String DBURL = "jdbc:mariadb://localhost:3306/livraria";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";

    public LocacaoDAOImpl(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void adicionarLocacao(Locacao l) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "INSERT INTO locacao (idLocacao, nomeSala, dataLocacao, horaLocacao, representanteLocacao)" +
                    " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, l.getIdLocacao());
            stmt.setString(2, l.getSalaLocacao());
            stmt.setDate(3, java.sql.Date.valueOf(l.getDataLocacao()));
            stmt.setString(4, l.getHoraLocacao());
            stmt.setString(5, l.getResponsavelLocacao());
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
            String sql = "DELETE FROM locacao WHERE idLocacao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Locacao> PesquisarLocacao(String sala) {
        List<Locacao> procurar = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM locacao WHERE nomeSala like '%" + sala + "%'";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Locacao loca = new Locacao();
                loca.setIdLocacao( rs.getInt("idLocacao"));
                loca.setSalaLocacao( rs.getString("nomeSala"));
                loca.setDataLocacao( rs.getDate("dataLocacao").toLocalDate());
                loca.setHoraLocacao( rs.getString("horaLocacao"));
                loca.setResponsavelLocacao( rs.getString("representanteLocacao"));
                procurar.add(loca);
            }
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return procurar;
    }
}
