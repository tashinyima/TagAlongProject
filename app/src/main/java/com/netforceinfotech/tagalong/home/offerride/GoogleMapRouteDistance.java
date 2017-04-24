package com.netforceinfotech.tagalong.home.offerride;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Tenzin on 12/14/2016.
 */

public class GoogleMapRouteDistance {

    LatLng latLng;

    public GoogleMapRouteDistance(LatLng latLng) {
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
