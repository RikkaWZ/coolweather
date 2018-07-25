package com.cxcxj.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cxcxj.coolweather.WeatherActivity;
import com.cxcxj.coolweather.gson.Weather;
import com.cxcxj.coolweather.util.HttpUtil;
import com.cxcxj.coolweather.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//后台自动更新天气
public class AutoUpdateService extends Service {

    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //更新天气与背景图片
        updateBingPic();
        updateWeather();

        //创建定时任务
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //8小时的毫秒数
        int anHour = 8 * 60 * 60 * 1000;

        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;

        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        return super.onStartCommand(intent, flags, startId);

    }

    /**
     * 更新天气信息
     */
    private void updateWeather() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = preferences.getString("weather", null);

        if (weatherString != null) {

            Weather weather = Utility.handleWeatherResponse(weatherString);
            String weatherId = weather.basic.weatherId;
            String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=fc27da5eada446eabe0423f1ad0fa79a";

            //发送请求
            HttpUtil.sendOkhttpRequest(weatherUrl, new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    //将返回的数据转换为iJSON对象
                    String responseText = response.body().string();
                    Weather weather = Utility.handleWeatherResponse(responseText);


                    if (weather != null && "ok".equals(weather.status)) {
                        //缓存数据
                        SharedPreferences.Editor editor =
                                PreferenceManager.getDefaultSharedPreferences
                                        (AutoUpdateService.this).edit();
                        editor.putString("weather", responseText);
                        editor.apply();
                    }

                }

            });


        }

    }

    /**
     * 更新必应每日一图
     */
    private void updateBingPic() {

        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkhttpRequest(requestBingPic, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.
                        getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
            }

        });

    }


}












