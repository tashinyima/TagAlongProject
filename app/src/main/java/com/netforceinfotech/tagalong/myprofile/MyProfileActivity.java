package com.netforceinfotech.tagalong.myprofile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

import java.util.Calendar;

public class MyProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageViewEmail, imageViewDob, imageViewContact, imageViewAddress, imageViewLanguage, imageViewDescription;
    EditText etEmail, etDob, etContact, etAddress, etLanguage, etDescription;
    Switch mySwitch;
    LinearLayout linearLayoutEditProfile;
    TextView textViewSave,tv_email,tv_dob,tv_phone,tv_address,tv_language,tv_description;

    Toolbar toolbar;
    String email,dob,contact,address,Language,desription;
    ImageView smokeDropdownImageView,chatDropdownImageView,petsDropdownImageView,musicDropdownImageView,copilotDropdownImageView;
    Context context;
    DroppyMenuPopup droppyMenu;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        initView();
        Intent intent = getIntent();
        if(intent!=null) {
            email = intent.getStringExtra("vEmail");
            dob = intent.getStringExtra("dob");
            contact = intent.getStringExtra("vPhone");

            Language = intent.getStringExtra("language");
            address = intent.getStringExtra("vAddress");
            desription = intent.getStringExtra("Description");
            etEmail.setText(email);
            etDob.setText(dob);
            etContact.setText(contact);
            etLanguage.setText(Language);
            etAddress.setText(address);
            etDescription.setText(desription);
        }


        setupToolbar(getString(R.string.my_profile));
    }

    private void initView() {
//        tv_email=(TextView)findViewById(R.id.tv_email);
//        tv_dob=(TextView)findViewById(R.id.tv_dob) ;
//        tv_phone=(TextView)findViewById(R.id.tv_dob) ;
        textViewSave = (TextView) findViewById(R.id.textViewSave);
        textViewSave.setOnClickListener(this);
        linearLayoutEditProfile = (LinearLayout) findViewById(R.id.linearLayoutEditProfile);
        findViewById(R.id.linearLayoutEditProfile).setOnClickListener(this);
        imageViewAddress = (ImageView) findViewById(R.id.imageViewAddress);
        imageViewEmail = (ImageView) findViewById(R.id.imageViewEmail);
        imageViewDob = (ImageView) findViewById(R.id.imageViewDob);
        imageViewContact = (ImageView) findViewById(R.id.imageViewContact);
        imageViewLanguage = (ImageView) findViewById(R.id.imageViewLanguage);
        imageViewDescription = (ImageView) findViewById(R.id.imageViewDescription);
        // New Data added by Tenzin...
        context=this;
        smokeDropdownImageView = (ImageView) findViewById(R.id.smokeDropDownImageView);
        smokeDropdownImageView.setOnClickListener(this);
        petsDropdownImageView = (ImageView) findViewById(R.id.petsDropDownImageView);
        petsDropdownImageView.setOnClickListener(this);
        chatDropdownImageView = (ImageView) findViewById(R.id.chatDropDownImageView);
        chatDropdownImageView.setOnClickListener(this);
        musicDropdownImageView = (ImageView) findViewById(R.id.musicDropDownImageView);
        musicDropdownImageView.setOnClickListener(this);
        copilotDropdownImageView = (ImageView) findViewById(R.id.copilotDropDownImageView);
        copilotDropdownImageView.setOnClickListener(this);

        // Ends here...
        etEmail = (EditText) findViewById(R.id.etEmail);
        etDob = (EditText) findViewById(R.id.etDob);
        etDob.setOnClickListener(this);
        etContact = (EditText) findViewById(R.id.etContact);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etLanguage = (EditText) findViewById(R.id.etLanguage);
        etDescription = (EditText) findViewById(R.id.etDescription);
        mySwitch = (Switch) findViewById(R.id.mySwitch);
        mySwitch.setChecked(false);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setEditable(b);
            }
        });
        setEditable(false);
    }

    private void setEditable(boolean b) {
        if (b) {
            imageViewAddress.setVisibility(View.VISIBLE);
            imageViewEmail.setVisibility(View.VISIBLE);
            imageViewDob.setVisibility(View.VISIBLE);
            imageViewContact.setVisibility(View.VISIBLE);
            imageViewLanguage.setVisibility(View.VISIBLE);
            imageViewDescription.setVisibility(View.VISIBLE);
            etEmail.setEnabled(true);
            etDob.setEnabled(true);
            etContact.setEnabled(true);
            etAddress.setEnabled(true);
            etLanguage.setEnabled(true);
            etDescription.setEnabled(true);
            linearLayoutEditProfile.setVisibility(View.GONE);
            textViewSave.setVisibility(View.VISIBLE);

            //Tenzin Datas...
            chatDropdownImageView.setVisibility(View.VISIBLE);
            musicDropdownImageView.setVisibility(View.VISIBLE);
            petsDropdownImageView.setVisibility(View.VISIBLE);
            smokeDropdownImageView.setVisibility(View.VISIBLE);
            copilotDropdownImageView.setVisibility(View.VISIBLE);
            //Ends here
        } else {
            imageViewAddress.setVisibility(View.GONE);
            imageViewEmail.setVisibility(View.GONE);
            imageViewDob.setVisibility(View.GONE);
            imageViewContact.setVisibility(View.GONE);
            imageViewLanguage.setVisibility(View.GONE);
            imageViewDescription.setVisibility(View.GONE);
            etEmail.setEnabled(false);
            etDob.setEnabled(false);
            etContact.setEnabled(false);
            etAddress.setEnabled(false);
            etLanguage.setEnabled(false);
            etDescription.setEnabled(false);
            linearLayoutEditProfile.setVisibility(View.VISIBLE);
            textViewSave.setVisibility(View.GONE);

            //Tenzin Datas...
             chatDropdownImageView.setVisibility(View.GONE);
             musicDropdownImageView.setVisibility(View.GONE);
            petsDropdownImageView.setVisibility(View.GONE);
            smokeDropdownImageView.setVisibility(View.GONE);
            copilotDropdownImageView.setVisibility(View.GONE);
            //Ends here

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayoutEditProfile:
                mySwitch.performClick();
                break;
            case R.id.textViewSave:
                mySwitch.performClick();
                break;
            case R.id.smokeDropDownImageView:
                ShowSmokeDropDown();
                break;
            case R.id.chatDropDownImageView: 
                ShowChatDropDown();
                break;
            case R.id.petsDropDownImageView:
                ShowPetsDropDown();
                break;
            case R.id.musicDropDownImageView:
                ShowMusicDropDown();
                break;
            case R.id.copilotDropDownImageView:
                ShowCopilotDropDown();
                break;
            case R.id.etDob:
                showDOB();
                break;


        }
    }

    private void showDOB() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        etDob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void ShowCopilotDropDown() {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, copilotDropdownImageView);
        DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.co_pilot_dropdown)
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int id) {

                        switch (id){

                            case R.id.dd1:
                                Toast.makeText(getApplicationContext(),"Chat ddl1 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd2:
                                Toast.makeText(getApplicationContext(),"Chat ddl2 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd3:
                                Toast.makeText(getApplicationContext(),"Chat ddl3 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd4:
                                Toast.makeText(getApplicationContext(),"Chat ddl4 clicked",Toast.LENGTH_LONG).show();

                                break;

                        }


                    }
                })
                .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                    @Override
                    public void call()
                    {

                        Log.i("Hello","sdfsdaf");
                    }
                })
                .setPopupAnimation(new DroppyFadeInAnimation())
                .setXOffset(5)
                .setYOffset(5)
                .build();
        droppyMenu.show();
    }

    private void ShowMusicDropDown() {

        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, musicDropdownImageView);
        DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.music_dropdown)
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int id) {

                        switch (id){

                            case R.id.dd1:
                                Toast.makeText(getApplicationContext(),"Chat ddl1 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd2:
                                Toast.makeText(getApplicationContext(),"Chat ddl2 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd3:
                                Toast.makeText(getApplicationContext(),"Chat ddl3 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd4:
                                Toast.makeText(getApplicationContext(),"Chat ddl4 clicked",Toast.LENGTH_LONG).show();

                                break;

                        }


                    }
                })
                .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                    @Override
                    public void call()
                    {

                        Log.i("Hello","sdfsdaf");
                    }
                })
                .setPopupAnimation(new DroppyFadeInAnimation())
                .setXOffset(5)
                .setYOffset(5)
                .build();
        droppyMenu.show();
    }

    private void ShowPetsDropDown() {

        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, petsDropdownImageView);
        DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.pets_dropdown)
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int id) {

                        switch (id){

                            case R.id.dd1:
                                Toast.makeText(getApplicationContext(),"Chat ddl1 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd2:
                                Toast.makeText(getApplicationContext(),"Chat ddl2 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd3:
                                Toast.makeText(getApplicationContext(),"Chat ddl3 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd4:
                                Toast.makeText(getApplicationContext(),"Chat ddl4 clicked",Toast.LENGTH_LONG).show();

                                break;

                        }


                    }
                })
                .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                    @Override
                    public void call()
                    {

                        Log.i("Hello","sdfsdaf");
                    }
                })
                .setPopupAnimation(new DroppyFadeInAnimation())
                .setXOffset(5)
                .setYOffset(5)
                .build();
        droppyMenu.show();

    }

    private void ShowChatDropDown() {

        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, chatDropdownImageView);
        DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.chat_dropdown)
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int id) {

                        switch (id){

                            case R.id.dd1:
                                Toast.makeText(getApplicationContext(),"Chat ddl1 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd2:
                                Toast.makeText(getApplicationContext(),"Chat ddl2 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd3:
                                Toast.makeText(getApplicationContext(),"Chat ddl3 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd4:
                                Toast.makeText(getApplicationContext(),"Chat ddl4 clicked",Toast.LENGTH_LONG).show();

                                break;

                        }


                    }
                })
                .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                    @Override
                    public void call()
                    {

                        Log.i("Hello","sdfsdaf");
                    }
                })
                .setPopupAnimation(new DroppyFadeInAnimation())
                .setXOffset(5)
                .setYOffset(5)
                .build();
        droppyMenu.show();
    }

    private void ShowSmokeDropDown() {
        
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, smokeDropdownImageView);
        DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.smokes_dropdown)
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int id) {

                        switch (id){

                            case R.id.dd1:
                                Toast.makeText(getApplicationContext(),"Heello ddl1 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd2:
                                Toast.makeText(getApplicationContext(),"Heello ddl2 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd3:
                                Toast.makeText(getApplicationContext(),"Heello ddl3 clicked",Toast.LENGTH_LONG).show();

                                break;
                            case R.id.dd4:
                                Toast.makeText(getApplicationContext(),"Heello ddl4 clicked",Toast.LENGTH_LONG).show();

                                break;

                        }


                    }
                })
                .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
            @Override
            public void call()
            {

                Log.i("Hello","sdfsdaf");
            }
        })
        .setPopupAnimation(new DroppyFadeInAnimation())
                .setXOffset(5)
                .setYOffset(5)
                .build();
        droppyMenu.show();
    }

    private void setupToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s.toUpperCase());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
