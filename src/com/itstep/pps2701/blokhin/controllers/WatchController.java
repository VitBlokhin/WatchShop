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

    public void init(JTabbedPane pane, JFrame frame) throws SQLException {
        model = new WatchModel();
        watchesList = model.getItemList();

        this.frame = frame;
        view = new WatchesPanel(pane, "Часы", "Работа со списком часов", watchesList, this);
    }

    public void editItemDialog(int id) {
        try {
            model = new WatchModel();
            item = model.getItemById(id);

            view.showEditWindow(item);
        } catch(Exception ex) {
            ErrorWindow ew = new ErrorWindow("Ошибка загрузки данных", ex.getMessage());
        }
    } // editItemDialog

    @Override
    public void saveItem() {
        try {
            model = new WatchModel();
            model.updateItem(item);
        } catch(Exception ex) {
            ErrorWindow ew = new ErrorWindow("Ошибка сохранения данных", ex.getMessage());
        }
    } // saveItem

    @Override
    public IData getItem() {
        return item;
    }

    @Override
    public void setItem(IData item) {
        this.item = item;
    }

}
