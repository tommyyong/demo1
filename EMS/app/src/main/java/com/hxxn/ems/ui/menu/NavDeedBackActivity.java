package com.hxxn.ems.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.hxxn.ems.R;
import com.hxxn.ems.databinding.ActivityNavDeedBackBinding;
import com.hxxn.ems.tools.CommonUtils;
import com.hxxn.ems.tools.PerfectClickListener;
import com.hxxn.ems.view.base.BaseActivity;
import com.hxxn.ems.webview.WebViewActivity;


public class NavDeedBackActivity extends BaseActivity<ActivityNavDeedBackBinding> {

    private static String string_url_faq = "http://jingbin.me/2016/12/25/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98-%E4%BA%91%E9%98%85/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_deed_back);
        setTitle("问题反馈");
        showContentView();

        bindingView.tvIssues.setOnClickListener(listener);
        bindingView.tvJianshu.setOnClickListener(listener);
        bindingView.tvQq.setOnClickListener(listener);
        bindingView.tvEmail.setOnClickListener(listener);
        bindingView.tvFaq.setOnClickListener(listener);
    }

    private PerfectClickListener listener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            switch (v.getId()) {
                case R.id.tv_issues:
                    WebViewActivity.loadUrl(v.getContext(), CommonUtils.getString(R.string.string_url_issues),"Issues");
                    break;
                case R.id.tv_qq:
                    String url = "mqqwpa://im/chat?chat_type=wpa&uin=770413277";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    break;
                case R.id.tv_email:
                    Intent data = new Intent(Intent.ACTION_SENDTO);
                    data.setData(Uri.parse("mailto:jingbin127@163.com"));
                    startActivity(data);
                    break;
                case R.id.tv_jianshu:
                    WebViewActivity.loadUrl(v.getContext(),CommonUtils.getString(R.string.string_url_jianshu),"加载中...");
                    break;
                case R.id.tv_faq:
                    WebViewActivity.loadUrl(v.getContext(),string_url_faq,"常见问题归纳");
                    break;
            }
        }
    };

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, NavDeedBackActivity.class);
        mContext.startActivity(intent);
    }
}
