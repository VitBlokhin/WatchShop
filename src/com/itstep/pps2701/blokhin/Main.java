package com.itstep.pps2701.blokhin;

import com.itstep.pps2701.blokhin.controllers.Session;
import com.itstep.pps2701.blokhin.data.User;
import com.itstep.pps2701.blokhin.models.UserModel;
import com.itstep.pps2701.blokhin.views.ConnectionWindow;
import com.itstep.pps2701.blokhin.views.LoginWindow;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
	// write your code here
        /*try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/watch_shop?autoReconnect=true&useSSL=false",
                "root",
                "")){


            System.out.println("Соединение установлено");

            UserModel um = new UserModel(conn);

            List<User> list = um.getUsers();

            for(User user: list) {
                System.out.println(user);
            }

        } catch(Exception ex) {
            System.out.println("Ошибка соединения с БД: " + ex.getMessage());
        } // catch*/

        Session session = new Session();

        SwingUtilities.invokeLater(()->{
            ConnectionWindow win = new ConnectionWindow("Подключение к БД", session);
        });

    } // main
} // class Main
