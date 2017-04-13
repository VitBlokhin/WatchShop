package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.data.IData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public class UsersPanel extends ContentPanel {


    public UsersPanel(JTabbedPane tabbedPane, String title, String tip) {
        super(tabbedPane, title, tip);
    }

    public UsersPanel(JTabbedPane tabbedPane, String title, String tip,  List<IData> dataList, Controller cont) {
        super(tabbedPane, title, tip,  dataList);
        setController(cont);
    }

    @Override
    final protected void setController(Controller cont) {
        controller = cont;
    }

    @Override
    final protected JLabel titleBuilder() {
        return new JLabel("Список пользователей");
    }

    @Override
    final protected JTable tableBuilder(List<IData> userList) {
        String[] header = {"id", "Имя", "email", "Телефон","Статус", "Админ"};
        DefaultTableModel dfm = new DefaultTableModel(header, 0){

            @Override
            public boolean isCellEditable(int x, int y) {
                return false;
            }
        };

        for(IData item : userList) {
            dfm.addRow(item.toObjects());
        }
        return new JTable(dfm);
    }

    @Override
    public void showEditWindow(IData item) {
        EditUserWindow ew = new EditUserWindow(item);
    }

    public JTable getUserTable() {
        return itemsTable;
    }

    public JButton getEditBtn() {
        return editBtn;
    }

} // UsersPanel
