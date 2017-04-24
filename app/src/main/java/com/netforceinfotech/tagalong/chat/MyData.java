package com.netforceinfotech.tagalong.chat;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class MyData  {
    String name,imageurl,time,id;

    MyData(String name,String imageurl,String time,String id) {
        this.name = name;
        this.id = id;
        this.imageurl=imageurl;
        this.time=time;
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
