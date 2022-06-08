package ImplementsDAO;
import Entity.Exemplar;
import DAO.ExemplarDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ExemplarDAOImpl implements ExemplarDAO {

    private static final String DBURL = "jdbc:mariadb://localhost:3306/livraria";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";


    public ExemplarDAOImpl() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adicionarExemplar(Exemplar exemplar) {
        try {
            Connection con = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
            String sql = "INSERT INTO exemplar (idexemplar,titulo,autor,npaginas,anopublicacao) VALUES (?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,exemplar.getId());
            statement.setString(2,exemplar.getTitulo());
            statement.setString(3,exemplar.getAutor());
            statement.setInt(4,exemplar.getnPaginas());
            statement.setDate(5, java.sql.Date.valueOf(exemplar.getAnoPublic()));
            statement.executeUpdate();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

//    public void atualiza(Exemplar exemplar) {
//        try {
//            Connection con = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
//            String sql = "UPDATE exemplares SET idexemplar = ? titulo = ?, autor = ?, npaginas = ?,anopublicacao = ? WHERE ID = ?";
//            PreparedStatement statement = con.prepareStatement(sql);
//            statement.setInt(1,exemplar.getId());
//            statement.setString(2,exemplar.getTitulo());
//            statement.setString(3,exemplar.getAutor());
//            statement.setInt(3,exemplar.getnPaginas());
//            statement.setDate(4,java.sql.Date.valueOf(exemplar.getAnoPublic()));
//            statement.setInt(5,exemplar.getId());
//            statement.executeUpdate();
//            con.close();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void remover(int id) {
        try {
            Connection con = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
            String sql = "DELETE FROM exemplar WHERE idexemplar = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            con.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<Exemplar> PesquisarExemplar(String titulo) {
        List<Exemplar> listasaida = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM exemplar WHERE titulo like '%" + titulo + "%'";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Exemplar ex = new Exemplar();
                ex.setId( rs.getInt("idexemplar"));
                ex.setTitulo( rs.getString("titulo"));
                ex.setAutor( rs.getString("autor"));
                ex.setnPaginas( rs.getInt("npaginas"));
                ex.setAnoPublic( rs.getDate("anopublicacao").toLocalDate());
                listasaida.add(ex);
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return listasaida;
    }


}

