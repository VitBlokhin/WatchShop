package com.itstep.pps2701.blokhin;

import com.itstep.pps2701.blokhin.controllers.ProducerController;
import com.itstep.pps2701.blokhin.controllers.UserController;
import com.itstep.pps2701.blokhin.controllers.WatchController;
import com.itstep.pps2701.blokhin.controllers.WatchTypeController;
import com.itstep.pps2701.blokhin.system.Utils;
import com.itstep.pps2701.blokhin.views.ErrorWindow;
import com.itstep.pps2701.blokhin.views.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
	// write your code here


        try{
            Utils.connect("root","");

            UserController uc = new UserController();
            WatchController wc = new WatchController();
            ProducerController pc = new ProducerController();
            WatchTypeController wtc = new WatchTypeController();

            SwingUtilities.invokeLater(()->{
                MainFrame win = new MainFrame("Магазин часов");
                try {
                    uc.init(win.getTabbedPane(), win);
                    wc.init(win.getTabbedPane(), win);
                    pc.init(win.getTabbedPane(), win);
                    wtc.init(win.getTabbedPane(), win);
                } catch (Exception ex) {
                    ErrorWindow ew = new ErrorWindow("Ошибка загрузки данных", ex.getMessage());
                }
            });
        } catch (Exception ex){
            ErrorWindow ew = new ErrorWindow("Ошибка загрузки данных", ex.getMessage());
        }


    } // main
} // class Main
