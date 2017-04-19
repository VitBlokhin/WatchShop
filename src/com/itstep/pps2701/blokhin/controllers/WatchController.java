package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.Watch;
import com.itstep.pps2701.blokhin.data.WatchProducer;
import com.itstep.pps2701.blokhin.data.WatchType;
import com.itstep.pps2701.blokhin.models.ProducerModel;
import com.itstep.pps2701.blokhin.models.WatchModel;
import com.itstep.pps2701.blokhin.models.WatchTypeModel;
import com.itstep.pps2701.blokhin.views.MainFrame;
import com.itstep.pps2701.blokhin.views.WatchesPanel;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vit on 13.04.2017.
 */
public class WatchController extends Controller {

    private List<IData> producerList;
    private List<IData> watchTypeList;

    private ProducerModel pModel;   // для отображения производителей и типов часов в диалоговых окнах вместо их id
    private WatchTypeModel wtModel; //

    public List<IData> getProducerList() {
        return producerList;
    }
    public List<IData> getWatchTypeList() {
        return watchTypeList;
    }

    public WatchController() {
    }

    public void init(JTabbedPane pane, MainFrame frame) throws SQLException {
        model = new WatchModel();
        pModel = new ProducerModel();
        wtModel = new WatchTypeModel();

        //setProducerList();
        //setWatchTypeList();
        updateProducerAndTypeLists();

        itemList = model.getItemList();
        this.frame = frame;
        view = new WatchesPanel(pane, "Часы", "Работа со списком часов", this);
    } // init

    private void setProducerList() {
        try {
            this.producerList = pModel.getItemList();
        } catch(Exception ex) {
            frame.showErrorDialog("Ошибка загрузки данных", ex.getMessage());
        }
    } // setProducerList

    private void setWatchTypeList() {
        try {
            this.watchTypeList = wtModel.getItemList();
        } catch(Exception ex) {
            frame.showErrorDialog("Ошибка загрузки данных", ex.getMessage());
        }
    } // setWatchTypeList

    public void updateProducerAndTypeLists(){
        try {
            this.producerList = pModel.getItemList();
            this.watchTypeList = wtModel.getItemList();
        } catch(Exception ex) {
            frame.showErrorDialog("Ошибка загрузки данных", ex.getMessage());
        }
    } // updateProducerAndTypeLists

    @Override
    public List<Object[]> getItemObjectsList() {
        try {
            // для отображения производителей и типов часов в диалоговых окнах вместо их id
            // Object[]{0"id", 1"Марка", 2"Цена", 3"Количество", 4"Видимость", 5"Производитель", 6"Тип"}
            // заменяем 5 и 6 элементов на элементы из моделей Производитель и Тип соответственно

            List<Object[]> objectList = new ArrayList<>();
            for(IData item : itemList) {
                Object[] itemObjects;
                WatchProducer watchProducer = pModel.getItemById(((Watch)item).getProducerId());
                WatchType watchType = wtModel.getItemById(((Watch)item).getTypeId());
                itemObjects = item.toObjects();
                itemObjects[5] = watchProducer.toObjects()[1];
                itemObjects[6] = watchType.toObjects()[1];
                objectList.add(itemObjects);
            }

            return objectList;
        } catch(Exception ex) {
            frame.showErrorDialog("Ошибка загрузки данных", ex.getMessage());
            return null;
        }
    } // getItemObjectsList

    // Запрос 1
    public void watchQuery1(WatchType type) {

        try {
            itemList = ((WatchModel)model).query1(type);

        } catch(Exception ex) {
            frame.showErrorDialog("Ошибка загрузки данных", ex.getMessage());
        }
    } // watchQuery1

    // Запрос 2
    public void watchQuery2(double price, WatchType type) {
        try {
            itemList = ((WatchModel)model).query2(price, type);

        } catch(Exception ex) {
            frame.showErrorDialog("Ошибка загрузки данных", ex.getMessage());
        }
    } // watchQuery2

    // Запрос 3
    public void watchQuery3(String country) {
        try {
            itemList = ((WatchModel)model).query3(country);

        } catch(Exception ex) {
            frame.showErrorDialog("Ошибка загрузки данных", ex.getMessage());
        }
    } // watchQuery3
} // class WatchController
