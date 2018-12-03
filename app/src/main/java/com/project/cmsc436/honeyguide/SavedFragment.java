package com.project.cmsc436.honeyguide;

import java.util.ArrayList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.widget.ListView;
import android.view.ViewGroup;

public class SavedFragment extends Fragment {


    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView listView;

    public static SavedFragment newInstance() {
        SavedFragment fragment = new SavedFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_saved, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        }


        final MainActivity activity = (MainActivity) getActivity();
        list = activity.getList();


        if(!list.isEmpty()) {
            view.findViewById(android.R.id.empty).setVisibility(View.GONE);
        }

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);

        listView = (ListView) view.findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                activity.setPiece((String) listView.getItemAtPosition(position));
                FragmentTransaction pieceTransaction = activity.getSupportFragmentManager().beginTransaction();
                pieceTransaction.replace(R.id.frame_layout, new PieceFragment());
                pieceTransaction.commit();
                /*Intent intent = new Intent(getActivity(), ShowPiece.class);
                intent.putStringArrayListExtra("pieces", list);
                intent.putExtra("piece", listView.getItemAtPosition(position).toString());
                startActivity(intent);*/
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                view.findViewById(R.id.piece_garbage_button).setVisibility(View.VISIBLE);
                //list.remove(position);
                //recreate();
                return true;
            }
        });


        /** Setting the adapter to the ListView */
       listView.setAdapter(adapter);

       return view;
    }



}