package com.project.cmsc436.honeyguide;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import io.chirp.connect.ChirpConnect;
import io.chirp.connect.interfaces.ConnectEventListener;
import io.chirp.connect.interfaces.ConnectSetConfigListener;
import io.chirp.connect.models.ChirpError;

import static android.support.v4.content.ContextCompat.*;

public class PieceFragment extends Fragment {
    private TextView textView;
    private String item;
    private ImageView imageView;
    private ShareActionProvider mShareActionProvider;

    public static PieceFragment newInstance() {
        PieceFragment fragment = new PieceFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_piece, container, false);

        MainActivity activity = (MainActivity) getActivity();
        ArrayList<String> list = activity.getList();

        item = activity.getItem();

        imageView = (ImageView) view.findViewById(R.id.imageView1);
        imageView.setImageResource(R.drawable.wip);

        textView = (TextView) view.findViewById(R.id.piece);
        textView.setText(item);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        return view;
    }


}