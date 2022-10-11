package com.example.adatest;

public class ButikerModels {
    String ButikNamn;
    int bild;
    String favstatus;

    public ButikerModels(String butikNamn, int bild) {
        ButikNamn = butikNamn;
        this.bild = bild;
    }

    public String getButikNamn() {
        return ButikNamn;
    }

    public int getBild() {
        return bild;
    }

    public String getFavstatus() {
        return favstatus;
    }

    public void setFavstatus(String favstatus) {
        this.favstatus = favstatus;
    }
}
