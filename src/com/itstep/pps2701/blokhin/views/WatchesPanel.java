package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.controllers.WatchController;
import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.Watch;
import com.itstep.pps2701.blokhin.data.WatchProducer;
import com.itstep.pps2701.blokhin.data.WatchType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Created by Vit on 13.04.2017.
 */
public class WatchesPanel extends ContentPanel {

    ButtonGroup queriesGroup;
    JRadioButton rbNoQuery;
    JRadioButton rbQuery1;
    JRadioButton rbQuery2;
    JRadioButton rbQuery3;
    JButton btnRunQuery;

    public WatchesPanel(JTabbedPane tabbedPane, String title, String tip, Controller cont) {
        super(tabbedPane, title, tip,  cont);
    }

    @Override
    protected void buildPanel() {
        contentPanel = new JPanel(new BorderLayout(5,5));
        controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        JPanel queryPanel = new JPanel();

        queriesGroup = new ButtonGroup();
        rbNoQuery = new JRadioButton("Вывести всё");
        rbQuery1 = new JRadioButton("Запрос 1");
        rbQuery2 = new JRadioButton("Запрос 2");
        rbQuery3 = new JRadioButton("Запрос 3");

        btnRunQuery = new JButton("Сделать запрос");
        btnRunQuery.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // опрос радиокнопок, по результату - вызов нужного диалогового окна
                if(rbQuery1.isSelected()) runDialogQuery1();
                if(rbQuery2.isSelected()) runDialogQuery2();
                if(rbQuery3.isSelected()) runDialogQuery3();
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
                showAddDialog();
            }
        });

        controlPanel.add(editBtn);
        controlPanel.add(addBtn);

        queriesGroup.add(rbNoQuery);
        queriesGroup.add(rbQuery1);
        queriesGroup.add(rbQuery2);
        queriesGroup.add(rbQuery3);

        queryPanel.add(rbNoQuery);
        queryPanel.add(rbQuery1);
        queryPanel.add(rbQuery2);
        queryPanel.add(rbQuery3);
        queryPanel.add(btnRunQuery);

        contentPanel.add(queryPanel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(itemsTable), BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);
    } // buildPanel

    // Создание заголовка таблицы
    @Override
    protected String[] tableHeaderBuilder() {
        return new String[]{"id", "Марка", "Цена", "Количество", "Видимость", "Производитель", "Тип"};
    } // tableHeaderBuilder


    // Диалоговое окно изменения элемента
    @Override
    final protected JDialog createEditDialog(String title, boolean modal, IData item){

        JButton acceptBtn, cancelBtn;

        JLabel lblMark, lblPrice, lblQuantity, lblProducer, lblType;
        JTextField txtMark;
        JSpinner spnPrice, spnQuantity;
        JCheckBox  chkVisible;
        JComboBox cboxProducer, cboxType;

        JDialog dialog = new JDialog(controller.getMainframe(), title, modal);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 320);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblMark = new JLabel("Марка");
        lblPrice = new JLabel("Цена");
        lblQuantity = new JLabel("Количество");
        lblProducer = new JLabel("Производитель");
        lblType = new JLabel("Тип");

        txtMark = new JTextField(((Watch)item).getMark());
        spnPrice = new JSpinner(new SpinnerNumberModel(0,0,9999999,0.01));
        spnPrice.setValue(((Watch)item).getPrice());
        spnQuantity = new JSpinner(new SpinnerNumberModel(0,0,9999,1));
        spnQuantity.setValue(((Watch)item).getQuantity());
        chkVisible = new JCheckBox("Видимость",((Watch)item).isVisible());

        List<IData> producerList = ((WatchController)controller).getProducerList();
        List<IData> watchTypeList = ((WatchController)controller).getWatchTypeList();

        cboxProducer = new JComboBox(producerList.toArray());
        for(IData producer : producerList) {
            if(((Watch) item).getProducerId() == producer.getId()) cboxProducer.setSelectedItem(producer);
        }
        cboxType = new JComboBox(watchTypeList.toArray());
        for(IData watchType : watchTypeList) {
            if(((Watch) item).getTypeId() == watchType.getId()) cboxType.setSelectedItem(watchType);
        }


        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WatchProducer producer = (WatchProducer)cboxProducer.getSelectedItem();
                WatchType type = (WatchType)cboxType.getSelectedItem();

                double price = (Double)spnPrice.getValue();
                int quantity = (Integer)spnQuantity.getValue();

                controller.saveItem(new Watch(item.getId(),
                        txtMark.getText(),
                        price,
                        quantity,
                        chkVisible.isSelected(),
                        producer.getId(),
                        type.getId()));

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

        contentPanel.add(lblMark);
        contentPanel.add(txtMark);
        contentPanel.add(lblPrice);
        contentPanel.add(spnPrice);
        contentPanel.add(lblQuantity);
        contentPanel.add(spnQuantity);
        contentPanel.add(lblProducer);
        contentPanel.add(cboxProducer);
        contentPanel.add(lblType);
        contentPanel.add(cboxType);
        contentPanel.add(chkVisible);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        return dialog;
    } // createEditDialog


    // Диалоговое окно добавления элемента в базу
    @Override
    final protected JDialog createAddDialog(String title, boolean modal){
        JButton acceptBtn, cancelBtn;

        JLabel lblMark, lblPrice, lblQuantity, lblProducer, lblType;
        JTextField txtMark, txtPrice;
        JSpinner spnQuantity;
        JCheckBox  chkVisible;
        JComboBox cboxProducer, cboxType;

        JDialog dialog = new JDialog(controller.getMainframe(), title, modal);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 320);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblMark = new JLabel("Марка");
        lblPrice = new JLabel("Цена");
        lblQuantity = new JLabel("Количество");
        lblProducer = new JLabel("Производитель");
        lblType = new JLabel("Тип");

        txtMark = new JTextField();
        txtPrice = new JTextField();
        spnQuantity = new JSpinner(new SpinnerNumberModel(0,0,9999,1));
        chkVisible = new JCheckBox("Видимость");

        List<IData> producerList = ((WatchController)controller).getProducerList();
        List<IData> watchTypeList = ((WatchController)controller).getWatchTypeList();

        cboxProducer = new JComboBox(producerList.toArray());
        cboxType = new JComboBox(watchTypeList.toArray());

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WatchProducer producer = (WatchProducer)cboxProducer.getSelectedItem();
                    WatchType type = (WatchType)cboxType.getSelectedItem();

                    double price = Double.parseDouble(txtPrice.getText());
                    int quantity = (Integer)spnQuantity.getValue();

                    Watch watch = new Watch(txtMark.getText(),
                            price,
                            quantity,
                            chkVisible.isSelected(),
                            producer.getId(),
                            type.getId());
                    controller.saveNewItem(watch);

                    controller.updateItemsList();
                    updateItemsTable();

                    dialog.setVisible(false);
                    dialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка ввода цены");
                    dialog.setVisible(false);
                    dialog.dispose();
                }
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

        contentPanel.add(lblMark);
        contentPanel.add(txtMark);
        contentPanel.add(lblPrice);
        contentPanel.add(txtPrice);
        contentPanel.add(lblQuantity);
        contentPanel.add(spnQuantity);
        contentPanel.add(lblProducer);
        contentPanel.add(cboxProducer);
        contentPanel.add(lblType);
        contentPanel.add(cboxType);
        contentPanel.add(chkVisible);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        return dialog;
    } // createAddDialog



    // ОБРАБОТКА ЗАПРОСОВ

    private void runDialogQuery1() {
        JButton acceptBtn, cancelBtn;

        JLabel lblText;
        JComboBox  cboxType;

        JDialog dialog = new JDialog(controller.getMainframe(), "Запрос 1", true);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 160);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblText = new JLabel("<html>Вывести марки заданного типа часов");

        List<IData> watchTypeList = ((WatchController)controller).getWatchTypeList();

        cboxType = new JComboBox(watchTypeList.toArray());

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WatchType type = (WatchType)cboxType.getSelectedItem();

                ((WatchController)controller).watchQuery1(type);

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
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(cboxType);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    } // runDialogQuery1

    private void runDialogQuery2() {
        JButton acceptBtn, cancelBtn;

        JLabel lblText, lblPrice, lblType;
        JTextField txtPrice;
        JComboBox  cboxType;

        JDialog dialog = new JDialog(controller.getMainframe(), "Запрос 2", true);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblText = new JLabel("<html>Вывести информацию о механических часах, цена на которые не превышает заданную");
        lblType = new JLabel("Тип");
        lblPrice = new JLabel("Цена");

        txtPrice = new JTextField();

        List<IData> watchTypeList = ((WatchController)controller).getWatchTypeList();

        cboxType = new JComboBox(watchTypeList.toArray());

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WatchType type = (WatchType) cboxType.getSelectedItem();
                    double price = Double.parseDouble(txtPrice.getText());

                    ((WatchController) controller).watchQuery2(price, type);

                    updateItemsTable();

                    dialog.setVisible(false);
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка ввода цены");
                    dialog.setVisible(false);
                    dialog.dispose();
                }
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
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(lblPrice);
        contentPanel.add(txtPrice);
        contentPanel.add(lblType);
        contentPanel.add(cboxType);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    } // runDialogQuery2


    private void runDialogQuery3() {
        JButton acceptBtn, cancelBtn;

        JLabel lblText;
        JTextField  txtCountry;

        JDialog dialog = new JDialog(controller.getMainframe(), "Запрос 2", true);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 160);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblText = new JLabel("<html>Вывести марки часов, изготовленных в заданной стране");

        txtCountry = new JTextField();

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String country = txtCountry.getText();

                ((WatchController) controller).watchQuery3(country);

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
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(txtCountry);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    } // runDialogQuery3
}
