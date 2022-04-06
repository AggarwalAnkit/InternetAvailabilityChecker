package com.treebo.internetconnectivitylistener;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;

public class MainActivity extends AppCompatActivity implements InternetConnectivityListener {

    private TextView mTvStatus;
    private InternetAvailabilityChecker mInternetAvailabilityChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mTvStatus = findViewById(R.id.tv_status);
        findViewById(R.id.btb_open_next_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance();// by default pinged url will be https://clients3.google.com/generate_204
        // if you need to change it for some reason ( local network , google baned , ping on a specific server , etc ... )
        // you  can use the bellow method :
       // mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance("HTTP://YOURURL");

        mInternetAvailabilityChecker.addInternetConnectivityListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInternetAvailabilityChecker.removeInternetConnectivityChangeListener(this);
    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {
        if (isConnected) {
            mTvStatus.setText("connected");
        } else {
            mTvStatus.setText("not connected");
        }
    }
}
