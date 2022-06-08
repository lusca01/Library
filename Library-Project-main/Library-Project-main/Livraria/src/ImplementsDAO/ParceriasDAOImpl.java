package ImplementsDAO;
import DAO.ParceriasDAO;
import Entity.Parcerias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ParceriasDAOImpl implements ParceriasDAO {
    private static final String DBURL = "jdbc:mariadb://localhost:3306/livraria";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";

    public ParceriasDAOImpl(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void adicionarParcerias(Parcerias p) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "INSERT INTO parceria (idParceria, tipoParceria, nomeParceria, dataParceria, fornece)" +
                    " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getIdParceria());
            stmt.setString(2, p.getTipoParceria());
            stmt.setString(3, p.getNomeParceria());
            stmt.setDate(4, java.sql.Date.valueOf(p.getDataParceria()));
            stmt.setString(5, p.getFornece());
            stmt.executeUpdate();

            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void remover(int id) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "DELETE FROM parceria WHERE idParceria = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Parcerias> PesquisarParcerias(String nome) {
        List<Parcerias> procurar = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM parceria WHERE nomeParceria like '%" + nome + "%'";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Parcerias par = new Parcerias();
                par.setIdParceria( rs.getInt("idParceria"));
                par.setTipoParceria( rs.getString("tipoParceria"));
                par.setNomeParceria( rs.getString("nomeParceria"));
                par.setDataParceria( rs.getDate("dataParceria").toLocalDate());
                par.setFornece(rs.getString("fornece"));
                procurar.add(par);
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return procurar;
    }
}
