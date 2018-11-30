package com.project.cmsc436.honeyguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class artPiece extends AppCompatActivity {

    private final String TAG = "Art Piece Page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_piece);

        //get button references
        Button startButton = findViewById(R.id.saveArtPiece_button);
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG,"click the start button");
                //after visitor click
                Intent newIntent = new Intent(artPiece.this,MainActivity.class);
                startActivity(newIntent);
            }
        });

    }
}
