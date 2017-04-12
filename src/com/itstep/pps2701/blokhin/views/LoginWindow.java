package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Session;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vit on 11.04.2017.
 */
public class LoginWindow extends JFrame {
    private Session session;

    private JLabel usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    private JButton loginBtn;

    public LoginWindow(String title, Session session) throws HeadlessException {
        super(title);
        this.session = session;

        //buildGUI();
    }

}
