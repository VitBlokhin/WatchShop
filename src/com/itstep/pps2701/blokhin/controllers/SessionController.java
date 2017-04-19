package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.User;
import com.itstep.pps2701.blokhin.models.UserModel;
import com.itstep.pps2701.blokhin.system.Utils;
import com.itstep.pps2701.blokhin.views.MainFrame;

import javax.swing.*;
import java.rmi.AccessException;
import java.sql.SQLException;

/**
 * Created by Vit on 07.04.2017.
 */
public class SessionController extends Controller{
    private UserModel model;
    private User currentUser = null;   // текущий юзер, работающий в программе

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

    // Логин юзера
    public void login(String userName, String password){
        try {
            if(userName.isEmpty() || password.isEmpty()) throw new AccessException("Неверный логин или пароль");
            Utils.connect("root","");
            model = new UserModel();
            currentUser = model.getUserByNamePassword(userName, password);
            if(currentUser == null) throw new AccessException("Неверный логин или пароль");
            else {
                init();
                frame.setLoginMenuItemsStatus(false);
            }
        } catch(AccessException ex) {
            frame.showErrorDialog("Ошибка авторизации", ex.getMessage());
        } catch(SQLException ex) {
            frame.showErrorDialog("Ошибка подключения к БД", ex.getMessage());
        }
    } // login

    // Выход юзера из программы
    public void logoff() {
        try {
            currentUser = null;
            Utils.disconnect();
            frame.dispose();
            createMainFrame();
        } catch(Exception ex) {
            frame.showErrorDialog("Потеря связи с БД", ex.getMessage());
        }
    } // logoff

} // class SessionController
