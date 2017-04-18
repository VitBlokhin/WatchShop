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
                // TODO: опрос радиокнопок, по результату - вызов нужного диалогового окна
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
