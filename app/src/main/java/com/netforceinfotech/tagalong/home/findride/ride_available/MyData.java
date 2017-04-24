package com.netforceinfotech.tagalong.home.findride.ride_available;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class MyData  {
    String id,imageurl,name,price,source,departuredate,departuretime,destination;
    float starRating;

    public MyData(String id, String imageurl, String name, String price, String source, String destination, String departuredate, String departuretime, float starRating) {
        this.id=id;
        this.imageurl=imageurl;
        this.name=name;
        this.price=price;
        this.source=source;
        this.departuredate=departuredate;
        this.departuretime=departuretime;
        this.source=source;
        this.destination=destination;
        this.starRating=starRating;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyData)) {
            return false;
        }

        MyData that = (MyData) obj;
        return this.id.equals(that.id);
    }


}
