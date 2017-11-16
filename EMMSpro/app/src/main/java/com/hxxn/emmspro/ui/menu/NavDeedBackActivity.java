package com.hxxn.emmspro.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hxxn.emmspro.R;
import com.hxxn.emmspro.base.BaseActivity;
import com.hxxn.emmspro.databinding.ActivityNavdeepbackBinding;


public class NavDeedBackActivity extends BaseActivity<ActivityNavdeepbackBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navdeepback);
        showContentView();
        setTitle("反馈问题");
    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, NavDeedBackActivity.class);
        mContext.startActivity(intent);
    }
}
