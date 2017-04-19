package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.models.ProducerModel;
import com.itstep.pps2701.blokhin.views.MainFrame;
import com.itstep.pps2701.blokhin.views.ProducersPanel;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by Vit on 14.04.2017.
 */
public class ProducerController extends Controller {

    public void init(JTabbedPane pane, MainFrame frame) throws SQLException {
        model = new ProducerModel();
        itemList = model.getItemList();
        this.frame = frame;
        view = new ProducersPanel(pane, "Производители", "Работа с производителями", this);
    } // init

    // Запрос 4
    public void producerQuery4(int quantity) {

        try {
            itemList = ((ProducerModel)model).query4(quantity);

        } catch(Exception ex) {
            frame.showErrorDialog("Ошибка загрузки данных", ex.getMessage());
        }
    } // producerQuery4
}
