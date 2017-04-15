package com.itstep.pps2701.blokhin.system;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
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

    static public MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
            formatter.setPlaceholderCharacter('0');
        } catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка форматирования");
        }
        return formatter;
    }
}
