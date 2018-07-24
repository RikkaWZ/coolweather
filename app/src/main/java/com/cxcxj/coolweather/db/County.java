package com.cxcxj.coolweather.db;

import org.litepal.crud.LitePalSupport;

//用于存放县的信息
public class County extends LitePalSupport {

    //字段
    private int id;

    //县的名字
    private String countyName;

    //县所对应的天气id
    private String weatherId;

    //当前县所属市的id
    private int cityId;

    public int getId() {
        return id;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCountyName() {
        return countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }
}
