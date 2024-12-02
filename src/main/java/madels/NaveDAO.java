package madels;

import database.SQLDatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NaveDAO {
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
    private boolean closeDBConnection() {
        try {
            SQLDatabaseManager.disconnect(connection);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al desconectar con la base de datos");
        }
        return false;
    }
    public boolean createNave(NaveModel nave) throws SQLException {
        boolean crateOk = false;
        if (!initDBConnection()) {
            return false;
        }
        try {
            String query = "INSERT INTO  nave(ueb_id)\n" +
                            "VALUES\n" +
                            "(?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, nave.getUeb_id());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                crateOk = true;
            }
        } finally {
            closeDBConnection();
        }
        return crateOk;
    }
    public ArrayList<NaveModel> getNaveList(){
        ArrayList<NaveModel> naveList = new ArrayList<>();

        if (!initDBConnection()) {
            return null;
        }
        try {
            String query = "SELECT u.ueb_id, u.nombre,u.tipo\n" +
                    "FROM nave n\n" +
                    "INNER JOIN ueb u\n" +
                    "ON n.ueb_id = u.ueb_id;";
            PreparedStatement statement = connection.prepareStatement(query);
            var result = statement.executeQuery();

            while (result.next()) {
                NaveModel nave = new NaveModel(
                        result.getInt("ueb_id"),
                        result.getString("nombre"),
                        result.getString("tipo")
                );
                naveList.add(nave);
            }

        }catch (SQLException e){
            System.err.println("Error al obtener lista Naves");
        }finally {
            closeDBConnection();
        }
        return naveList;
    }
    public ArrayList<NaveModel> getNaveListUEB(String nombre) {
        ArrayList<NaveModel> naveList = new ArrayList<>();

        if (!initDBConnection()) {
            return null;
        }

        try {
            String query = "SELECT n.nave_id, u.nombre, u.tipo " +
                    "FROM nave n " +
                    "INNER JOIN ueb u " +
                    "ON n.ueb_id = u.ueb_id " +
                    "WHERE LOWER(u.nombre) LIKE LOWER(?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + nombre + "%"); // Establecer el valor del parámetro
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                NaveModel nave = new NaveModel(
                        result.getInt("nave_id"),
                        result.getString("nombre"),
                        result.getString("tipo")
                );
                naveList.add(nave);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener lista Naves " + e.getMessage());
        } finally {
            closeDBConnection();
        }

        return naveList;
    }
}
