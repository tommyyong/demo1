package com.hxxn.eems.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hxxn.eems.MainActivity;
import com.hxxn.eems.R;
//import com.hxxn.eems.api.GetService;
import com.hxxn.eems.base.BaseActivity;
import com.hxxn.eems.base.Constants;
import com.hxxn.eems.cookies.PersistentCookieStore;
import com.hxxn.eems.dataBean.RealTimeData;
import com.hxxn.eems.utils.SPUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private String TAG=getClass().getSimpleName();

    private boolean CheckLogin=false,isTipOaNotify=true;
    private EditText et_account,et_pass;
    private Button bt_login,bt_regist;
    String account,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Boolean isFirst=SPUtils.getBoolean("isFirst",false);
         if(isFirst){
             startActivity(MainActivity.class);
         }
        initView();
        initData();



        bt_login.setOnClickListener(this);
        bt_regist.setOnClickListener(this);
    }



    private void initView() {
        et_account=findViewById(R.id.login_accounts);
        et_pass=findViewById(R.id.login_password);
        bt_login=findViewById(R.id.login_btn);
        bt_regist=findViewById(R.id.regist_btn);
    }
    private void initData() {

    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.login_btn:
                account=et_account.getText().toString().trim();
                password=et_pass.getText().toString().trim();
                if(account.length()==0||password.length()==0){
                    Toast.makeText(this,"账号密码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    login(account,password);   //登陆逻辑
                }
                break;

            case R.id.regist_btn:
                getData();

                break;
        }
    }

    private void getData() {


    }

    private void login(String account, String password) {

        OkGo.post(Constants.BASEURL_LOGING)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("isTipOaNotify", "true")
                .params("username", account)
                .params("password", password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG,"登录成功！");
                        SPUtils.putBoolean("isFirst",true);
                        startActivity(MainActivity.class);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.d(TAG,e.toString());

                        showShortToast("账号密码不一致!");
                    }
                });


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASEURL_LOGING)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        retrofit.create(GetService.class).getLoginData(isTipOaNotify,account,password).enqueue(new Callback<Call>() {
//            @Override
//            public void onResponse(Call<Call> call, Response<Call> response) {
//                SPUtils.putBoolean("isFirst",true);
//
//
//
////                startActivity(MainActivity.class);
//            }
//
//            @Override
//            public void onFailure(Call<Call> call, Throwable t) {
//                Log.d(TAG,"登陆失败!");
//                showShortToast("账号密码不一致!");
//            }
//        });

    }


}
