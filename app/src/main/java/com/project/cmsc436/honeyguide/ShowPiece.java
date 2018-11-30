package com.project.cmsc436.honeyguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Toast;

public class ShowPiece extends AppCompatActivity {


    TextView textView;
    String item;
    RatingBar ratingBar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_piece);

        Intent intent = getIntent();
        item = intent.getStringExtra("piece");

        imageView = (ImageView) findViewById(R.id.imageView1);
        imageView.setImageResource(R.drawable.wip);

        textView = (TextView) findViewById(R.id.piece);
        textView.setText(item);

        ratingBar = findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(),"Your Selected Ratings  : " + String.valueOf(rating),Toast.LENGTH_LONG).show();
            }
        });
    }
}
