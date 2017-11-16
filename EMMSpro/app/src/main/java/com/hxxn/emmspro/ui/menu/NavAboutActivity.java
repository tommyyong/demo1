package com.hxxn.emmspro.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hxxn.emmspro.R;
import com.hxxn.emmspro.base.BaseActivity;
import com.hxxn.emmspro.databinding.ActivityNavaboutBinding;


public class NavAboutActivity extends BaseActivity<ActivityNavaboutBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navabout);
        showContentView();
        setTitle("关于我们");
    }



    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, NavAboutActivity.class);
        mContext.startActivity(intent);
    }
}
