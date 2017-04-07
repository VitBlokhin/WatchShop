package com.itstep.pps2701.blokhin.system;

import com.itstep.pps2701.blokhin.data.User;

import java.sql.Connection;

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
    }

    public void logout(){
        currentUser = null;
    }

    public void disconnect(){
        conn = null;
    }
} // class Session
