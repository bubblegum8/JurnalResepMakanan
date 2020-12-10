package com.iqbal.jurnalresepmakanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;
import android.os.Bundle;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Kategori Masakan");

        TextView halloUser = findViewById(R.id.hallo_user);
        halloUser.setText(String.format("Selamat Datang %s !", getIntent().getStringExtra("username")));

        ArrayList<Category> categories = new JSONParser(this).getresepCategories();
        adapter = new HomeAdapter(this, categories);


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }
}
