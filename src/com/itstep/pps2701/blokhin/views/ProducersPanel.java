package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.WatchProducer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Created by Vit on 14.04.2017.
 */
public class ProducersPanel extends ContentPanel {
    public ProducersPanel(JTabbedPane pane, String title, String tip, Controller cont) {
        super(pane, title, tip,  cont);
    }

    @Override
    protected String[] tableHeaderBuilder() {
        return new String[]{"id", "Производитель", "Страна"};
    } // tableHeaderBuilder

    @Override
    protected JDialog createEditDialog(String title, boolean modal, IData item) {
        JButton acceptBtn, cancelBtn;

        JLabel lblName, lblCountry;
        JTextField txtName, txtCountry;

        JDialog dialog = new JDialog(controller.getMainframe(), title, modal);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(220, 200);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblName = new JLabel("Производитель");
        lblCountry = new JLabel("Страна");
        txtName = new JTextField(((WatchProducer) item).getName());
        txtCountry = new JTextField(((WatchProducer) item).getCountry());

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setItem(new WatchProducer(item.getId(),
                        txtName.getText(), txtCountry.getText()));
                controller.saveItem();

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
        contentPanel.add(lblCountry);
        contentPanel.add(txtCountry);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        return dialog;
    }

    @Override
    protected JDialog createAddDialog(String title, boolean modal) {
        JButton acceptBtn, cancelBtn;

        JLabel lblName, lblCountry;
        JTextField txtName, txtCountry;

        JDialog dialog = new JDialog(controller.getMainframe(), title, modal);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(220, 200);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblName = new JLabel("Производитель");
        lblCountry = new JLabel("Страна");
        txtName = new JTextField();
        txtCountry = new JTextField();

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WatchProducer watchProducer = new WatchProducer(txtName.getText(), txtCountry.getText());
                controller.saveNewItem(watchProducer);

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
        contentPanel.add(lblCountry);
        contentPanel.add(txtCountry);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        return dialog;
    }
}
