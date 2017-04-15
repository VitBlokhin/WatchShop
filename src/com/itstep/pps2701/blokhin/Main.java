package com.itstep.pps2701.blokhin;

import com.itstep.pps2701.blokhin.controllers.*;
import com.itstep.pps2701.blokhin.system.Utils;
import com.itstep.pps2701.blokhin.views.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SessionController sessionController = new SessionController();
        sessionController.createMainFrame();
    } // main
} // class Main
