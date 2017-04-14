package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.models.WatchModel;
import com.itstep.pps2701.blokhin.views.ErrorWindow;
import com.itstep.pps2701.blokhin.views.WatchesPanel;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vit on 13.04.2017.
 */
public class WatchController extends Controller {

    public WatchController() {
    }

    public void init(JTabbedPane pane, JFrame frame) throws SQLException {
        model = new WatchModel();
        itemList = model.getItemList();
        this.frame = frame;
        view = new WatchesPanel(pane, "Часы", "Работа со списком часов", itemList, this);
    } // init
}
