package com.iqbal.jurnalresepmakanan;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.RecipeViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private final ArrayList<Category> categories;

    public HomeAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.categories = categories;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.adapter_home, parent, false);
        return new RecipeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Category currentCategory = categories.get(position);
        // set recycler view card text
        holder.cardText.setText(currentCategory.getNama());
        // set recycler view card image
        try {
            // get input stream
            InputStream ims = context.getAssets().open(currentCategory.getImage());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            holder.cardImage.setImageDrawable(d);
            ims .close();
        }
        catch(IOException e) {
            e.getStackTrace();
        }

        Log.d("MyTag", currentCategory.getNama());
        Log.d("MyTag", currentCategory.getImage());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView cardText;
        public final ImageView cardImage;
        final HomeAdapter mAdapter;

        public RecipeViewHolder(View itemView, HomeAdapter adapter) {
            super(itemView);
            this.cardText = itemView.findViewById(R.id.nama_masakan);
            this.cardImage = itemView.findViewById(R.id.gambar_masakan);
            this.mAdapter = adapter;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            String category = categories.get(mPosition).getNama();

            Intent intent = new Intent(context, ListAdapter.class);
            intent.putExtra("category", category);

            context.startActivity(intent);
        }
    }

}
