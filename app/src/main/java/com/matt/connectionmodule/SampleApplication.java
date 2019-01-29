package com.matt.connectionmodule;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.inno72.connection.openapi.ConnectionApi;
import com.inno72.connection.openapi.IPipe;

/**
 * Author:Created by jiaguofeng on 2018/7/18.
 * Email:jiaguofeng@inno72.com
 */

public class SampleApplication extends Application {

    private static final String TAG = "SampleApplication";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ConnectionApi.install(new IPipe() {
            @Override
            public String getPackage() {
                return getPackageName();
            }

            @Override
            public void result(String source, String data) {
                Log.i(TAG, "result: "+data+",source:"+source);
            }


            @Override
            public String query(String param) {
                Log.i(TAG, "query: "+param);
                return null;
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ConnectionApi.init(getApplicationContext());
    }
}
