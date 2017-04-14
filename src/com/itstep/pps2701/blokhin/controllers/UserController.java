package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.models.UserModel;
import com.itstep.pps2701.blokhin.views.ErrorWindow;
import com.itstep.pps2701.blokhin.views.UsersPanel;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public class UserController extends Controller {


    public UserController() {

    }

    public void init(JTabbedPane pane, JFrame frame) throws SQLException {
        model = new UserModel();
        itemList = model.getItemList();
        this.frame = frame;

        view = new UsersPanel(pane, "Пользователи", "Работа со списком пользователей", itemList, this);
    } // init
}