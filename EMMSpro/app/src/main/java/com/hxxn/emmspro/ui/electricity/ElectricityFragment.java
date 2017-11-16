package com.hxxn.emmspro.ui.electricity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.hxxn.emmspro.R;
import com.hxxn.emmspro.base.BaseFragment;
import com.hxxn.emmspro.databinding.FragmentElectricityBinding;
import com.hxxn.emmspro.ui.electricity.child.DailyFragment;
import com.hxxn.emmspro.ui.electricity.child.MonthlyFragment;
import com.hxxn.emmspro.ui.electricity.child.ReactiveFragment;
import com.hxxn.emmspro.ui.electricity.child.RealTimeFragment;
import com.hxxn.emmspro.ui.electricity.child.WeeklyFragment;
import com.hxxn.emmspro.view.MyFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/15 0015.
 */

public class ElectricityFragment extends BaseFragment<FragmentElectricityBinding> {

    private ArrayList<String> mTitleList = new ArrayList<>(5);
    private ArrayList<Fragment> mFragments = new ArrayList<>(5);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showLoading();
        initFragmentList();
        /**
         * 注意使用的是：getChildFragmentManager，
         * 这样setOffscreenPageLimit()就可以添加上，保留相邻3个实例，切换时不会卡
         * 但会内存溢出，在显示时加载数据
         */
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitleList);
        bindingView.vpElectricity.setAdapter(myAdapter);
        // 左右预加载页面的个数
        bindingView.vpElectricity.setOffscreenPageLimit(3);
        myAdapter.notifyDataSetChanged();
        bindingView.tabElectricity.setTabMode(TabLayout.MODE_FIXED);
        bindingView.tabElectricity.setupWithViewPager(bindingView.vpElectricity);
        showContentView();
        // item点击跳转
//        initRxBus();
    }


    @Override
    public int setContent() {
        return R.layout.fragment_electricity;
    }

    private void initFragmentList() {
        mTitleList.add("实时数据");
        mTitleList.add("无功数据");
        mTitleList.add("数据日报");
        mTitleList.add("数据周报");
        mTitleList.add("数据月报");
        mFragments.add(new RealTimeFragment());
        mFragments.add(new ReactiveFragment());
        mFragments.add(new DailyFragment());
        mFragments.add(new WeeklyFragment());
        mFragments.add(new MonthlyFragment());
    }


//    private void initRxBus() {
//    }
}
