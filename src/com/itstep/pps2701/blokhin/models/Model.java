package com.itstep.pps2701.blokhin.models;

import com.itstep.pps2701.blokhin.data.IData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public abstract class Model {
    private String connectionAddress = "localhost";
    private String connectionBase = "watch_shop";
    private int connectionPort = 3306;

    protected Connection connection;

    public Model() { }

    // подключение к БД
    public void connect(String username, String password) throws SQLException{
        connection = DriverManager.getConnection(
                "jdbc:mysql://"
                        + connectionAddress + ":" + connectionPort + "/" + connectionBase
                        + "?autoReconnect=true&useSSL=false",
                username,
                password);
    } // connect

    // отключение от БД
    public void disconnect() throws SQLException{
        connection.close();
    } // disconnect

    // получить соединение
    public Connection getConnection() {
        return connection;
    }

    abstract public IData getItemById(int id) throws SQLException;              // получение элемента из БД по его id
    abstract public List<IData> getItemList() throws SQLException;              // получение списка элементов из БД
    abstract public void updateItem(IData item) throws SQLException;            // изменение элемента в БД
    abstract public void addItem(IData item) throws SQLException;               // добавление элемента в БД
    abstract protected IData parseItem(ResultSet result) throws SQLException;   // парсинг элемента

}
