package com.itxiaogu.administrator.cnxw.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.itxiaogu.administrator.cnxw.bean.Banner;
import com.itxiaogu.administrator.cnxw.http.BaseCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/31.
 */

public class JsonUtils {
    private static JSON jsonUtils;
    public static JSON getGsonUtils() {
        if (jsonUtils == null) {
            synchronized (ThreadManager.class) {
                if (jsonUtils == null) {
                    jsonUtils=new JSON();
                }
            }
        }
        return jsonUtils;
    }

    public static class JSON{
        private  Gson mGson = new Gson();
        /**
         * 将json字符串转化成实体对象
         * @param json
         * @param classOfT
         * @return
         */
        public  Object stringToObject( String json , Class classOfT){
            return  mGson.fromJson( json , classOfT ) ;
        }

        /**
         * 将对象准换为json字符串 或者 把list 转化成json
         * @param object
         * @param <T>
         * @return
         */
        public  <T> String objectToString(T object) {
            return mGson.toJson(object);
        }

        /**
         * 把json 字符串转化成list
         * @param json
         * @param cls
         * @param <T>
         * @return
         */
        public  <T> List<T> stringToList(String json , Class<T> cls  ){
            Gson gson = new Gson();
            List<T> list = new ArrayList<T>();
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for(final JsonElement elem : array){
                list.add(gson.fromJson(elem, cls));
            }
            return list ;
        }

        /**
         * 把json解析成对象或list集合
         * @param json
         * @param callback
         * @return
         */
        public Object jsonstrToData (String json,BaseCallback callback){
            return mGson.fromJson(json,callback.mType);
        }
    }
}
