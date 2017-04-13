package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.controllers.UserController;
import com.itstep.pps2701.blokhin.data.IData;

import javax.swing.*;
import java.awt.*;
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

    public ContentPanel(JTabbedPane parent, String title, String tip, List<IData> dataList) {
        rebuildPanel(dataList);
        this.parent = parent;
        parent.addTab(title, null,
                contentPanel, tip);
    }

    public ContentPanel(JTabbedPane tabbedPane, String title, String tip) {
        buildPanel();
        parent = tabbedPane;

        parent.addTab(title, null,
                contentPanel, tip);
    } // ContentPanel


    protected void buildPanel() {
        contentPanel = new JPanel(new BorderLayout(5,5));
        controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblPrompt = titleBuilder();

        itemsTable = new JTable();
        itemsTable.setGridColor(Color.LIGHT_GRAY);

        editBtn = new JButton("Редактировать");
        editBtn.addActionListener(controller);

        addBtn = new JButton("Добавить");
        addBtn.addActionListener(controller);

        controlPanel.add(editBtn);
        controlPanel.add(addBtn);

        contentPanel.add(lblPrompt, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(itemsTable), BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);
    } // buildPanel

    public void rebuildPanel(List<IData> itemsList){
        contentPanel = new JPanel(new BorderLayout(5,5));
        controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblPrompt = titleBuilder();

        itemsTable = tableBuilder(itemsList);

        editBtn = new JButton("Редактировать");
        editBtn.addActionListener(controller);

        addBtn = new JButton("Добавить");
        addBtn.addActionListener(controller);

        itemsTable.setFillsViewportHeight(true);
        itemsTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemsTable.getTableHeader().setReorderingAllowed(false);

        controlPanel.add(editBtn);
        controlPanel.add(addBtn);

        contentPanel.add(lblPrompt, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(itemsTable), BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);
    } // rebuildPanel

    public void showErrorWindow(String title, String errorText){
        ErrorWindow errWin = new ErrorWindow(title, errorText);
    }

    // abstract protected void buildPanel();
    // abstract public void rebuildPanel(List<IData> userList);
    abstract protected void setController(Controller cont);

    abstract protected JLabel titleBuilder();
    abstract protected JTable tableBuilder(List<IData> itemsList);
} // class ContentPanel
