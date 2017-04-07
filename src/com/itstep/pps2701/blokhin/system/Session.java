package com.itstep.pps2701.blokhin.system;

import com.itstep.pps2701.blokhin.data.User;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Vit on 07.04.2017.
 */
public class Session {
    private String connectionAddress = "localhost";
    private int connectionPort = 3306;
    private String connectionBase = "watch_shop";

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
    public boolean connect(String addr, int port, String dbname){
        boolean code = false;
        try{
            conn = DriverManager.getConnection(
                    "jdbc:mysql://"
                            + addr + ":" + port + "/" + dbname
                            + "?autoReconnect=true&useSSL=false",
                    "root",
                    "");
            code = true;
        } catch(Exception ex) {
            code = false;
        } // catch
        finally{
            return code;
        } // finally
    } // connect

    public void disconnect(){
        conn = null;
    } // disconnect
} // class Session
