package com.iqbal.jurnalresepmakanan;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class ResepDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_masakan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Resep Detail");

        Resep resep = (Resep)getIntent().getSerializableExtra("resep");
        renderResep(resep);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    protected void renderResep(Resep rec) {
        // set resep name
        TextView resepName = ((TextView)findViewById(R.id.nama_masakan));
        resepName.setText(rec.getName());
        if (rec.getName().length() > 25)
            resepName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        // set resep time
        ((TextView)findViewById(R.id.waktu_masakan)).setText(rec.getTime());
        // set resep size
        ((TextView)findViewById(R.id.porsi_masakan)).setText(rec.getSize());
        // set resep image
        try {
            // get input stream
            InputStream ims = this.getAssets().open(rec.getImage());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            ((ImageView)findViewById(R.id.gambar_masakan)).setImageDrawable(d);
            ims .close();
        }
        catch(IOException e) {
            e.getStackTrace();
        }
        // set resep ingredients
        ((TextView)findViewById(R.id.bahan_masakan)).setText(Html.fromHtml(rec.getIngredientsHTML()));
        // set resep steps
        ((TextView)findViewById(R.id.tahap_masakan)).setText(Html.fromHtml(rec.getStepsHTML()));
        // set resep source url
        ((Button)findViewById(R.id.resep_source)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(rec.getUrl()));
                startActivity(intent);
            }
        });
    }
}