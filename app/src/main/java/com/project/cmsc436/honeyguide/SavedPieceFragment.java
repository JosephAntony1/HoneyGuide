package com.project.cmsc436.honeyguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.widget.SeekBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import android.os.Handler;
import java.io.IOException;

public class SavedPieceFragment extends Fragment {
    private final String TAG = "PieceFragment";
    private SharedPreferences sharedpreferences;
    private MediaPlayer mediaPlayer;
    private SeekBar seekbar;
    private double startTime = 0;
    private double finalTime = 0;
    public static int oneTimeOnly = 0;
    private Handler myHandler;


    public static SavedPieceFragment newInstance() {
        SavedPieceFragment fragment = new SavedPieceFragment();
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
        final MainActivity activity = (MainActivity) getActivity();

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        //fetch the id received
        String name = activity.getPiece();

        //fetch content from local SharedPreference
        sharedpreferences = activity.getSharedPreferences(name, Context.MODE_PRIVATE);
        String date = sharedpreferences.getString("Date made",null);
        String artist = sharedpreferences.getString("Artist",null);
        String description1 = sharedpreferences.getString("Description1",null);
        String description2 = sharedpreferences.getString("Description2",null);
        String description3 = sharedpreferences.getString("Description3",null);
        String url = sharedpreferences.getString("Image_URL",null);
        String audio_url = sharedpreferences.getString("Audio_URL",null);

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

        mediaPlayer = new MediaPlayer();
        myHandler = new Handler();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        try{
            mediaPlayer.setDataSource(audio_url);
            mediaPlayer.prepareAsync();
        }catch(IOException e){
            Log.e(TAG,"audio error");
        }

        final Button play = (Button)view.findViewById(R.id.playButton);
        final Button pause = (Button)view.findViewById(R.id.pauseButton);

        seekbar = (SeekBar)view.findViewById(R.id.seekBar);
        seekbar.setClickable(false);
        pause.setEnabled(false);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer player) {
                Toast.makeText(activity, "Press Play button to hear the audio!", Toast.LENGTH_LONG).show();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(new Runnable() {
                    public void run() {
                        startTime = mediaPlayer.getCurrentPosition();
                        seekbar.setProgress((int)startTime);
                        myHandler.postDelayed(this, 100);
                    }
                },100);

                pause.setEnabled(true);
                play.setEnabled(false);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                pause.setEnabled(false);
                play.setEnabled(true);
            }
        });

        return view;
    }

//    @Override
//    public void onDestroyView(){
//        super.onDestroyView();
//        mediaPlayer.stop();
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.already_saved_piece, menu);
    }
}