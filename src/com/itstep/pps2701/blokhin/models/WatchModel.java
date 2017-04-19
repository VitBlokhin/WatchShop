package com.itstep.pps2701.blokhin.models;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.Watch;
import com.itstep.pps2701.blokhin.data.WatchProducer;
import com.itstep.pps2701.blokhin.data.WatchType;
import com.itstep.pps2701.blokhin.system.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public class WatchModel extends Model {


    public WatchModel() {
        super(Utils.getConnection());
    }

    @Override
    public IData getItemById(int id) throws SQLException {
        Watch watch;
        String sqlRequest = "select * from `watches` where `id` = \'" + id + "\';";
        PreparedStatement pstatement = conn.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        if(result.next()){
            watch = parseItem(result);
        } else return null;
        pstatement.close();

        return watch;
    } // getItemById

    @Override
    public List<IData> getItemList() throws SQLException {
        List<IData> watchList = new ArrayList<>();

        String sqlRequest = "select * from `watches`;";
        PreparedStatement pstatement = conn.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        while(result.next()) {
            watchList.add(parseItem(result));
        } // while
        pstatement.close();

        return watchList;
    } // getItemList

    @Override
    public void updateItem(IData item) throws SQLException {
        Watch tmp = (Watch)item;
        int visible = tmp.isVisible() ? 1 : 0;
        String sqlUpdate = "update `watches` "
                + " set `mark` = \'" + tmp.getMark()
                + "\', `price` = \'" + tmp.getPrice()
                + "\', `quantity` = \'" + tmp.getQuantity()
                + "\', `visible` = \'" + visible
                + "\', `producer_id` = \'" + tmp.getProducerId()
                + "\', `type_id` = \'" + tmp.getTypeId()
                + "\' where `id` = \'" + tmp.getId() + "\';";
        PreparedStatement pstatement = conn.prepareStatement(sqlUpdate);
        pstatement.executeUpdate();
        pstatement.close();
    } // updateItem

    @Override
    public void addItem(IData item) throws SQLException {
        Watch tmp = (Watch)item;
        int visible = tmp.isVisible() ? 1 : 0;
        String sqlInsert = "insert into `watches` (`mark`, `price`, `quantity`, `visible`, `producer_id`, `type_id`)"
                + " values (\'" + tmp.getMark()
                + "\', \'" + tmp.getPrice()
                + "\', \'" + tmp.getQuantity()
                + "\', \'" + visible
                + "\', \'" + tmp.getProducerId()
                + "\', \'" + tmp.getTypeId()
                + "\');";
        PreparedStatement pstatement = conn.prepareStatement(sqlInsert);
        pstatement.executeUpdate();
        pstatement.close();
    } // addItem


    // Вывести марки заданного типа часов
    public List<IData> query1(WatchType type) throws SQLException {
        List<IData> watchList = new ArrayList<>();

        String sqlRequest = "SELECT w.* FROM watches as w " +
                "join watch_types as wt on w.type_id = wt.id " +
                "where wt.type_name = '" + type.getTypename() + "'";
        PreparedStatement pstatement = conn.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        while(result.next()) {
            watchList.add(parseItem(result));
        } // while
        pstatement.close();

        return watchList;
    } // query1

    // Вывести информацию о механических часах, цена на которые не превышает заданную
    // ---- здесь небольшое изменение - добавлен выбор типа часов
    public List<IData> query2(double price, WatchType type) throws SQLException {
        List<IData> watchList = new ArrayList<>();

        String sqlRequest = "SELECT w.* FROM watches as w " +
                "join watch_types as wt on w.type_id = wt.id " +
                "where w.price <= " + price + " and wt.type_name = \'" + type.getTypename() + "\';";
        PreparedStatement pstatement = conn.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        while(result.next()) {
            watchList.add(parseItem(result));
        } // while
        pstatement.close();

        return watchList;
    } // query2

    // Вывести марки часов, изготовленных в заданной стране
    public List<IData> query3(String country) throws SQLException {
        List<IData> watchList = new ArrayList<>();

        String sqlRequest = "SELECT w.* FROM watches as w " +
                "join producers as p on w.producer_id = p.id " +
                "where p.country = \'" + country + "\'";
        PreparedStatement pstatement = conn.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        while(result.next()) {
            watchList.add(parseItem(result));
        } // while
        pstatement.close();

        return watchList;
    } // query3


    @Override
    final protected Watch parseItem(ResultSet result) throws SQLException {
        return new Watch(result.getInt("id"),
                result.getString("mark"),
                result.getDouble("price"),
                result.getInt("quantity"),
                result.getBoolean("visible"),
                result.getInt("producer_id"),
                result.getInt("type_id"));
    } // parseItem
}
