package com.netforceinfotech.tagalong.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.home.HomeActivity;

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

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button sendButton;
    private EditText forgetPassEditText;
    Context context;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        pd = new ProgressDialog(this);
        setupToolbar(getString(R.string.forgot_password));
        initView();
    }

    private void initView() {
        context = this;
        sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);
        forgetPassEditText = (EditText) findViewById(R.id.forgetPassEditText);
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
        if (v.getId() == R.id.sendButton) {

            StartValidation();
        }

    }

    private void StartValidation() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE || activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

                if (!forgetPassEditText.getText().toString().isEmpty()) {
                    if (isValidEmail(forgetPassEditText.getText().toString().trim())) {

                        forgetpassword_webservice(this);

                    } else {
                        showMessage("Enter a Valid Email Address");
                    }

                } else {
                    showMessage("Enter Email Address");
                }


            }
        } else {

            showMessage("No Internet Connection");
        }
    }

    private void forgetpassword_webservice(Context context) {


        pd.show();
        setupSelfSSLCert();

        JsonObject js = new JsonObject();
        js.addProperty("type", "forgot_password");
        js.addProperty("vEmail", forgetPassEditText.getText().toString().trim());


        Log.e("js_login", js.toString());


        String Webservice_login_url = getResources().getString(R.string.webservice_api_url);
        Log.e("Webservice_login_url", Webservice_login_url);
        Ion.with(context)
                .load(Webservice_login_url)
                .setJsonObjectBody(js)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result != null) {
                            Log.e("result", result.toString());
                            String login_status = result.get("action").getAsString();
                            if (login_status.contains("1")) {
                                String message = result.get("message").getAsString();


                                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                showMessage(message);
                                overridePendingTransition(R.anim.enter, R.anim.exit);
                            } else {
                                showMessage("Email id not exist");
                            }


                            if (pd != null) {
                                pd.dismiss();
                            }


                        } else {
                            Log.e("result_null", "result_null");
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

    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
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
        final LoginActivity.Trust trust = new LoginActivity.Trust();
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
