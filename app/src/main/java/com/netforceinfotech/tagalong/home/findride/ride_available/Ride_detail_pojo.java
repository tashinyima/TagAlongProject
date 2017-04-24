package com.netforceinfotech.tagalong.home.findride.ride_available;

/**
 * Created by Nishant on 1/3/2017.
 */

public class Ride_detail_pojo {

    String drivername,rating,from,to,date,time,schudle,detour,luggagesize,car_brand,car_comfort,car_type,
    car_plateno,preference_no_smoking,preference_copilot,preference_nopets,preference_imquiettype
            ,preference_silence,memberid,member_image,ride_price,avail_seat;



    public Ride_detail_pojo(String memberid,String member_image,String drivername, String rating, String from, String to,String ride_price ,String date, String time, String schudle,
                            String detour, String luggagesize, String car_brand, String car_comfort, String car_type,
                            String car_plateno, String preference_no_smoking, String preference_copilot, String preference_nopets,
                            String preference_imquiettype, String preference_silence,String avail_seat)
    {
        this.avail_seat=avail_seat;
        this.ride_price=ride_price;
        this.memberid=memberid;
        this.member_image=member_image;
        this.drivername = drivername;
        this.rating = rating;
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.schudle = schudle;
        this.detour = detour;
        this.luggagesize = luggagesize;
        this.car_brand = car_brand;
        this.car_comfort = car_comfort;
        this.car_type = car_type;
        this.car_plateno = car_plateno;
        this.preference_no_smoking = preference_no_smoking;
        this.preference_copilot = preference_copilot;
        this.preference_nopets = preference_nopets;
        this.preference_imquiettype = preference_imquiettype;
        this.preference_silence = preference_silence;
    }
}
