package com.itstep.pps2701.blokhin;

import com.itstep.pps2701.blokhin.controllers.Session;
import com.itstep.pps2701.blokhin.controllers.UserController;
import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.User;
import com.itstep.pps2701.blokhin.models.UserModel;
import com.itstep.pps2701.blokhin.views.LoginWindow;
import com.itstep.pps2701.blokhin.views.MainFrame;
import com.itstep.pps2701.blokhin.views.UsersPanel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
	// write your code here
        try{
            /*UserModel um = new UserModel();
            um.connect("root", "");

            List<IData> list = um.getItemList();



            for(IData user: list) {
                System.out.println(user);
            }

            User forChange = (User)list.get(list.size() - 1);
            forChange.setStatus(false);

            um.updateItem(forChange);

            System.out.println("=====");
            list = um.getItemList();

            for(IData user: list) {
                System.out.println(user);
            }

            um.disconnect();*/

            UserController uc = new UserController();

            SwingUtilities.invokeLater(()->{
                MainFrame win = new MainFrame("Магазин часов");
                uc.init(win.getTabbedPane());
            });





        } catch(Exception ex) {
            System.out.println("Ошибка соединения с БД: " + ex.getMessage());
        } // catch

        //Session session = new Session();



    } // main
} // class Main
