package com.cxcxj.coolweather.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {

    /*@SerializedName()注解用于让JSON字段与Java字段之间建立映射关系*/

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {

        @SerializedName("loc")
        public String updateTime;

    }

}
