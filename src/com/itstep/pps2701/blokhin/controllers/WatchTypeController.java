package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.models.WatchTypeModel;
import com.itstep.pps2701.blokhin.views.WatchTypesPanel;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by Vit on 14.04.2017.
 */
public class WatchTypeController extends Controller {
    public WatchTypeController() {
    }

    public void init(JTabbedPane pane, JFrame frame) throws SQLException {
        model = new WatchTypeModel();
        itemList = model.getItemList();
        this.frame = frame;
        view = new WatchTypesPanel(pane, "Типы часов", "Работа с типами часов", itemList, this);
    } // init
}