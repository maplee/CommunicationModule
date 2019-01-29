package com.matt.connection.openapi;

import android.content.Context;

import com.matt.connection.inner.PipeHelper;
import com.matt.connection.inner.ProviderConfig;

/**
 * Author:Created by Matt on 2018/7/18.
 */

public class ConnectionApi {

    private static Context sContext;

    // 初始化回调接口，建议在attchBaseContext中实现
    public static void install(IPipe pipe){
        PipeHelper.init(pipe);
    }

    // 初始化Application onCreate
    public static void init(Context context){
        sContext = context;
        ProviderConfig.getIntance(context).init();
    }

    private static Context getContext(){
        if(sContext == null){
            throw new RuntimeException("Please init \"ConnectionApi.init(context)\" on Application onCreate");
        }
        return sContext;
    }

    /**
     * 交互指令
     * @param packageName
     * @param param
     * @return
     */
    public static String query(String packageName,String param){
        return ProviderConfig.getIntance(getContext()).query(packageName,null,param);
    }

    /**
     * 发送指令
     * @param packageName
     * @param data
     */
    public static void update(String packageName,String data){
        ProviderConfig.getIntance(getContext()).update(packageName,data);
    }

    /**
     * 添加拦截器
     * @param hold
     */
    public static void addIntercept(IHold hold){
        PipeHelper.addIntercept(hold);
    }

}
