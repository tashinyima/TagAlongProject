package com.netforceinfotech.tagalong.home.findride.paymentmodes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.netforceinfotech.tagalong.R;

import java.util.ArrayList;
import java.util.List;

public class PaymentModeActivity extends AppCompatActivity{

    private Toolbar toolbar;
    Context context;
    private RecyclerView cardRecycle;
    private List<CardListData> cardlist;
    private CardCustomAdapter cardAdapter;
    public EditText userCardNoEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_mode);
        context = this;

        setupToolbar("PAYMENT MODE");

        initialValue();


    }

    private void initialValue() {

        cardRecycle = (RecyclerView) findViewById(R.id.cardRecycle);

        cardlist = new ArrayList<CardListData>();
        cardAdapter = new CardCustomAdapter(this,cardlist);
        RecyclerView.LayoutManager rmanager= new LinearLayoutManager(getApplicationContext());
        cardRecycle.setAdapter(cardAdapter);
        cardRecycle.setLayoutManager(rmanager);
        userCardNoEditText = (EditText) findViewById(R.id.userCardNoEditText);
        userCardNoEditText.addTextChangedListener(new FourDigitCardFormatWatcher());
        GetCardData();
    }

    private void GetCardData() {

        CardListData cardlists= new CardListData("9009303030302010");
        cardlist.add(cardlists);
        cardlists = new CardListData("9878393929234562");
        cardlist.add(cardlists);
        cardAdapter.notifyDataSetChanged();
    }

    private void setupToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.inflateMenu(R.menu.home_menu);
        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return onOptionsItemSelected(item);
            }
        });
        return true;
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
