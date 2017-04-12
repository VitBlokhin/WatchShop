package com.itstep.pps2701.blokhin.data;

/**
 * Created by Vit on 07.04.2017.
 */
public class Watch implements IData  {
    private int id;             // id пользователя
    private String mark;        // марка часов
    private double price;       // цена
    private int quantity;       // количество
    private boolean visible;    // видимость
    private int producerId;     // id производителя
    private int typeId;         // id типа часов

    public Watch(int id, String mark, double price, int quantity, boolean visible, int producerId, int typeId) {
        this.id = id;
        this.mark = mark;
        this.price = price;
        this.quantity = quantity;
        this.visible = visible;
        this.producerId = producerId;
        this.typeId = typeId;
    } // Watch

    public Watch(String mark, double price, int quantity, boolean visible, int producerId, int typeId) {
        this.mark = mark;
        this.price = price;
        this.quantity = quantity;
        this.visible = visible;
        this.producerId = producerId;
        this.typeId = typeId;
    } // Watch

    public int getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getProducerId() {
        return producerId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Watch{" + "mark='" + mark + '\'' + ", price=" + price + ", quantity=" + quantity + ", visible=" + visible + ", producerId=" + producerId + ", typeId=" + typeId + '}';
    } // toString

    @Override
    public Object[] toObjects() {
        return new Object[]{getId(), getMark(), getPrice(), getQuantity(), isVisible(), getProducerId(), getTypeId()};
    }
} // class Watch

