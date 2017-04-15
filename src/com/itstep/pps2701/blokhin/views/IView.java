package com.itstep.pps2701.blokhin.views;

import com.itstep.pps2701.blokhin.data.IData;

import javax.swing.*;
import java.util.List;

/**
 * Created by Vit on 12.04.2017.
 */
public interface IView {

    void showEditDialog(IData item);
    void showAddDialog();
    void updateItemsTable();
}
