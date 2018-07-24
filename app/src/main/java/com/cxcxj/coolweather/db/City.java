package com.cxcxj.coolweather.db;

import org.litepal.crud.LitePalSupport;

//用于存放市的数据
public class City extends LitePalSupport {

    //字段
    private int id;

    //城市名字
    private String cityName;

    //城市代号
    private int cityCode;

    //所属省代号
    private int provinceId;

    public int getId() {
        return id;
    }

    public int getCityCode() {
        return cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
