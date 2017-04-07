package com.itstep.pps2701.blokhin.models;

import com.itstep.pps2701.blokhin.data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vit on 07.04.2017.
 */
public class UserModel {
    private Connection conn;

    public UserModel(Connection conn) {
        this.conn = conn;
    }

    public User getUserById(int id) throws SQLException{
        User user;
        String sqlRequest = "select * from users where `id` = " + id;
        PreparedStatement pstatement = conn.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        if(result.next()){
            user = new User(result.getInt("id"),
                    result.getString("name"),
                    result.getString("address"),
                    result.getString("phone"),
                    result.getString("password"),
                    result.getBoolean("status"),
                    result.getBoolean("superuser"));
        } else return null;
        pstatement.close();

        return user;
    } // getUserById

    public List<User> getUsers() throws SQLException{
        List<User> userList= new ArrayList<>();

        String sqlRequest = "select * from users";
        PreparedStatement pstatement = conn.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        while(result.next()) {
            userList.add(new User(result.getInt("id"),
                    result.getString("name"),
                    result.getString("address"),
                    result.getString("phone"),
                    result.getString("password"),
                    result.getBoolean("status"),
                    result.getBoolean("superuser")));
        } // while
        pstatement.close();

        return userList;
    } // getUsers

} // class UserModel
