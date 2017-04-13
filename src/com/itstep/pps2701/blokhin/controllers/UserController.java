package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.User;
import com.itstep.pps2701.blokhin.models.UserModel;
import com.itstep.pps2701.blokhin.views.UsersPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public class UserController extends Controller {
    private List<IData> userList;

    public UserController() {

    }

    public void init(JTabbedPane pane) {
        try {
            model = new UserModel();
            model.connect("root", "");
            userList = model.getItemList();

            view = new UsersPanel(pane, "Пользователи", "Работа со списком пользователей", userList, this);
        } catch(Exception ex) {
            view.showErrorWindow("Ошибка загрузки данных", ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //(UsersPanel)view
    }
}