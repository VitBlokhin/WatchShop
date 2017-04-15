package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.models.IModel;
import com.itstep.pps2701.blokhin.views.IView;
import com.itstep.pps2701.blokhin.views.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public abstract class Controller {
    protected MainFrame frame;
    protected IModel model;
    protected IView view;

    protected List<IData> itemList;
    protected IData item;


    public JFrame getMainframe() {
        return frame;
    }
    public void setMainframe(MainFrame mainframe) {
        this.frame = mainframe;
    }

    public IData getItem() {
        return item;
    }
    public void setItem(IData item) {
        this.item = item;
    }

    public void editItemDialog(int id) {
        try {
            item = model.getItemById(id);
            view.showEditDialog(item);
        } catch(Exception ex) {
            frame.showErrorDialog("Ошибка загрузки данных", ex.getMessage());
        }
    } // editItemDialog

    public void addItemDialog() {
        view.showAddDialog();
    } // addItemDialog

    public void saveItem() {
        try {
            model.updateItem(item);
        } catch(Exception ex) {
            frame.showErrorDialog("Ошибка сохранения данных", ex.getMessage());
        }
    } // saveItem

    public void saveNewItem(IData item) {
        try {
            model.addItem(item);
        } catch(Exception ex) {
            frame.showErrorDialog("Ошибка сохранения данных", ex.getMessage());
        }
    } // saveNewItem

    public List<Object[]> getItemObjectsList(){
        try {
            itemList = model.getItemList();

            List<Object[]> objectList = new ArrayList<>();
            for(IData item : itemList) {
                objectList.add(item.toObjects());
            }

            return objectList;
        } catch(Exception ex) {
            frame.showErrorDialog("Ошибка загрузки данных", ex.getMessage());
            return null;
        }
    } // getItemObjectsList
}

