package com.itstep.pps2701.blokhin.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Vit on 11.04.2017.
 */
public class ErrorWindow  extends JFrame {
    private JTextArea errorTextArea;
    private JButton btnOk;
    private String errorText;

    public ErrorWindow(String title, String text) throws HeadlessException {
        super(title);
        errorText = text;
        buildGUI();
    }

    private void buildGUI() {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);


        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));

        errorTextArea = new JTextArea(5,20);
        errorTextArea.setText(errorText);
        errorTextArea.setLineWrap(true);
        errorTextArea.setEditable(false);
        btnOk = new JButton("Ok");
        btnOk.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        p1.add(errorTextArea);
        p1.add(Box.createVerticalStrut(15));
        p1.add(btnOk);

        getContentPane().add(p1, "North");

        setVisible(true);
    }
}
