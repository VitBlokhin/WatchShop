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

    public void init(JTabbedPane pane) throws SQLException {
        model = new UserModel();
        userList = model.getItemList();

        view = new UsersPanel(pane, "Пользователи", "Работа со списком пользователей", userList, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getEditBtn()) {
            try {
                int row = view.getItemsTable().getSelectedRow();
                int id = (Integer)view.getItemsTable().getValueAt(row, 0);

                model = new UserModel();
                item = model.getItemById(id);

                view.showEditWindow(item);
            } catch(Exception ex) {
                ErrorWindow ew = new ErrorWindow("Ошибка загрузки данных", ex.getMessage());
            }
        }
    }
}