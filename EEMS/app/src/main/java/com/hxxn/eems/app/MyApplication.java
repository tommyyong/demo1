package com.hxxn.eems.app;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.PersistentCookieStore;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;

    public static MyApplication getInstance(){
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;

        OkGo.init(this);          //初始化OkGo
        try {
            OkGo.getInstance()
                    .setCookieStore(new PersistentCookieStore())          //设置缓存
                    .setCertificates();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
