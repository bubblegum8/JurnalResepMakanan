package com.iqbal.jurnalresepmakanan;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

    private String nama;
    private String image;

    public String getNama() {
        return nama;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}