package com.netforceinfotech.tagalong.home.offerride;

import com.netforceinfotech.tagalong.myCars.carlist.CarDatas;

/**
 * Created by JitendraSingh on 12/8/2016.
 */

public class DummyData {
    String userid,id,title,body;

    public DummyData(String userid, String id, String title, String body) {
        this.userid = userid;
        this.id = id;
        this.title = title;
        this.body = body;
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DummyData)) {
            return false;
        }

        DummyData that = (DummyData) obj;
        return this.id.equals(that.id);
    }

}