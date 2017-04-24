package com.netforceinfotech.tagalong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.netforceinfotech.tagalong.dashboard.MyDashboardActivity;
import com.netforceinfotech.tagalong.googledistance.GoogleMapActivity;
import com.netforceinfotech.tagalong.home.HomeActivity;
import com.netforceinfotech.tagalong.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
   SharedPreferences sp ;
//comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sp = getSharedPreferences(
                getString(R.string.preference_tagalong), Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(sp!=null) {
                    String userid = sp.getString("userid","notlogin");
                    if (userid .equals("notlogin")) {
                        Log.e("userid",userid);
                        Intent i = new Intent(SplashActivity.this,LoginActivity .class);
                        startActivity(i);
                        finish();
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        finish();
                    }
                    else
                    {
                        Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                        Log.e("userid",userid);
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        finish();
                    }
                }

            }
        }, 3000);
    }
}
