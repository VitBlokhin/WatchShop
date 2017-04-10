package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.User;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Vit on 07.04.2017.
 */
public class Session {
    private String connectionAddress = "localhost";
    private String connectionBase = "watch_shop";
    private int connectionPort = 3306;


    private Connection conn = null;
    private User currentUser = null;

    public Session() {
    }

    public boolean login(String name, String password){

        return false;
    } // login

    public void logout(){
        currentUser = null;
    } // logout

    // TODO: переделать метод под один строковый аргумент
    // сделать отдельный метод для формирования строки адреса подключения
    // начать делать GUI - окно ввода данных бд, далее логин, далее - основное окно программы
    // соответственно, привязать всё к логике работы сессии
    public boolean connect(String url, String username, String password){
        boolean code = false;
        try{
            conn = DriverManager.getConnection(
                    getUrl(),
                    username,
                    password);
            code = true;
        } catch(Exception ex) {
            code = false;
        } // catch
        finally{
            return code;
        } // finally
    } // connect

    private String getUrl(){
        return "jdbc:mysql://"
                + connectionAddress + ":" + connectionPort + "/" + connectionBase
                + "?autoReconnect=true&useSSL=false";
    } // getUrl

    public void disconnect(){
        conn = null;
    } // disconnect
} // class Session
