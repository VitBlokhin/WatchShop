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

    public UsersPanel(JTabbedPane tabbedPane, String title, String tip,  List<IData> itemsList, Controller cont) {
        super(tabbedPane, title, tip,  itemsList);
        setController(cont);
    }

    @Override
    final protected void setController(Controller cont) {
        controller = cont;
    }

    @Override
    final protected JLabel titleBuilder() {
        return new JLabel("Список пользователей");
    }

    @Override
    final protected JTable tableBuilder(List<IData> userList) {
        String[] header = {"id", "Имя", "email", "Телефон","Работает", "Админ"};
        DefaultTableModel dfm = new DefaultTableModel(header, 0){

            @Override
            public boolean isCellEditable(int x, int y) {
                return false;
            }
        };

        for(IData item : userList) {
            dfm.addRow(item.toObjects());
        }
        return new JTable(dfm);
    }

    @Override
    public void showEditWindow(IData item) {
        //EditUserWindow ew = new EditUserWindow(controller.getMainframe(), "Редактировать", item);
        JDialog editDialog = createEditDialog("Редактировать", true, item);
        editDialog.setVisible(true);
    }

    private JDialog createEditDialog(String title, boolean modal, IData item){

        JButton acceptBtn, cancelBtn;

        JLabel lblName, lblEmail, lblPhone;
        JTextField txtName, txtEmail;
        JFormattedTextField ftxtPhone;
        JCheckBox chkStatus, chkAdmin;

        JDialog dialog = new JDialog(controller.getMainframe(), title, modal);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 250);
        dialog.setLocation(350,300);
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblName = new JLabel("Имя");
        lblEmail = new JLabel("Email");
        lblPhone = new JLabel("Телефон");

        txtName = new JTextField(((User) item).getName());
        txtEmail = new JTextField(((User) item).getEmail());
        ftxtPhone = new JFormattedTextField(Utils.createFormatter("+###-##-###-####"));
        ftxtPhone.setText(((User) item).getPhone());
        ftxtPhone.setColumns(16);


        // TODO: добавить обновление таблицы, если изменились данные
        chkStatus = new JCheckBox("Работает", ((User) item).isStatus());
        chkAdmin = new JCheckBox("Админ", ((User) item).isSuperuser());

        acceptBtn = new JButton("Сохранить");
        acceptBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setItem(new User(((User)item).getId(),
                        txtName.getText(),
                        txtEmail.getText(),
                        ftxtPhone.getText(),
                        ((User) item).getPassword(),
                        chkStatus.isSelected(),
                        chkAdmin.isSelected()));
                controller.saveItem();

                dialog.setVisible(false);
            }
        });
        cancelBtn = new JButton("Отмена");

        controlPanel.add(acceptBtn);
        controlPanel.add(cancelBtn);

        contentPanel.add(lblName);
        contentPanel.add(txtName);
        contentPanel.add(lblEmail);
        contentPanel.add(txtEmail);
        contentPanel.add(lblPhone);
        contentPanel.add(ftxtPhone);
        contentPanel.add(chkStatus);
        contentPanel.add(chkAdmin);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        return dialog;
    }

    public JTable getUserTable() {
        return itemsTable;
    }

    public JButton getEditBtn() {
        return editBtn;
    }



} // UsersPanel
