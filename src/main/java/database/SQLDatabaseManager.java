package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseManager {

    private static final String JDBC_URL ="jdbc:postgresql://localhost:5432/CanDB";

    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection connect () throws SQLException {
        try{
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            if (connection != null) {
                System.out.printf("Conexion exitosa a la base de datos Postgres.");
            }
            return connection;
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("Error al conectar con la base de datos Postgres.");
        }
    }
    public static void disconnect (Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
