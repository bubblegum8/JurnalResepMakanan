package com.iqbal.jurnalresepmakanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class ListMasakan extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_masakan);

        String category = getIntent().getStringExtra("category");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(category);
        ArrayList<Resep> resep = new JSONParser(this).getRecipesFromCategory(category);

        adapter = new RecipeListAdapter(this, recipes);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
