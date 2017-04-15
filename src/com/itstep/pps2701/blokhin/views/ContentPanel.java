package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.data.IData;
import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public abstract class ContentPanel implements IView{
    protected Controller controller;

    protected JPanel contentPanel;
    protected JPanel controlPanel;

    protected JTable itemsTable;
    protected JButton editBtn;
    protected JButton addBtn;


    public ContentPanel(JTabbedPane parent, String title, String tip, Controller cont) {
        this.controller = cont;
        buildPanel();

        parent.addTab(title, null,
                contentPanel, tip);
    } // ContentPanel


    protected void buildPanel() {
        contentPanel = new JPanel(new BorderLayout(5,5));
        controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        itemsTable = tableBuilder();

        editBtn = new JButton("Редактировать");
        editBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = itemsTable.getSelectedRow();
                if(row >= 0) {
                    int id = (Integer) itemsTable.getValueAt(row, 0);
                    controller.editItemDialog(id);
                }
            }
        });

        addBtn = new JButton("Добавить");
        addBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addItemDialog();
            }
        });

        controlPanel.add(editBtn);
        controlPanel.add(addBtn);

        contentPanel.add(new JScrollPane(itemsTable), BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);
    } // buildPanel


    // Не работает, нужно полностью пересоздавать панель, либо перейти на другую и вернуться
    public void updateItemsTable(){
        int index = 0;
        for(int i = 0; i < contentPanel.getComponentCount(); i++){
            if(contentPanel.getComponent(i).equals(itemsTable)) index = i;
        }
        itemsTable = tableBuilder();

        contentPanel.add(new JScrollPane(itemsTable), BorderLayout.CENTER, index);
    } // updateItemsTable

    public void showEditDialog(IData item) {
        JDialog editDialog = createEditDialog("Редактировать", true, item);
        editDialog.setVisible(true);
    } // showEditDialog

    public void showAddDialog() {
        JDialog addDialog = createAddDialog("Добавить", true);
        addDialog.setVisible(true);
    } // showAddDialog

    protected JTable tableBuilder() {
        String[] header = tableHeaderBuilder();
        DefaultTableModel dfm = new DefaultTableModel(header, 0){

            @Override
            public boolean isCellEditable(int x, int y) {
                return false;
            }
        };

        for(Object[] objects : controller.getItemObjectsList()) {
            dfm.addRow(objects);
        }

        JTable table = new JTable(dfm);
        table.setFillsViewportHeight(true);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        return table;
    } // tableBuilder

    protected abstract String[] tableHeaderBuilder();

    abstract protected JDialog createEditDialog(String name, boolean modal, IData item);
    abstract protected JDialog createAddDialog(String title, boolean modal);

} // class ContentPanel
