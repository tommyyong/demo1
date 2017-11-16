package com.hxxn.emmspro.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hxxn.emmspro.MainActivity;

import com.hxxn.emmspro.R;
import com.hxxn.emmspro.app.Constants;
import com.hxxn.emmspro.databinding.ActivityLoginBinding;
import com.hxxn.emmspro.tools.PerfectClickListener;
import com.hxxn.emmspro.tools.SPUtils;
import com.hxxn.emmspro.tools.ToastUtil;
import com.hxxn.emmspro.ui.menu.NavAboutActivity;
import com.hxxn.emmspro.ui.menu.NavDeedBackActivity;
import com.hxxn.emmspro.ui.menu.NavDownloadActivity;
import com.hxxn.emmspro.ui.menu.NavHomePageActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

//import com.hxxn.eems.api.GetService;


/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG=getClass().getSimpleName();
    private ActivityLoginBinding mBinding;

    private boolean CheckLogin=false,isTipOaNotify=true;
    private EditText et_account,et_pass;
    private Button bt_login,bt_regist;
    String account,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        Boolean isFirst= SPUtils.getBoolean("isFirst",false);
         if(isFirst){
             MainActivity.start(LoginActivity.this);
         }
        initView();
        initListener();



//        bt_login.setOnClickListener(this);
//        bt_regist.setOnClickListener(this);
    }

    private void initView() {
        et_account=mBinding.loginAccounts;
        et_pass=mBinding.loginPassword;
    }

    private void initListener() {
        mBinding.loginBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.login_btn:
                account=et_account.getText().toString().trim();
                password=et_pass.getText().toString().trim();
                if(account.length()==0||password.length()==0){
                    ToastUtil.showToast("账号密码不能为空");
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

    private void login(final String account, String password) {

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
                        SPUtils.putString("username",account);
                        MainActivity.start(LoginActivity.this);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.d(TAG,e.toString());

                        ToastUtil.showToast("账号密码不一致!");
                    }
                });



    }


}
