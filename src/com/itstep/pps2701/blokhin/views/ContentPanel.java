package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.controllers.UserController;

import javax.swing.*;

/**
 * Created by Vit on 12.04.2017.
 */
public abstract class ContentPanel implements IView{
    protected Controller controller;
    public JPanel contentPanel;

    public ContentPanel(JTabbedPane tabbedPane, String title, String tip, Controller cont) {
        buildPanel();
        tabbedPane.addTab(title, null,
                contentPanel, tip);
        controller = cont;
    } // ContentPanel

    abstract protected void buildPanel();

    public void showErrorWindow(String title, String errorText){
        ErrorWindow errWin = new ErrorWindow(title, errorText);
    }
} // class ContentPanel
