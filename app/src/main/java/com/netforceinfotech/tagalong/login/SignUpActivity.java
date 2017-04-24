package com.netforceinfotech.tagalong.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.home.HomeActivity;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText user_name, user_email, user_password, user_repassword;
    private Button signupbutton;
    private Context context;
    private TextView user_prefered_language;
    LinearLayout ll_preferd_lang;
    RelativeLayout rl_preferd_lang;
    private ImageView userlangdropdownlist;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        context = this;

        initView();
        setupToolbar(getString(R.string.signup));

    }

    private void initView() {
        pd=new ProgressDialog(this);
        user_name = (EditText) findViewById(R.id.userNameEditText);
        user_email = (EditText) findViewById(R.id.userEmailEditText);
        user_password = (EditText) findViewById(R.id.userPasswordEditText);
        user_repassword = (EditText) findViewById(R.id.userRepasswordEditText);
        user_prefered_language = (TextView) findViewById(R.id.userPreferedLangTextView);
        ll_preferd_lang=(LinearLayout)findViewById(R.id.ll_preferd_lang);
        rl_preferd_lang=(RelativeLayout) findViewById(R.id.rl_preferd_lang);
        userlangdropdownlist= (ImageView) findViewById(R.id.langDropDownImageView);
        userlangdropdownlist.setOnClickListener(this);
        signupbutton = (Button) findViewById(R.id.signupButton);
        signupbutton.setOnClickListener(this);
        ll_preferd_lang.setOnClickListener(this);
        rl_preferd_lang.setOnClickListener(this);
        user_prefered_language.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.signupButton) {

            check_validation();

        }
        if(v.getId()==R.id.rl_preferd_lang)
        {
            showLangOptions();
        }
//        if(v.getId()==R.id.langDropDownImageView){
//            showLangOptions();
//
//        }
        if(v.getId()==R.id.ll_preferd_lang)
        {
            showLangOptions();
        }
        if(v.getId()==R.id.userPreferedLangTextView)
        {
            showLangOptions();
        }
    }

    private void showLangOptions() {

        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this,userlangdropdownlist);
        DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.lang_dropdown)
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int id) {

                        switch (id){

                            case R.id.english:
                                user_prefered_language.setText(R.string.lang_eng);

                                break;
                            case R.id.hindi:
                                user_prefered_language.setText(R.string.lang_hindi);


                                break;
                            case R.id.tibetan:
                                user_prefered_language.setText(R.string.lang_tib);


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

    private void check_validation() {


        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE || activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {


              if(!user_name.getText().toString().isEmpty()){
                  if(isValidName(user_name.getText().toString().trim())){

                      if(!user_email.getText().toString().isEmpty()){
                          if(isValidEmail(user_email.getText().toString().trim())){

                              if(!user_password.getText().toString().isEmpty()){
                                  if(isValidPassword(user_password.getText().toString().trim())){
                                      if(!user_repassword.getText().toString().isEmpty()){


                                          if(user_password.getText().toString().trim().equals(user_repassword.getText().toString().trim())){
                                              call_signup_webservice(context);













                                          }else {
                                              user_password.getText().clear();
                                              user_repassword.getText().clear();
                                              user_password.setFocusable(true);
                                              showMessage("Password didn't match");
                                          }

                                      }else {

                                          showMessage("Enter Confirmation Password");
                                      }


                                  }else{
                                      user_password.getText().clear();
                                      user_repassword.getText().clear();
                                      user_password.setFocusable(true);
                                      showMessage("Password should have at least 6 characters");
                                  }


                              }else {

                                  showMessage("Enter Password");
                              }

                          }else {
                              showMessage("Enter a valid Email Adress");
                          }



                      }else {
                          showMessage("Enter Email Address");
                      }

                  }else {
                      showMessage("User Name should have at least 5 characters");
                  }

              } else
              {
                  showMessage("Enter User Name");

              }




            } // end of the second if statement...


        } else {

            // First Else...
            Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
        }
    }

    private void call_signup_webservice(Context context) {
        setupSelfSSLCert();



        JsonObject js=new JsonObject();
        js.addProperty("type", "register");
        js.addProperty("vFirstName",user_name.getText().toString().trim());
        js.addProperty("vLastName", "test");
        js.addProperty("vEmail",user_email.getText().toString().trim());
        js.addProperty("vPassword", user_password.getText().toString().trim());

        js.addProperty("vLanguageCode", "eng");
        Log.e("js",js.toString());

String Webservice_signup_url=getResources().getString(R.string.webservice_api_url);
        Log.e("Webservice_signup_url",Webservice_signup_url);
        Ion.with(context)
                .load(Webservice_signup_url)
                .setJsonObjectBody(js)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        String login_status=result.get("action").getAsString();
                        if(login_status.contains("1"))
                        {
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.enter, R.anim.exit);
                            Log.e("result",result.toString());
                            finish();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.enter, R.anim.exit);
                        }
                        else{
                            showMessage("Incorrect Username or password ");
                            showMessage("Sucessfully Signup");
                        }

                        if(pd!=null)
                        {
                            pd.dismiss();
                        }

                        // do stuff with the result or error
                    }
                });




    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    private boolean isValidName(String name){

        if(name!=null && name.length()>5){
            return true;
        }
        return false;
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    private static class Trust implements X509TrustManager {

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }

    public void setupSelfSSLCert() {
        final Trust trust = new Trust();
        final TrustManager[] trustmanagers = new TrustManager[]{trust};
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustmanagers, new SecureRandom());
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setTrustManagers(trustmanagers);
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setSSLContext(sslContext);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (final KeyManagementException e) {
            e.printStackTrace();
        }
    }




















}


