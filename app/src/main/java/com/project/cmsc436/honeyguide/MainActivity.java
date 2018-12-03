package com.project.cmsc436.honeyguide;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.IntentFilter;

import android.content.DialogInterface;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;

import android.support.v4.content.LocalBroadcastManager;

import android.support.v7.app.AlertDialog;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.util.Log;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import android.content.SharedPreferences;
import android.widget.Toast;


/** Note that here we are inheriting ListActivity class instead of Activity class **/
public class MainActivity extends AppCompatActivity {
    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<String>();
    private Map<String,String> pieces = new HashMap<>();
    String piece;
   // ArrayList<String> selectList = new ArrayList<String>();
    private final int RESULT_REQUEST_RECORD_AUDIO = 1;
    private String TAG = "Honeyguide-Debug: ";
    private final String COLLECTION_NAME = "art_pieces ";
    public static final int notificationID = 13822;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference docRef;
    private SharedPreferences sharedpreferences;
    BroadcastReceiver mBroadcastReceiver;

    public static boolean isVisible;
    public static final String RECEIVER_INTENT = "RECEIVER_INTENT";
    public static final String RECEIVER_MESSAGE = "RECEIVER_MESSAGE";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                FragmentTransaction homeTransaction = getSupportFragmentManager().beginTransaction();
                                homeTransaction.replace(R.id.frame_layout, HomeFragment.newInstance());
                                homeTransaction.commit();
                                break;
                            case R.id.navigation_saved:
                                FragmentTransaction savedTransaction = getSupportFragmentManager().beginTransaction();
                                savedTransaction.replace(R.id.frame_layout, SavedFragment.newInstance());
                                savedTransaction.commit();
                                break;
                            case R.id.navigation_settings:
                                FragmentTransaction settingsTransaction = getSupportFragmentManager().beginTransaction();
                                settingsTransaction.replace(R.id.frame_layout, new SettingsFragment());
                                settingsTransaction.commit();
                                break;
                        }
                        return true;
                    }
                });

        //fetching information from firebase once

        for(int i = 1; i <= 3; i++){
            docRef = db.collection(COLLECTION_NAME).document(Integer.toString(i));
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Map< String,Object> docFields = document.getData();
                            String title = docFields.get("Full title").toString();
                            Log.d(TAG, "Firebase Success: " + title);
                            sharedpreferences = getSharedPreferences(title, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            for (Map.Entry<String,Object> field : docFields.entrySet()){
                                editor.putString(field.getKey(), field.getValue().toString());
                            }

                            editor.apply();
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }

        //set up id - art piece name correlation
        loadPieces(getPieces());

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String message = intent.getStringExtra(RECEIVER_MESSAGE);
                Log.i(TAG, "Message received: " + message);
                setPiece(message);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, PieceFragment.newInstance());
                transaction.commit();

            }
        };

        if (checkSelfPermission(android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.RECORD_AUDIO}, RESULT_REQUEST_RECORD_AUDIO);
        }
        else {

            String id = getIntent().getStringExtra("data");
            Log.i(TAG, getIntent().getExtras() + " WAS RECEIVED");
            Intent i = new Intent(getApplicationContext(), ChirpService.class);

            if(id!= null){
                setPiece(id);
                FragmentTransaction pieceTransaction = getSupportFragmentManager().beginTransaction();
                pieceTransaction.replace(R.id.frame_layout, new PieceFragment());
                pieceTransaction.commit();
                i.putExtra("piece",pieces.get(id ));
            }
                startService(i);

        }

    }

//    public void addItem() {
//        Log.i("i", "entered addItem()");
//        EditText edit = (EditText) findViewById(R.id.txtItem);
//        list.add(edit.getText().toString());
//        edit.setText("");
//    }

    public void saveArtPiece() {
        Log.i("i", "entered saveArtPiece()");
        String name = pieces.get(piece);
        list.add(name);
    }

   /* public void selectItem(String item) {
        selectList.add(item);
    }

    public ArrayList<String> getSelectList() {
        return selectList;
    }
*/

    public ArrayList<String> getList() {
        return list;
    }

    public Map<String,String> getPieces() {
        return pieces;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public void clearData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

        // Add the buttons
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                for(int i = 1; i <= 3; i++){
                    String name = pieces.get(Integer.toString(i));
                    getSharedPreferences(name, Context.MODE_PRIVATE).edit().clear().apply();

                }
                ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                activityManager.clearApplicationUserData();

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
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((mBroadcastReceiver),
                new IntentFilter(RECEIVER_INTENT)
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        isVisible = true;
    }



    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        isVisible = false;
        ChirpService.currentPiece = "";
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RESULT_REQUEST_RECORD_AUDIO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent i= new Intent(getApplicationContext(), ChirpService.class);
                    startService(i);
                }
                return;
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent i= new Intent(getApplicationContext(), ChirpService.class);
        stopService(i);
    }

    public void onShareAction(MenuItem m) {
        Log.i(TAG,"Enter the onShareAction");
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBody = "Art piece title";
        String shareSubject = "Art piece subject";

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

        startActivity(Intent.createChooser(shareIntent, "Share using"));
    }

    public void onSaveAction(MenuItem m) {
       Log.i(TAG,"Enter the onSaveAction");
       saveArtPiece();
       String name = pieces.get(piece);
       Toast.makeText(getApplicationContext(), "The art piece: "+name+"is saved!", Toast.LENGTH_SHORT).show();
    }

    private void loadPieces(Map<String,String> pieces){
        pieces.put("1","Sunflowers");
        pieces.put("2","A Young Woman standing at a Virginal");
        pieces.put("3","The Fighting Temeraire");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed Called");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();
        ChirpService.currentPiece = "";
    }

}
