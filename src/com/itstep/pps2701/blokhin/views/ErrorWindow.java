package com.itstep.pps2701.blokhin.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Vit on 11.04.2017.
 */
public class ErrorWindow  extends JFrame {
    private JLabel errorTextArea;
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


        Box outerBox = new Box(BoxLayout.Y_AXIS);

        errorTextArea = new JLabel("<html>" + errorText);


        btnOk = new JButton("Ok");
        btnOk.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        outerBox.add(errorTextArea);
        outerBox.add(btnOk);

        getContentPane().add(outerBox);

        setVisible(true);
    }
}
