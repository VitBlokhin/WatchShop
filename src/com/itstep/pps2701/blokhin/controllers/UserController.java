package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.models.UserModel;
import com.itstep.pps2701.blokhin.views.ErrorWindow;
import com.itstep.pps2701.blokhin.views.UsersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public class UserController extends Controller {
    private List<IData> userList;

    public UserController() {

    }



    public void init(JTabbedPane pane, JFrame frame) throws SQLException {
        model = new UserModel();
        userList = model.getItemList();
        this.frame = frame;

        view = new UsersPanel(pane, "Пользователи", "Работа со списком пользователей", userList, this);
    } // init

    public void editItemDialog(int id) {
        try {
            item = model.getItemById(id);

            view.showEditWindow(item);
        } catch(Exception ex) {
            ErrorWindow ew = new ErrorWindow("Ошибка загрузки данных", ex.getMessage());
        }
    } // editItemDialog

    @Override
    public void saveItem() {
        try {
            model = new UserModel();
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