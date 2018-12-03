package com.project.cmsc436.honeyguide;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.widget.Button;
import android.widget.ListView;
import android.view.ViewGroup;
import android.widget.Toast;
import android.util.Log;

public class SavedFragment extends Fragment {

    private final String TAG = "SavedFragmet";
    ArrayList<String> list = new ArrayList<String>();
    //ArrayList<String> selectList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView listView;

    public static SavedFragment newInstance() {
        SavedFragment fragment = new SavedFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_saved, container, false);

        /*Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        */
        final MainActivity activity = (MainActivity) getActivity();
        list = activity.getList();
        //selectList = activity.getSelectList();

        if(!list.isEmpty()) {
            view.findViewById(android.R.id.empty).setVisibility(View.GONE);
        }

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);

        listView = (ListView) view.findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"Clicked on saved piece: "+list.get(position));
                activity.setPiecebyName(list.get(position));
                FragmentTransaction pieceTransaction = activity.getSupportFragmentManager().beginTransaction();
                pieceTransaction.replace(R.id.frame_layout, new SavedPieceFragment());
                pieceTransaction.commit();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int pos = position;

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.dialog_message2).setTitle(R.string.dialog_title);

                // Add the buttons
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "Deleted art piece: "+list.get(pos), Toast.LENGTH_LONG).show();
                        adapter.remove(list.get(pos));
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog

                        //Do nothing?
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

       listView.setAdapter(adapter);
       return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.saved_pieces_menu, menu);
    }



}