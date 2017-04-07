package com.itstep.pps2701.blokhin;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
	// write your code here
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/watch_shop?autoReconnect=true&useSSL=false",
                "root",
                "")){
            System.out.println("Соединение установлено");

        } catch(Exception ex) {
            System.out.println("Ошибка соединения с БД: " + ex.getMessage());
        } // catch
    } // main
} // class Main
