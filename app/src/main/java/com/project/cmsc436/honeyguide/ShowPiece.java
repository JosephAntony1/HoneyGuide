package com.project.cmsc436.honeyguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.ShareActionProvider;

import java.util.ArrayList;
import java.util.HashMap;


public class ShowPiece extends AppCompatActivity {


    private TextView textView;
    private String item;
    private ImageView imageView;
  //  private ArrayList<String> list = new ArrayList<String>();
  //  private HashMap<String, String> piece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_piece);

        Intent intent = getIntent();
       // list = intent.getStringArrayListExtra("pieces");

        item = intent.getStringExtra("piece");

        imageView = (ImageView) findViewById(R.id.imageView1);
        imageView.setImageResource(R.drawable.wip);

        textView = (TextView) findViewById(R.id.piece);
        textView.setText(item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pieces_menu_fragment, menu);
        return true;
    }

    public void onShareAction(MenuItem m) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBody = item;
        String shareSubject = item;

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

        startActivity(Intent.createChooser(shareIntent, "Share using"));
    }

    /*public void onGarbageAction(MenuItem m) {
        list.remove(item);*/
        /*Intent intent = new Intent(this, SavedFragment.class);
        intent.putStringArrayListExtra("pieces", list);
        startActivity(intent);*/
/*
        MainActivity activity = (MainActivity) getActivity();
        activity.addItem();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(SavedFragment.newInstance(), null);
        ft.commit();
    }
*/

}
