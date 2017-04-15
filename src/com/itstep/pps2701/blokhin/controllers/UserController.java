package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.models.UserModel;
import com.itstep.pps2701.blokhin.views.MainFrame;
import com.itstep.pps2701.blokhin.views.UsersPanel;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Created by Vit on 12.04.2017.
 */
public class UserController extends Controller {


    public UserController() {

    }

    public void init(JTabbedPane pane, MainFrame frame) throws SQLException {
        model = new UserModel();
        itemList = model.getItemList();
        this.frame = frame;

        view = new UsersPanel(pane, "Пользователи", "Работа со списком пользователей", itemList, this);
    } // init
}