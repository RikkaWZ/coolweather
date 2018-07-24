package com.cxcxj.coolweather.db;

import org.litepal.crud.LitePalSupport;

//用于存放省的数据信息
public class Province extends LitePalSupport {

    //字段信息
    private int id;

    //省的名字
    private String provinceName;

    //省的代号
    private int provinceCode;

    public int getId() {
        return id;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

}
