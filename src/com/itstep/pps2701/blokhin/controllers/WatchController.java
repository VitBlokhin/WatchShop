package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.models.WatchModel;
import com.itstep.pps2701.blokhin.views.ErrorWindow;
import com.itstep.pps2701.blokhin.views.WatchesPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vit on 13.04.2017.
 */
public class WatchController extends Controller {
    private List<IData> watchesList;

    public WatchController() {
    }

    public void init(JTabbedPane pane) throws SQLException {
        model = new WatchModel();
        watchesList = model.getItemList();

        view = new WatchesPanel(pane, "Часы", "Работа со списком часов", watchesList, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //(UsersPanel)view
    }
}
