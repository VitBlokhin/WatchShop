package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.data.IData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Created by Vit on 13.04.2017.
 */
public class WatchesPanel extends ContentPanel {


    public WatchesPanel(JTabbedPane tabbedPane, String title, String tip) {
        super(tabbedPane, title, tip);
    }

    public WatchesPanel(JTabbedPane tabbedPane, String title, String tip, List<IData> dataList, Controller cont) {
        super(tabbedPane, title, tip,  dataList);
        setController(cont);
    }

    @Override
    final protected void setController(Controller cont) {
        controller = cont;
    }

    @Override
    final protected JLabel titleBuilder() {
        return new JLabel("Список часов");
    }

    @Override
    final protected JTable tableBuilder(List<IData> watchesList) {
        String[] header = {"id", "Марка", "Цена", "Количество", "Видимость", "Производитель", "Тип"};
        DefaultTableModel dfm = new DefaultTableModel(header, 0);

        for(IData item : watchesList) {
            dfm.addRow(item.toObjects());
        }
        return new JTable(dfm);
    }

    public JTable getWatchesTable() {
        return itemsTable;
    }

    public JButton getEditBtn() {
        return editBtn;
    }
}
