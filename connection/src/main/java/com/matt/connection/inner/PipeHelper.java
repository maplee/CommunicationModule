package com.matt.connection.inner;

import com.matt.connection.openapi.IHold;
import com.matt.connection.openapi.IPipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:Created by Matt on 2018/7/18.
 */

public class PipeHelper {

    private static IPipe sPipe;

    private static List<IHold> sIntercepts;


    public static void addIntercept(IHold hold){
        if(sIntercepts == null){
            sIntercepts = new ArrayList<>();
        }
        sIntercepts.add(hold);
    }

    public static void init(IPipe pipe){
        sPipe = pipe;
    }

    public static String getPackage(){
        if(sPipe == null){
            return null;
        }
        return sPipe.getPackage();
    }

    public static void result(String source,String data){
        if(sPipe == null){

        }
        if(sIntercepts != null && sIntercepts.size()>0){
            for(IHold hold : sIntercepts){
                if (hold == null){
                    continue;
                }
                hold.result(source, data);
            }
        }
        sPipe.result(source,data);
    }

    public static String query(String param){
        if(sPipe == null){
            return null;
        }
        return sPipe.query(param);
    }


}
