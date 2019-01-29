package com.matt.connection.inner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Author:Created by Matt on 2018/7/16.
 */

public class ProviderConfig {

    private static final String TAG = "Pipe";

    private static ProviderConfig mIntance;

    private Context mContext;

    private ProviderConfig(Context context){
        this.mContext = context;
    }

    public static ProviderConfig getIntance(Context context){
        if(mIntance == null){
            synchronized (ProviderConfig.class){
                if(mIntance == null){
                    mIntance = new ProviderConfig(context);
                }
            }
        }
        return mIntance;
    }

    public void init() {
    }

    public String query(String method) {
        return query(method,null,null);
    }


    public String query(String method,String[] projection,String param) {
        String result = null;
        try {
            Uri uri = Uri.parse("content://" + Constant.AUTHORITY+method + "/"
                    + Constant.QUERY);
            Cursor cursor = mContext.getContentResolver().query(uri, projection, param, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(0);
                }
            } finally {
                if (cursor != null)
                    cursor.close();
            }
        } catch (Exception e) {
            if (CompileConfig.DEBUG) {
                Log.e(TAG, "query error", e);
            }
        }
        return result;
    }


    public void update(String method, String data) {
        Map<String,String> map = new HashMap<>();
        map.put(Constant.DATA,data);
        map.put(Constant.SOURCE,PipeHelper.getPackage());
        if(CompileConfig.DEBUG){
            Log.i(TAG, "update: "+data);
        }
        update(method, map);
    }

    public void update(String method, Map<String,String> map) {

        try {
            ContentValues cv = new ContentValues();
            if(map != null && !map.isEmpty()){
                Iterator<String> it = map.keySet().iterator();
                while (it.hasNext()){
                    String key = it.next();
                    cv.put(key,map.get(key));
                }
            }
            mContext.getContentResolver().update(
                    Uri.parse("content://" + Constant.AUTHORITY+method + "/"
                            + Constant.UPDATE), cv, null, null);
        } catch (Exception e) {
            if (CompileConfig.DEBUG) {
                Log.e(TAG, "update error", e);
            }
        }

    }

}
