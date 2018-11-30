package com.project.cmsc436.honeyguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class defaultPiece extends AppCompatActivity {

    private final String TAG = "default piece page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_piece);

        Button artPiece1 = findViewById(R.id.artPiece_1_button);
        artPiece1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"clicked the test button to gather art piece information");
                loadArtPiece("1");
            }
        });

        Button artPiece2 = findViewById(R.id.artPiece_2_button);
        artPiece2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"clicked the test button to gather art piece information");
                loadArtPiece("2");
            }
        });

        Button artPiece3 = findViewById(R.id.artPiece_3_button);
        artPiece3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"clicked the test button to gather art piece information");
                loadArtPiece("3");
            }
        });
    }

    public void loadArtPiece(String num){
        Intent newIntent = new Intent(defaultPiece.this,artPiece.class);
        newIntent.putExtra("num",num);
        startActivity(newIntent);
    }
}
