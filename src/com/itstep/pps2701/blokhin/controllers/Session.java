package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.User;
import com.itstep.pps2701.blokhin.models.UserModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Vit on 07.04.2017.
 */
public class Session {
    private String connectionAddress = "localhost";
    private String connectionBase = "watch_shop";
    private int connectionPort = 3306;

    private Connection conn = null;
    private User currentUser = null;
    private UserModel um;

    public Connection getConn() {
        return conn;
    }

    public void setUm(UserModel um) {
        this.um = um;

    }

    public Session() {

    }

    public boolean login(String name, String password){


        return false;
    } // login

    public void logout(){
        currentUser = null;
    } // logout

    public void connect(String username, String password) throws SQLException{
        conn = DriverManager.getConnection(
                "jdbc:mysql://"
                        + connectionAddress + ":" + connectionPort + "/" + connectionBase
                        + "?autoReconnect=true&useSSL=false",
                username,
                password);
    } // connect

    public void disconnect(){
        currentUser = null;
        conn = null;
    } // disconnect
} // class Session
