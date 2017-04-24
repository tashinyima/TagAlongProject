package com.netforceinfotech.tagalong.general;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nishant on 1/3/2017.
 */

public class Ridedetail_parcel  implements Parcelable {
    private String name;
    private int numOfSeason;
    private int numOfEpisode;

    /**
     * Constructors and Getters/Setters have been removed to make reading easier
     **/

    public Ridedetail_parcel(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);
        this.name = data[0];
        this.numOfSeason = Integer.parseInt(data[1]);
        this.numOfEpisode = Integer.parseInt(data[2]);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.name,
                String.valueOf(this.numOfSeason),
                String.valueOf(this.numOfEpisode)});

    }

    private void readFromParcel(Parcel in) {
        name = in.readString();
        numOfSeason = in.readInt();
        numOfEpisode = in.readInt();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Ridedetail_parcel createFromParcel(Parcel in) {
            return new Ridedetail_parcel(in);
        }

        @Override
        public Ridedetail_parcel[] newArray(int size) {
            return new Ridedetail_parcel[size];
        }
    };
}