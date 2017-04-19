package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.controllers.ProducerController;
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
    ButtonGroup queriesGroup;
    JRadioButton rbNoQuery;
    JRadioButton rbQuery;
    JButton btnRunQuery;

    public ProducersPanel(JTabbedPane pane, String title, String tip, Controller cont) {
        super(pane, title, tip,  cont);
    }

    @Override
    protected void buildPanel() {
        contentPanel = new JPanel(new BorderLayout(5,5));
        controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        JPanel queryPanel = new JPanel();

        queriesGroup = new ButtonGroup();
        rbNoQuery = new JRadioButton("Вывести всё");
        rbQuery = new JRadioButton("Запрос 4");

        btnRunQuery = new JButton("Сделать запрос");
        btnRunQuery.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // опрос радиокнопок, по результату - вызов нужного диалогового окна
                if(rbQuery.isSelected()) runDialogQuery4();
                if(rbNoQuery.isSelected()) {
                    controller.updateItemsList();
                    updateItemsTable();
                }
            }
        });

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

        queriesGroup.add(rbNoQuery);
        queriesGroup.add(rbQuery);

        queryPanel.add(rbNoQuery);
        queryPanel.add(rbQuery);
        queryPanel.add(btnRunQuery);

        contentPanel.add(queryPanel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(itemsTable), BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);
    } // buildPanel

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
        contentPanel.add(lblCountry);
        contentPanel.add(txtCountry);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        return dialog;
    } // createEditDialog

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
        contentPanel.add(lblCountry);
        contentPanel.add(txtCountry);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        return dialog;
    } // createAddDialog


    private void runDialogQuery4() {
        JButton acceptBtn, cancelBtn;

        JLabel lblText;
        JSpinner spnQuantity;

        JDialog dialog = new JDialog(controller.getMainframe(), "Запрос 1", true);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 160);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblText = new JLabel("<html>Вывести производителей, общая сумма часов которых в магазине не превышает заданную");

        spnQuantity = new JSpinner(new SpinnerNumberModel(0,0,9999,1));

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = (Integer)spnQuantity.getValue();
                //objectList =
                ((ProducerController)controller).producerQuery4(quantity);

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

        contentPanel.add(lblText);
        contentPanel.add(spnQuantity);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    } // runDialogQuery4
}
