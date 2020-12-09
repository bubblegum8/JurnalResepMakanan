package com.example.foodrecipes;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {

    private String nama;
    private String image;
    private String waktu;
    private String size;
    private List<String> bahan;
    private List<String> tahapan;


    public String getnama() {
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

    public List<String> getbahan() {
        return bahan;
    }

    public void setbahan(List<String> bahan) {
        this.bahan = bahan;
    }

    public String getbahanHTML() {
        String htmlString = "";
        for (String bahan: bahan) {
            htmlString += String.format("&#8226 %s<br/>", bahan);
        }
        return htmlString;
    }

    public List<String> gettahapan() {
        return tahapan;
    }

    public void settahapan(List<String> tahapan) {
        this.tahapan = tahapan;
    }

    public String gettahapanHTML() {
        String htmlString = "";
        for (int i=0; i<tahapan.size(); i++) {
            htmlString += String.format("<b> Langkah-%d </b><br/>", (i+1));
            htmlString += String.format("%s <br/>", tahapan.get(i));
            if (i<tahapan.size()-1) htmlString += "<br/>";
        }
        return htmlString;
    }

    public void printData() {
        Log.d("Recipenama", nama);
        Log.d("Recipewaktu", waktu);
        Log.d("RecipeSize", size);
        Log.d("RecipeImage", image);

    }

    public String getwaktu() {
        return waktu;
    }

    public void setwaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}