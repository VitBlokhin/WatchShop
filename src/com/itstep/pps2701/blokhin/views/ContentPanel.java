package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.data.IData;
import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public abstract class ContentPanel implements IView{
    protected Controller controller;

    protected JTabbedPane parent;
    protected JPanel contentPanel;
    protected JPanel controlPanel;

    protected JTable itemsTable;
    protected JButton editBtn;
    protected JButton addBtn;

    protected void setController(Controller cont) {
        controller = cont;
    }


    public ContentPanel(JTabbedPane parent, String title, String tip, List<IData> itemsList) {
        buildPanel(itemsList);
        parent = parent;

        parent.addTab(title, null,
                contentPanel, tip);
    } // ContentPanel


    protected void buildPanel(List<IData> itemsList) {
        contentPanel = new JPanel(new BorderLayout(5,5));
        controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        itemsTable = tableBuilder(itemsList);

        editBtn = new JButton("Редактировать");
        editBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = itemsTable.getSelectedRow();
                if(row >= 0) {
                    int id = (Integer) itemsTable.getValueAt(row, 0);
                    controller.setItemById(id);
                    controller.editItemDialog();
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

        itemsTable.setFillsViewportHeight(true);
        itemsTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemsTable.getTableHeader().setReorderingAllowed(false);

        controlPanel.add(editBtn);
        controlPanel.add(addBtn);

        contentPanel.add(new JScrollPane(itemsTable), BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);
    } // buildPanel


    public void updateItemsTable(List<IData> itemsList){
        int index = 0;
        for(int i = 0; i < contentPanel.getComponentCount(); i++){
            if(contentPanel.getComponent(i).equals(itemsTable)) index = i;
        }
        itemsTable = tableBuilder(itemsList);
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

    abstract protected JTable tableBuilder(List<IData> itemsList);
    abstract protected JDialog createEditDialog(String name, boolean modal, IData item);
    abstract protected JDialog createAddDialog(String title, boolean modal);

} // class ContentPanel
