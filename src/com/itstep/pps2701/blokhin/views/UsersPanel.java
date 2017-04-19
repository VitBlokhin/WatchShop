package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Controller;
import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.User;
import com.itstep.pps2701.blokhin.system.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Created by Vit on 12.04.2017.
 */
public class UsersPanel extends ContentPanel {

    public UsersPanel(JTabbedPane tabbedPane, String title, String tip, Controller cont) {
        super(tabbedPane, title, tip, cont);
        // setController(cont);
    }

    @Override
    protected String[] tableHeaderBuilder() {
        return new String[]{"id", "Имя", "email", "Телефон","Работает", "Админ"};
    } // tableHeaderBuilder

    @Override
    final protected JDialog createEditDialog(String title, boolean modal, IData item){

        JButton acceptBtn, cancelBtn;

        JLabel lblName, lblEmail, lblPhone, lblPassword;
        JTextField txtName, txtEmail, txtPassword;
        JFormattedTextField ftxtPhone;
        JCheckBox chkStatus, chkAdmin;

        JDialog dialog = new JDialog(controller.getMainframe(), title, modal);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblName = new JLabel("Имя");
        lblEmail = new JLabel("Email");
        lblPhone = new JLabel("Телефон");
        lblPassword = new JLabel("Пароль");

        txtName = new JTextField(((User) item).getName());
        txtEmail = new JTextField(((User) item).getEmail());
        ftxtPhone = new JFormattedTextField(Utils.createFormatter("+###-##-###-####"));
        ftxtPhone.setText(((User) item).getPhone());
        ftxtPhone.setColumns(16);
        txtPassword = new JTextField(((User)item).getPassword());

        chkStatus = new JCheckBox("Работает", ((User) item).isStatus());
        chkAdmin = new JCheckBox("Админ", ((User) item).isSuperuser());

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setItem(new User(item.getId(),
                        txtName.getText(),
                        txtEmail.getText(),
                        ftxtPhone.getText(),
                        txtPassword.getText(),
                        chkStatus.isSelected(),
                        chkAdmin.isSelected()));
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
        contentPanel.add(lblEmail);
        contentPanel.add(txtEmail);
        contentPanel.add(lblPhone);
        contentPanel.add(ftxtPhone);
        contentPanel.add(lblPassword);
        contentPanel.add(txtPassword);
        contentPanel.add(chkStatus);
        contentPanel.add(chkAdmin);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        return dialog;
    } // createEditDialog

    @Override
    final protected JDialog createAddDialog(String title, boolean modal){

        JButton acceptBtn, cancelBtn;

        JLabel lblName, lblEmail, lblPhone, lblPassword;
        JTextField txtName, txtEmail, txtPassword;  // ввод пароля не скрываем
        JFormattedTextField ftxtPhone;
        JCheckBox chkStatus, chkAdmin;

        JDialog dialog = new JDialog(controller.getMainframe(), title, modal);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblName = new JLabel("Имя");
        lblEmail = new JLabel("Email");
        lblPhone = new JLabel("Телефон");
        lblPassword = new JLabel("Пароль");

        txtName = new JTextField();
        txtEmail = new JTextField();
        ftxtPhone = new JFormattedTextField(Utils.createFormatter("+###-##-###-####"));
        ftxtPhone.setColumns(16);
        txtPassword = new JTextField();

        chkStatus = new JCheckBox("Работает");
        chkAdmin = new JCheckBox("Админ");

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User(txtName.getText(),
                        txtEmail.getText(),
                        ftxtPhone.getText(),
                        txtPassword.getText(),
                        chkStatus.isSelected(),
                        chkAdmin.isSelected());
                controller.saveNewItem(user);

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
        contentPanel.add(lblEmail);
        contentPanel.add(txtEmail);
        contentPanel.add(lblPhone);
        contentPanel.add(ftxtPhone);
        contentPanel.add(lblPassword);
        contentPanel.add(txtPassword);
        contentPanel.add(chkStatus);
        contentPanel.add(chkAdmin);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        return dialog;
    } // createAddDialog

} // UsersPanel
