package com.itstep.pps2701.blokhin.data;

/**
 * Created by Vit on 12.04.2017.
 */
public class WatchType implements IData {
    private int id;
    private String typename;

    public WatchType(String typename) {
        this.typename = typename;
    }

    public WatchType(int id, String typename) {
        this.id = id;
        this.typename = typename;
    }


    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getId() {
        return id;
    }

    public String getTypename() {
        return typename;
    }

    @Override
    public String toString() {
        return typename;
    } // toString

    @Override
    public Object[] toObjects() {
        return new Object[] {getId(), getTypename()};
    }

} // class WatchType
