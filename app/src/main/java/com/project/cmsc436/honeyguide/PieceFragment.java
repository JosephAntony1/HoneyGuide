package com.project.cmsc436.honeyguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Map;

public class PieceFragment extends Fragment {
    private SharedPreferences sharedpreferences;

    public static PieceFragment newInstance() {
        PieceFragment fragment = new PieceFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_piece, container, false);
        MainActivity activity = (MainActivity) getActivity();

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        //set up id - art piece name correlation
        loadPieces(activity.getPieces());


        //fetch the id received
        String id = activity.getPiece();
        String name = activity.getPieces().get(id);

        //fetch content from local SharedPreference
        sharedpreferences = activity.getSharedPreferences(name, Context.MODE_PRIVATE);
        String date = sharedpreferences.getString("Date made",null);
        String artist = sharedpreferences.getString("Artist",null);
        String description1 = sharedpreferences.getString("Description1",null);
        String description2 = sharedpreferences.getString("Description2",null);
        String description3 = sharedpreferences.getString("Description3",null);
        String url = sharedpreferences.getString("Image_URL",null);

        //get references to views
        ImageView art = (ImageView) view.findViewById(R.id.imageView);
        TextView title = (TextView) view.findViewById(R.id.Title);
        TextView date_artist = (TextView) view.findViewById(R.id.Date_Artist);
        TextView p1 = (TextView) view.findViewById(R.id.Description1);
        TextView p2 = (TextView) view.findViewById(R.id.Description2);
        TextView p3 = (TextView) view.findViewById(R.id.Description3);

        //update the view
        //image downloading
        String date_artist_str = date+", "+artist;
        Picasso.with(activity.getApplicationContext()).load(url).into(art);
        title.setText(name);
        date_artist.setText(date_artist_str);
        p1.setText(description1);
        p2.setText(description2);
        p3.setText(description3);

        return view;
    }

    private void loadPieces(Map<String,String> pieces){
        pieces.put("1","Sunflowers");
        pieces.put("2","A Young Woman standing at a Virginal");
        pieces.put("3","The Fighting Temeraire");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.pieces_menu_fragment, menu);
    }
}