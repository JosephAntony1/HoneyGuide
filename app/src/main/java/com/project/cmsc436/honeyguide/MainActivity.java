package com.project.cmsc436.honeyguide;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.util.Log;
import android.widget.EditText;
import java.util.ArrayList;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import io.chirp.connect.ChirpConnect;
import io.chirp.connect.interfaces.ConnectEventListener;
import io.chirp.connect.interfaces.ConnectSetConfigListener;
import io.chirp.connect.models.ChirpError;
import io.chirp.connect.models.ConnectState;
import io.chirp.connect.ChirpConnect;
import io.chirp.connect.interfaces.ConnectEventListener;
import io.chirp.connect.interfaces.ConnectSetConfigListener;
import io.chirp.connect.models.ChirpError;

/** Note that here we are inheriting ListActivity class instead of Activity class **/
public class MainActivity extends AppCompatActivity {
    /** Items entered by the user is stored in this ArrayList variable */
    ArrayList<String> list = new ArrayList<String>();

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
        
      

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();
    }

    public void launchHomeScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void addItem() {
        Log.i("i", "entered addItem()");
        EditText edit = (EditText) findViewById(R.id.txtItem);
        list.add(edit.getText().toString());
        edit.setText("");
    }

    public ArrayList<String> getList() {
        return list;
    }
    
    public void clearData() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.clearApplicationUserData();
    }

}