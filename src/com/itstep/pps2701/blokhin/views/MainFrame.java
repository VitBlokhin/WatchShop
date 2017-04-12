package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.Session;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vit on 12.04.2017.
 */
public class MainFrame extends JFrame {
    // JMenuBar menubar;
    // JMenu menu;
    private JTabbedPane tabbedPane;
    private ContentPanel panel;


    public MainFrame(String title) throws HeadlessException {
        super(title);

        buildGUI();
    } // MainFrame

    private void buildGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(600,500);
        setLocationRelativeTo(null);
        setResizable(false);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        getContentPane().add(tabbedPane);

        setVisible(true);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
