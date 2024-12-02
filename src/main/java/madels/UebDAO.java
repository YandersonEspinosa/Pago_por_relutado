package madels;

import database.SQLDatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class UebDAO {

    private Connection connection;

    private boolean initDBConnection() {
        try {
            connection = SQLDatabaseManager.connect();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos");
        }
        return false;
    }
    public boolean closeConnection() {
        try {
            SQLDatabaseManager.disconnect(connection);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al desconectar con la base de datos");
        }
        return false;
    }

    public boolean createUEB(UebModel ueb){
        boolean createdOk = false;

        if (!initDBConnection()) {
            return false;
        }
        try {
            String query = "INSERT INTO ueb(nombre, tipo)\n" +
                            "VALUES \n" +
                            "(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ueb.getNombre());
            statement.setString(2, ueb.getTipo());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                createdOk = true;
            }
        }catch (SQLException e){
            System.err.println("Error al insertar UEB");
        }finally {
            closeConnection();
        }
        return createdOk;
    }

    public ArrayList<UebModel> getUebList(){
        ArrayList<UebModel> uebList = new ArrayList<>();

        if (!initDBConnection()) {
            return null;
        }
        try {
            String query = "SELECT ueb.ueb_id, ueb.nombre, ueb.tipo\n" +
                           "FROM ueb;";
            PreparedStatement statement = connection.prepareStatement(query);
            var resule = statement.executeQuery();

            while (resule.next()) {
                var ueb = new UebModel();
                ueb.setUeb_id(resule.getInt("ueb_id"));
                ueb.setNombre(resule.getString("nombre"));
                ueb.setTipo(resule.getString("tipo"));
                uebList.add(ueb);
            }

        }catch (SQLException e){
            System.err.println("Error al obtener lista UEBs");
        }finally {
            closeConnection();
        }
        return uebList;
    }
    public ArrayList<UebModel> getUebByProposito(String proposito){
        ArrayList<UebModel> uebList = new ArrayList<>();

        if (!initDBConnection()) {
            return null;
        }
        try {
            String query = "SELECT ueb.ueb_id, ueb.nombre, ueb.tipo \n" +
                           "FROM ueb\n" +
                           "WHERE LOWER(tipo) LIKE LOWER(?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%"+ proposito + "%");
            var resule = statement.executeQuery();

            while (resule.next()) {
                var ueb = new UebModel();
                ueb.setUeb_id(resule.getInt("ueb_id"));
                ueb.setNombre(resule.getString("nombre"));
                ueb.setTipo(resule.getString("tipo"));
                uebList.add(ueb);
            }

        }catch (SQLException e){
            System.err.println("Error al obtener lista UEBs");
        }finally {
            closeConnection();
        }
        return uebList;
    }
    public boolean deleteUEB(int ueb_id) throws SQLException {
        boolean deletedOk = false;

        if (!initDBConnection()) {
            return false;
        }
        try {
            String query = "DELETE FROM ueb\n" +
                            " WHERE ueb_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ueb_id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                deletedOk = true;
            }
        } finally {
            closeConnection();
        }
        return deletedOk;
    }

}
