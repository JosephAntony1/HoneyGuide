package com.project.cmsc436.honeyguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SavedFragment extends Fragment {
    /**
     * Items entered by the user is stored in this ArrayList variable
     */
    ArrayList<String> list = new ArrayList<>();

    /**
     * Declaring an ArrayAdapter to set items to ListView
     */
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

        MainActivity activity = (MainActivity) getActivity();
        list = activity.getList();

        if(!list.isEmpty()) {
            view.findViewById(android.R.id.empty).setVisibility(View.GONE);
        }

        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);

        listView = (ListView) view.findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ShowPiece.class);
                intent.putExtra("piece", listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

        /** Setting the adapter to the ListView */
        listView.setAdapter(adapter);

        return view;
    }
}
