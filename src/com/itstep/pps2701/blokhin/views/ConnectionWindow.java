package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Session;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vit on 10.04.2017.
 */
public class ConnectionWindow extends JFrame {
    private Session session;

    private JLabel lblUrl, lblUsername, lblPwd;
    private JButton connectBtn;

    private String url, username, password;

    public ConnectionWindow(String title) throws HeadlessException {
        super(title);

        buildGUI();
    }

    private void buildGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(400, 380);
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }

} // class ConnectionWindow
