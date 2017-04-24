package com.netforceinfotech.tagalong.chat.driverchat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.netforceinfotech.tagalong.R;

import java.util.ArrayList;

public class DriverChatActivity extends AppCompatActivity {

    Toolbar toolbar;
    Context context;
    ArrayList<MyData> myDataList=new ArrayList<>();
    private MyAdapter myAdapter;
    ImageView sendmessage;
    EditText et_message;
    ProgressDialog progressDialog;
    DatabaseReference _group, _group_child, _user_group, _user_group_user_id, _user_group_group_id;
    SharedPreferences sp ;
    private boolean child_group_exist = false;
    private boolean user_group_user_id_flag = false;
    private boolean user_group_group_id_flag = false;
    ArrayList<MyData> myDatas = new ArrayList<>();
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_chat);
        Initview();
        sp = getSharedPreferences(
                getString(R.string.preference_tagalong), Context.MODE_PRIVATE);
        userid=sp.getString("userid","notlogin");
        Log.e("userid",userid);
       // setupFirebase(userid,"");
        context=this;
        setupToolbar("Dummy Driver");
        setupRecyclerView(myDataList);
        setupDummyData();
    }

    private void Initview() {
        progressDialog=new ProgressDialog(this);

        sendmessage=(ImageView)findViewById(R.id.sendmessage);
        et_message=(EditText)findViewById(R.id.editText);
        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{if(et_message.getText().length()!=0) {
                    myDataList.add(new MyData("Arvind", "", "", "", true, et_message.getText().toString()));
                    //myDataList.add(new MyData("Arvind", "", "", "", false, et_message.getText().toString()));
                    myAdapter.notifyDataSetChanged();
                    setupRecyclerView(myDataList);
                    et_message.setText("");
                }

                }catch (Exception ex){
                    Log.e("ex",ex.toString());

                }
            }
        });



    }

    private void setupDummyData() {
        try{
            myDataList.clear();
        }catch (Exception ex){
            myDataList.add(new MyData("Arvind","","","",true,""));
        }

        myAdapter.notifyDataSetChanged();
        myDataList.clear();
    }

    private void setupRecyclerView(ArrayList<MyData> myDataList) {
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler);
        myAdapter=new MyAdapter(context, myDataList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return true;
    }

    private void setupToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            case R.id.itemNotification:
                // Exit option clicked.
                showMessage("Notification method called");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void setupFirebase(final String userid, String s2) {
        progressDialog.show();
       // checkUserGroupExist(userid);
        _group = FirebaseDatabase.getInstance().getReference().child("chat_detail");
        _group.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                if (dataSnapshot.hasChild(userid)) {
                    // run some code
                    _group_child = _group.child(userid);
                    child_group_exist = true;
                    //textViewNoMessage.setVisibility(View.GONE);
                    _group_child.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            appendMessage(dataSnapshot,userid);
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                } else {
                    child_group_exist = false;
                    //textViewNoMessage.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void checkUserGroupExist(final String userid) {
        _user_group = FirebaseDatabase.getInstance().getReference().child("user_group");
        _user_group.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(userid)) {
                    user_group_user_id_flag = false;
                } else {
                    user_group_user_id_flag = true;
                    _user_group_user_id = _user_group.child(userid);
                    _user_group_user_id.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.hasChild(userid)) {
                                user_group_group_id_flag = false;
                            } else {
                                user_group_group_id_flag = true;
                                _user_group_group_id = _user_group_user_id.child(userid);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void appendMessage(DataSnapshot dataSnapshot,String userid) {

        Log.i("datasnampshot", dataSnapshot.toString());
        String id = dataSnapshot.child("user_id").getValue(String.class);
        String image_url = dataSnapshot.child("image").getValue(String.class);
        String message = dataSnapshot.child("message").getValue(String.class);
        String name = dataSnapshot.child("name").getValue(String.class);
        Long timestamp = dataSnapshot.child("timestamp").getValue(Long.class);
        String key = dataSnapshot.getKey();
        boolean you = false;
        if (userid.equalsIgnoreCase(id)) {
            you = true;
        } else {
            you = false;
        }
        Log.i("message", dataSnapshot.child("message").getValue(String.class) + "");
        MyData myData = new MyData( name, image_url, timestamp.toString(), id, you,message);
        if (!myDatas.contains(myData)) {
            myDatas.add(myData);
        }
        myAdapter.notifyDataSetChanged();
      //  recyclerView.smoothScrollToPosition(myDatas.size());

    }

































}
