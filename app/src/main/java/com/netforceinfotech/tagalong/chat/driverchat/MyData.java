package com.netforceinfotech.tagalong.chat.driverchat;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class MyData  {
    String name,imageurl,time,id,message;
    boolean you;

    MyData(String name,String imageurl,String time,String id,boolean you,String message) {
        this.name = name;
        this.you=you;
        this.id = id;
        this.imageurl=imageurl;
        this.time=time;
        this.message=message;
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
