package com.hxxn.emmspro.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hxxn.emmspro.R;
import com.hxxn.emmspro.base.BaseActivity;
import com.hxxn.emmspro.databinding.ActivityNavdownloadBinding;


public class NavDownloadActivity extends BaseActivity<ActivityNavdownloadBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navdownload);
        showContentView();
        setTitle("下载");
    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, NavDownloadActivity.class);
        mContext.startActivity(intent);
    }
}
