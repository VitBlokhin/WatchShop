package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.controllers.ProducerController;
import com.itstep.pps2701.blokhin.controllers.WatchController;
import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.Watch;
import com.itstep.pps2701.blokhin.data.WatchProducer;
import com.itstep.pps2701.blokhin.data.WatchType;
import com.itstep.pps2701.blokhin.system.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Created by Vit on 13.04.2017.
 */
public class WatchesPanel extends ContentPanel {

    public WatchesPanel(JTabbedPane tabbedPane, String title, String tip, List<IData> itemList, Controller cont) {
        super(tabbedPane, title, tip,  itemList);
        setController(cont);
    }

    @Override
    final protected JLabel titleBuilder() {
        return new JLabel("Список часов");
    }

    @Override
    final protected JTable tableBuilder(List<IData> watchesList) {
        String[] header = {"id", "Марка", "Цена", "Количество", "Видимость", "Производитель", "Тип"};
        DefaultTableModel dfm = new DefaultTableModel(header, 0){

            @Override
            public boolean isCellEditable(int x, int y) {
                return false;
            }
        };

        //TODO: вывод названий производителей и типов вместо их id
        for(IData item : watchesList) {
            dfm.addRow(item.toObjects());
        }
        return new JTable(dfm);
    }


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
        dialog.setLocation(350,300);
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
        cboxType = new JComboBox(watchTypeList.toArray());

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WatchProducer producer = (WatchProducer)cboxProducer.getSelectedItem();
                WatchType type = (WatchType)cboxType.getSelectedItem();

                double price = (Double)spnPrice.getValue();
                int quantity = (Integer)spnQuantity.getValue();

                controller.setItem(new Watch(((Watch)item).getId(),
                        txtMark.getText(),
                        price,
                        quantity,
                        chkVisible.isSelected(),
                        producer.getId(),
                        type.getId()));
                controller.saveItem();

                controller.updateItemsList();

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
        dialog.setLocation(350,300);
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

                    dialog.setVisible(false);
                    dialog.dispose();
                } catch (Exception ex) {
                    ErrorWindow ew = new ErrorWindow("Ошибка формата", "Неверно указана цена");
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

}
