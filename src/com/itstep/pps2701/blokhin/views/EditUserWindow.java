package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.data.IData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vit on 13.04.2017.
 */
public class EditUserWindow extends EditWindow {

    public EditUserWindow(IData item) {
        super(item);

        buildGUI();
    }

    @Override
    void buildGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new FlowLayout(FlowLayout.CENTER, 24, 24));

        acceptBtn = new JButton("Сохранить");
        cancelBtn = new JButton("Отмена");

        setVisible(true);
    }
}
