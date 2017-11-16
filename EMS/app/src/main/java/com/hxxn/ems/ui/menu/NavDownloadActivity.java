package com.hxxn.ems.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hxxn.ems.R;
import com.hxxn.ems.databinding.ActivityNavDownloadBinding;
import com.hxxn.ems.tools.PerfectClickListener;
import com.hxxn.ems.tools.QRCodeUtil;
import com.hxxn.ems.tools.ShareUtils;
import com.hxxn.ems.view.base.BaseActivity;


public class NavDownloadActivity extends BaseActivity<ActivityNavDownloadBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_download);
        showContentView();

        setTitle("扫码下载");
        String url = "https://fir.im/cloudreader";
        QRCodeUtil.showThreadImage(this, url, bindingView.ivErweima, R.drawable.ic_cloudreader_mip);
        bindingView.tvShare.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                ShareUtils.share(v.getContext(), R.string.string_share_text);
            }
        });
    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, NavDownloadActivity.class);
        mContext.startActivity(intent);
    }
}
