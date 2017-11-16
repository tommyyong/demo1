package com.hxxn.emmspro.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hxxn.emmspro.R;
import com.hxxn.emmspro.base.BaseActivity;
import com.hxxn.emmspro.databinding.ActivityNavhomeBinding;


public class NavHomePageActivity extends BaseActivity<ActivityNavhomeBinding> {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navhome);
        showContentView();
        setTitle("主页");
    }




    public static void startHome(Context mContext) {
        Intent intent = new Intent(mContext, NavHomePageActivity.class);
        mContext.startActivity(intent);
    }
}
