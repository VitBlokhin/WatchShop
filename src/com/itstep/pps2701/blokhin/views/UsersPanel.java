package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.UserController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by Vit on 12.04.2017.
 */
public class UsersPanel extends ContentPanel {

    private JLabel lblPrompt;
    public JTable userTable;
    public JButton editBtn;

    public UsersPanel(JTabbedPane tabbedPane, String title, String tip, UserController uc) {
        super(tabbedPane, title, tip, uc);
    }

    @Override
    final protected void buildPanel() {
        contentPanel = new JPanel();
        lblPrompt = new JLabel("Список пользователей:");

        userTable = new JTable();
        userTable.setGridColor(Color.LIGHT_GRAY);

        editBtn = new JButton("Редактировать");
        editBtn.addActionListener(controller);

        contentPanel.add(lblPrompt);
        contentPanel.add(editBtn);

    } // buildPanel

    // TODO: сделать метод для отрисовки панели, который будет вызываться из контроллера!!
} // UsersPanel
