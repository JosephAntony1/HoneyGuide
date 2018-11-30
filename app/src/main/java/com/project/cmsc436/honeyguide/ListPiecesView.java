package com.project.cmsc436.honeyguide;

import java.util.ArrayList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.widget.ListView;

/** Note that here we are inheriting ListActivity class instead of Activity class **/
public class ListPiecesView extends AppCompatActivity {

    /**
     * Items entered by the user is stored in this ArrayList variable
     */
    ArrayList<String> list = new ArrayList<String>();

    /**
     * Declaring an ArrayAdapter to set items to ListView
     */
    ArrayAdapter<String> adapter;

    ListView listView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_piece_view);

        Intent intent = getIntent();
        list = intent.getStringArrayListExtra("pieces");
        if(!list.isEmpty()) {
            findViewById(android.R.id.empty).setVisibility(View.GONE);
        }

        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);



        listView = (ListView) findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListPiecesView.this, ShowPiece.class);
                intent.putExtra("piece", listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

        /** Setting the adapter to the ListView */
        listView.setAdapter(adapter);
    }
}