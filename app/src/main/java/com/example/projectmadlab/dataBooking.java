package com.example.projectmadlab;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class dataBooking {
    String id;
    String name;
    String address;
    String cekin;
    String cekout;
    String totalharga;

    public dataBooking(String id, String name, String address, String cekin, String cekout, String totalharga) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cekin = cekin;
        this.cekout = cekout;
        this.totalharga = totalharga;



    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCekin() {
        return cekin;
    }

    public void setCekin(String cekin) {
        this.cekin = cekin;
    }

    public String getCekout() {
        return cekout;
    }

    public void setCekout(String cekout) {
        this.cekout = cekout;
    }

    public String getTotalharga() {
        return totalharga;
    }

    public void setTotalharga(String totalharga) {
        this.totalharga = totalharga;
    }


}



