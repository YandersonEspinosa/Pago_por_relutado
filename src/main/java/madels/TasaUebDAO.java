package madels;

import database.SQLDatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class TasaUebDAO {
    private Connection connection;

    private boolean initDBConnection() {
        try {
            connection = SQLDatabaseManager.connect();
            return true;
        }catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos");
        }
        return false;
    }
    private boolean closeConnection() {
        try {
            SQLDatabaseManager.disconnect(connection);
            return true;
        }catch (SQLException e){
            System.err.println("Error al desconectar con la base de datos");
        }
        return false;
    }
  public boolean createTasaUeb(TasaUebMadel tasaUeb){
        boolean createOK = false;
        if (!initDBConnection()) {
            return false;
        }
        try {
            String query ="INSERT INTO tasa_ueb(ueb_id,tasa_id)\n " +
                    "VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, tasaUeb.getUeb_id());
            statement.setInt(2, tasaUeb.getTasa_id());
            int arowAffectes = statement.executeUpdate();
            if (arowAffectes > 0) {
                createOK = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al crear la tasa ueb" );
        }finally {
            closeConnection();
        }
        return createOK;
  }
    public ArrayList<TasaUebMadel> getTasaUebList(){
        ArrayList<TasaUebMadel> tasaUebList = new ArrayList<>();

        if (!initDBConnection()) {
            return null;
        }
        try {
            String query = "SELECT u.ueb_id, t.tasa_id, u.nombre, u.tipo, t.porcentaje, t.plan\n" +
                    "FROM tasa_ueb tu\n" +
                    "INNER JOIN ueb u\n" +
                    "ON tu.ueb_id = u.ueb_id\n" +
                    "INNER JOIN tasa t\n" +
                    "ON tu.tasa_id = t.tasa_id;";
            PreparedStatement statement = connection.prepareStatement(query);
            var result = statement.executeQuery();

            while (result.next()) {
                double porcentaje = result.getDouble("porcentaje");
                String plan = (porcentaje >= 60) ? "A" : "B";

                TasaUebMadel tasaUeb = new TasaUebMadel(
                        result.getInt("ueb_id"),
                        result.getInt("tasa_id"),
                        result.getString("nombre"),
                        result.getString("tipo"),
                        porcentaje,
                        plan
                );
                tasaUebList.add(tasaUeb);
            }
        }catch (SQLException e){
            System.err.println("Error al obtener lista de Tasas de las UEBs");
        }finally {
            closeConnection();
        }
        return tasaUebList;
    }
    public ArrayList<TasaUebMadel> getTasaUebByUEB(int ueb_id){
        ArrayList<TasaUebMadel> tasaUebList = new ArrayList<>();

        if (!initDBConnection()) {
            return null;
        }
        try {
            String query = "SELECT u.ueb_id, t.tasa_id, u.nombre, u.tipo, t.porcentaje, t.plan\n" +
                    "FROM tasa_ueb tu\n" +
                    "INNER JOIN ueb u\n" +
                    "ON tu.ueb_id = u.ueb_id\n" +
                    "INNER JOIN tasa t\n" +
                    "ON tu.tasa_id = t.tasa_id\n" +
                    "WHERE tu.ueb_id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ueb_id);
            var result = statement.executeQuery();

            while (result.next()) {
                double porcentaje = result.getDouble("porcentaje");
                String plan = (porcentaje >= 60) ? "A" : "B";

                TasaUebMadel tasaUeb = new TasaUebMadel(
                        result.getInt("ueb_id"),
                        result.getInt("tasa_id"),
                        result.getString("nombre"),
                        result.getString("tipo"),
                        porcentaje,
                        plan
                );
                tasaUebList.add(tasaUeb);
            }
        }catch (SQLException e){
            System.err.println("Error al obtener lista de Tasas de las UEBs");
        }finally {
            closeConnection();
        }
        return tasaUebList;
    }

}
