package com.project.cmsc436.honeyguide;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.chirp.connect.ChirpConnect;
import io.chirp.connect.interfaces.ConnectEventListener;
import io.chirp.connect.interfaces.ConnectSetConfigListener;
import io.chirp.connect.models.ChirpError;

import static android.support.v4.content.ContextCompat.*;

import android.content.Intent;

public class MicFragment extends Fragment {
    private String TAG = "Honeyguide-MicFragment";
    private final int RESULT_REQUEST_RECORD_AUDIO = 1;

    private String KEY;
    private String SECRET;

    String CONFIG = "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";

    ChirpConnect chirpConnect;
    ConnectEventListener connectEventListener;
    private SoundPool soundPool;
    private int soundID;
    private int streamID;

    Button testButton;

    public static MicFragment newInstance() {
        MicFragment fragment = new MicFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Activity activity = getActivity();

        final View view = inflater.inflate(R.layout.fragment_mic, container, false);

        KEY = getString(R.string.chirp_application_key);
        SECRET = getString(R.string.chirp_client_secret);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }

        if (soundPool != null) {
            soundID = soundPool.load(activity.getApplicationContext(), R.raw.chirptest, 1);
        } else {
            Toast.makeText(activity.getApplicationContext(), "Failed to load Chirp. Try again later.", Toast.LENGTH_LONG);
        }

        chirpConnect = new ChirpConnect(activity, KEY, SECRET);

        chirpConnect.setConfig(CONFIG, new ConnectSetConfigListener() {

            @Override
            public void onSuccess() {
                Log.i("setConfig", "Config successfully set.");
            }

            @Override
            public void onError(ChirpError setConfigError) {
                Log.e("setConfig", setConfigError.getMessage());
            }
        });

        connectEventListener = new ConnectEventListener() {
            @Override
            public void onSending(byte[] payload, byte channel) {
                Log.v(TAG, "This is called when a payload is being sent " + payload + " on channel: " + channel);
            }

            @Override
            public void onSent(byte[] payload, byte channel) {
                Log.v(TAG, "This is called when a payload has been sent " + new String(payload) + " on channel: " + channel);
            }

            @Override
            public void onReceiving(byte channel) {
                Log.v(TAG, "This is called when the SDK is expecting a payload to be received on channel: " + channel);
            }

            @Override
            public void onReceived(byte[] payload, byte channel) {
                if(payload!=null) {
                    final String str = new String(payload);
                    final byte c = channel;

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.v(TAG, "This is called when a payload has been received \"" + str + "\" on channel: " + c);
                            Toast.makeText(activity.getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                            TextView v = (TextView) view.findViewById(R.id.result);
                            v.setText(str);
                        }
                    });

                    soundPool.stop(streamID);
                    chirpConnect.stop();
                } else {
                    TextView v = (TextView) view.findViewById(R.id.result);
                    v.setText("Your microphone doesn't seem to be working. Check your phone settings or try again later.");
                }

            }

            @Override
            public void onStateChanged(byte oldState, byte newState) {
                Log.v(TAG, "This is called when the SDK state has changed " + oldState + " -> " + newState);
            }

            @Override
            public void onSystemVolumeChanged(int old, int current) {
                Log.d(TAG, "This is called when the Android system volume has changed " + old + " -> " + current);
            }
        };

        testButton = view.findViewById(R.id.testSpeakerButtonText);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity.getParent(), new String[] {Manifest.permission.RECORD_AUDIO}, RESULT_REQUEST_RECORD_AUDIO);
                }
                else {
                    chirpConnect.start();
                }

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity.getApplicationContext(), "Playing Chirp...", Toast.LENGTH_SHORT);
                        TextView textView = view.findViewById(R.id.result);
                        textView.setText("Chirp message will appear here");
                    }
                });

                streamID = soundPool.play(soundID,1,1,1,-1,1);
                chirpConnect.setListener(connectEventListener);
                chirpConnect.start();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Activity activity = getActivity();

        Intent i = new Intent(activity.getApplicationContext(), ChirpService.class);
        activity.startService(i);
    }

}