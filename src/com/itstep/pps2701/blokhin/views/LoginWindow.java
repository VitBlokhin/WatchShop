package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Session;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vit on 11.04.2017.
 */
public class LoginWindow extends JFrame {
    private JFrame parent;

    private JLabel usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    private JButton loginBtn;

    public LoginWindow(String title, JFrame parent) throws HeadlessException {
        super(title);
        this.parent = parent;

        buildGUI();
    }

    private void buildGUI() {
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);



    }

}
