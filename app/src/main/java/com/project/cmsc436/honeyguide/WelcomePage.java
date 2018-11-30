package com.project.cmsc436.honeyguide;

/*import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

import io.chirp.connect.ChirpConnect;
import io.chirp.connect.interfaces.ConnectEventListener;
import io.chirp.connect.interfaces.ConnectSetConfigListener;
import io.chirp.connect.models.ChirpError;


public class WelcomePage extends AppCompatActivity {

    private String TAG = "Honeyguide-Debug: ";
    private final int RESULT_REQUEST_RECORD_AUDIO = 1;

    ConnectEventListener connectEventListener;
    ChirpConnect chirpConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        //get button references
        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG,"click the start button");
                //after visitor click
                Intent newIntent = new Intent(WelcomePage.this,defaultPiece.class);
                startActivity(newIntent);
            }
        });


        String KEY = getString(R.string.chirp_application_key);
        String SECRET = getString(R.string.chirp_client_secret);

        String CONFIG = "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";

        chirpConnect = new ChirpConnect(this, KEY, SECRET);

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
                if(payload!=null)
                    Log.v(TAG, "This is called when a payload has been received \"" +  new String(payload)  + "\" on channel: " + channel);
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

        chirpConnect.setListener(connectEventListener);
        chirpConnect.start();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECORD_AUDIO}, RESULT_REQUEST_RECORD_AUDIO);
        }
        else {
            chirpConnect.start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RESULT_REQUEST_RECORD_AUDIO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chirpConnect.start();
                }
                return;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        chirpConnect.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        chirpConnect.stop();
        try {
            chirpConnect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome_page);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);


        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        //prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomePage.this, MainActivity.class));
        finish();
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}