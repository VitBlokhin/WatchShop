package com.itstep.pps2701.blokhin.models;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.WatchType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public class WatchTypeModel  extends Model {



    @Override
    public IData getItemById(int id) throws SQLException {
        WatchType watchType;
        String sqlRequest = "select * from `watch_types` where `id` = \'" + id + "\';";
        PreparedStatement pstatement = connection.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        if(result.next()){
            watchType = parseItem(result);
        } else return null;
        pstatement.close();

        return watchType;
    } // getItemById

    @Override
    public List<IData> getItemList() throws SQLException {
        List<IData> watchTypeList = new ArrayList<>();

        String sqlRequest = "select * from `watch_types`;";
        PreparedStatement pstatement = connection.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        while(result.next()) {
            watchTypeList.add(parseItem(result));
        } // while
        pstatement.close();

        return watchTypeList;
    } // getItemList

    @Override
    public void updateItem(IData item) throws SQLException {
        WatchType tmp = (WatchType)item;
        String sqlUpdate = "update `watch_types` "
                + " set `type_name` = \'" + tmp.getTypename()
                + "\' where `id` = \'" + tmp.getId() + "\';";
        PreparedStatement pstatement = connection.prepareStatement(sqlUpdate);
        pstatement.executeUpdate();
        pstatement.close();
    } // updateItem

    @Override
    public void addItem(IData item) throws SQLException {
        WatchType tmp = (WatchType)item;
        String sqlInsert = "insert into `watch_types` (`type_name`)"
                + " values (\'" + tmp.getTypename()
                + "\');";
        PreparedStatement pstatement = connection.prepareStatement(sqlInsert);
        pstatement.executeUpdate();
        pstatement.close();
    } // addItem

    @Override
    protected WatchType parseItem(ResultSet result) throws SQLException {
        return new WatchType(result.getInt("id"),
                result.getString("type_name"));
    } // parseItem
}
