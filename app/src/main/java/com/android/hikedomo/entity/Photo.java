package com.android.hikedomo.entity;

import com.android.hikedomo.utils.Constants;

public class Photo {

    public String id;
    public String owner;
    public String secret;
    public String server;
    public int farm;
    public String title;
    public int ispublic;
    public int isfriend;
    public int isfamily;


    public String getURL() {
        return String.format(Constants.BASE_IMAGE_URL, farm, server, id, secret);
    }
}
