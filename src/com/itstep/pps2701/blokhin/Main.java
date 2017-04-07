package com.itstep.pps2701.blokhin;

import com.itstep.pps2701.blokhin.data.User;
import com.itstep.pps2701.blokhin.models.UserModel;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
	// write your code here
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/watch_shop?autoReconnect=true&useSSL=false",
                "root",
                "")){
            System.out.println("Соединение установлено");


            UserModel um = new UserModel(conn);

            User user = um.getUserById(1);

            System.out.println(user);

        } catch(Exception ex) {
            System.out.println("Ошибка соединения с БД: " + ex.getMessage());
        } // catch
    } // main
} // class Main
