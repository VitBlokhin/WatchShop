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


    private Connection conn = null;
    private UserModel um;

    public Connection getConn() {
        return conn;
    }

    public void setUm(UserModel um) {
        this.um = um;
    }




} // class Session
