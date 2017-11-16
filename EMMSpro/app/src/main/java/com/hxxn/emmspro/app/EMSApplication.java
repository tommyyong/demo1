package com.hxxn.emmspro.app;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.example.http.HttpUtils;
import com.hxxn.emmspro.tools.DebugUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.PersistentCookieStore;


/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class EMSApplication  extends Application {

    private static EMSApplication emsApplication;

    public static EMSApplication getInstance() {
        return emsApplication;
    }

    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        super.onCreate();
        emsApplication = this;
        HttpUtils.getInstance().init(this, DebugUtil.DEBUG);

        initTextSize();

        OkGo.init(this);          //初始化OkGo
        try {
            OkGo.getInstance()
                    .setCookieStore(new PersistentCookieStore())          //设置缓存
                    .setCertificates();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 使其系统更改字体大小无效
     */
    private void initTextSize() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

}
