package com.itstep.pps2701.blokhin.controllers;

import com.itstep.pps2701.blokhin.data.IData;
import com.itstep.pps2701.blokhin.data.WatchProducer;
import com.itstep.pps2701.blokhin.data.WatchType;
import com.itstep.pps2701.blokhin.models.ProducerModel;
import com.itstep.pps2701.blokhin.models.WatchModel;
import com.itstep.pps2701.blokhin.models.WatchTypeModel;
import com.itstep.pps2701.blokhin.views.ErrorWindow;
import com.itstep.pps2701.blokhin.views.WatchesPanel;
import com.oracle.jrockit.jfr.Producer;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vit on 13.04.2017.
 */
public class WatchController extends Controller {

    private List<IData> producerList;
    private List<IData> watchTypeList;

    private ProducerModel pModel;
    private WatchTypeModel wtModel;

    public List<IData> getProducerList() {
        return producerList;
    }

    public List<IData> getWatchTypeList() {
        return watchTypeList;
    }

    public WatchController() {
    }

    public void init(JTabbedPane pane, JFrame frame) throws SQLException {
        model = new WatchModel();
        pModel = new ProducerModel();
        wtModel = new WatchTypeModel();

        setProducerList();
        setWatchTypeList();

        itemList = model.getItemList();
        this.frame = frame;
        view = new WatchesPanel(pane, "Часы", "Работа со списком часов", itemList, this);
    } // init

    private void setProducerList() {
        try {
            this.producerList = pModel.getItemList();
        } catch(Exception ex) {
            ErrorWindow ew = new ErrorWindow("Ошибка загрузки данных", ex.getMessage());
        }
    }

    private void setWatchTypeList() {
        try {
            this.watchTypeList = wtModel.getItemList();
        } catch(Exception ex) {
            ErrorWindow ew = new ErrorWindow("Ошибка загрузки данных", ex.getMessage());
        }
    }

    public Object[] watchToObjects(IData watch){
        Object[] obj = watch.toObjects();
        for(IData producer : producerList) {
            if(((WatchProducer) producer).getId() == (Integer)obj[5]) obj[5] = ((WatchProducer)producer).getName();
        }
        for(IData type : watchTypeList) {
            if(((WatchType)type).getId() == (Integer)obj[6]) obj[6] = ((WatchType)type).getTypename();
        }
        return obj;
    }
}
