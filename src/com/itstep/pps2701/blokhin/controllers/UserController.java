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
public class UserController extends Controller{
    private List<IData> userList;

    public UserController() {
        model = new UserModel();

    }

    public void init(JTabbedPane tabbedPane){
        view = new UsersPanel(tabbedPane, "Пользователи", "Управление пользователями", this);
        view = (UsersPanel)view;
        String[] header = {"id", "Имя", "email", "Телефон", "Пароль", "Статус", "Админ"};
        DefaultTableModel dfm = new DefaultTableModel(header, 0);
        try {
            model.connect("root", "");
            userList = model.getItemList();
            ((UsersPanel) view).userTable = new JTable(dfm);
            for(IData item : userList) {
                dfm.addRow(item.toObjects());
            }
            ((UsersPanel) view).contentPanel.add(((UsersPanel) view).userTable);
        } catch (Exception ex){
            view.showErrorWindow("Ошибка загрузки данных", ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
