package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.data.IData;

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

    protected JLabel lblPrompt;
    protected JTable itemsTable;
    protected JButton editBtn;
    protected JButton addBtn;

    public JButton getEditBtn() {
        return editBtn;
    }
    public JButton getAddBtn() {
        return addBtn;
    }

    public JTable getItemsTable() {
        return itemsTable;
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

        lblPrompt = titleBuilder();

        itemsTable = tableBuilder(itemsList);

        editBtn = new JButton("Редактировать");
        editBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(itemsTable.getSelectedRow() >= 0) {
                    int row = itemsTable.getSelectedRow();
                    int id = (Integer) itemsTable.getValueAt(row, 0);
                    controller.editItemDialog(id);
                }
            }
        });

        addBtn = new JButton("Добавить");


        itemsTable.setFillsViewportHeight(true);
        itemsTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemsTable.getTableHeader().setReorderingAllowed(false);

        controlPanel.add(editBtn);
        controlPanel.add(addBtn);

        contentPanel.add(lblPrompt, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(itemsTable), BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);
    } // buildPanel



    abstract protected void setController(Controller cont);

    abstract protected JLabel titleBuilder();
    abstract protected JTable tableBuilder(List<IData> itemsList);
    abstract public void showEditWindow(IData item);
} // class ContentPanel
