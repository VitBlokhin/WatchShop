package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.models.IModel;
import com.itstep.pps2701.blokhin.views.IView;

import javax.swing.*;

/**
 * Created by Vit on 12.04.2017.
 */
public abstract class Controller{
    protected JFrame frame;
    protected IModel model;
    protected IView view;
    protected IData item;



    public JFrame getMainframe() {
        return frame;
    }

    public void setMainframe(JFrame mainframe) {
        this.frame = mainframe;
    }

    abstract public void editItemDialog(int id);
    abstract public void saveItem();
    abstract public IData getItem();
    abstract public void setItem(IData item);
}
