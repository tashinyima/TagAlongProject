package com.netforceinfotech.tagalong.myCars.carlist;

import android.widget.ImageView;

/**
 * Created by Tenzin Choephel on 12/6/2016.
 */

public class CarDatas {
    public String carImgUrl;
    public String carName;
    public String car_id;
    public String comfort;
    public String  car_type;
    public String totalseats;
    public String plateNumber;
    public String carModel;


    public CarDatas(String carImgUrl, String carName, String car_id, String comfort, String car_type, String totalseats, String plateNumber, String carModel) {
        this.carImgUrl = carImgUrl;
        this.carName = carName;
        this.car_id = car_id;
        this.comfort = comfort;
        this.car_type = car_type;
        this.totalseats = totalseats;
        this.plateNumber = plateNumber;
        this.carModel = carModel;
    }

    public String getCarImgUrl() {
        return carImgUrl;
    }

    public String getCarName() {
        return carName;
    }

    public String getCar_id() {
        return car_id;
    }

    public String getComfort() {
        return comfort;
    }

    public String getCar_type() {
        return car_type;
    }

    public String getTotalseats() {
        return totalseats;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getCarModel() {
        return carModel;
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CarDatas)) {
            return false;
        }

        CarDatas that = (CarDatas) obj;
        return this.car_id.equals(that.car_id);
    }
}
