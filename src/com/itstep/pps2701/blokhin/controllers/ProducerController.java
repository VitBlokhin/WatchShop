package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.models.ProducerModel;
import com.itstep.pps2701.blokhin.views.ProducersPanel;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by Vit on 14.04.2017.
 */
public class ProducerController extends Controller {

    public void init(JTabbedPane pane, JFrame frame) throws SQLException {
        model = new ProducerModel();
        itemList = model.getItemList();
        this.frame = frame;
        view = new ProducersPanel(pane, "Производители", "Работа с производителями", itemList, this);
    } // init
}
