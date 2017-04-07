package com.itstep.pps2701.blokhin.data;

/**
 * Created by Vit on 07.04.2017.
 */
public class User {
    private int id;             // id пользователя
    private String name;        // фио
    private String address;     // адрес
    private String phone;       // телефон
    private String password;    // пароль
    private boolean status;     // статус пользователя - работает/уволен
    private boolean superuser;  // принзнак суперпользователя

    public User(int id, String name, String address, String phone, String password, boolean status, boolean superuser) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.status = status;
        this.superuser = superuser;
    } // User

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isSuperuser() {
        return superuser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setSuperuser(boolean superuser) {
        this.superuser = superuser;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", address='" + address + '\'' + ", phone='" + phone + '\'' + ", password='" + password + '\'' + ", status=" + status + ", superuser=" + superuser + '}';
    }
} // class User
