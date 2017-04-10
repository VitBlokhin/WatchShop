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

    private JLabel lblUrl, lblUsername, lblPwd;
    JTextField urlField, usernameField;
    JPasswordField passwordField;
    private JButton connectBtn;

    private String url, username, password;

    public ConnectionWindow(String title, Session session) throws HeadlessException {
        super(title);
        this.session = session;

        buildGUI();
    }

    private void buildGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(400, 380);
        setLocationRelativeTo(null);
        setResizable(false);

        urlField = new JTextField(15);
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        passwordField.setEchoChar('☼');

        connectBtn = new JButton("Connect");

        connectBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                session.connect(urlField.getText(), usernameField.getText(), passwordField.getText());
            }
        });

        setVisible(true);
    }

} // class ConnectionWindow
