package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.data.IData;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vit on 13.04.2017.
 */
public abstract class EditWindow extends JDialog{
    protected IData item;

    protected JPanel mainPanel;
    protected JButton acceptBtn, cancelBtn;

    public EditWindow(JFrame frame, String title, IData item) {
        super(frame, title, true);
    }

    abstract void buildGUI();
}
