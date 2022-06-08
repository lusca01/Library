package ImplementsDAO;
import DAO.EventosDAO;
import Entity.Eventos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EventosDAOImpl implements EventosDAO {
    private static final String DBURL = "jdbc:mariadb://localhost:3306/livraria";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";

    public EventosDAOImpl(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void adicionarEventos(Eventos e) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "INSERT INTO eventos (idEvento, tipoEvento, dataEvento, representante, participacao, publicoAlvo)" +
                    " VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, e.getIdEvento());
            stmt.setString(2, e.getTipoEvento());
            stmt.setDate(3, java.sql.Date.valueOf(e.getDataEvento()));
            stmt.setString(4, e.getRepresentante());
            stmt.setString(5,e.getParticipacao());
            stmt.setString(6, e.getPublicoAlvo());
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
            String sql = "DELETE FROM eventos WHERE idEvento = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Eventos> PesquisarEvento(String tipo) {
        List<Eventos> procurar = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM eventos WHERE tipoEvento like '%" + tipo + "%'";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Eventos eve = new Eventos();
                eve.setIdEvento( rs.getInt("idEvento") );
                eve.setTipoEvento( rs.getString("tipoEvento") );
                eve.setDataEvento( rs.getDate("dataEvento").toLocalDate() );
                eve.setRepresentante( rs.getString("representante") );
                eve.setParticipacao( rs.getString("participacao") );
                eve.setPublicoAlvo( rs.getString("publicoAlvo") );
                procurar.add(eve);
            }
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return procurar;
    }
}
