package com.iqbal.jurnalresepmakanan;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONParser {

    private Context context;


    public JSONParser(Context context){
        this.context = context;
    }

    public ArrayList<Category> getresepCategories() {
        ArrayList<Category> categories = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject jsonValue = (JSONObject)obj.get(key);

                String nama = key;
                String image = jsonValue.get("image").toString();

                Category category = new Category();
                category.setnama(nama);
                category.setImage(image);

                categories.add(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public ArrayList<resep> getresepsFromCategory(String category) {
        ArrayList<resep> reseps = new ArrayList<>();
        try {
            JSONArray resepArray = new JSONObject(loadJSONFromAsset())
                    .getJSONObject(category)
                    .getJSONArray("resep");

            for(int i = 0; i < resepArray.length(); i++){
                JSONObject DapurUmamiJson = resepArray.getJSONObject(i);

                String nama = DapurUmamiJson.getString("nama");
                String image = DapurUmamiJson.getString("image");
                String waktu = DapurUmamiJson.getString("waktu");
                String size = DapurUmamiJson.getString("size");

                Log.d("resepnama", nama);
                Log.d("resepImage", image);


                JSONArray jsonArray = DapurUmamiJson.getJSONArray("bahan");
                List<String> bahan = new ArrayList<>();
                for(int j = 0; j < jsonArray.length(); j++){
                    bahan.add(jsonArray.getString(j));
                }

                jsonArray = DapurUmamiJson.getJSONArray("tahapan");
                List<String> tahapan = new ArrayList<>();
                for(int j = 0; j < jsonArray.length(); j++){
                    tahapan.add(jsonArray.getString(j));
                }

                Resep resep = new resep();
                resep.setnama(nama);
                resep.setImage(image);
                resep.setwaktu(waktu);
                resep.setSize(size);
                resep.setbahan(bahan);
                resep.settahapan(tahapan);

                resep.printData();

                reseps.add(resep);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reseps;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("DapurUmami.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
