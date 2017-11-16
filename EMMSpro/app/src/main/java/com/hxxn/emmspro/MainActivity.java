package com.hxxn.emmspro;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hxxn.emmspro.app.Constants;
import com.hxxn.emmspro.databinding.ActivityMainBinding;
import com.hxxn.emmspro.databinding.NavHeaderMainBinding;
import com.hxxn.emmspro.http.rx.RxBus;
import com.hxxn.emmspro.http.rx.RxBusBaseMessage;
import com.hxxn.emmspro.http.rx.RxCodeConstants;
import com.hxxn.emmspro.tools.CommonUtils;
import com.hxxn.emmspro.tools.ImgLoadUtil;
import com.hxxn.emmspro.tools.PerfectClickListener;
import com.hxxn.emmspro.tools.SPUtils;
import com.hxxn.emmspro.ui.electricity.ElectricityFragment;
import com.hxxn.emmspro.ui.home.HomeFragment;
import com.hxxn.emmspro.ui.menu.NavAboutActivity;
import com.hxxn.emmspro.ui.menu.NavDeedBackActivity;
import com.hxxn.emmspro.ui.menu.NavDownloadActivity;
import com.hxxn.emmspro.ui.menu.NavHomePageActivity;
import com.hxxn.emmspro.ui.monitoring.MonitoringFragment;
import com.hxxn.emmspro.ui.water.WaterFragment;
import com.hxxn.emmspro.view.MyFragmentPagerAdapter;
import com.hxxn.emmspro.view.statusbar.StatusBarUtil;
import com.hxxn.emmspro.webview.WebViewActivity;

import java.util.ArrayList;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private FrameLayout llTitleMenu;
    private Toolbar toolbar;

    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private ViewPager vpContent;

    // 一定需要对应的bean
    private ActivityMainBinding mBinding;
    private ImageView llTitleElectricity;
    private ImageView llTitleWater;
    private ImageView llTitleMonitoring;
    private ImageView llTitleHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initStatusView();
        initId();
