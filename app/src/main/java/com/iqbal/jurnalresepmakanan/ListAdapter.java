package com.iqbal.jurnalresepmakanan;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ResepViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private final ArrayList<Resep> resepList;

    public ListAdapter(Context context, ArrayList<Resep> resepList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.resepList = resepList;
    }

    @Override
    public ResepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.adapter_home, parent, false);
        return new ResepViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ResepViewHolder holder, int position) {
        Resep currentRecipe = resepList.get(position);
        // set recycler view card text
        holder.cardText.setText(currentRecipe.getnama());
        if (currentRecipe.getnama().length() > 25)
            holder.cardText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        // set recycler view card image
        try {
            // get input stream
            InputStream ims = context.getAssets().open(currentRecipe.getImage());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            holder.cardImage.setImageDrawable(d);
            ims .close();
        }
        catch(IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return resepList.size();
    }

    public class ResepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView cardText;
        public final ImageView cardImage;
        final ListAdapter mAdapter;

        public ResepViewHolder(View itemView, ListAdapter adapter) {
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
            Resep resep = resepList.get(mPosition);

            Intent intent = new Intent(context, ResepDetail.class);
            intent.putExtra("resep", resep);

            context.startActivity(intent);
        }
    }

}