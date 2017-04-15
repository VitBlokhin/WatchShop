package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.controllers.SessionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Vit on 15.04.2017.
 */
public class MainFrame extends JFrame {
    private JMenu menuFile;
    private JMenu menuQueries; // TODO
    private JMenuItem mnuLogin;
    private JMenuItem mnuLogoff;

    private SessionController sessionController;

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    private JTabbedPane tabbedPane;

    public MainFrame(String title) throws HeadlessException {
        super(title);

        buildGUI();
    } // MainWindow

    public void buildGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(600,500);
        setLocationRelativeTo(null);
        setResizable(false);

        setJMenuBar(createMenuBar());

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        getContentPane().add(tabbedPane);

        setVisible(true);
    } // buildGUI

    public void rebuildGUI(){
        setJMenuBar(createMenuBar());

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        getContentPane().add(tabbedPane);

        setVisible(true);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        menuFile = new JMenu("Файл");

        mnuLogin = new JMenuItem("Войти...");
        mnuLogoff = new JMenuItem("Выйти");
        mnuLogoff.setEnabled(false);

        mnuLogin.addActionListener(new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
               showLoginDialog();
           }
       });

        mnuLogoff.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sessionController.logoff();
                mnuLogin.setEnabled(true);
                this.setEnabled(false);
            }
        });

        JMenuItem mnuExit = new JMenuItem("Выход");
        mnuExit.addActionListener(e -> System.exit(0));

        menuFile.add(mnuLogin);
        menuFile.add(mnuLogoff);
        menuFile.addSeparator();
        menuFile.add(mnuExit);
        menuBar.add(menuFile);

        return menuBar;
    }

    public void showLoginDialog() {
        JButton btnAccept, btnClose;
        JLabel lblName, lblPassword;
        JTextField txtName;
        JPasswordField ptxtPassword;

        JDialog dialog = new JDialog(this, "Вход в систему", true);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 200);
        dialog.setLocation(350,300);
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblName = new JLabel("Имя");
        lblPassword = new JLabel("Пароль");

        txtName = new JTextField();
        ptxtPassword = new JPasswordField();

        btnAccept = new JButton("Войти");
        btnAccept.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sessionController.login(
                        txtName.getText(),
                        String.valueOf(ptxtPassword.getPassword()
                        ));


                mnuLogoff.setEnabled(true);
                mnuLogin.setEnabled(false);

                dialog.setVisible(false);
                dialog.dispose();
            }
        });

        btnClose = new JButton("Отмена");
        btnClose.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
            }
        });

        controlPanel.add(btnAccept);
        controlPanel.add(btnClose);

        contentPanel.add(lblName);
        contentPanel.add(txtName);
        contentPanel.add(lblPassword);
        contentPanel.add(ptxtPassword);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    // Показать диалог ошибки
    public void showErrorDialog(String title, String errorText){
        JButton btnClose;

        JLabel lblName;

        JDialog dialog = new JDialog(this, title, true);
        dialog.setLayout(new BorderLayout(5,5));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(300, 200);
        dialog.setLocation(350,300);
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,5));

        lblName = new JLabel("<html>" + errorText);

        btnClose = new JButton("Ок");
        btnClose.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                dialog.dispose();
            }
        });

        controlPanel.add(btnClose);

        contentPanel.add(lblName);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    } // showErrorDialog


} // class MainFrame
