package com.netforceinfotech.tagalong.general;

import android.app.Application;

/**
 * Created by JitendraSingh on 11/25/2016.
 */

public class TagAlongTypeFont extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TypefaceUtil.overrideFont(getApplicationContext(), "MONOSPACE", "fonts/robotoregularfont.ttf");

    }
}
