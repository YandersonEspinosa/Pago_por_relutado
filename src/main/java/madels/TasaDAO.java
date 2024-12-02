package madels;

import database.SQLDatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TasaDAO {
    private static Connection connection;
    private String currentPlan;
    private static boolean initDBConnection() {
        try {
            connection = SQLDatabaseManager.connect();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos");
        }
        return false;
    }

    public static boolean closeConnection() {
        try {
            SQLDatabaseManager.disconnect(connection);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al desconectar con la base de datos");
        }
        return false;
    }

    public boolean createTasa(TasaModel tasa) {
        boolean createOk = false;
        if (!initDBConnection()) {
            return false;
        }
        try {
            String query = "INSERT INTO tasa(porcentaje) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, tasa.getPorcentaje());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                createOk = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al crear la tasa " + e.getMessage());
        } finally {
            closeConnection();
        }
        return createOk;
    }

    public static ArrayList<TasaModel> getTasaList(String plan) {
        ArrayList<TasaModel> tasaList = new ArrayList<>();
        if (!initDBConnection()) {
            return null;
        }
        try {
            String query = "SELECT tasa.tasa_id, tasa.porcentaje, tasa.plan FROM tasa";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                TasaModel tasa = new TasaModel();
                tasa.setTasa_id(result.getInt("tasa_id"));
                tasa.setPorcentaje(result.getDouble("porcentaje"));
                tasa.setPlan(result.getString("plan"));
                if (tasa.getPorcentaje() >= 60) {
                    // Solo actualizamos el plan si el porcentaje es mayor o igual a 60
                    tasa.setPlan("A");
                }else
                    tasa.setPlan("B");
                plan = plan.toUpperCase();

                tasaList.add(tasa);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de tasas");
        } finally {
            closeConnection();
        }

        return tasaList;
    }
    public ArrayList<TasaModel> getTasasByPlan(String plan) {
        ArrayList<TasaModel> tasasPorPlan = new ArrayList<>();
        if (!initDBConnection()) {
            return null;
        }
        try {
            String query = "SELECT tasa.tasa_id, " +
                    "tasa.porcentaje, tasa.plan FROM tasa WHERE plan = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, plan);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                TasaModel tasa = new TasaModel();
                tasa.setTasa_id(result.getInt(tasa.getTasa_id()));
                tasa.setPorcentaje(result.getDouble(tasa.getPorcentaje()));
                tasa.setPlan(result.getString(tasa.getPlan()));
                tasasPorPlan.add(tasa);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la lista de tasas por plan");
        } finally {
            closeConnection();
        }
        return tasasPorPlan;
    }
    public boolean deleteTasa(int tasaId) throws SQLException {
        boolean deleteOk = false;
        if (!initDBConnection()) {
            return false;
        }
        try {
            String query = "DELETE FROM tasa\n" +
                            "WHERE tasa_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, tasaId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                deleteOk = true;
            }
        } finally {
            closeConnection();
        }
        return deleteOk;
    }

}
