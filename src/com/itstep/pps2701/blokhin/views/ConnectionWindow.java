package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Session;
import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Vit on 10.04.2017.
 */
public class ConnectionWindow extends JFrame {
    private Session session;

    private JLabel addrLabel, portLabel, baseLabel, userLabel, passwordLabel;
    JTextField addrField, portField, baseField, usernameField;
    JPasswordField passwordField;
    private JButton connectBtn;

    private String url, username, password;

    public ConnectionWindow(String title, Session session) throws HeadlessException {
        super(title);
        this.session = session;

        buildGUI();
    }

    private void buildGUI() {
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(400, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        addrLabel = new JLabel("Адрес сервера:");
        portLabel = new JLabel("Порт сервера:");
        baseLabel = new JLabel("Название БД:");
        userLabel = new JLabel("Имя пользователя БД:");
        passwordLabel = new JLabel("Пароль:");

        addrField = new JTextField(15);
        portField = new JTextField(4);
        baseField = new JTextField(15);
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        passwordField.setEchoChar('☼');

        connectBtn = new JButton("Connect");
        connectBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    session.buildUrl(addrField.getText(), Integer.parseInt(portField.getText()), baseField.getText());
                    session.connect(usernameField.getText(), passwordField.getPassword().toString());
                    setVisible(false);
                    dispose();
                } catch (Exception ex) {
                    ErrorWindow errw = new ErrorWindow("Ошибка подключения к БД", ex.getMessage());
                }
            }
        });

        p1.add(addrLabel);
        p1.add(addrField);
        p1.add(portLabel);
        p1.add(portField);
        p1.add(baseLabel);
        p1.add(baseField);
        p1.add(userLabel);
        p1.add(usernameField);
        p1.add(passwordLabel);
        p1.add(passwordField);
        p1.add(connectBtn);

        getContentPane().add(p1, "North");

        setVisible(true);
    } // buildGUI

} // class ConnectionWindow
