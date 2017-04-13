package com.itstep.pps2701.blokhin.models;

import com.itstep.pps2701.blokhin.data.IData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public interface IModel {

    IData getItemById(int id) throws SQLException;              // получение элемента из БД по его id
    List<IData> getItemList() throws SQLException;              // получение списка элементов из БД
    void updateItem(IData item) throws SQLException;            // изменение элемента в БД
    void addItem(IData item) throws SQLException;               // добавление элемента в БД
}
