package com.itstep.pps2701.blokhin.data;

/**
 * Created by Vit on 12.04.2017.
 */
public class WatchProducer implements IData{
    private int id;
    private String name;
    private String country;

    public WatchProducer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public WatchProducer(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "WatchProducer{" + "id=" + id + ", name='" + name + '\'' + ", country='" + country + '\'' + '}';
    } // toString
} // class WatchProducer
