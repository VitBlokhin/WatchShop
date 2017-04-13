package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.data.IData;

import javax.swing.*;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public interface IView {

    JButton getEditBtn();
    JButton getAddBtn();
    JTable getItemsTable();
    void showEditWindow(IData item);
    // void rebuildPanel(List<IData> userList);
}
