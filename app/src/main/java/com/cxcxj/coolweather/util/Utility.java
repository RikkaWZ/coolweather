package com.cxcxj.coolweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.cxcxj.coolweather.db.City;
import com.cxcxj.coolweather.db.County;
import com.cxcxj.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONObject;

//处理json数据
public class Utility {

    private static final String TAG = "Utility";

    /**
     * 解析处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){

        //接收数据不为空才可进行下一步操作，否则直接返回错误
        if (!TextUtils.isEmpty(response)){

            try{

                //先将返回的解析到JSONArray
                JSONArray allProvinces=new JSONArray(response);

                //再遍历解析内容中的每条数据
                for (int i=0;i<allProvinces.length();i++){

                    //获取一条数据
                    JSONObject provinceObject=allProvinces.getJSONObject(i);

                    //将数据存储到到数据库
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();

                }

                return true;

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return false;

    }

    /**
     * 解析处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response,int provinceId){

        //接收数据不为空才可进行下一步操作，否则直接返回错误
        if (!TextUtils.isEmpty(response)){

            try{

                //先将返回的解析到JSONArray
                JSONArray allCitys=new JSONArray(response);

                //再遍历解析内容中的每条数据
                for (int i=0;i<allCitys.length();i++){

                    //获取一条数据
                    JSONObject cityObject=allCitys.getJSONObject(i);

                    //将数据存储到到数据库
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();

                }

                return true;

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return false;

    }

    /**
     * 解析处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response,int cityId){

        //接收数据不为空才可进行下一步操作，否则直接返回错误
        if (!TextUtils.isEmpty(response)){

            try{

                //先将返回的解析到JSONArray
                JSONArray allCounties=new JSONArray(response);

                //再遍历解析内容中的每条数据
                for (int i=0;i<allCounties.length();i++){

                    //获取一条数据
                    JSONObject countyObject=allCounties.getJSONObject(i);

                    //将数据存储到到数据库
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();

                }

                return true;

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return false;

    }

}
