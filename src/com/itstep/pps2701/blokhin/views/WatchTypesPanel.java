package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.WatchType;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Created by Vit on 14.04.2017.
 */
public class WatchTypesPanel extends ContentPanel {
    public WatchTypesPanel(JTabbedPane pane, String title, String tip, Controller cont) {
        super(pane, title, tip,  cont);
    }

    @Override
    protected String[] tableHeaderBuilder() {
        return new String[]{"id", "Тип"};
    } // tableHeaderBuilder

    @Override
    protected JDialog createEditDialog(String title, boolean modal, IData item) {
        JButton acceptBtn, cancelBtn;

        JLabel lblName;
        JTextField txtName;

        JDialog dialog = new JDialog(controller.getMainframe(), title, modal);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(220, 120);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblName = new JLabel("Тип");
        txtName = new JTextField(((WatchType) item).getTypename());

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setItem(new WatchType(item.getId(),
                        txtName.getText()));
                controller.saveItem();

                controller.updateItemsList();
                updateItemsTable();

                dialog.setVisible(false);
                dialog.dispose();
            }
        });
        cancelBtn = new JButton("Отмена");
        cancelBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
            }
        });

        controlPanel.add(acceptBtn);
        controlPanel.add(cancelBtn);

        contentPanel.add(lblName);
        contentPanel.add(txtName);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        return dialog;
    }

    @Override
    protected JDialog createAddDialog(String title, boolean modal) {
        JButton acceptBtn, cancelBtn;

        JLabel lblName;
        JTextField txtName;

        JDialog dialog = new JDialog(controller.getMainframe(), title, modal);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(220, 120);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblName = new JLabel("Тип");
        txtName = new JTextField();

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WatchType watchType = new WatchType(txtName.getText());
                controller.saveNewItem(watchType);

                controller.updateItemsList();
                updateItemsTable();

                dialog.setVisible(false);
                dialog.dispose();
            }
        });
        cancelBtn = new JButton("Отмена");
        cancelBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
            }
        });

        controlPanel.add(acceptBtn);
        controlPanel.add(cancelBtn);

        contentPanel.add(lblName);
        contentPanel.add(txtName);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        return dialog;
    }
}
