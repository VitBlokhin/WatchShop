package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.models.IModel;
import com.itstep.pps2701.blokhin.views.ErrorWindow;
import com.itstep.pps2701.blokhin.views.IView;

import javax.swing.*;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public abstract class Controller {
    protected JFrame frame;
    protected IModel model;
    protected IView view;

    protected List<IData> itemList;
    protected IData item;


    public JFrame getMainframe() {
        return frame;
    }
    public void setMainframe(JFrame mainframe) {
        this.frame = mainframe;
    }

    public IData getItem() {
        return item;
    }
    public void setItem(IData item) {
        this.item = item;
    }

    public void editItemDialog() {
        try {
            view.showEditDialog(item);
        } catch(Exception ex) {
            ErrorWindow ew = new ErrorWindow("Ошибка загрузки данных", ex.getMessage());
        }
    } // editItemDialog

    public void addItemDialog() {
        view.showAddDialog();
    } // addItemDialog

    public void saveItem() {
        try {
            model.updateItem(item);
        } catch(Exception ex) {
            ErrorWindow ew = new ErrorWindow("Ошибка сохранения данных", ex.getMessage());
        }
    } // saveItem

    public void saveNewItem(IData item) {
        try {
            model.addItem(item);
        } catch(Exception ex) {
            ErrorWindow ew = new ErrorWindow("Ошибка сохранения данных", ex.getMessage());
        }
    } // saveNewItem

    public void setItemById(int id) {
        try {
            item = model.getItemById(id);
        } catch(Exception ex) {
            ErrorWindow ew = new ErrorWindow("Ошибка загрузки данных", ex.getMessage());
        }
    } // setItemById

    public void updateItemsList() {
        try {
            itemList = model.getItemList();

            view.updateItemsTable(itemList);
        } catch(Exception ex) {
            ErrorWindow ew = new ErrorWindow("Ошибка обновления данных", ex.getMessage());
        }
    } // updateItemsList
}

