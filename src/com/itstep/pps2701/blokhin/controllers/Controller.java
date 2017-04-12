package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.models.IModel;
import com.itstep.pps2701.blokhin.views.IView;

import java.awt.event.ActionListener;

/**
 * Created by Vit on 12.04.2017.
 */
public abstract class Controller implements ActionListener {
    protected IModel model;
    protected IView view;



}
