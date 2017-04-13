package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.User;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

/**
 * Created by Vit on 13.04.2017.
 */
public class EditUserWindow extends JDialog{
    // "id", "Имя", "email", "Телефон","Статус", "Админ"
    protected IData item;

    protected JPanel mainPanel;
    protected JButton acceptBtn, cancelBtn;

    JLabel lblName, lblEmail, lblPhone;
    JTextField txtName, txtEmail;
    JFormattedTextField ftxtPhone;
    JCheckBox chkStatus, chkAdmin;

    public EditUserWindow(JFrame frame, String title, IData item) {
        super(frame, title, true);

        buildGUI();
    }

    void buildGUI() {
        mainPanel = new JPanel(new BorderLayout(5,5));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        JPanel contentPanel = new JPanel(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblName = new JLabel("Имя");
        lblEmail = new JLabel("Email");
        lblPhone = new JLabel("Телефон");

        txtName = new JTextField();
        txtEmail = new JTextField();

        try {
            MaskFormatter phone = new MaskFormatter("+###-##-###-####");
            phone.setPlaceholderCharacter('0');  // символ для отрисовки шаблона ввода
            ftxtPhone = new JFormattedTextField(phone);
            ftxtPhone.setColumns(15);
        } catch (Exception ex) {
            ErrorWindow ew = new ErrorWindow("Неверный формат номера: ", ex.getMessage());;
        }

        chkStatus = new JCheckBox("Работает", ((User) item).isStatus());
        chkAdmin = new JCheckBox("Админ", ((User) item).isSuperuser());

        acceptBtn = new JButton("Сохранить");
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

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
