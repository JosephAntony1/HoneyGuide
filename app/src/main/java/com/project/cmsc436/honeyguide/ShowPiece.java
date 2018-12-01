package com.project.cmsc436.honeyguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.Button;


public class ShowPiece extends AppCompatActivity {


    TextView textView;
    String item;
    RatingBar ratingBar;
    ImageView imageView;
    Button share;
    Button delete;

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

        share = (Button) findViewById(R.id.shareButton);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                //TODO: need a name and a body from previous activity
                String shareBody = item;
                String shareSubject = item;

                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

                startActivity(Intent.createChooser(shareIntent, "Share using"));


            }
        });


        ratingBar = findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(),"Your Selected Ratings  : " + String.valueOf(rating),Toast.LENGTH_LONG).show();
            }
        });




    }
}
