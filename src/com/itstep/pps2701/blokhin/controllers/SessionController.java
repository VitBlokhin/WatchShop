package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.User;
import com.itstep.pps2701.blokhin.models.UserModel;
import com.itstep.pps2701.blokhin.system.Utils;
import com.itstep.pps2701.blokhin.views.ContentPanel;
import com.itstep.pps2701.blokhin.views.MainFrame;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Vit on 07.04.2017.
 */
public class SessionController extends Controller{
    private UserModel model;
    private User currentUser = null;

    public SessionController() {
    }

    public void createMainFrame(){
        SwingUtilities.invokeLater(()-> {
            frame = new MainFrame("Магазин часов");
            frame.setSessionController(this);
        });
    }

    public void init() {
        try{
            if(currentUser.isSuperuser()) {
                UserController uc = new UserController();
                uc.init(frame.getTabbedPane(), frame);
            }
            WatchController wc = new WatchController();
            ProducerController pc = new ProducerController();
            WatchTypeController wtc = new WatchTypeController();
            wc.init(frame.getTabbedPane(), frame);
            pc.init(frame.getTabbedPane(), frame);
            wtc.init(frame.getTabbedPane(), frame);
        } catch (Exception ex) {
            frame.showErrorDialog("Ошибка загрузки данных", ex.getMessage());
        }
    } // init

    public User getCurrentUser() {
        return currentUser;
    } // getCurrentUser

    public void login(String userName, String password){
        try {
            Utils.connect("root","");
            model = new UserModel();
            currentUser = model.getUserByNamePassword(userName, password);
            if(currentUser == null) throw new Exception();
            else init();
        } catch(Exception e) {
            frame.showErrorDialog("Ошибка авторизации", "Неверный логин или пароль");
        }
    } // login

    public void logoff() {
        try {
            currentUser = null;
            Utils.disconnect();
            frame.dispose();
            frame = new MainFrame("Магазин часов");
        } catch(Exception ex) {
            frame.showErrorDialog("Потеря связи с БД", ex.getMessage());
        }
    } // logoff

} // class SessionController
