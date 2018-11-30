package com.project.cmsc436.honeyguide;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import android.content.Intent;

import io.chirp.connect.ChirpConnect;
import io.chirp.connect.interfaces.ConnectEventListener;
import io.chirp.connect.interfaces.ConnectSetConfigListener;
import io.chirp.connect.models.ChirpError;
import io.chirp.connect.models.ConnectState;

/** Note that here we are inheriting ListActivity class instead of Activity class **/
public class MainActivity extends AppCompatActivity {

    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        Button btn1 = (Button) findViewById(R.id.btnShow);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItems();
            }
        });



    }

    public void addItem() {
        Log.i("i", "entered addItem()");
        EditText edit = (EditText) findViewById(R.id.txtItem);
        list.add(edit.getText().toString());
        edit.setText("");
    }

    public void showItems() {
        Log.i("i1", "entered showItems()");
        Intent intent = new Intent(this, ListPiecesView.class);
        intent.putStringArrayListExtra("pieces", list);
        startActivity(intent);
    }


}