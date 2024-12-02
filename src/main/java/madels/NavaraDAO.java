package madels;

import database.SQLDatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class NavaraDAO {

    private  Connection connection;

    private boolean initDBConnection(){
        try {
            connection = SQLDatabaseManager.connect();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos");
        }
        return false;
    }
    private boolean closeConnection(){
        try {
            SQLDatabaseManager.disconnect(connection);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al desconectar con la base de datos");
        }
        return false;
    }
    public boolean createNavera(NaveraModel navera){
        boolean createOK = false;

        if (!initDBConnection()){
          return false;
        }
        try {
            String query = "INSERT INTO navera(nombre,sueldo_fijo)\n" +
                            "VALUES \n" +
                            "(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, navera.getNavera_nombre());
            statement.setDouble(2, navera.getSueldo_fijo());
            int rowsAffect = statement.executeUpdate();
            if (rowsAffect > 0) {
                createOK = true;
            }
        }catch (SQLException e){
            System.err.println("Error al crear la navera");
        }finally {
            closeConnection();
        }
        return createOK;
    }
    public ArrayList<NaveraModel> getNaveraList(){
       ArrayList<NaveraModel> naveraList = new ArrayList<>();

        if (!initDBConnection()){
            return null;
        }
        try {
            String query = "SELECT navera.navera_id, navera.nombre,navera.sueldo_fijo\n" +
                           "FROM navera";
            PreparedStatement statement = connection.prepareStatement(query);
            var result = statement.executeQuery();
            while (result.next()) {
                var navera = new NaveraModel();
                navera.setNavera_id(result.getInt("navera_id"));
                navera.setNavera_nombre(result.getString("nombre"));
                navera.setSueldo_fijo(result.getDouble("sueldo_fijo"));
                naveraList.add(navera);
            }

        }catch (SQLException e){
            System.err.println("Error al obtener lista de las naveras"  );
        }finally {
            closeConnection();
        }
        return naveraList;
    }
    public ArrayList<NaveraModel> getNaveraListByNombre(String nombre){
        ArrayList<NaveraModel> naveraList = new ArrayList<>();

        if (!initDBConnection()){
            return null;
        }
        try {
            String query = "SELECT navera.navera_id, navera.nombre,navera.sueldo_fijo\n" +
                            "FROM navera\n" +
                            "WHERE LOWER(navera.nombre) LIKE LOWER(?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + nombre + "%");
            var result = statement.executeQuery();
            while (result.next()) {
                var navera = new NaveraModel();
                navera.setNavera_id(result.getInt("navera_id"));
                navera.setNavera_nombre(result.getString("nombre"));
                navera.setSueldo_fijo(result.getDouble("sueldo_fijo"));
                naveraList.add(navera);
            }

        }catch (SQLException e){
            System.err.println("Error al obtener lista de las naveras"  );
        }finally {
            closeConnection();
        }
        return naveraList;
    }
}
