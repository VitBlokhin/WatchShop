package com.itstep.pps2701.blokhin.models;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.WatchProducer;
import com.itstep.pps2701.blokhin.system.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public class ProducerModel  extends Model {

    public ProducerModel() {
        super(Utils.getConnection());
    }

    @Override
    public WatchProducer getItemById(int id) throws SQLException {
        WatchProducer watchProducer;
        String sqlRequest = "select * from `producers` where `id` = \'" + id + "\';";
        PreparedStatement pstatement = conn.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        if(result.next()){
            watchProducer = parseItem(result);
        } else return null;
        pstatement.close();

        return watchProducer;
    } // getItemById

    @Override
    public List<IData> getItemList() throws SQLException {
        List<IData> producerList = new ArrayList<>();

        String sqlRequest = "select * from `producers`;";
        PreparedStatement pstatement = conn.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        while(result.next()) {
            producerList.add(parseItem(result));
        } // while
        pstatement.close();

        return producerList;
    } // getItemList

    @Override
    public void updateItem(IData item) throws SQLException {
        WatchProducer tmp = (WatchProducer)item;
        String sqlUpdate = "update `producers` "
                + " set `name` = \'" + tmp.getName()
                + "\', `country` = \'" + tmp.getCountry()
                + "\' where `id` = \'" + tmp.getId() + "\';";
        PreparedStatement pstatement = conn.prepareStatement(sqlUpdate);
        pstatement.executeUpdate();
        pstatement.close();
    } // updateItem

    @Override
    public void addItem(IData item) throws SQLException {
        WatchProducer tmp = (WatchProducer)item;
        String sqlInsert = "insert into `producers` (`name`, `country`)"
                + " values (\'" + tmp.getName()
                + "\', \'" + tmp.getCountry()
                + "\');";
        PreparedStatement pstatement = conn.prepareStatement(sqlInsert);
        pstatement.executeUpdate();
        pstatement.close();
    } // addItem

    @Override
    final protected WatchProducer parseItem(ResultSet result) throws SQLException {
        return new WatchProducer(result.getInt("id"),
                result.getString("name"),
                result.getString("country"));
    } // parseItem

    // Вывести производителей, общая сумма часов которых в магазине не превышает заданную
    public List<IData> query4(int quantity) throws SQLException {
        List<IData> watchList = new ArrayList<>();

        String sqlRequest = "select p.*" +
                                "from watches as w" +
                                "join producers as p on w.producer_id = p.id" +
                                "group by p.id" +
                                "HAVING sum(quantity) >" + quantity + ";";
        PreparedStatement pstatement = conn.prepareStatement(sqlRequest);
        ResultSet result = pstatement.executeQuery();

        while(result.next()) {
            watchList.add(parseItem(result));
        } // while
        pstatement.close();

        return watchList;
    } // query4

} // class ProducerModel