//        initRxBus();

        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this, drawerLayout,
                CommonUtils.getColor(R.color.colorTheme));
        initContentFragment();
        initDrawerLayout();
        initListener();
    }

    private void initStatusView() {
        ViewGroup.LayoutParams layoutParams = mBinding.include.viewStatus.getLayoutParams();
        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
        mBinding.include.viewStatus.setLayoutParams(layoutParams);
    }

    private void initId() {
        drawerLayout = mBinding.drawerLayout;
        navView = mBinding.navView;

        toolbar = mBinding.include.toolbar;
        llTitleMenu = mBinding.include.llTitleMenu;
        vpContent = mBinding.include.vpContent;


        llTitleElectricity = mBinding.include.ivTitleElectricity;
        llTitleWater = mBinding.include.ivTitleWater;
        llTitleMonitoring = mBinding.include.ivTitleMonitoring;
        llTitleHome = mBinding.include.ivTitleHome;
    }

    private void initListener() {
        llTitleMenu.setOnClickListener(this);
        mBinding.include.ivTitleElectricity.setOnClickListener(this);
        mBinding.include.ivTitleWater.setOnClickListener(this);
        mBinding.include.ivTitleMonitoring.setOnClickListener(this);
        mBinding.include.ivTitleHome.setOnClickListener(this);

    }

    NavHeaderMainBinding bind;

    /**
     * inflateHeaderView 进来的布局要宽一些
     */
    private void initDrawerLayout() {
        navView.inflateHeaderView(R.layout.nav_header_main);
        View headerView = navView.getHeaderView(0);
        bind = DataBindingUtil.bind(headerView);
        bind.setListener(this);


        ImgLoadUtil.displayCircle(bind.ivAvatar, R.drawable.log);
        bind.llNavExit.setOnClickListener(this);
//        bind.ivAvatar.setOnClickListener(this);

        bind.llNavHomepage.setOnClickListener(listener);
        bind.llNavScanDownload.setOnClickListener(listener);
        bind.llNavDeedback.setOnClickListener(listener);
        bind.llNavAbout.setOnClickListener(listener);

    }

    private void initContentFragment() {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new HomeFragment());
        mFragmentList.add(new ElectricityFragment());
        mFragmentList.add(new WaterFragment());
        mFragmentList.add(new MonitoringFragment());
        // 注意使用的是：getSupportFragmentManager
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        vpContent.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗少)
        vpContent.setOffscreenPageLimit(2);
        vpContent.addOnPageChangeListener(this);
        mBinding.include.ivTitleElectricity.setSelected(true);   //设置进入显示的页面
        vpContent.setCurrentItem(1);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }


    private PerfectClickListener listener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(final View v) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
            mBinding.drawerLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (v.getId()) {
                        case R.id.ll_nav_homepage:// 主页
//                            NavHomePageActivity.startHome(MainActivity.this);
                            WebViewActivity.loadUrl(v.getContext(), Constants.BASEURL_HOME, "主页");
                            break;
                        case R.id.ll_nav_scan_download://扫码下载
                            NavDownloadActivity.start(MainActivity.this);
                            break;
                        case R.id.ll_nav_deedback:// 问题反馈
                            NavDeedBackActivity.start(MainActivity.this);
                            break;
                        case R.id.ll_nav_about:// 关于我们
                            NavAboutActivity.start(MainActivity.this);
                            break;

                    }
                }
            }, 260);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title_menu:// 开启菜单
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_title_home:// 首页
                if (vpContent.getCurrentItem() != 0) {//不然cpu会有损耗
                    llTitleHome.setSelected(true);
                    llTitleElectricity.setSelected(false);
                    llTitleWater.setSelected(false);
                    llTitleMonitoring.setSelected(false);
                    vpContent.setCurrentItem(0);
                }
                break;
            case R.id.iv_title_electricity:// 用电
                if (vpContent.getCurrentItem() != 1) {//不然cpu会有损耗
                    llTitleHome.setSelected(false);
                    llTitleElectricity.setSelected(true);
                    llTitleWater.setSelected(false);
                    llTitleMonitoring.setSelected(false);
                    vpContent.setCurrentItem(1);
                }
                break;
            case R.id.iv_title_water:// 用水
                if (vpContent.getCurrentItem() != 2) {
                    llTitleHome.setSelected(false);
                    llTitleElectricity.setSelected(false);
                    llTitleWater.setSelected(true);
                    llTitleMonitoring.setSelected(false);
                    vpContent.setCurrentItem(2);
                }
                break;
            case R.id.iv_title_monitoring:// 监控
                if (vpContent.getCurrentItem() != 3) {
                    llTitleHome.setSelected(false);
                    llTitleElectricity.setSelected(false);
                    llTitleWater.setSelected(false);
                    llTitleMonitoring.setSelected(true);
                    vpContent.setCurrentItem(3);
                }
                break;

            case R.id.ll_nav_exit:// 退出应用
                SPUtils.putBoolean("isFirst",false);
                finish();
                break;
            default:
                break;
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_search:
////                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                llTitleHome.setSelected(true);
                llTitleElectricity.setSelected(false);
                llTitleWater.setSelected(false);
                llTitleMonitoring.setSelected(false);
                break;
            case 1:
                llTitleHome.setSelected(false);
                llTitleElectricity.setSelected(true);
                llTitleWater.setSelected(false);
                llTitleMonitoring.setSelected(false);
                break;
            case 2:
                llTitleHome.setSelected(false);
                llTitleElectricity.setSelected(false);
                llTitleWater.setSelected(true);
                llTitleMonitoring.setSelected(false);
                break;
            case 3:
                llTitleHome.setSelected(false);
                llTitleElectricity.setSelected(false);
                llTitleWater.setSelected(false);
                llTitleMonitoring.setSelected(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                mBinding.drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                // 不退出程序，进入后台
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

//    /**
//     * 每日推荐点击"新电影热映榜"跳转
//     */
//    private void initRxBus() {
//        RxBus.getDefault().toObservable(RxCodeConstants.JUMP_TYPE_TO_ONE, RxBusBaseMessage.class)
//                .subscribe(new Action1<RxBusBaseMessage>() {
//                    @Override
//                    public void call(RxBusBaseMessage integer) {
//                        mBinding.include.vpContent.setCurrentItem(1);
//                    }
//                });
//    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }
}
