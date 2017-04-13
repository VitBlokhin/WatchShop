package com.itstep.pps2701.blokhin.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Vit on 13.04.2017.
 */
public class Utils {
    private static String connectionAddress = "localhost";
    private static String connectionBase = "watch_shop";
    private static int connectionPort = 3306;

    private static Connection connection;

    public static void connect(String username, String password) throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://"
                        + connectionAddress + ":" + connectionPort + "/" + connectionBase
                        + "?autoReconnect=true&useSSL=false",
                username,
                password);
    }

    public static void disconnect() throws SQLException{
        connection.close();
    }

    public static Connection getConnection(){
        return connection;
    }
}
