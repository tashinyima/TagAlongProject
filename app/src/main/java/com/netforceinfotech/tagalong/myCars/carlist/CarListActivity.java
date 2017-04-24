package com.netforceinfotech.tagalong.myCars.carlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.netforceinfotech.tagalong.R;
import com.netforceinfotech.tagalong.chat.MyChatActivity;
import com.netforceinfotech.tagalong.myCars.MyCarActivity;
import com.netforceinfotech.tagalong.myCars.registercar.RegisterNewCar;

import java.util.ArrayList;
import java.util.List;

public class CarListActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Intent intent;
    CarAdapter carAdapter;
    List<CarDatas> carDatas;
    RecyclerView carlistRecycler;
    TextView addnewCarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        setupToolbar(getString(R.string.mycars));
        initViews();

    }

    private void initViews() {
        addnewCarTextView = (TextView) findViewById(R.id.textViewAddNewCar);
        addnewCarTextView.setOnClickListener(this);
        carlistRecycler = (RecyclerView) findViewById(R.id.carListRecycler);
        carDatas = new ArrayList<CarDatas>();
        carAdapter = new CarAdapter(this,carDatas);
        RecyclerView.LayoutManager rmanager= new LinearLayoutManager(getApplicationContext());
        carlistRecycler.setAdapter(carAdapter);
        carlistRecycler.setLayoutManager(rmanager);
        GetRidesDatas();
    }

    private void GetRidesDatas() {
       // public CarDatas(String carImgUrl, String carName, String car_id, String comfort, String car_type, String totalseats, String plateNumber, String carModel) {

        CarDatas cars= new CarDatas("http://st.motortrend.com/uploads/sites/10/2016/05/2017-bmw-x4-xdrive28i-suv-rear-view.png","Audi ","Audi1","Luxury","Audi","5","343KJ","2011MD");
        carDatas.add(cars);
        cars= new CarDatas("http://st.motortrend.com/uploads/sites/10/2015/11/2014-bmw-x5-xdrive-35d-suv-angular-front.png","Audi ","Audi34","Luxury","Audi","5","343KJ","2011MD");
        carDatas.add(cars);
        cars= new CarDatas(" http://st.motortrend.com/uploads/sites/10/2015/11/2014-bmw-x5-xdrive-35d-suv-angular-front.png","Audi ","Audi14","Luxury","Audi","5","343KJ","2011MD");
        carDatas.add(cars);
        cars= new CarDatas("http://www.mototourism-rally.lt/logo/mts2016vln/bmw-motociklai-vintage.png","Audi ","MDD4","Luxury","Audi","5","343KJ","2011MD");
        carDatas.add(cars);
        cars= new CarDatas("http://st.motortrend.com/uploads/sites/10/2015/11/2014-bmw-x5-xdrive-35d-suv-angular-front.png","Audi ","BMDW454","Luxury","Audi","5","343KJ","2011MD");
        carDatas.add(cars);
        cars= new CarDatas("http://st.motortrend.com/uploads/sites/10/2015/11/2014-bmw-x5-xdrive-35d-suv-angular-front.png","Audi ","343BDFD","Luxury","Audi","5","343KJ","2011MD");
        carDatas.add(cars);

        carAdapter.notifyDataSetChanged();


    }

    private void setupToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s.toUpperCase());
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
                intent = new Intent(getApplicationContext(), MyChatActivity.class);
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

    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.textViewAddNewCar){

            intent = new Intent(CarListActivity.this,RegisterNewCar.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enter, R.anim.exit);
            return;
        }


    }
}
