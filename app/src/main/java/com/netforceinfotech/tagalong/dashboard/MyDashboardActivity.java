package com.netforceinfotech.tagalong.dashboard;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyChatActivity;
import com.netforceinfotech.tagalong.myrides.Myrides;

public class MyDashboardActivity extends AppCompatActivity {
    Context context;
    Toolbar toolbar;
    private Intent intent;
    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;
    LinearLayout ll_myrides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dashboard);



        context=this;
        setupToolBar(getString(R.string.my_dashboard));
        Initview(this);
    }

    private void Initview(MyDashboardActivity myDashboardActivity) {

        ll_myrides=(LinearLayout)findViewById(R.id.ll_mybooking);
        ll_myrides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MyDashboardActivity.this, Myrides.class);
                startActivity(i);
                finish();
            }
        });








    }

    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name.toUpperCase());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;

            case R.id.itemChat:
                intent = new Intent(context, MyChatActivity.class);
                startActivity(intent);
                return true;
            case R.id.itemNotification:
                // Exit option clicked.
                showMessage("Notification method called");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


}
