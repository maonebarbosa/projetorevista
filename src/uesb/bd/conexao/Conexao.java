
package uesb.bd.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Maone
 */
public class Conexao {
    public static String status = "Not connected";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            String serverName = "localhost";
            String mydatabase = "projetobd";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "root";
            String password = "123456";

            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                status = "Sucessfully connected";
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Wasn't possible connect.\n" + e);
        } finally {
            return connection;
        }
    }

    public static String statusConnection() {
        return status;
    }

    public static boolean closeConnection(Connection connection) {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Wasn't possible close connection.\n" + e);
            return false;
        }
    }
    
    public Connection restartConnection(Connection connection) {
        closeConnection(connection);
        return Conexao.getConnection();
    }
}