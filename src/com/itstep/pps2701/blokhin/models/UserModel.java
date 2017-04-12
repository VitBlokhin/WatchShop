package com.itstep.pps2701.blokhin.models;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vit on 07.04.2017.
 */
public class UserModel extends Model{

    public UserModel() {
        super();
    }

    public User getItemById(int id) throws SQLException{
        User user;
        String sqlRequest = "select * from users where `id` = \'" + id + "\';";
        PreparedStatement pstatement = connection.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        if(result.next()){
            user = parseItem(result);
        } else return null;
        pstatement.close();

        return user;
    } // getItemById

    public List<IData> getItemList() throws SQLException{
        List<IData> userList= new ArrayList<>();

        String sqlRequest = "select * from `users`;";
        PreparedStatement pstatement = connection.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        while(result.next()) {
            userList.add(parseItem(result));
        } // while
        pstatement.close();

        return userList;
    } // getItemList

    @Override
    public void updateItem(IData item) throws SQLException {
        User tmp = (User)item;
        int tmpStat = tmp.isStatus() ? 1 : 0;
        int tmpSup = tmp.isSuperuser() ? 1 : 0;
        String sqlUpdate = "update `users` "
                + " set `name` = \'" + tmp.getName()
                + "\', `email` = \'" + tmp.getEmail()
                + "\', `phone` = \'" + tmp.getPhone()
                + "\', `password` = \'" + tmp.getPassword()
                + "\', `status` = \'" + tmpStat
                + "\', `superuser` = \'" + tmpSup
                + "\' where `id` = \'" + tmp.getId() + "\';";
        PreparedStatement pstatement = connection.prepareStatement(sqlUpdate);
        pstatement.executeUpdate();
        pstatement.close();
    }

    @Override
    public void addItem(IData item) throws SQLException {
        User tmp = (User)item;
        int tmpStat = tmp.isStatus() ? 1 : 0;
        int tmpSup = tmp.isSuperuser() ? 1 : 0;
        String sqlInsert = "insert into `users` (`name`, `email`, `phone`, `password`, `status`, `superuser`)"
                + " values (\'" + tmp.getName()
                + "\', \'" + tmp.getEmail()
                + "\', \'" + tmp.getPhone()
                + "\', \'" + tmp.getPassword()
                + "\', \'" + tmpStat
                + "\', \'" + tmpSup
                + "\');";
        PreparedStatement pstatement = connection.prepareStatement(sqlInsert);
        pstatement.executeUpdate();
        pstatement.close();
    } // addItem


    public User getUserByNamePassword(String name, String password) throws SQLException{
        User user;
        String sqlRequest = "select * from `users` where `name` = \'" + name + "\' and `password` = \'" + password + "\';";
        PreparedStatement pstatement = connection.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        if(result.next()){
            user = parseItem(result);
            pstatement.close();
            return user;
        } else return null;
    } // getUserByNamePassword

    protected final User parseItem(ResultSet result) throws SQLException{
        return new User(result.getInt("id"),
                result.getString("name"),
                result.getString("email"),
                result.getString("phone"),
                result.getString("password"),
                result.getBoolean("status"),
                result.getBoolean("superuser"));
    } // parseItem

} // class UserModel
