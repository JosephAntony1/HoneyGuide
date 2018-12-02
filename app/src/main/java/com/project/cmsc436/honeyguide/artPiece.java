package com.project.cmsc436.honeyguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.net.URL;
import java.util.*;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;

public class artPiece extends AppCompatActivity {

    private final String TAG = "Art Piece Page";

    private Map<String,String> pieces = new HashMap<>();

    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_piece);

        //set up id - art piece name correlation
        loadPieces(pieces);

        //fetch the id received
        Intent received = getIntent();
        String id = received.getStringExtra("num");
        String name = pieces.get(id);

        //fetch content from local SharedPreference
        sharedpreferences = getSharedPreferences(name, Context.MODE_PRIVATE);
        String date = sharedpreferences.getString("Date made",null);
        String artist = sharedpreferences.getString("Artist",null);
        String description1 = sharedpreferences.getString("Description1",null);
        String description2 = sharedpreferences.getString("Description2",null);
        String description3 = sharedpreferences.getString("Description3",null);
        String url = sharedpreferences.getString("Image_URL",null);

        //get references to views
        ImageView art = (ImageView) findViewById(R.id.imageView);
        TextView title = (TextView)findViewById(R.id.Title);
        TextView date_artist = (TextView)findViewById(R.id.Date_Artist);
        TextView p1 = (TextView)findViewById(R.id.Description1);
        TextView p2 = (TextView)findViewById(R.id.Description2);
        TextView p3 = (TextView)findViewById(R.id.Description3);

        //update the view
        //image downloading
        String date_artist_str = date+", "+artist;
        art.setImageDrawable(LoadImageFromWebOperations(url));
        title.setText(name);
        date_artist.setText(date_artist_str);
        p1.setText(description1);
        p2.setText(description2);
        p3.setText(description3);

    }

    private void loadPieces(Map<String,String> pieces){
        pieces.put("1","Sunflowers");
        pieces.put("2","A Young Woman standing at a Virginal");
        pieces.put("3","The Fighting Temeraire");
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }


}
